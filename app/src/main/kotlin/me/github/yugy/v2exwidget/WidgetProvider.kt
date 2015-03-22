package me.github.yugy.v2exwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.RemoteViews
import android.widget.Toast

public class WidgetProvider : AppWidgetProvider() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.getAction() == ACTION_SET_DATA && intent.hasExtra("data")) {
            println("set data")
            val appWidgetIds = AppWidgetManager.getInstance(context).getAppWidgetIds(
                    ComponentName(context, javaClass<WidgetProvider>()))
            for (appWidgetId in appWidgetIds) {
                val setAdapterIntent = Intent(context, javaClass<AdapterService>())
                setAdapterIntent.putExtra("data", intent.getStringExtra("data"))
                setAdapterIntent.setData(Uri.parse(setAdapterIntent.toUri(Intent.URI_INTENT_SCHEME)))
                val views = RemoteViews(context.getPackageName(), R.layout.widget)
                views.setRemoteAdapter(appWidgetId, R.id.list, setAdapterIntent)

                val clickIntent = Intent(context, javaClass<WidgetProvider>())
                clickIntent.setAction(ACTION_CLICK)
                val pendingClickIntent = PendingIntent.getBroadcast(context, 1, clickIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT)
                views.setPendingIntentTemplate(R.id.list, pendingClickIntent)

                views.setViewVisibility(R.id.loading, View.GONE)

                AppWidgetManager.getInstance(context).updateAppWidget(appWidgetId, views)
            }
        } else if (intent.getAction() == ACTION_CLICK) {
            println("handle click")
            val url = intent.getStringExtra("url")
            val viewIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            viewIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            if (context.getPackageManager().queryIntentActivities(viewIntent, 0).size() != 0) {
                context.startActivity(viewIntent)
            } else {
                Toast.makeText(context, "Browsers not found.", Toast.LENGTH_SHORT).show()
            }
        } else {
            println("on receive")
            super.onReceive(context, intent)
        }
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        println("on update")
        val refreshIntent = Intent(context, javaClass<NetworkService>())
        for (appWidgetId in appWidgetIds) {
            val views = RemoteViews(context.getPackageName(), R.layout.widget)
            views.setViewVisibility(R.id.loading, View.VISIBLE)
            val intent = PendingIntent.getBroadcast(context, 0, refreshIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT)
            views.setOnClickPendingIntent(R.id.refresh, intent)
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
        context.startService(refreshIntent)
    }

    companion object {

        public val ACTION_SET_DATA: String = "ACTION_SET_DATA"
        public val ACTION_CLICK: String = "ACTION_CLICK"
    }

}