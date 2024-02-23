package com.app.meditation.ui.screen.tuneList

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.Gson

data class DataTunes(
    var name: String?,
    var listener: Int,
    var img: Int,
    var duration: Int
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun toString(): String = Uri.encode(Gson().toJson(this))
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<DataTunes> {
        override fun createFromParcel(parcel: Parcel): DataTunes {
            return DataTunes(parcel)
        }

        override fun newArray(size: Int): Array<DataTunes?> {
            return arrayOfNulls(size)
        }
    }

}
