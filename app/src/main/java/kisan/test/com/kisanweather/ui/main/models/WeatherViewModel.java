package kisan.test.com.kisanweather.ui.main.models;

import android.arch.lifecycle.*;
import kisan.test.com.kisanweather.service.model.WeatherModel;
import kisan.test.com.kisanweather.service.repository.WeatherRepository;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by william on 11-12-2018.
 */
public class WeatherViewModel extends ViewModel {

    private LiveData<List<WeatherModel>> maxTempLiveData;
    private LiveData<List<WeatherModel>> minTempLiveData;
    private LiveData<List<WeatherModel>> rainfallLiveData;


    private MutableLiveData<String> mLocation ;


    @Inject
    public WeatherViewModel(WeatherRepository weatherRepository) {
        mLocation = new MutableLiveData<>();
        maxTempLiveData = Transformations.switchMap(mLocation,input -> weatherRepository.getMaxTemp(mLocation.getValue()));
        minTempLiveData = Transformations.switchMap(mLocation,input -> weatherRepository.getMinTemp(mLocation.getValue()));
        rainfallLiveData = Transformations.switchMap(mLocation,input -> weatherRepository.getRainfall(mLocation.getValue()));
    }


    public LiveData<List<WeatherModel>> getMaxTempObservable() {
        return maxTempLiveData;
    }

    public LiveData<List<WeatherModel>> getMinTempObservable() {
        return minTempLiveData;
    }

    public LiveData<List<WeatherModel>> getRainfallObservable() {
        return rainfallLiveData;
    }


    public void setRequestData(String location) {
        mLocation.setValue(location);
    }
}
