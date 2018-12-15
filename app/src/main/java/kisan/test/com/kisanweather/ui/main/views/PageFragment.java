package kisan.test.com.kisanweather.ui.main.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kisan.test.com.kisanweather.R;
import kisan.test.com.kisanweather.service.model.WeatherModel;
import kisan.test.com.kisanweather.CommonUtils;
import kisan.test.com.kisanweather.ui.main.models.WeatherPageModel;

import java.util.*;

/**
 * Created by william on 12-12-2018.
 */
public class PageFragment extends Fragment {

    private static final String KEY_WEATHER_MODEL = "key_weather_model";
    private static final String KEY_FRAG_POSITION = "key_frag_pos";
    private WeatherPageModel weatherPageModel;
    private int fragPosition;
    private Unbinder unbinder;

    @BindViews({R.id.tv_jan_max_temp,R.id.tv_feb_max_temp,R.id.tv_mar_max_temp,R.id.tv_apr_max_temp,R.id.tv_may_max_temp,
            R.id.tv_jun_max_temp,R.id.tv_jul_max_temp,R.id.tv_aug_max_temp,R.id.tv_sept_max_temp,R.id.tv_oct_max_temp,
            R.id.tv_nov_max_temp,R.id.tv_dec_max_temp})
    List<TextView> maxTempTextViewList;


    @BindViews({R.id.tv_jan_min_temp,R.id.tv_feb_min_temp,R.id.tv_mar_min_temp,R.id.tv_apr_min_temp,R.id.tv_may_min_temp,
            R.id.tv_jun_min_temp,R.id.tv_jul_min_temp,R.id.tv_aug_min_temp,R.id.tv_sept_min_temp,R.id.tv_oct_min_temp,
            R.id.tv_nov_min_temp,R.id.tv_dec_min_temp})
    List<TextView> minTempTextViewList;

    @BindViews({R.id.tv_jan_rain,R.id.tv_feb_rain,R.id.tv_mar_rain,R.id.tv_apr_rain,R.id.tv_may_rain,
            R.id.tv_jun_rain,R.id.tv_jul_rain,R.id.tv_aug_rain,R.id.tv_sept_rain,R.id.tv_oct_rain,
            R.id.tv_nov_rain,R.id.tv_dec_rain})
    List<TextView> rainTextViewList;


    @BindView(R.id.tv_year_header)
    TextView tvYearHeader;


    public static PageFragment newInstance(WeatherPageModel weatherPageModel,int position) {
        PageFragment fragmentFirst = new PageFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY_WEATHER_MODEL, weatherPageModel);
        args.putInt(KEY_FRAG_POSITION, position);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherPageModel = getArguments().getParcelable(KEY_WEATHER_MODEL);
        fragPosition = getArguments().getInt(KEY_FRAG_POSITION);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvYearHeader.setText(String.format("%d", weatherPageModel.getOrderedYearList().get(fragPosition)));

        HashMap<Integer, List<WeatherModel>> maxTempYearWeatherMap;
        if (weatherPageModel.getmMaxTempModelList() != null){
            maxTempYearWeatherMap = CommonUtils.getGroupedWeatherData(weatherPageModel.getmMaxTempModelList());
            List<WeatherModel> maxTempModelList = maxTempYearWeatherMap.get(weatherPageModel.getOrderedYearList().get(fragPosition));
            if (maxTempModelList != null) {
                for (int i = 0; i < maxTempModelList.size(); i++) {
                    maxTempTextViewList.get(i).setText(maxTempModelList.get(i).getValue()+" \u2103");
                }
            }
        }

        HashMap<Integer, List<WeatherModel>> minTempYearWeatherMap;
        if (weatherPageModel.getmMinTempModelList() != null){
            minTempYearWeatherMap = CommonUtils.getGroupedWeatherData(weatherPageModel.getmMinTempModelList());
            List<WeatherModel> minTempModelList = minTempYearWeatherMap.get(weatherPageModel.getOrderedYearList().get(fragPosition));
            if (minTempModelList != null) {
                for (int i = 0; i < minTempModelList.size(); i++) {
                    minTempTextViewList.get(i).setText(minTempModelList.get(i).getValue()+" \u2103");
                }
            }
        }



        HashMap<Integer, List<WeatherModel>> rainYearWeatherMap;
        if (weatherPageModel.getmRainfallModelList() != null){
            rainYearWeatherMap = CommonUtils.getGroupedWeatherData(weatherPageModel.getmRainfallModelList());
            List<WeatherModel> rainModelList = rainYearWeatherMap.get(weatherPageModel.getOrderedYearList().get(fragPosition));
            if (rainModelList != null) {
                for (int i = 0; i < rainModelList.size(); i++) {
                    rainTextViewList.get(i).setText(""+rainModelList.get(i).getValue());
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
