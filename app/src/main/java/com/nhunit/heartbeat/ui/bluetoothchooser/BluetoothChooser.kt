package com.nhunit.heartbeat.ui.bluetoothchooser

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.nhunit.heartbeat.R
import com.nhunit.heartbeat.data.Config.Companion.devicesMap
import com.nhunit.heartbeat.utils.BluetoothUtils
import kotlinx.android.synthetic.main.activity_bluetooth_chooser.*


class BluetoothChooser : AppCompatActivity() {

    private var devices: ArrayList<BluetoothDevice> = ArrayList()
    private lateinit var adapter: BluetoothDeviceAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth_chooser)

        // Khởi tạo Recyclerview để chứa danh sách
        abcRvDevices.setHasFixedSize(true)
        abcRvDevices.layoutManager = LinearLayoutManager(this)

        // Khởi tạo adapter
        adapter = BluetoothDeviceAdapter(devices)

        // Gắp adapter vào RecyclerView
        abcRvDevices.adapter = adapter


        // Kiểm tra các trạng thái của bluetooth và thiết bị
        checkingBluetooth()
    }

    // Phương thức kiểm tra chung
    private fun checkingBluetooth() {
        // Nếu đã bật bluetooth và tìm thấy thiết bị trong phạm vi
        if (!bluetoothNotEnableNotify() && !deviceNotfoundNotify()) {
            // Tiến hành lấy danh sách thiết bị và hiển thị cho người dùng chọn

            // Tiến hành chạy trình tìm kiếm và show các thiết bị mới xuất hiện trong phạm vi
            val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
            registerReceiver(mReceiver, filter) // Don't forget to unregister during onDestroy

            // Lấy danh sách các thiết bị đã kết nối
            for (device in BluetoothAdapter.getDefaultAdapter().bondedDevices) {
                devicesMap[device.address] = device

                // Thêm vào danh sách
                devices.add(device)

                // Hiển thị lên cho người dùng
                adapter.notifyDataSetChanged()
            }
        }
    }

    // Kiểm tra xem xung quanh có thiế bị nào được tìm thấy chưa
    private fun deviceNotfoundNotify(): Boolean {
        if (BluetoothUtils.countDevice() <= 0) {
            SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Bạn cần đặt thiết bị trong phạm vi kết nối và khởi động lại ứng dụng?")
                .setContentText("KHÔNG TÌM THẤY THIẾT BỊ!")
                .setConfirmButton("Đã có thiết bị") {
                    it.dismissWithAnimation()
                    // Thực hiện gọi kiểm tra lại
                    checkingBluetooth()
                }
                .apply {
                    setCancelable(false)
                }
                .show()
            return true
        }
        return false
    }

    // Kiểm tra xem đã bật blue tooth chưa
    private fun bluetoothNotEnableNotify(): Boolean {
        if (BluetoothUtils.isDisabled()) {
            SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Bạn cần bật Bluetooth và thử lại!")
                .setContentText("CHƯA BẬT BLUETOOTH")
                .setConfirmButton("Đã bật Bluetooth") {
                    it.dismissWithAnimation()
                    // Thực hiện gọi kiểm tra lại
                    checkingBluetooth()
                }
                .apply {
                    setCancelable(false)
                }
                .show()
            return true
        }
        return false
    }

    // Tạo một BroadcastReceiver cho hành động ACTION_FOUND
    private val mReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action

            // Khi tìm thấy được một thiết bị mới
            if (BluetoothDevice.ACTION_FOUND == action) {
                // Lấy đối tượng BluetoothDevice từ Intent
                val device =
                    intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                devicesMap[device?.address]?.apply {
                    for (i in devices.indices) {
                        val tmp = devices[i]
                        if (device != null && tmp.address == device.address)
                            break
                    }

                    if (device != null) {
                        devices.add(device)

                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}