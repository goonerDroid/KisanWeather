package kisan.test.com.kisanweather.service.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by william on 10-12-2018.
 */
@Parcelize class WeatherModel : Parcelable {

    @SerializedName("metric")
    @Expose
    val metric: String? = null
    @SerializedName("location")
    @Expose
    val location: String? = null
    @SerializedName("value")
    @Expose
    val value: Double = 0.toDouble()
    @SerializedName("year")
    @Expose
    val year: Int = 0
    @SerializedName("month")
    @Expose
    val month: Int = 0
}
