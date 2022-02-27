package com.example.quizzitch

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class SignIn : AppCompatActivity() {
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth

    private companion object {
        private const val RC_SIGN_IN = 100
        private const val TAG = "GOOGLE_SIGN_IN_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin)
//        val expandablelayout: LinearLayout = findViewById(R.id.expandablelayout)
//        val signupcardview: CardView = findViewById(R.id.signupcardview)
//        val expand: Button = findViewById(R.id.expand)
//        expand.setOnClickListener {
//            if (expandablelayout.visibility == View.GONE) {
//                TransitionManager.beginDelayedTransition(signupcardview , AutoTransition())
//                expandablelayout.visibility = View.VISIBLE
//                expand.text = "COLLAPSE"
//            } else {
//                TransitionManager.beginDelayedTransition(signupcardview , AutoTransition())
//                expandablelayout.visibility = View.GONE
//                expand.text = "EXPAND"
//            }
//        }

        val googleSignInBtn: SignInButton = findViewById(R.id.SignInBtn)
        val account = GoogleSignIn.getLastSignedInAccount(this)

        googleSignInBtn.visibility = View.VISIBLE
        googleSignInBtn.setSize(SignInButton.SIZE_WIDE)

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this , googleSignInOptions)
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()
        googleSignInBtn.setOnClickListener {
            Log.d(SignIn.TAG , "onCreate: begin Google SignIn")
            val intent = googleSignInClient.signInIntent
            startActivityForResult(intent , SignIn.RC_SIGN_IN)
//            Toast.makeText(this,"chlpo pls",Toast.LENGTH_SHORT).show()
        }

//        val signupbt: Button = findViewById(R.id.button3)
//        val enterMail = findViewById<EditText>(R.id.textView8)
//        val enterPass: EditText = findViewById(R.id.editPassword)
//        signupbt.setOnClickListener {
//            when {
//                TextUtils.isEmpty(enterMail.text.toString().trim {it <= ' '}) -> {
//                    Toast.makeText(
//                        this ,
//                        "Please enter a password" ,
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//                else -> {
//                    val email: String = enterMail.text.toString().trim {it <= ' '}
//                    val password: String = enterPass.text.toString().trim {it <= ' '}
//                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email , password)
//                        .addOnCompleteListener(
//                            OnCompleteListener<AuthResult> {task ->
//                                if (task.isSuccessful) {
//                                    val firebaseUser: FirebaseUser = task.result!!.user!!
//                                    Toast.makeText(
//                                        this ,
//                                        "You were registered successfully." ,
//                                        Toast.LENGTH_SHORT
//                                    ).show()
//
//                                    val intent = Intent(this , SigninDetails::class.java)
//                                    intent.flags =
//                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                                    intent.putExtra("User_id" , firebaseUser.uid)
//                                    intent.putExtra("email-id" , firebaseUser.email)
//                                    startActivity(intent)
//                                    finish()
//                                } else {
//                                    Toast.makeText(
//                                        this ,
//                                        task.exception!!.message.toString() ,
//                                        Toast.LENGTH_SHORT
//                                    ).show()
//                                }
//                            }
//
//
//                        )
//                }
//            }
//        }


        if (intent.getStringArrayExtra("bool").toString() == "true") {
            Snackbar.make(findViewById(R.id.button) , "You were logged out" , 1000)
                .setAction("Action" , null).show()
        }
        val signinBt: Button = findViewById(R.id.button)
        val signinenterMail: EditText = findViewById(R.id.textView3)
        val signinenterPass: EditText = findViewById(R.id.signinEditPassword)

        signinBt.setOnClickListener {
            when {
                TextUtils.isEmpty(signinenterMail.text.toString().trim {it <= ' '}) -> {

                    Snackbar.make(
                        findViewById(R.id.signinEditPassword) ,
                        "Enter mail please" ,
                        2000
                    )
                        .setAction("action" , null).show()
                }
                TextUtils.isEmpty(signinenterPass.text.toString().trim {it <= ' '}) -> {
//                    Toast.makeText(
//                        this,
//                        "Enter Password please",
//                        Toast.LENGTH_SHORT
//                    ).show()

                    Snackbar.make(
                        findViewById(R.id.signinEditPassword) ,
                        "Enter Password please" ,
                        2000
                    )
                        .setAction("action" , null).show()
                }
                else -> {
                    val mail: String = signinenterMail.text.toString()
                    val password: String = signinenterPass.text.toString()
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(mail , password)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> {task ->
                                if (task.isSuccessful) {
                                    val firebaseUser: FirebaseUser = task.result!!.user!!
                                    Toast.makeText(this , "LOGGED IN" , Toast.LENGTH_SHORT).show()

//                                    Snackbar.make(findViewById(R.id.textView5), "LOGGED IN", 2000)
//                                        .setAction("action", null).show()

                                    val intent = Intent(this , GetStarted::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("User_id" , firebaseUser.uid)
                                    intent.putExtra("email-id" , firebaseUser.email)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    //Toast.makeText(this, task.exception!!.toString(), Toast.LENGTH_SHORT).show()

                                    Snackbar.make(
                                        findViewById(R.id.signinEditPassword) ,
                                        task.exception!!.toString() ,
                                        Snackbar.LENGTH_LONG
                                    )
                                        .setAction("action" , null).show()
                                }

                            }
                        )
                }
            }
        }
        val signup: TextView = findViewById(R.id.SIGNUPhighlight)
        signup.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            startActivity(Intent(this@SignIn , SigninDetails::class.java))
            finish()
        }

    }

    override fun onActivityResult(requestCode: Int , resultCode: Int , data: Intent?) {
        super.onActivityResult(requestCode , resultCode , data)
        if (requestCode == SignIn.RC_SIGN_IN) {
            Log.d(SignIn.TAG , "onaActivityResult: Google SignIn intent result")
            val accountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = accountTask.getResult(ApiException::class.java)
                firebaseAuthWithGoogleAccount(account)
                val googleSignInBtn: SignInButton = findViewById(R.id.googleSignInBtn)
                googleSignInBtn.visibility = View.GONE
            } catch (e: Exception) {
                Log.d(SignIn.TAG , "onActivityResult: ${e.message}")
                val googleSignInBtn: SignInButton = findViewById(R.id.googleSignInBtn)
                googleSignInBtn.visibility = View.VISIBLE
            }
        }
    }

    private fun firebaseAuthWithGoogleAccount(account: GoogleSignInAccount?) {
        Log.d(SignIn.TAG , "firebaseAuthWithGoogleAccount: begin firebase auth with google account")
        val credential = GoogleAuthProvider.getCredential(account!!.idToken , null)
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener {authResult ->
                Log.d(SignIn.TAG , "firebaseAuthWithGoogleAccount: LoggedIn")
                val firebaseUser = firebaseAuth.currentUser
                val uid = firebaseAuth.uid
                val email = firebaseUser!!.email
                Log.d(SignIn.TAG , "firebaseAuthWithGoogleAccount: Uid: $uid")
                Log.d(SignIn.TAG , "firebaseAuthWithGoogleAccount: Email: $email")

                if (authResult.additionalUserInfo!!.isNewUser) {
                    Log.d(SignIn.TAG , "firebaseAuthWithGoogleAccount: Account created...\n$email")
                    Toast.makeText(this@SignIn , "Account created...\n$email" , Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Log.d(SignIn.TAG , "firebaseAuthWithGoogleAccount: Existing user...\n$email")
                    Toast.makeText(this@SignIn , "LoggedIn...\n$email" , Toast.LENGTH_SHORT).show()
                }
                startActivity(Intent(this@SignIn , GetStarted::class.java))
                finish()
            }
            .addOnFailureListener {e ->
                Log.d(
                    SignIn.TAG ,
                    "firebaseAuthWithGoogleAccount:Loggin Failed due to ${e.message}"
                )
                Toast.makeText(
                    this@SignIn ,
                    "Loggin Failed due to ${e.message}" ,
                    Toast.LENGTH_SHORT
                ).show()
            }

    }



}
