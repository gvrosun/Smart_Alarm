package com.gvr.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

public class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        var i = Intent(context, AlarmActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context!!.startActivity(i)
    }
}