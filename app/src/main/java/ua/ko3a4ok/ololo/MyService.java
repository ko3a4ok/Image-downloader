package ua.ko3a4ok.ololo;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

import java.io.InputStream;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyService extends Service {
    public static final String LINK = "link";

    private static final int W = 320;
    private static final int H = 480;

    private boolean stopped;

    private MyApplication application;
    private ExecutorService poolExecutor;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = (MyApplication) getApplication();
        poolExecutor = Executors.newFixedThreadPool(6);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopped = true;
        poolExecutor.shutdownNow();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.hasExtra(LINK)) {
            String link = intent.getStringExtra(LINK);
            poolExecutor.execute(new Downloader(link));
        }
        return super.onStartCommand(intent, flags, startId);
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
                        int percentage = 100 * total / len;
                        ih.setPercentage(percentage);
                        updateUi();
                        if (stopped) {
                            failed();
                            return;
                        }
                    }
                    ih.setRawData(ib.get().buf);
                }
                ih.setPercentage(100);
                saveImage(ih.getRawData());
                updateUi();
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                failed();
                System.gc();
            } catch (Exception e) {
                e.printStackTrace();
                failed();
            }
        }

        private void updateUi() {
            LocalBroadcastManager.getInstance(MyService.this).sendBroadcast(new Intent(MyActivity.REFRESH_DATA));
        }

        private void failed() {
            ih.setFailed(true);
            updateUi();
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

    class ImageBuffer {

        byte[] buf;
        ImageBuffer(int size) {
            buf = new byte[size];
        }
    }


}
