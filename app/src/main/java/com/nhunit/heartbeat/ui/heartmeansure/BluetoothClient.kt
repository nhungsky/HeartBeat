package com.nhunit.heartbeat.ui.heartmeansure

import java.util.*
import kotlin.math.round

class BluetoothClient(val context: HeartMeansureActivity) : Thread() {
    val uuid: UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb")
    private val socket =
        HeartMeansureActivity.selectedDevice?.createRfcommSocketToServiceRecord(uuid)

    override fun run() {
        if (this.socket == null)
            return
        this.socket.connect()

        val inputStream = this.socket.inputStream
        var text = "";
        while (true) {
            val available = inputStream.available()
            if (available <= 0)
                continue

            val bytes = ByteArray(available)
            inputStream.read(bytes, 0, available)
            text += String(bytes)

            if (text.indexOf('\r') > 0 || text.indexOf('\n') > 0) {
                text = text.replace("\r", "")
                    .replace("\n", "")

                if (text.matches(Regex("B\\d+_Q\\d+"))) {
                    for (r in text.split("_")) {
                        if (r.matches(Regex("B\\d+"))) {
                            val res = r.replace("B", "").toFloatOrNull() ?: 0F
                            context.updateBPM(res)
                        } else if (r.matches(Regex("Q\\d+"))) {
                            val ee = r.replace("Q", "").toDoubleOrNull() ?: 0.0
                            val fx = ee / 1000
                            context.updateSPB(round(fx * 10) / 10)
                        }
                    }
                }
                text = ""
            }
        }
    }
}