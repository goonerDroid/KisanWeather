package kisan.test.com.kisanweather;

import kisan.test.com.kisanweather.service.model.WeatherModel;

import java.util.*;

/**
 * Created by william on 12-12-2018.
 */
public class CommonUtils {


    public static List<Integer> getOrderedYearList(List<WeatherModel> weatherModelList) {
        List<Integer> unorderedYearList = new ArrayList<>();
        List<Integer> orderedYearList = new ArrayList<>();
        for (int i = 0; i < weatherModelList.size(); i++) {
            unorderedYearList.add(weatherModelList.get(i).getYear());
        }


        Set<Integer> hashSet = new HashSet<>();

        for(int i=0; i < unorderedYearList.size(); i++){
            boolean unique = hashSet.add(unorderedYearList.get(i));
            if(unique) orderedYearList.add(unorderedYearList.get(i));

        }

        return orderedYearList;
    }


    public static HashMap<Integer, List<WeatherModel>> getGroupedWeatherData(List<WeatherModel> weatherModelList){
        HashMap<Integer, List<WeatherModel>> yearWeatherMap = new HashMap<>();
        for (int i = 0; i < weatherModelList.size(); i++) {
            List<WeatherModel> list = new ArrayList<>();
            if (!yearWeatherMap.containsKey(weatherModelList.get(i).getYear())) {
                list.add(weatherModelList.get(i));
                yearWeatherMap.put(weatherModelList.get(i).getYear(), list);
            } else {
                yearWeatherMap.get(weatherModelList.get(i).getYear()).add(weatherModelList.get(i));
            }

        }

        return yearWeatherMap;
    }
}
