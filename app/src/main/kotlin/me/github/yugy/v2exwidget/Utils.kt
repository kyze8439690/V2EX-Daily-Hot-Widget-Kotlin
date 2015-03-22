package me.github.yugy.v2exwidget

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL

public class Utils {
    companion object {

        public fun httpGetString(url: String): String {
            var input: BufferedReader? = null
            try {
                val connection = URL(url).openConnection()
                connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                connection.setRequestProperty("accept-encoding", "gzip, deflate, sdch")
                connection.setRequestProperty("accept-language", "zh-CN,zh;q=0.8,en;q=0.6")
                connection.setRequestProperty("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.94 Safari/537.36")
                connection.connect()
                input = BufferedReader(InputStreamReader(connection.getInputStream()))
                var result = ""
                var line = input?.readLine()
                while (line != null) {
                    result += "$line\n"
                    line = input?.readLine()
                }
                return result;
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            } finally {
                try {
                    input?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            return "";
        }

        public fun getBitmap(imageUrl: String): Bitmap? {
            try {
                val url = URL(imageUrl)
                val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                return bitmap
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            }
            return null
        }
    }
}