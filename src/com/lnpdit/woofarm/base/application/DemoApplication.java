/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lnpdit.woofarm.base.application;

import java.util.LinkedList;
import java.util.List;

import com.lnpdit.woofarm.page.activity.tabhost.MainTabHostActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

public class DemoApplication extends Application {

    public static Context applicationContext;
    private static DemoApplication instance = null;
    // login user name
    public final String PREF_USERNAME = "username";
    public MainTabHostActivity mainTabHostActivity;
    
    /**
     * 当前用户nickname,为了苹果推送不是userid而是昵称
     */
    public static String currentUserNick = "";
 
    public synchronized static DemoApplication getInstance() {
        if (null == instance) {
            instance = new DemoApplication();
        }
        return instance;
    }

    // 运用list来保存们每一个activity是关键
    private List<Activity> mList = new LinkedList<Activity>();

    // add Activity
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    // 关闭每一个list内的activity
    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // finally {
        // System.exit(0);
        // }
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
//        instance = this;
        instance = getInstance();
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);

    }

    public void exitApp() {
            if (mainTabHostActivity != null) {
                mainTabHostActivity.finish();
            }
          
    }
}
