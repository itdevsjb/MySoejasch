package com.tpsmedia.mysoejasch.service


import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.widget.Toast
import com.dantsu.escposprinter.EscPosPrinter
import com.dantsu.escposprinter.connection.bluetooth.BluetoothPrintersConnections
import com.google.zxing.BarcodeFormat
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.tpsmedia.mysoejasch.MainActivity
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.OutputStream


class BluetoothPrinterHelper(private val context: Context) {

    private val bluetoothAdapter: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private var bluetoothSocket: BluetoothSocket? = null
    private lateinit var outputStream: OutputStream

    @SuppressLint("MissingPermission")
//    fun selectBluetoothDevice(onDeviceSelected: (Boolean) -> Unit) {
//        if (!bluetoothAdapter.isEnabled) {
//            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
//            (context as? Activity)?.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
//        } else {
//            showPairedDevices(onDeviceSelected)
//        }
//    }
//    fun selectBluetoothDevice(callback: BluetoothDeviceCallback) {
//        if (!bluetoothAdapter.isEnabled) {
//            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
//            (context as? Activity)?.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
//        } else {
//            showPairedDevices { isConnected ->
//                callback.onDeviceSelected(isConnected)
//            }
//        }
//    }

    // Interface untuk callback
    interface BluetoothDeviceCallback {
        fun onDeviceSelected(isConnected: Boolean)
    }
    @SuppressLint("MissingPermission")
    private fun showPairedDevices(onDeviceSelected: (Boolean) -> Unit) {
        val pairedDevices = bluetoothAdapter.bondedDevices
        val deviceNames = pairedDevices.map { it.name }.toTypedArray()
        val deviceList = pairedDevices.toList()

        AlertDialog.Builder(context)
            .setTitle("Pilih Perangkat Bluetooth")
            .setItems(deviceNames) { _, which ->
                connectToDevice(deviceList[which], onDeviceSelected)
            }
            .show()
    }

    @SuppressLint("MissingPermission")
    private fun connectToDevice(device: BluetoothDevice, onDeviceSelected: (Boolean) -> Unit) {
        val uuid = device.uuids[0].uuid
        bluetoothSocket = device.createRfcommSocketToServiceRecord(uuid)

        try {
            bluetoothSocket?.connect()
            outputStream = bluetoothSocket!!.outputStream
            Toast.makeText(context, "Tersambung ke ${device.name}", Toast.LENGTH_SHORT).show()
            onDeviceSelected(true)
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(context, "Gagal terhubung ke perangkat", Toast.LENGTH_SHORT).show()
            onDeviceSelected(false)
        }
    }

    fun generateQRCode(kode: String, nama:String, tanggal:String, size: Int): ByteArray {
        val qrCodeWriter = QRCodeWriter()
        val bitMatrix: BitMatrix = qrCodeWriter.encode(kode, BarcodeFormat.QR_CODE, size, size)

        // Mengubah BitMatrix menjadi Bitmap
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (bitMatrix[x, y]) android.graphics.Color.BLACK else android.graphics.Color.WHITE)
            }
        }

        // Mengonversi Bitmap menjadi byte array
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)

        return byteArrayOutputStream.toByteArray()
    }



    fun printQRCodeToThermalPrinter(kode:String, nama: String, tanggal: String) {
        try {
            val printer =
                EscPosPrinter(BluetoothPrintersConnections.selectFirstPaired(), 203, 48f, 32)
            printer
                .printFormattedText(
                    """
        [C]
        [C]<b>$nama</b>
        [C]====================================
        [C]
        [C]<qrcode size='20'>$kode</qrcode>
        [C]
        [C]<b>$kode</b>
        [C]$tanggal
        """.trimIndent()
                )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun closeConnection() {
        try {
            bluetoothSocket?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @SuppressLint("MissingPermission")
    fun selectBluetoothDevice(BluetoothDeviceCallback: MainActivity.BluetoothDeviceCallback) {
        if (!bluetoothAdapter.isEnabled) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            (context as? Activity)?.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
        } else {
            showPairedDevices { isConnected ->
                BluetoothDeviceCallback.onDeviceSelected(isConnected)
            }
        }
    }

    companion object {
        private const val REQUEST_ENABLE_BT = 1
    }

}
