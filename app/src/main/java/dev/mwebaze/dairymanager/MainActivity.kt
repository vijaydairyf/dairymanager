package dev.mwebaze.dairymanager

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import dev.mwebaze.dairymanager.database.DairyDatabase
import dev.mwebaze.dairymanager.service.LoginService
import dev.mwebaze.dairymanager.service.LoginServiceInterface
import dev.mwebaze.diarymanager.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.password_linear_layout.*

/**
 * @author Michael Mwebaze
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = DairyDatabase(this)

        setContentView(R.layout.activity_main)

        showHideBtn.setOnClickListener() {
            if(showHideBtn.text.toString().equals("Show")){
                passwordEditText.transformationMethod = HideReturnsTransformationMethod.getInstance()
                showHideBtn.text = getString(R.string.hide)
            }
            else {
                passwordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
                showHideBtn.text = getString(R.string.show)
            }
        }

        btnReset.setOnClickListener(){
            val uname = findViewById<EditText>(R.id.usernameEditText) as EditText
            val passWd = findViewById<EditText>(R.id.passwordEditText) as EditText

            uname.setText("")
            passWd.setText("")
        }
    }

    fun login(view: View) {
        val username = findViewById<EditText>(R.id.usernameEditText)
        val password = findViewById<EditText>(R.id.passwordEditText)
        val login : LoginServiceInterface =
            LoginService()
        //Log.d("Login service", username.text.toString())
        val status = login.login(username.text.toString(), password.text.toString())

        if (status) {
            val dataCollectionIntent = Intent(this, DataCollectionActivity::class.java)
            startActivity(dataCollectionIntent)
        }
    }
}