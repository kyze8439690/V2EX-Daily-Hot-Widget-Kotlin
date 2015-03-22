package me.github.yugy.v2exwidget

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViewsService

import com.google.gson.Gson

import org.json.JSONArray
import org.json.JSONException

import me.github.yugy.v2exwidget.model.Topic

public class AdapterFactory(private val mContext: Context, intent: Intent) : RemoteViewsService.RemoteViewsFactory {

    private val mTopics: Array<Topic>

    init{
        val result = intent.getStringExtra("data")
        try {
            val json = JSONArray(result)
            mTopics = Array(json.length(), {i -> Gson().fromJson<Topic>(json.getString(i), javaClass<Topic>())})
        } catch (e: JSONException) {
            e.printStackTrace()
            mTopics = Array(0, {i -> Topic() });
        }

    }

    override fun onCreate() = Unit

    override fun onDataSetChanged() = Unit

    override fun onDestroy() = Unit

    override fun getCount(): Int = mTopics.size()

    override fun getViewAt(position: Int): RemoteViews {
        val views = RemoteViews(mContext.getPackageName(), R.layout.item)
        views.setTextViewText(R.id.name, mTopics[position].member.username)
        views.setTextViewText(R.id.title, mTopics[position].title)
        val timeText = DateUtils.getRelativeTimeSpanString(
                mTopics[position].created * 1000,
                System.currentTimeMillis(),
                DateUtils.MINUTE_IN_MILLIS,
                DateUtils.FORMAT_ABBREV_RELATIVE
        )
        views.setTextViewText(R.id.time, timeText)
        val bitmap = Utils.getBitmap("https:${mTopics[position].member.avatar_large}")
        if (bitmap != null) {
            views.setImageViewBitmap(R.id.head_icon, bitmap)
        }
        val extras = Bundle()
        extras.putString("url", mTopics[position].url)
        val fillInIntent = Intent()
        fillInIntent.putExtras(extras)
        views.setOnClickFillInIntent(R.id.root, fillInIntent)

        return views
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = position.toLong()

    override fun hasStableIds(): Boolean = true
}