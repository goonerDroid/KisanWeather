package kisan.test.com.kisanweather.di;

import dagger.Subcomponent;
import kisan.test.com.kisanweather.ui.main.models.WeatherViewModel;

/**
 * Created by william on 22-09-2018.
 */


@Subcomponent
public interface ViewModelSubComponent {

    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    WeatherViewModel weatherViewModel();
}