package ua.com.foxminded.testkotlinproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ua.com.foxminded.testkotlinproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding
    private var login = "empty"
    private var password = "empty"
    private var name = "empty"
    private var secondName = "empty"
    private var lastName = "empty"
    private var imageId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constance.REQUEST_CODE_SIGN_IN) {
            val log = data?.getStringExtra(Constance.LOGIN)
            val pas = data?.getStringExtra(Constance.PASSWORD)
            if (login == log && password == pas) {
                bindingClass.ivFotoMain.setImageResource(imageId)
                val textInfo = "$name $secondName $lastName"
                bindingClass.tvInfo.text = textInfo
                bindingClass.bEnter.visibility = View.GONE
                bindingClass.bExit.text = "EXIT"
            } else {
                bindingClass.tvInfo.text = "Net takogo akkaunta"
            }

        } else if (requestCode == Constance.REQUEST_CODE_SIGN_UP) {
            login = data?.getStringExtra(Constance.LOGIN)!!
            password = data?.getStringExtra(Constance.PASSWORD)!!
            name = data?.getStringExtra(Constance.NAME)!!
            secondName = data?.getStringExtra(Constance.SECOND_NAME)!!
            lastName = data?.getStringExtra(Constance.LAST_NAME)!!
            imageId = data?.getIntExtra(Constance.AVATAR_ID, 0)!!
            bindingClass.ivFotoMain.setImageResource(imageId)
            val textInfo = "$name $secondName $lastName"
            bindingClass.tvInfo.text = textInfo
            bindingClass.bEnter.visibility = View.GONE
            bindingClass.bExit.text = "EXIT"

        }
    }

    fun onClickSignIn(view: View) {
        val intent = Intent(this, SignInUpActivity::class.java)
        intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_IN_STATE)
        startActivityForResult(intent, Constance.REQUEST_CODE_SIGN_IN)
    }

    fun onClickSignUp(view: View) {
        val intent = Intent(this, SignInUpActivity::class.java)
        intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_UP_STATE)
        startActivityForResult(intent, Constance.REQUEST_CODE_SIGN_UP)
    }
}