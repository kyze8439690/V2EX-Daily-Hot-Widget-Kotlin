package me.github.yugy.v2exwidget

import android.app.IntentService
import android.content.Intent
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL

public class NetworkService : IntentService("V2EXService") {

    override fun onHandleIntent(intent: Intent) {
        val result = Utils.httpGetString("https://www.v2ex.com/api/topics/hot.json")
        val broadcastIntent = Intent(this, javaClass<WidgetProvider>())
        broadcastIntent.setAction(WidgetProvider.ACTION_SET_DATA)
        broadcastIntent.putExtra("data", result)
        sendBroadcast(broadcastIntent)
    }
}