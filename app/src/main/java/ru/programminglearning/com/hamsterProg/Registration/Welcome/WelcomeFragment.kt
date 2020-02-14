package ru.programminglearning.com.hamsterProg.Registration.Welcome

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v4.view.ViewPager.OnPageChangeListener
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_welcome.*
import ru.programminglearning.com.hamsterProg.Basics.BasicActivity
import ru.programminglearning.com.project123456.R
import java.util.*

class WelcomeFragment : Fragment() {
    private var mGoogleClient: GoogleSignInClient? = null
    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //настройки входа в гугл
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        mGoogleClient = GoogleSignIn.getClient(activity!!, gso)
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)
        init()
        onClick()
        return view
    }

    private fun init() {
        val sliderAdapter = SliderAdapter(activity!!)
        viewPager.adapter = sliderAdapter
        addDotsIndicator(0) // ставим индикатор позиции на 0
        viewPager.addOnPageChangeListener(viewListener)
    }

    //показ позиции
    private fun addDotsIndicator(position: Int) {
        val mDots = arrayOfNulls<TextView>(2)
        dotsLayout.removeAllViews()
        for (i in mDots.indices) {
            mDots[i] = TextView(activity)
            mDots[i]!!.text = Html.fromHtml("&#8226;")
            mDots[i]!!.setTextColor(resources.getColor(R.color.colorIndicatorFalse))
            mDots[i]!!.textSize = 30f
            dotsLayout.addView(mDots[i])
        }
        if (mDots.size > 0) {
            mDots[position]!!.setTextColor(resources.getColor(R.color.colorIndicatorTrue))
        }
    }

    /**
     * Слушатель об изменениях позициию */
    var viewListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
        override fun onPageSelected(position: Int) {
            addDotsIndicator(position)
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }

    /**
     * После интеграции Google вход в систему, вход в деятельность
     */
    private fun signInIntent() {
        val signInIntent = mGoogleClient!!.signInIntent
        startActivityForResult(signInIntent, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        // Результат, возвращенный при запуске Intent из GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 101) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try { // Вход в Google был успешным, аутентификация с Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) { // Ошибка входа в Google
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth!!.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        connectDatabase
                        startActivity(BasicActivity::class.java)
                    } else {
                        val error = "Ошибка входа:(" + "\n" + "Проверьте наличие интернета"
                        val parentLayout = view!!.findViewById<View>(android.R.id.content)
                        Snackbar.make(parentLayout, error, Snackbar.LENGTH_LONG).show()
                    }
                }
    }

    private fun startActivity(classes: Class<*>) {
        val intent = Intent(activity, classes)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        activity!!.finish()
    }

    //если юзера нет в базе
    private val connectDatabase: Unit
        private get() {
            val user = mAuth!!.currentUser
            val userId = user!!.uid
            val databaseReference = FirebaseDatabase.getInstance()
                    .getReference("Users")
            databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (!dataSnapshot.hasChild(userId)) { //если юзера нет в базе
                        val name = user.displayName
                        val photoAvatar = user.photoUrl
                        val bill = 0
                        val map: MutableMap<String, Any?> = HashMap()
                        map["Name"] = name
                        map["Photo"] = photoAvatar.toString()
                        map["Score"] = bill
                        for (i in 0..8) map["Number$i"] = numbersList[i]
                        databaseReference.child(userId).setValue(map)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
        }

    private val numbersList: Array<String>
        private get() = arrayOf("0/1", "0/2", "0/2", "0/1", "0/2", "0/1", "0/2")

    private fun onClick() {
        btnNextToLevel.setOnClickListener { signInIntent() }
    }

    companion object {
        fun newInstance(): WelcomeFragment {
            return WelcomeFragment()
        }
    }
}