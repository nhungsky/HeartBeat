package com.nhunit.heartbeat.ui.bluetoothchooser

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.nhunit.heartbeat.R
import com.nhunit.heartbeat.ui.heartmeansure.HeartMeansureActivity

class BluetoothDeviceViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    val bdLnRoot: LinearLayout = v.findViewById(R.id.bdLnRoot)
    val bdTvDeviceName: TextView = v.findViewById(R.id.bdTvDeviceName)
    val bdTvDeviceMac: TextView = v.findViewById(R.id.bdTvDeviceMac)

    fun bind(device: BluetoothDevice) {
        bdTvDeviceName.text = device.name ?: "Không xác định"
        bdTvDeviceMac.text = device.address

        bdLnRoot.setOnClickListener {
            // Ngưng tìm thiết bị mới
            BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
            HeartMeansureActivity.selectedDevice = device

            // Khởi động màn hình đo nhịp tim
            val intent = Intent(bdLnRoot.context, HeartMeansureActivity::class.java)
            bdLnRoot.context.startActivity(intent)

            // Kết thúc màn hình chọn bluetooth hiện tại
            (bdLnRoot.context as AppCompatActivity).finish()
        }
    }
}