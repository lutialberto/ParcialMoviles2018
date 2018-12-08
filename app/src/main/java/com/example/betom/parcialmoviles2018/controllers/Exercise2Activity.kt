package com.example.betom.parcialmoviles2018.controllers

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.TextView
import com.example.betom.parcialmoviles2018.R
import com.example.betom.parcialmoviles2018.utilities.*
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat





@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class Exercise2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise2)

        restoreInstanceState(savedInstanceState)

        if(!checkPermissionForReadExtertalStorage()){
            requestPermissionForReadExtertalStorage()
        }
    }

    @Throws(Exception::class)
    fun requestPermissionForReadExtertalStorage() {
        try {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS),
                    0x3)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }

    }

    private fun checkPermissionForReadExtertalStorage(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val result = checkSelfPermission(Manifest.permission.READ_CONTACTS)
            return result == PackageManager.PERMISSION_GRANTED
        }
        return false
    }

    private fun restoreInstanceState(savedInstanceState: Bundle?) {
        if(savedInstanceState!=null) {
            val result= findViewById<TextView>(R.id.txtMultiplicationResult)
            val dataContact= findViewById<TextView>(R.id.txtContactInformation)

            val resultValue = savedInstanceState.getString(INSTANCE_STATE_EXERCISE_2_RESULT)
            val dataContactValue = savedInstanceState.getString(INSTANCE_STATE_EXERCISE_2_CONTACT_INFO)

            result.text = resultValue
            dataContact.text = dataContactValue
        }
    }

    fun btnGetContactClicked(view: View) {
        startActivityForResult(Intent(Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI), REQUEST_PICK_CONTACTS)
    }

    fun btnMultiplicationClicked(view: View) {
        val exercise1Activity= Intent(this,MultiplicationActivity::class.java)
        startActivityForResult(exercise1Activity,REQUEST_MULTIPLICATION)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_MULTIPLICATION && resultCode == Activity.RESULT_OK) {
            val multiplicationResult = data!!.getFloatExtra(EXTRA_MULTIPLICATION_RESULT, 0F)
            val txtResult = findViewById<TextView>(R.id.txtMultiplicationResult)
            txtResult.text = multiplicationResult.toString()
        }

        if (requestCode == REQUEST_PICK_CONTACTS && resultCode == RESULT_OK) {
            val uriContact = data!!.data
            val name = retrieveContactName(uriContact)
            val phoneNumber = retrieveContactNumber(uriContact)
            val contactInfo = findViewById<TextView>(R.id.txtContactInformation)
            contactInfo.text = "$name, $phoneNumber"
        }
    }

    private fun retrieveContactNumber(uriContact: Uri):String {
        var contactNumber = ""
        val cursorID = contentResolver.query(uriContact,arrayOf(ContactsContract.Contacts._ID),
                null, null, null)
        var contactID=""
        if (cursorID.moveToFirst()) {
            contactID = cursorID.getString(cursorID.getColumnIndex(ContactsContract.Contacts._ID))
        }
        cursorID.close()
        val cursorPhone = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER),
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " +
                        ContactsContract.CommonDataKinds.Phone.TYPE + " = " +
                        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,arrayOf(contactID),null)
        if (cursorPhone.moveToFirst()) {
            contactNumber = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
        }
        cursorPhone.close()
        return contactNumber
    }

    private fun retrieveContactName(uriContact: Uri?):String {
        var contactName = ""
        val cursor = contentResolver.query(uriContact, null, null, null, null)
        if (cursor.moveToFirst()) {
            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
        }
        cursor.close()
        return contactName
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val result = findViewById<TextView>(R.id.txtMultiplicationResult)
        val dataContact = findViewById<TextView>(R.id.txtContactInformation)

        saveInfo(result, INSTANCE_STATE_EXERCISE_2_RESULT,outState)
        saveInfo(dataContact, INSTANCE_STATE_EXERCISE_2_CONTACT_INFO,outState)
    }

    private fun saveInfo(textView: TextView, tag: String, outState: Bundle) {
        val value = textView.text.toString().trim()
        if(value.isNotEmpty()) {
            outState.putString(tag,value)
        }
    }
}
