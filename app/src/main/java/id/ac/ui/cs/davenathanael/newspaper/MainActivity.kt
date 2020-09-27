package id.ac.ui.cs.davenathanael.newspaper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var helloCounter = HelloCounter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.hello_button)
        button.setOnClickListener { sayHello() }
    }

    private fun sayHello() {
        helloCounter.increment {
            val count: TextView = findViewById(R.id.hello_counter)
            count.visibility = View.VISIBLE
            count.text = getString(R.string.hello_counter_label, it)
        }
    }
}