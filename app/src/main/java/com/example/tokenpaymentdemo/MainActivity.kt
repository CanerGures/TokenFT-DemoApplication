package com.example.tokenpaymentdemo

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.tokenpaymentdemo.model.GetQrBodyModel
import com.example.tokenpaymentdemo.model.PaymentActionList
import com.example.tokenpaymentdemo.model.PaymentBodyModel
import com.example.tokenpaymentdemo.model.PaymentInfoList
import com.example.tokenpaymentdemo.service.RestApiService
import com.example.tokenpaymentdemo.service.RestApiServicePayment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.zxing.BarcodeFormat
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var intentIntegrator: IntentIntegrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText: EditText = findViewById(R.id.editText)

        intentIntegrator = IntentIntegrator(this)

        getQrButton.setOnClickListener {
            val text = editText.text.toString()
            val number = Integer.valueOf(text)
            fetchQR(number)
        }
        scanQrButton.setOnClickListener {
            intentIntegrator.setBeepEnabled(true).initiateScan()
        }

    }

    private fun fetchQR(body: Int) {
        val apiService = RestApiService()
        val bodyModel = GetQrBodyModel(
            body
        )

        apiService.fetchPayQR(bodyModel) {
            val barcodeEncoder = BarcodeEncoder()
            val imgQRCode = barcodeEncoder.encodeBitmap(it?.qRdata, BarcodeFormat.QR_CODE, 600, 600)
            qrImage.setImageBitmap(imgQRCode)
            val generatedText = "QR code Generated"
            textView.text = generatedText
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (intentResult != null) {
            if (intentResult.contents != null) {
                paymentButton.visibility = View.VISIBLE
                payment(intentResult.contents)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun payment(contents: String) {
        paymentButton.setOnClickListener {
            paymentTransaction(contents)
        }
    }

    private fun paymentTransaction(contents: String) {
        val apiServicePayment = RestApiServicePayment()
        val paymentActionList = PaymentActionList(
            3,
            100,
            949,
            800
        )
        val paymentInfoList = PaymentInfoList(
            67,
            listOf(paymentActionList)
        )
        val paymentBody = PaymentBodyModel(
            1000,
            "success",
            "beko Campaign/n2018",
            "beko Campaign Merchant/n2018",
            listOf(paymentInfoList), contents

        )

        apiServicePayment.paymentService(paymentBody) {
            if (it?.returnCode == 1000) {

                val materialAlert = MaterialAlertDialogBuilder(this@MainActivity)
                materialAlert.setTitle("Alert")
                materialAlert.setMessage("Transaction Complete!")
                materialAlert.setPositiveButton(
                    "OKAY"
                ) { dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.cancel()
                }
                materialAlert.show()

            } else {
                val materialAlert = MaterialAlertDialogBuilder(this@MainActivity)
                materialAlert.setTitle("Alert")
                materialAlert.setMessage(it?.returnDesc)
                materialAlert.setPositiveButton(
                    "OKAY"
                ) { dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.cancel()
                }
                materialAlert.show()

            }
        }

    }
}