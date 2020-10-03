package id.ac.ui.cs.davenathanael.newspaper

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.SystemClock

val TIME_BROADCAST_ACTION = "id.ac.ui.cs.davenathanael.newspaper.broadcast.TIME"

class WatchService : Service() {

    private val binder = WatchServiceBinder()

    private val handler = Handler()

    private val initialTime = SystemClock.uptimeMillis()

    private val intent = Intent(TIME_BROADCAST_ACTION)

    inner class WatchServiceBinder: Binder()

    private val tickRunnable = object : Runnable {
        override fun run() {
            tick()
            handler.postDelayed(this, 1000)
        }
    }

    fun tick() {
        val diff = SystemClock.uptimeMillis() - initialTime
        val elapsedTime = (diff / 1000).toInt()

        intent.putExtra("time", elapsedTime)
        sendBroadcast(intent)
    }

    override fun onBind(intent: Intent): IBinder {
        handler.removeCallbacks(tickRunnable)
        handler.postDelayed(tickRunnable, 1000)

        return binder
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(tickRunnable)
    }

}
