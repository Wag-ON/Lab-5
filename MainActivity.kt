package com.example.pogoda

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : MvpAppCompatActivity() {

    val manager = supportFragmentManager
    val myBundle = Bundle()

    private lateinit var mHandler: Handler
    private lateinit var mRunnable:Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        go()

        mHandler = Handler()
        swipe_refresh_layout.setOnRefreshListener {
            mRunnable = Runnable {

                Toast.makeText(this, "refresh", Toast.LENGTH_SHORT).show()
                go()
                swipe_refresh_layout.isRefreshing = false
            }

            mHandler.postDelayed(
                mRunnable,
                500 // Delay 1 to 5 seconds
            )
        }
    }

    fun go(){
        val transaction = manager.beginTransaction()
        val fragment = FragmentOne()
        fragment.setArguments(myBundle)
        transaction.replace(R.id.fragment_holder, fragment)
        transaction.commit()
    }

}
