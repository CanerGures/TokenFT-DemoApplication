package com.example.tokenpaymentdemo.ui


import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tokenpaymentdemo.R
import com.example.tokenpaymentdemo.model.GetQrBodyModel
import com.example.tokenpaymentdemo.model.PaymentActionList
import com.example.tokenpaymentdemo.model.PaymentBodyModel
import com.example.tokenpaymentdemo.model.PaymentInfoList
import com.example.tokenpaymentdemo.repository.FetchApiQrRepository
import com.example.tokenpaymentdemo.repository.PaymentRepository
import com.example.tokenpaymentdemo.service.ApiService
import com.example.tokenpaymentdemo.service.client.WebClient
import com.example.tokenpaymentdemo.viewmodel.GetQrViewModel
import com.example.tokenpaymentdemo.viewmodel.PaymentViewModel
import com.example.tokenpaymentdemo.viewmodel.util.PaymentViewModelFactory
import com.example.tokenpaymentdemo.viewmodel.util.ViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.zxing.BarcodeFormat
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var intentIntegrator: IntentIntegrator

    private var viewModel: GetQrViewModel? = null
    private var paymentViewModel: PaymentViewModel? = null


    private val service: ApiService by lazy { WebClient.buildService(ApiService::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText: EditText = findViewById(R.id.editText)



        intentIntegrator = IntentIntegrator(this)

        getQrButton.setOnClickListener {
            val text = editText.text.toString()

            if (text != "") {
                val number = Integer.valueOf(text)
                fetchQR(number)
            } else {
                Toast.makeText(this, "Please enter a number!", Toast.LENGTH_LONG).show()
            }
        }
        scanQrButton.setOnClickListener {
            intentIntegrator.setBeepEnabled(true).initiateScan()
        }

    }

    private fun fetchQR(body: Int) {
        val bodyModel = GetQrBodyModel(
            body
        )
        progressBar.visibility = View.VISIBLE
        val rep = FetchApiQrRepository(service, bodyModel)
        viewModel = ViewModelFactory(rep).create(GetQrViewModel::class.java)
        observeQr()

    }
    private fun payment(contents: String) {
        paymentButton.setOnClickListener {
            paymentTransaction(contents)
        }
    }

    private fun paymentTransaction(contents: String) {
        if (contents != "") {
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
            val rep = PaymentRepository(service, paymentBody)
            paymentViewModel = PaymentViewModelFactory(rep).create(PaymentViewModel::class.java)
            observePayment()
        } else {
            Toast.makeText(this, "Something went wrong! Try Again.", Toast.LENGTH_SHORT).show()
        }

    }

    private fun observePayment() {
        paymentViewModel?.fetch?.observe(this, {
            if (it != null && it.returnCode == 1000) {
                val materialAlert = MaterialAlertDialogBuilder(this@MainActivity)
                materialAlert.setTitle("Alert")
                materialAlert.setMessage("Transaction Complete!")

                materialAlert.setPositiveButton(
                    "OKAY"
                ) { dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.cancel()
                }
                materialAlert.show()
                paymentButton.visibility = View.INVISIBLE


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
                paymentButton.visibility = View.INVISIBLE

            }
        })
        viewModel?.empty?.observe(this, {

        })

        viewModel?.dataLoading?.observe(this, {

            Log.e("TAG", "Loading")
        })

    }

    private fun observeQr() {
        viewModel?.fetch?.observe(this, {
            if (it != null) {
                val barcodeEncoder = BarcodeEncoder()
                val imgQRCode =
                    barcodeEncoder.encodeBitmap(it.qRdata, BarcodeFormat.QR_CODE, 600, 600)
                qrImage.setImageBitmap(imgQRCode)
                val generatedText = "QR code Generated"
                textView.text = generatedText
                progressBar.visibility = View.GONE
                paymentButton.visibility = View.VISIBLE
                payment(it.qRdata)


            }
        })
        viewModel?.empty?.observe(this, {

        })

        viewModel?.dataLoading?.observe(this, {
        })
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
}