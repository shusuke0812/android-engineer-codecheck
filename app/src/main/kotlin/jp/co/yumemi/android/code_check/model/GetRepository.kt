package jp.co.yumemi.android.code_check.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetRepository(
    @Json(name = "subscribers_count")
    val subscribersCount: Int
) : Parcelable
