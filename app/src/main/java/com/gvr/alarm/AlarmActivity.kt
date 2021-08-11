package com.gvr.alarm

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.annotation.RequiresApi


class AlarmActivity : AppCompatActivity() {

    var rnds1 = 0
    var rnds2 = 0
    var rnds3 = 0
    var ans = 0
    var ans1:Boolean = false
    var pas:Boolean = false
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED,WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD,WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
        getSupportActionBar()!!.hide()
        rnds1 = (-99..99).random()
        rnds2 = (0..3).random()
        rnds3 = (-99..99).random()
        var f = findViewById<TextView>(R.id.first)
        f.setText("($rnds1) ")
        var m = findViewById<TextView>(R.id.middle)
        when(rnds2)
        {
            0 -> {
                m.setText("+")
                ans = rnds1+rnds3
            }
            1 -> {
                m.setText("-")
                ans = rnds1-rnds3
            }
            2 -> {
                m.setText("*")
                ans = rnds1*rnds3
            }
            3 -> {
                m.setText("/")
                ans = rnds1/rnds3
            }
        }
        var l = findViewById<TextView>(R.id.last)
        l.setText(" ($rnds3)")
        var b = findViewById<Button>(R.id.stop)
        var vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        val mVibratePattern = longArrayOf(0, 400, 200, 400)
        vibrator.vibrate(mVibratePattern, 0)
        var noti: Notification = Notification.Builder(this)
            .setContentTitle("Alarm is ON")
            .setContentText("You had set up the alarm")
            .setSmallIcon(R.mipmap.ic_launcher).build()
        var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        noti.flags = Notification.FLAG_AUTO_CANCEL
        manager.notify(0,noti)
        var notification:Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        var r: Ringtone = RingtoneManager.getRingtone(this,notification)
        r.setLooping(true)
        r.play()
        b.setOnClickListener(View.OnClickListener {
            var an = findViewById<EditText>(R.id.answer)
            var ans3 = an.text.toString()
            Log.d("Alarm", "Answer string: ${ans.toString()}")
            if(ans.toString() == ans3)
            {
                ans1 = true
                pas = true
                Toast.makeText(this,"Bye Bye",Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this,"Incorrect answer",Toast.LENGTH_SHORT).show()
                an.setTextColor(Color.RED)
            }
            if(ans1) {
                r.stop()
                vibrator.cancel()
                this.finish()
            }
        })
    }

    override fun onBackPressed() {
        Toast.makeText(this,"Solve the problem first",Toast.LENGTH_SHORT).show()
    }

    /*override fun onPause() {
        if(pas) {
            super.onPause()
        }
        else
        {
            super.onPause()
            var i = Intent(this,AlarmActivity::class.java)
            startActivity(i)
        }
    }*/
}

private fun Window.setFlags(flagFullscreen: Int, flagFullscreen1: Int, flagShowWhenLocked: Int) {
}
