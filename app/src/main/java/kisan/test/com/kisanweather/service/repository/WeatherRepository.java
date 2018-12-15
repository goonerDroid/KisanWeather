package kisan.test.com.kisanweather.service.repository;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import kisan.test.com.kisanweather.service.model.WeatherModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Created by william on 11-12-2018.
 */
@Singleton
public class WeatherRepository {

    private WeatherService weatherService;
    private final MutableLiveData<List<WeatherModel>> maxTempLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<WeatherModel>> minTempLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<WeatherModel>> rainfallLiveData = new MutableLiveData<>();

    @Inject
    public WeatherRepository(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public MutableLiveData<List<WeatherModel>> getMaxTemp(String value1) {
        weatherService.getWeather("Tmax",value1).enqueue(new Callback<List<WeatherModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<WeatherModel>> call,@NonNull Response<List<WeatherModel>> response) {
                maxTempLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<WeatherModel>> call,@NonNull Throwable t) {
                Log.wtf("ONFailure","onFailure called");
                maxTempLiveData.setValue(null);
            }
        });

        return maxTempLiveData;
    }


    public MutableLiveData<List<WeatherModel>> getMinTemp(String value1) {
        weatherService.getWeather("Tmin",value1).enqueue(new Callback<List<WeatherModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<WeatherModel>> call,@NonNull Response<List<WeatherModel>> response) {
                minTempLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<WeatherModel>> call,@NonNull Throwable t) {
                Log.wtf("ONFailure","onFailure called");
                minTempLiveData.setValue(null);
            }
        });

        return minTempLiveData;
    }

    public MutableLiveData<List<WeatherModel>> getRainfall(String value1) {
        weatherService.getWeather("Rainfall",value1).enqueue(new Callback<List<WeatherModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<WeatherModel>> call,@NonNull Response<List<WeatherModel>> response) {
                rainfallLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<WeatherModel>> call,@NonNull Throwable t) {
                Log.wtf("ONFailure","onFailure called");
                rainfallLiveData.setValue(null);
            }
        });

        return rainfallLiveData;
    }
}
