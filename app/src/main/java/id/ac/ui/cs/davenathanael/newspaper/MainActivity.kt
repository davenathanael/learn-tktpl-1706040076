package id.ac.ui.cs.davenathanael.newspaper

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private val PERMISSIONS_REQUEST_CODE_ACCESS_FINE_LOCATION = 1919
    private val service = RequestbinService.create()
    private val wifiManager: WifiManager by lazy {
        applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    }

    private val wifiScanReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent == null) return

            val success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
            if (success) {
                scanSuccess()
            } else {
                scanFailure()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.base_url).text = RequestbinService.BASE_URL

        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        applicationContext.registerReceiver(wifiScanReceiver, intentFilter)

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSIONS_REQUEST_CODE_ACCESS_FINE_LOCATION)
        } else {
            startScan()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSIONS_REQUEST_CODE_ACCESS_FINE_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startScan()
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun startScan() {
        Toast.makeText(applicationContext, "Start to scan APs...", Toast.LENGTH_SHORT).show()

        val success = wifiManager.startScan()
        if (!success) {
            scanFailure()
        }
    }

    private fun scanSuccess() {
        val results = wifiManager.scanResults
        val json = Gson().toJson(results)
        Toast.makeText(applicationContext, "SUCCESS SCAN: $json", Toast.LENGTH_SHORT).show()
        runBlocking {
            withContext(Dispatchers.IO) {
                Log.i("RequestBin result", service.postWifis(Request(results)).toString())
            }
        }
    }

    private fun scanFailure() {
        Toast.makeText(applicationContext, "ERROR: failed to scan APs", Toast.LENGTH_SHORT).show()
    }
}