package kisan.test.com.kisanweather.di;

import android.arch.lifecycle.ViewModelProvider;
import dagger.Module;
import dagger.Provides;
import kisan.test.com.kisanweather.service.model.ProjectViewModelFactory;
import kisan.test.com.kisanweather.service.repository.WeatherService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;

/**
 * Created by william on 22-09-2018.
 */


@Module(subcomponents = ViewModelSubComponent.class)
class AppModule {
    @Singleton
    @Provides
    WeatherService provideWeatherService() {
        return new Retrofit.Builder()
                .baseUrl(WeatherService.HTTPS_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherService.class);
    }


    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(ViewModelSubComponent.Builder viewModelSubComponent) {
        return new ProjectViewModelFactory(viewModelSubComponent.build());
    }
}