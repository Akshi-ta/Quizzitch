package com.example.quizzitch

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzitch.databinding.ActivityDashBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class SignUp: AppCompatActivity() {
//    private lateinit var binding: ActivityDashBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth
    private companion object{
        private const val RC_SIGN_IN = 100
        private const val TAG = "GOOGLE_SIGN_IN_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityDashBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        setContentView(R.layout.signup)
        val googleSignInBtn: SignInButton = findViewById(R.id.googleSignInBtn)
        val account = GoogleSignIn.getLastSignedInAccount(this)

        googleSignInBtn.visibility = View.VISIBLE
        googleSignInBtn.setSize(SignInButton.SIZE_WIDE)

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions)
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        googleSignInBtn.setOnClickListener {
            Log.d(TAG, "onCreate: begin Google SignIn")
            val intent = googleSignInClient.signInIntent
            startActivityForResult(intent , RC_SIGN_IN)
//            Toast.makeText(this,"chlpo pls",Toast.LENGTH_SHORT).show()
        }


        val signupbt: Button = findViewById(R.id.button3)
        val enterMail : EditText = findViewById(R.id.textView8)
        val enterPass : EditText = findViewById(R.id.editPassword)
        signupbt.setOnClickListener {
            when {
                TextUtils.isEmpty(enterMail.text.toString().trim { it <= ' '}) -> {
                    Toast.makeText(
                        this,
                        "Please enter a password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    val email: String = enterMail.text.toString().trim { it<= ' '}
                    val password: String = enterPass.text.toString().trim { it <= ' ' }
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> {task ->
                                if (task.isSuccessful) {
                                    val firebaseUser: FirebaseUser = task.result!!.user!!
                                    Toast.makeText(
                                        this,
                                        "You were registered successfully.",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    val intent = Intent(this, SigninDetails::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("User_id", firebaseUser.uid)
                                    intent.putExtra("email-id", firebaseUser.email)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(
                                        this,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }


                        )
                }
            }
        }
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser!= null){
            startActivity(Intent(this@SignUp,SigninDetails::class.java))
            finish()
        }


    }

    override fun onActivityResult(requestCode: Int , resultCode: Int , data: Intent?) {
        super.onActivityResult(requestCode , resultCode , data)
        if(requestCode == RC_SIGN_IN){
            Log.d(TAG, "onaActivityResult: Google SignIn intent result")
            val accountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = accountTask.getResult(ApiException::class.java)
                firebaseAuthWithGoogleAccount(account)
                val googleSignInBtn: SignInButton = findViewById(R.id.googleSignInBtn)
                googleSignInBtn.visibility = View.GONE
            }
            catch (e:Exception){
                Log.d(TAG, "onActivityResult: ${e.message}")
                val googleSignInBtn: SignInButton = findViewById(R.id.googleSignInBtn)
                googleSignInBtn.visibility = View.VISIBLE
            }
        }
    }

    private fun firebaseAuthWithGoogleAccount(account: GoogleSignInAccount?) {
        Log.d(TAG,"firebaseAuthWithGoogleAccount: begin firebase auth with google account")
        val credential = GoogleAuthProvider.getCredential(account!!.idToken,null)
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener { authResult ->
                Log.d(TAG,"firebaseAuthWithGoogleAccount: LoggedIn")
                val firebaseUser = firebaseAuth.currentUser
                val uid = firebaseAuth.uid
                val email = firebaseUser!!.email
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Uid: $uid")
                Log.d(TAG,"firebaseAuthWithGoogleAccount: Email: $email")

                if (authResult.additionalUserInfo!!.isNewUser){
                    Log.d(TAG,"firebaseAuthWithGoogleAccount: Account created...\n$email")
                    Toast.makeText(this@SignUp, "Account created...\n$email",Toast.LENGTH_SHORT).show()
                }
                else{
                    Log.d(TAG,"firebaseAuthWithGoogleAccount: Existing user...\n$email")
                    Toast.makeText(this@SignUp,"LoggedIn...\n$email",Toast.LENGTH_SHORT).show()
                }
                startActivity(Intent(this@SignUp,GetStarted::class.java))
                finish()
            }
            .addOnFailureListener{e ->
                Log.d(TAG,"firebaseAuthWithGoogleAccount:Loggin Failed due to ${e.message}")
                Toast.makeText(this@SignUp,"Loggin Failed due to ${e.message}",Toast.LENGTH_SHORT).show()
            }

    }
}
