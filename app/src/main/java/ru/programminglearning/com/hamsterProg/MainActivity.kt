package ru.programminglearning.com.hamsterProg

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import ru.programminglearning.com.hamsterProg.Basics.BasicActivity
import ru.programminglearning.com.hamsterProg.Registration.Welcome.WelcomeActivity
import ru.programminglearning.com.project123456.R

class MainActivity : AppCompatActivity() {
    private val currentUser = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (isOnline(this)) {
            if (currentUser == null) {
                startActivity(WelcomeActivity::class.java)
            } else {
                anim()
                Handler().postDelayed({ startActivity(BasicActivity::class.java) }, 2400)
            }
        } else {
        }
    }

    private fun anim() {
        splashView.animate().setDuration(2000).rotation(360f)
    }

    private fun startActivity(classes: Class<*>) {
        val intent = Intent(this@MainActivity, classes)
        startActivity(intent)
        finish()
    }

    /**метод для проверки наличия подключения к сети */
    fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        if (netInfo != null && netInfo.isConnectedOrConnecting) {
            return true
        }
        val parentLayout = findViewById<View>(android.R.id.content)
        Snackbar.make(parentLayout, "Нет подключения к интернету:( " + "\n" + "Проверьте связь и перезайдите в приложение", Toast.LENGTH_SHORT).show()
        return false
    }
}