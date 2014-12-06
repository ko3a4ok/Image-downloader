package ua.ko3a4ok.ololo;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.os.ResultReceiver;

import java.io.InputStream;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.net.URLConnection;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyService extends Service {
    private static final long MIN_REFRESH_INTERVAL = 300;

    private static final int W = 320;
    private static final int H = 480;
    public static final String RECEIVER = "receiver";

    private boolean stopped;

    private static volatile boolean emptyBatch = true;
    private MyApplication application;
    private ExecutorService poolExecutor;
    private IBinder binder = new MyBinder();
    private ResultReceiver receiver;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = (MyApplication) getApplication();
        poolExecutor = Executors.newFixedThreadPool(6);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        receiver = intent.getParcelableExtra(RECEIVER);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopped = true;
        poolExecutor.shutdownNow();
    }


    private class Downloader implements Runnable {
        private String link;
        ImageHolder ih;

        public Downloader(String link) {
            this.link = link;
            application.updatePercentage(link, 0);
            ih = application.getImageHolder(link);
        }
        @Override
        public void run() {
            URL url;
            try {
                if (ih.getRawData() == null) {
                    url = new URL(link);
                    URLConnection conn = url.openConnection();
                    conn.connect();
                    int len = conn.getContentLength();
                    Reference<ImageBuffer> ib = new SoftReference<ImageBuffer>(new ImageBuffer(len));

                    InputStream input = conn.getInputStream();
                    int total = 0;
                    int count;
                    byte[] buf = new byte[0x500];
                    while ((count = input.read(buf)) != -1) {
                        System.arraycopy(buf, 0, ib.get().buf, total, count);
                        total += count;
                        int percentage = (int) (100l * total / len);
                        ih.setPercentage(percentage);
                        updateUi(MyService.this);
                        if (stopped) {
                            failed();
                            return;
                        }
                    }
                    ih.setRawData(ib.get().buf);
                }
                ih.setPercentage(100);
                saveImage(ih.getRawData());
                updateUi(MyService.this);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                failed();
                System.gc();
            } catch (Exception e) {
                e.printStackTrace();
                failed();
            }
        }


        private void failed() {
            ih.setFailed(true);
            updateUi(MyService.this);
        }

        private void saveImage(byte[] img) {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(img, 0, img.length, options);
            options.inSampleSize = Utils.calculateInSampleSize(options, W, H);

            options.inJustDecodeBounds = false;
            Bitmap b = BitmapFactory.decodeByteArray(img, 0, img.length, options);
            Bitmap result = Bitmap.createScaledBitmap(b, W, H, false);
            b.recycle();
            String uri = Utils.saveImage(MyService.this, result);
            ih.setUri(uri);
        }
    }

    private static synchronized void updateUi(final MyService service) {
        if (!emptyBatch) return;
        emptyBatch = false;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                emptyBatch = true;
                if (service.receiver != null)
                    service.receiver.send(0, null);
            }
        }, MIN_REFRESH_INTERVAL);
    }

    class ImageBuffer {

        byte[] buf;
        ImageBuffer(int size) {
            buf = new byte[size];
        }
    }

    public class MyBinder extends Binder {

        public void loadLink(String link) {
            poolExecutor.execute(new Downloader(link));
        }

        public void clearReceiver() {
            receiver = null;
        }
    }

}
