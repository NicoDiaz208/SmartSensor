package at.htl.countries.model

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class Data(
    @SerializedName("id")
    val id: String,
    @SerializedName("Timestamp")
    val timestamp: Double,
    @SerializedName("Topic")
    val topic: String,
    @SerializedName("Message")
    val message: String
)