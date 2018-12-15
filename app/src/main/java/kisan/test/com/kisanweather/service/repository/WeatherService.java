package kisan.test.com.kisanweather.service.repository;

import kisan.test.com.kisanweather.service.model.WeatherModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * Created by william on 10-12-2018.
 */
public interface WeatherService {

    String HTTPS_API_URL = "https://s3.eu-west-2.amazonaws.com/interview-question-data/metoffice/";

    @GET("{metric}-{location}.json")
    Call<List<WeatherModel>> getWeather(@Path("metric") String metric, @Path("location") String location);
}
