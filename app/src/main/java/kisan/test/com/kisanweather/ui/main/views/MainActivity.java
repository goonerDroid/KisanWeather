package kisan.test.com.kisanweather.ui.main.views;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.shuhart.stepview.StepView;
import dagger.android.AndroidInjection;
import jp.wasabeef.blurry.Blurry;
import kisan.test.com.kisanweather.R;
import kisan.test.com.kisanweather.service.model.WeatherModel;
import kisan.test.com.kisanweather.CommonUtils;
import kisan.test.com.kisanweather.ui.main.models.WeatherPageModel;
import kisan.test.com.kisanweather.ui.main.adapter.WeatherPagerAdapter;
import kisan.test.com.kisanweather.ui.main.models.WeatherViewModel;

import javax.inject.Inject;
import java.util.*;

/**
 * Created by william on 11-12-2018.
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    @BindView(R.id.countries_spinner)
    AppCompatSpinner countrySpinner;
    @BindView(R.id.viewpager)
    ViewPager weatherPager;
    @BindView(R.id.bg_img)
    ImageView imgBackground;
    @BindView(R.id.main)
    ConstraintLayout constraintLayout;
    @BindView(R.id.step_view)
    StepView stepView;



    @Inject
    ViewModelProvider.Factory viewModelFactory;

    int[] bgImages = new int[]{R.drawable.img_uk, R.drawable.img_england, R.drawable.img_scotland, R.drawable.img_wales};
    private WeatherViewModel weatherViewModel;
    private ArrayAdapter<String> countriesDataAdapter;
    private WeatherPagerAdapter adapterViewPager;
    private List<Integer> orderedYearList;
    private List<WeatherModel> minTempWeatherModelList = new ArrayList<>();
    private List<WeatherModel> maxTempWeatherModelList = new ArrayList<>();
    private List<WeatherModel> rainFallWeatherModelList = new ArrayList<>();
    private List<String> countriesList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(MainActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        loadSpinner();
        loadWeatherPager();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWeather(countriesList.get(0));
    }

    private void loadWeatherPager() {
        adapterViewPager = new WeatherPagerAdapter(getSupportFragmentManager());
        weatherPager.setAdapter(adapterViewPager);
    }

    private void loadSpinner() {
        // Spinner Drop down elements

        countriesList.add("UK");
        countriesList.add("England");
        countriesList.add("Scotland");
        countriesList.add("Wales");

        countriesDataAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, countriesList);
        countriesDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        countrySpinner.setAdapter(countriesDataAdapter);
        countrySpinner.setSelection(0);
        countrySpinner.setOnItemSelectedListener(this);

        blurBackground();


        stepView.getState()
                .steps(new ArrayList<String>() {{
                    add("2000s");
                    add("1980s");
                    add("1950s");
                    add("Early Time");
                }})
                .stepsNumber(4)
                .commit();
        weatherPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if ( i < 18 ){
                    stepView.go(0,true);
                }else if (i >18  && i < 31){
                    stepView.go(1, true);
                }if (i > 58 && i < 100){
                    stepView.go(2,true);
                }else if (i > 100){
                    stepView.go(3,true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        stepView.setOnStepClickListener(step -> {
            if (step == 0){
                weatherPager.setCurrentItem(9);
            }else if (step == 1){
                weatherPager.setCurrentItem(31);
            }else if (step == 2){
                weatherPager.setCurrentItem(59);
            }else if (step == 3){
                weatherPager.setCurrentItem(99);
            }


            stepView.go(step,true);
        });

    }

    private void getWeather(String locationName) {
        weatherViewModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherViewModel.class);
        weatherViewModel.setRequestData(locationName);
        WeatherPageModel weatherPageModel = new WeatherPageModel();
        weatherViewModel.getMaxTempObservable().observe(this, weatherModelList -> {
            if (weatherModelList != null){
                orderedYearList = CommonUtils.getOrderedYearList(weatherModelList);
                maxTempWeatherModelList.clear();
                maxTempWeatherModelList.addAll(weatherModelList);
                weatherPageModel.setOrderedYearList(orderedYearList);
                weatherPageModel.setmMaxTempModelList(maxTempWeatherModelList);
            }

        });

        weatherViewModel.getMinTempObservable().observe(this, weatherModelList -> {
            if (weatherModelList != null) {
                minTempWeatherModelList.clear();
                minTempWeatherModelList.addAll(weatherModelList);
                weatherPageModel.setmMinTempModelList(minTempWeatherModelList);
            }
        });

        weatherViewModel.getRainfallObservable().observe(this, weatherModelList -> {
            if (weatherModelList != null) {
                rainFallWeatherModelList.clear();
                rainFallWeatherModelList.addAll(weatherModelList);
                weatherPageModel.setmRainfallModelList(rainFallWeatherModelList);
            }
        });

        if (orderedYearList != null) {
            adapterViewPager.setData(orderedYearList, weatherPageModel);
            adapterViewPager.refresh();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String locationName = adapterView.getItemAtPosition(position).toString();
        imgBackground.setImageResource(bgImages[position]);
        blurBackground();
        getWeather(locationName);
        adapterViewPager.refresh();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        getWeather(countriesList.get(0));
    }

    private void blurBackground(){
        Animation fadeIn = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in);
        imgBackground.startAnimation(fadeIn);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                constraintLayout.post(() -> Blurry.with(MainActivity.this)
                        .radius(25)
                        .sampling(1)
                        .async()
                        .animate(1200)
                        .capture(imgBackground)
                        .into(imgBackground));
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                //Not Used
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                //Not Used
            }
        });

    }
}
