package com.nhunit.heartbeat.ui.heartmeansure

import android.bluetooth.BluetoothDevice
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nhunit.heartbeat.R
import kotlinx.android.synthetic.main.activity_heart_meansure.*
import java.util.*

class HeartMeansureActivity : AppCompatActivity() {

    companion object {
        var selectedDevice: BluetoothDevice? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heart_meansure)

        snake.setMinValue(0F)
        snake.setMaxValue(160F)
    }

    fun updateBPM(s: Float) {
        ahmTvHearthBeat.post {
            ahmTvHearthBeat.text = s.toString()
            snake.addValue(s.toFloat())
        }
    }

    fun updateSPB(s: Double) {
        ahmTvSpb.post {
            ahmTvSpb.text = "Mất trung bình ${s}ms mỗi nhịp"
        }
    }
}