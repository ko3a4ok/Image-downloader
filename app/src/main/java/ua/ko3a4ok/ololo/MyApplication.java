package ua.ko3a4ok.ololo;

import android.app.Application;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by ko3a4ok on 12/2/14.
 */
public class MyApplication extends Application {
    private final Map<String, ImageHolder> images = new HashMap();
    private final List<String> tasks = new LinkedList();
    private boolean serviceRunning;


    public void updatePercentage(String link, int p) {
        ImageHolder ih = images.get(link);
        ih.setPercentage(p);
        ih.setFailed(false);
    }

    public ImageHolder getImageHolder(String link) {
        return images.get(link);
    }

    public void addTask(String link) {
        tasks.add(0, link);
        if (!images.containsKey(link)) images.put(link, new ImageHolder());
    }

    public boolean isServiceRunning() {
        return serviceRunning;
    }

    public void setServiceRunning(boolean serviceRunning) {
        this.serviceRunning = serviceRunning;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public void removeTask(int idx) {
        String link = tasks.get(idx);
        tasks.remove(idx);
        if (!tasks.contains(link)) images.remove(link);
    }
}
