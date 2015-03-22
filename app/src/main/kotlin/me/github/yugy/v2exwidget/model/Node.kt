package me.github.yugy.v2exwidget.model

import android.os.Parcel
import android.os.Parcelable

public class Node : Parcelable {
    public var id: Int = 0
    public var name: String = ""
    public var title: String = ""
    public var title_alternative: String = ""
    public var url: String = ""
    public var topics: Int = 0
    public var avatar_mini: String = ""
    public var avatar_normal: String = ""
    public var avatar_large: String = ""

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.id)
        dest.writeString(this.name)
        dest.writeString(this.title)
        dest.writeString(this.title_alternative)
        dest.writeString(this.url)
        dest.writeInt(this.topics)
        dest.writeString(this.avatar_mini)
        dest.writeString(this.avatar_normal)
        dest.writeString(this.avatar_large)
    }

    public constructor() {
    }

    private constructor(`in`: Parcel) {
        this.id = `in`.readInt()
        this.name = `in`.readString()
        this.title = `in`.readString()
        this.title_alternative = `in`.readString()
        this.url = `in`.readString()
        this.topics = `in`.readInt()
        this.avatar_mini = `in`.readString()
        this.avatar_normal = `in`.readString()
        this.avatar_large = `in`.readString()
    }

    companion object {

        public val CREATOR: Parcelable.Creator<Node> = object : Parcelable.Creator<Node> {
            override fun createFromParcel(source: Parcel): Node {
                return Node(source)
            }

            override fun newArray(size: Int): Array<Node?> {
                return arrayOfNulls(size)
            }
        }
    }
}
