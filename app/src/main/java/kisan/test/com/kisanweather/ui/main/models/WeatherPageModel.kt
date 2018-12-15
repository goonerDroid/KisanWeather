package kisan.test.com.kisanweather.ui.main.models

import android.os.Parcelable
import kisan.test.com.kisanweather.service.model.WeatherModel
import kotlinx.android.parcel.Parcelize

/**
 * Created by william on 12-12-2018.
 */
@Parcelize class WeatherPageModel : Parcelable{

    private var mMaxTempModelList: List<WeatherModel>? = null
    private var mMinTempModelList: List<WeatherModel>? = null
    private var mRainfallModelList: List<WeatherModel>? = null
    private var mOrderedYearList: List<Int>? = null

    fun getmMaxTempModelList(): List<WeatherModel>? {
        return mMaxTempModelList
    }

    fun setmMaxTempModelList(mMaxTempModelList: List<WeatherModel>) {
        this.mMaxTempModelList = mMaxTempModelList
    }

    fun getmMinTempModelList(): List<WeatherModel>? {
        return mMinTempModelList
    }

    fun setmMinTempModelList(mMinTempModelList: List<WeatherModel>) {
        this.mMinTempModelList = mMinTempModelList
    }

    fun getmRainfallModelList(): List<WeatherModel>? {
        return mRainfallModelList
    }

    fun setmRainfallModelList(mRainfallModelList: List<WeatherModel>) {
        this.mRainfallModelList = mRainfallModelList
    }


    fun getOrderedYearList(): List<Int>? {
        return mOrderedYearList
    }

    fun setOrderedYearList(mOrderedYearList: List<Int>) {
        this.mOrderedYearList = mOrderedYearList.asReversed()
    }
}
