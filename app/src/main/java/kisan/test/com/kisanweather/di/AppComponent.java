package kisan.test.com.kisanweather.di;

import android.app.Application;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import kisan.test.com.kisanweather.KisanWeather;

import javax.inject.Singleton;

/**
 * Created by william on 22-09-2018.
 */

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        MainActivityModule.class
})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
    void inject(KisanWeather kisanWeather);
}