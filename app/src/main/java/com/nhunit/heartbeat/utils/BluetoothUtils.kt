package com.nhunit.heartbeat.utils

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice

class BluetoothUtils {
    companion object {
        fun isDisabled(): Boolean {
            return BluetoothAdapter.getDefaultAdapter() == null || !BluetoothAdapter.getDefaultAdapter().isEnabled
        }

        fun getAllDevice(): List<BluetoothDevice> {
            if (isDisabled())
                return emptyList()
            else
                return BluetoothAdapter.getDefaultAdapter().bondedDevices.filterNotNull()
        }

        fun countDevice(): Int {
            if (isDisabled())
                return 0
            return BluetoothAdapter.getDefaultAdapter().bondedDevices.size
        }
    }
}