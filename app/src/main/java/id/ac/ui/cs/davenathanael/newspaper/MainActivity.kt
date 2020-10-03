package id.ac.ui.cs.davenathanael.newspaper

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var backCount = 0

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent != null) {
                updateTime(intent)
            }
        }
    }

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {}
        override fun onServiceDisconnected(name: ComponentName?) {}
    }

    private fun updateTime(intent: Intent) {
        val time = intent.getIntExtra("time", 0)
        findViewById<TextView>(R.id.time).apply {
            text = "Elapsed time: $time s"
            if (visibility != View.VISIBLE) {
                visibility = View.VISIBLE
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Intent(this, WatchService::class.java).also {
            bindService(it, connection, Context.BIND_AUTO_CREATE)
        }
        registerReceiver(receiver, IntentFilter(TIME_BROADCAST_ACTION))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
        unbindService(connection)
    }

    override fun onBackPressed() {
        backCount += 1
        findViewById<TextView>(R.id.center_text).apply {
            text = "Back x$backCount"
        }

        Toast.makeText(applicationContext, "You pressed back!", Toast.LENGTH_SHORT).show()
    }
}