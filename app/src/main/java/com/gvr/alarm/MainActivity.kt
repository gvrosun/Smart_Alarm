package com.gvr.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.TimePicker
import android.view.View
import android.widget.Button
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity(),View.OnClickListener {

    lateinit var timePicker:TimePicker
    lateinit var textView:TextView
    var mHour:Int = 0
    var mMin:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        timePicker = findViewById(R.id.timePicker)
        textView = findViewById(R.id.timeTextView)
        var b = findViewById<Button>(R.id.button)
        b.setOnClickListener(this)
        timePicker.setOnTimeChangedListener { view, hourOfDay, minute ->
            mHour = hourOfDay
            mMin = minute
            textView.text = "Time:"+" "+mHour+":"+mMin
        }
    }
    override fun onClick(v: View?)
    {
        when(v!!.id)
        {
            R.id.button -> setTimer()
        }
    }
    public fun setTimer()
    {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        var date = Date()
        var cal_alarm:Calendar = Calendar.getInstance()
        var cal_now:Calendar = Calendar.getInstance()
        cal_now.setTime(date)
        cal_alarm.setTime(date)
        cal_alarm.set(Calendar.HOUR_OF_DAY,mHour)
        cal_alarm.set(Calendar.MINUTE,mMin)
        cal_alarm.set(Calendar.SECOND,0)
        if(cal_alarm.before(cal_now))
        {
            cal_alarm.add(Calendar.DATE,1 )
        }
        var remain = cal_alarm
        setNoti()
        var i = Intent(this,MyReceiver::class.java)
        var pendingIntent:PendingIntent = PendingIntent.getBroadcast(this,24444,i,0)
        alarmManager.set(AlarmManager.RTC_WAKEUP,cal_alarm.timeInMillis,pendingIntent)
        var calader = cal_alarm.timeInMillis - cal_now.timeInMillis
        var mins: Long = calader/(1000*60)
        Toast.makeText(this,"Alarm in: "+mins.toString() +" minutes",Toast.LENGTH_SHORT).show()
        this.finish()
    }
    public fun setNoti()
    {

    }
}
