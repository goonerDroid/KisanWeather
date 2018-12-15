package kisan.test.com.kisanweather.ui.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import kisan.test.com.kisanweather.ui.main.models.WeatherPageModel;
import kisan.test.com.kisanweather.ui.main.views.PageFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
 * sequence.
 */
public class WeatherPagerAdapter extends FragmentStatePagerAdapter {


    private List<Integer> yearList = new ArrayList<>();
    private WeatherPageModel weatherPageModel;

    public void setData(List<Integer> yearDataList, WeatherPageModel weatherPageModel) {
        yearList.clear();
        yearList.addAll(yearDataList);

       this.weatherPageModel = weatherPageModel;
        refresh();
    }


    public void refresh(){
        notifyDataSetChanged();
    }
    public WeatherPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(weatherPageModel,position);
    }

    @Override
    public int getCount() {
        return yearList.size();
    }
}
