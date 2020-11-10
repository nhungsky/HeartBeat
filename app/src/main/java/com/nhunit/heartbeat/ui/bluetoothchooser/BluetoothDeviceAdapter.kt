package com.nhunit.heartbeat.ui.bluetoothchooser

import android.bluetooth.BluetoothDevice
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nhunit.heartbeat.R

class BluetoothDeviceAdapter(val devices: ArrayList<BluetoothDevice>) :
    RecyclerView.Adapter<BluetoothDeviceViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BluetoothDeviceViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.bluetooth_device, parent, false)
        return BluetoothDeviceViewHolder(v)
    }

    override fun getItemCount(): Int {
        return devices.size
    }

    override fun onBindViewHolder(holder: BluetoothDeviceViewHolder, position: Int) {
        val device = devices[position]
        holder.bind(device)
    }

}