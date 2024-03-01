package oguz.library.etut.model

import android.os.Parcel
import android.os.Parcelable



class Model_formula(val image:Int, val name:String, val at:String, val form:Int) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()!!,

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(image)
        parcel.writeString(name)
        parcel.writeString(at)
        parcel.writeInt(form)


    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Model_formula> {
        override fun createFromParcel(parcel: Parcel): Model_formula {
            return Model_formula(parcel)
        }

        override fun newArray(size: Int): Array<Model_formula?> {
            return arrayOfNulls(size)
        }
    }
}


