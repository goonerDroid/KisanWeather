package kisan.test.com.kisanweather.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import kisan.test.com.kisanweather.ui.main.views.MainActivity;

/**
 * Created by william on 22-09-2018.
 */

@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector()
    abstract MainActivity contributeMainActivity();
}