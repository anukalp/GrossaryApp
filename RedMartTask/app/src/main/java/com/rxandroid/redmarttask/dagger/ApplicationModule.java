package com.rxandroid.redmarttask.dagger;

import android.content.Context;

import com.rxandroid.redmarttask.application.RedMartApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final RedMartApplication application;

    public ApplicationModule(RedMartApplication mmtApplication) {
        this.application = mmtApplication;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }
}
