package me.github.yugy.v2exwidget.model

import android.os.Parcel
import android.os.Parcelable

public class Member : Parcelable {

    public var id: Int = 0
    public var username: String = ""
    public var tagline: String = ""
    public var avatar_mini: String = ""
    public var avatar_normal: String = ""
    public var avatar_large: String = ""

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.id)
        dest.writeString(this.username)
        dest.writeString(this.tagline)
        dest.writeString(this.avatar_mini)
        dest.writeString(this.avatar_normal)
        dest.writeString(this.avatar_large)
    }

    public constructor() {
    }

    private constructor(`in`: Parcel) {
        this.id = `in`.readInt()
        this.username = `in`.readString()
        this.tagline = `in`.readString()
        this.avatar_mini = `in`.readString()
        this.avatar_normal = `in`.readString()
        this.avatar_large = `in`.readString()
    }

    companion object {

        public val CREATOR: Parcelable.Creator<Member> = object : Parcelable.Creator<Member> {
            override fun createFromParcel(source: Parcel): Member {
                return Member(source)
            }

            override fun newArray(size: Int): Array<Member?> {
                return arrayOfNulls(size)
            }
        }
    }
}