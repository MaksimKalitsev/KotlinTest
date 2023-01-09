package ua.com.foxminded.testkotlinproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import ua.com.foxminded.testkotlinproject.databinding.ActivityMainBinding
import ua.com.foxminded.testkotlinproject.databinding.ActivitySignInUpBinding

class SignInUpActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivitySignInUpBinding
    private var signState = "empty"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivitySignInUpBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        signState = intent.getStringExtra(Constance.SIGN_STATE)!!
        if (signState == Constance.SIGN_IN_STATE) {
            bindingClass.bAvatar.visibility = View.INVISIBLE
            bindingClass.etName.visibility = View.GONE
            bindingClass.etSecondName.visibility = View.GONE
            bindingClass.etLastName.visibility = View.GONE
        }
    }

    fun onClickDone(view: View) {
        signState = intent.getStringExtra(Constance.SIGN_STATE)!!
        if (signState == Constance.SIGN_UP_STATE) {
            val intent = Intent()
            intent.putExtra(Constance.LOGIN, bindingClass.etLogin.text.toString())
            intent.putExtra(Constance.PASSWORD, bindingClass.etPassword.text.toString())
            intent.putExtra(Constance.NAME, bindingClass.etName.text.toString())
            intent.putExtra(Constance.SECOND_NAME, bindingClass.etSecondName.text.toString())
            intent.putExtra(Constance.LAST_NAME, bindingClass.etLastName.text.toString())
            if (bindingClass.ivFoto.isVisible)intent.putExtra(Constance.AVATAR_ID, R.drawable.ic_launcher_background)
            setResult(RESULT_OK, intent)
            finish()
        } else if (signState == Constance.SIGN_IN_STATE) {
            intent.putExtra(Constance.LOGIN, bindingClass.etLogin.text.toString())
            intent.putExtra(Constance.PASSWORD, bindingClass.etPassword.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    fun onClickAvatar(view: View) {
        bindingClass.ivFoto.visibility = View.VISIBLE
    }
}