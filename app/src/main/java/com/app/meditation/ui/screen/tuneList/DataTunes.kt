package com.app.meditation.ui.screen.tuneList

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.Gson
import java.io.Serializable

data class DataTunes(
    var name: String?="",
    var listener: Int=0,
    var image: String?="",
    var duration: Int=0,
    var link: String?=""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    )

    override fun toString(): String = Uri.encode(Gson().toJson(this))

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeInt(listener)
        dest.writeString(image)
        dest.writeInt(duration)
        dest.writeString(link)
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