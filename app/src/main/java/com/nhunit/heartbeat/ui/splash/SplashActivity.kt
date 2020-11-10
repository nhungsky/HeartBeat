package com.nhunit.heartbeat.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nhunit.heartbeat.R
import com.nhunit.heartbeat.ui.bluetoothchooser.BluetoothChooser
import java.util.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        gotoBluethChooser()
    }

    private fun gotoBluethChooser() {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                //Tạo thể hiện chuyển đổi ngữ cảnh sang màn hình Bluetooth
                val intent = Intent(this@SplashActivity, BluetoothChooser::class.java)
                //Bắt đầu activity mới
                startActivity(intent)
                //Kết thúc activity hiện tại
                finish()
            }
        }, 1000)
    }
}