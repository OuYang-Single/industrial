package com.ijcsj.common_library.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityManager {
    private static ActivityManager instance;
    private List<Activity> activityList;
    
    private ActivityManager() {
        activityList = new ArrayList<>();
    }
    
    public static synchronized ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }
    
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }
    
    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public List<Activity> getActivityList() {
        return activityList;
    }

    public Activity getTopActivity() {
        if (activityList.isEmpty()) {
            return null;
        } else {
            return activityList.get(activityList.size() - 1);
        }
    }
}