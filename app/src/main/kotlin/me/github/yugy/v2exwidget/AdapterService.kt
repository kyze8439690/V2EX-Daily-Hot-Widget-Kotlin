package me.github.yugy.v2exwidget

import android.content.Intent
import android.widget.RemoteViewsService

public class AdapterService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsService.RemoteViewsFactory {
        return AdapterFactory(this, intent)
    }
}