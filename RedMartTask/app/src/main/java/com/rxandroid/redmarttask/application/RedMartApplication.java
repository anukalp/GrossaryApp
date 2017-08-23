package com.rxandroid.redmarttask.application;

import android.app.Application;

import com.rxandroid.redmarttask.dagger.ApplicationComponent;
import com.rxandroid.redmarttask.dagger.ApplicationModule;
import com.rxandroid.redmarttask.dagger.DaggerApplicationComponent;
import com.rxandroid.redmarttask.dagger.RemoteModule;
import com.rxandroid.redmarttask.util.AppConstants;

/**
 * Created by anukalp on 22/8/17.
 */

public class RedMartApplication extends Application {

    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .remoteModule(new RemoteModule(AppConstants.BASE_URL))
                .build();
        applicationComponent.inject(this);
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
