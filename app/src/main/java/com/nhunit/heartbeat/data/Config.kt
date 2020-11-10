package com.nhunit.heartbeat.data

import android.bluetooth.BluetoothDevice
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Config {
    companion object {
        var devices = ArrayList<BluetoothDevice>()
        var devicesMap = HashMap<String, BluetoothDevice>()
        val uuid: UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb")
    }
}