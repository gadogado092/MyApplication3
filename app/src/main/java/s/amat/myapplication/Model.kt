package s.amat.myapplication

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Model(
    val userId: Int?,
    val id: Int?,
    val title: String?,
    val completed: Boolean?) : Parcelable