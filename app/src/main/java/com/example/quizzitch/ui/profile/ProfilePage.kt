package com.example.quizzitch.ui.profile

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentTransaction
import com.example.quizzitch.R
import com.example.quizzitch.SignIn
import com.example.quizzitch.databinding.FragmentProfilePageBinding
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class ProfilePage : Fragment() {
    private lateinit var imageUri: Uri
    private var isclick: Boolean = false
    private var storage: FirebaseStorage = FirebaseStorage.getInstance()
    private var storageReference = storage.getReference()
    private var _binding: FragmentProfilePageBinding? = null
    private lateinit var profilePageFragmentViewModel: ProfilePageFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        fun newInstance(name: String): ProfilePage{
//
//        }

        val name: String = requireArguments().getString("avatar").toString()

        val dp: ImageView = view.findViewById(R.id.imageView5)
        val mauth: FirebaseAuth = FirebaseAuth.getInstance()
        val uid = mauth.currentUser!!.uid.toString()
        val store: FirebaseFirestore = FirebaseFirestore.getInstance()
        val dpLocation = mauth.currentUser!!.uid
        val url = "images/$name.jpeg"
        val max_bytes:Long = 1024*1024
        val imgRef = storageReference.child(url)
       imgRef.getBytes(max_bytes).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeByteArray(it,0,it.size)
            dp.setImageBitmap(bitmap)
        }.addOnFailureListener{
            dp.setImageResource(R.drawable.blackpanther)
        }
        store.collection("desc").document(uid).get().addOnSuccessListener {
            val map: HashMap<String,Any> = it.data as HashMap<String,Any>

            if(it["gameplayed"] == null){
                map["gameplayed"] = 0
            }
            val gameplayed: TextView = view.findViewById(R.id.textView45)
            gameplayed.text = map["gameplayed"].toString()

            if(it["quizdone"]==null){
                map["quizdone"] = 0
            }
            val quizdone: TextView= view.findViewById(R.id.textView46)
            quizdone.text = map["quizdone"].toString()

            if(it["gamewon"]==null){
                map["gamewon"] = 0
            }
            val gamewon: TextView= view.findViewById(R.id.textView44)
            gamewon.text = map["gamewon"].toString()

            store.collection("desc").document(uid).update(map)

        }

        if (mauth.currentUser != null) {
            store.collection("desc").document(uid).get()
                .addOnSuccessListener {
                    val name: TextView = view.findViewById(R.id.textView)
                    val mail: TextView = view.findViewById(R.id.textView6)
                    val displayName: TextView = view.findViewById(R.id.textView39)
                    val fullName: String =
                        it.get("first").toString() + " " + it.get("last").toString()
                    name.text = fullName
                    displayName.text = it.get("displayName").toString()
                    mail.text = it.get("mail").toString()
                }
        }


        val logOut: TextView = view.findViewById(R.id.logout)
        logOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            val intent = Intent(activity, SignIn::class.java)
            intent.putExtra("bool", "true")
            startActivity(intent)
        }

        val myButton: View = view.findViewById(R.id.changeDp)
        myButton.setOnClickListener {
            selectImage()
        }

        val save: TextView = view.findViewById(R.id.saveChanges)
        save.setOnClickListener {
            val name = requireArguments().getString("avatar")
            if (isclick)
                UploadImage(view, name)
            else
                Snackbar.make(view, "No changes to save,...", Snackbar.LENGTH_SHORT)
                    .setAction("action", null).show()
        }
    }


    private fun selectImage() {
        val fragment: Fragment = ChooseAvatar()
        val cons: ConstraintLayout = requireView().findViewById(R.id.profiler)
        cons.visibility = View.GONE
        val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.profileR, fragment)
        transaction.addToBackStack("avatar")
        transaction.commit()
//        val a: Intent = Intent(Intent.ACTION_GET_CONTENT).setType("image/*")
//        val chooser: Intent = Intent.createChooser(a, "Select image from here")
//        startActivityForResult(chooser, 100)
//        isclick = true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            imageUri = data?.data!!
        //error    _binding?.imageView5?.setImageURI(imageUri)

        }

    }

    private fun UploadImage(view: View, name: String?) {

        if (imageUri != null) {
            val id = FirebaseAuth.getInstance().currentUser?.uid
            val ref: StorageReference = FirebaseStorage.getInstance().getReference("images/$name.jpeg")
            ref.putFile(imageUri)
                .addOnSuccessListener {
                    Toast.makeText(activity, "Profile photo changed", Toast.LENGTH_SHORT).show()

                }
                .addOnFailureListener {
                    OnFailureListener() {
                        Toast.makeText(activity, "Upload Failed ${it.toString()}", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnProgressListener {
                    Toast.makeText(activity, "working on it", Toast.LENGTH_LONG).show()
                }
        }
    }


}