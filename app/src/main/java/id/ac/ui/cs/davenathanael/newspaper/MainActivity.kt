package id.ac.ui.cs.davenathanael.newspaper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var backCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        backCount += 1
        val centerText: TextView = findViewById(R.id.center_text)
        centerText.text = "Back x$backCount"
        
        Toast.makeText(applicationContext, "You pressed back!", Toast.LENGTH_SHORT).show()
    }
}