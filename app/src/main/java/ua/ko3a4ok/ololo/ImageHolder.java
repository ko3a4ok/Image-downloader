package ua.ko3a4ok.ololo;

import android.graphics.Bitmap;

import java.lang.ref.WeakReference;

/**
 * Created by ko3a4ok on 12/2/14.
 */
public class ImageHolder {
    private int percentage;
    private String uri;
    private boolean failed;
    private byte[] rawData;

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }


    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
        rawData = null;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    public boolean isFailed() {
        return failed;
    }

    public byte[] getRawData() {
        return rawData;
    }

    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
    }
}
