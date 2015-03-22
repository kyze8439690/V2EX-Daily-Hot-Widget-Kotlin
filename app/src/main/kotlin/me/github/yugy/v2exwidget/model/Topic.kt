package me.github.yugy.v2exwidget.model

import android.os.Parcel
import android.os.Parcelable

public class Topic : Parcelable {

    public var id: Int = 0
    public var title: String = ""
    public var url: String = ""
    public var content: String = ""
    public var content_rendered: String = ""
    public var replies: Int = 0
    public var member: Member = Member()
    public var node: Node = Node()
    public var created: Long = 0
    public var last_modified: Long = 0
    public var last_touched: Long = 0

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.id)
        dest.writeString(this.title)
        dest.writeString(this.url)
        dest.writeString(this.content)
        dest.writeString(this.content_rendered)
        dest.writeInt(this.replies)
        dest.writeParcelable(this.member, 0)
        dest.writeParcelable(this.node, 0)
        dest.writeLong(this.created)
        dest.writeLong(this.last_modified)
        dest.writeLong(this.last_touched)
    }

    public constructor() {
    }

    private constructor(`in`: Parcel) {
        this.id = `in`.readInt()
        this.title = `in`.readString()
        this.url = `in`.readString()
        this.content = `in`.readString()
        this.content_rendered = `in`.readString()
        this.replies = `in`.readInt()
        this.member = `in`.readParcelable<Member>(javaClass<Member>().getClassLoader())
        this.node = `in`.readParcelable<Node>(javaClass<Node>().getClassLoader())
        this.created = `in`.readLong()
        this.last_modified = `in`.readLong()
        this.last_touched = `in`.readLong()
    }

    companion object {

        public val CREATOR: Parcelable.Creator<Topic> = object : Parcelable.Creator<Topic> {
            override fun createFromParcel(source: Parcel): Topic {
                return Topic(source)
            }

            override fun newArray(size: Int): Array<Topic?> {
                return arrayOfNulls(size)
            }
        }
    }
}