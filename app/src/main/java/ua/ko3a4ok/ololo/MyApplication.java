package ua.ko3a4ok.ololo;

import android.app.Application;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ko3a4ok on 12/2/14.
 */
public class MyApplication extends Application {
    private Map<String, ImageHolder> images = new HashMap<String, ImageHolder>();

    public void updatePercentage(String link, int p) {
        ImageHolder ih = images.containsKey(link) ? images.get(link) : new ImageHolder();
        ih.setPercentage(p);
        ih.setFailed(false);
        images.put(link, ih);
    }


    public boolean isExist(String link) {
        return images.containsKey(link);
    }

    public ImageHolder getImageHolder(String link) {
        return images.get(link);
    }
}
