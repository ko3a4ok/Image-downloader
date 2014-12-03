package ua.ko3a4ok.ololo;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class MyService extends Service {
    public static final String LINK = "link";

    private static final int W = 320;
    private static final int H = 480;

    private boolean stopped;

    private MyApplication application;
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
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        stopped = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.hasExtra(LINK)) {
            String link = intent.getStringExtra(LINK);
            if (!application.isExist(link) || application.getImageHolder(link).isFailed())
                new Downloader(link).start();
        }
        return super.onStartCommand(intent, flags, startId);
    }


    private class Downloader extends Thread {
        private String link;
        ImageHolder ih;

        public Downloader(String link) {
            this.link = link;
            application.updatePercentage(link, 0);
            ih = application.getImageHolder(link);
        }
        @Override
        public void run() {
            URL url = null;
            try {
                if (ih.getRawData() == null) {
                    url = new URL(link);
                    URLConnection conn = url.openConnection();
                    conn.connect();

                    int len = conn.getContentLength();
                    byte[] img = new byte[len];
                    InputStream input = conn.getInputStream();
                    int total = 0;
                    int count;
                    byte[] buf = new byte[0x500];
                    while ((count = input.read(buf)) != -1) {
                        System.arraycopy(buf, 0, img, total, count);
                        total += count;
                        int percentage = 100 * total / len;
                        ih.setPercentage(percentage);
                        updateUi();
                        if (stopped) {
                            failed();
                            return;
                        }
                    }
                    ih.setRawData(img);
                }
                ih.setPercentage(100);
                saveImage(ih.getRawData());
                updateUi();
            } catch (OutOfMemoryError e) {
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


}
