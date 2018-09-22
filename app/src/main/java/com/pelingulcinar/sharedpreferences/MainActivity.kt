package com.pelingulcinar.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import com.pelingulcinar.sharedpreferences.enums.SharedPrefKeys
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {


    private val sharedPRef by lazy { getSharedPreferences(SharedPrefKeys.PrivateSharedPref.toString(), Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkBox.setOnCheckedChangeListener(this)

        if (!getUserName().isEmpty()) {
            textUser.setText(getUserName())
            checkBox.isChecked = true

        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

        if (isChecked && !textUser.text.trim().isEmpty()) {
            sharedPRef.edit().setUserName(textUser.text.toString())
        } else {
            sharedPRef.edit().setUserName("")
        }

    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun getUserName(): String {
        return sharedPRef.getString(SharedPrefKeys.UserName.toString(), "")
    }


}





fun SharedPreferences.Editor.setUserName(userName: String) {

    with(this) {
        putString(com.pelingulcinar.sharedpreferences.enums.SharedPrefKeys.UserName.toString(), userName)
        apply()

    }

}



