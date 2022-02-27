package com.example.quizzitch.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.quizzitch.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class ChooseAvatar : Fragment() {
    val store: FirebaseFirestore = FirebaseFirestore.getInstance()
    val mauth: FirebaseAuth = FirebaseAuth.getInstance()
    val uid: String = mauth.currentUser!!.uid


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_choose_avatar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding = ActivityChooseAvatarBinding.inflate(LayoutInflater)
//        setContentView(binding.root)

        Toast.makeText(
            activity,
            "Please select your favourite avatar and then click on done",
            Toast.LENGTH_LONG
        ).show()

        val blackWidow: ImageView = view.findViewById(R.id.blackWidow)
        val blackPanther: ImageView = view.findViewById(R.id.blackPanther)
        val hawkeye: ImageView = view.findViewById(R.id.hawkeye)
        val falcon: ImageView = view.findViewById(R.id.falcon)
        val bucky: ImageView = view.findViewById(R.id.bucky)
        val captainMarvel: ImageView = view.findViewById(R.id.captainMarvel)
        val scarletWitch: ImageView = view.findViewById(R.id.scarletWitch)
        val thor: ImageView = view.findViewById(R.id.thor)
        val thanos: ImageView = view.findViewById(R.id.thanos)
        val starLord: ImageView = view.findViewById(R.id.starLord)
        val ironMan: ImageView = view.findViewById(R.id.ironMan)
        val spiderMan: ImageView = view.findViewById(R.id.spiderMan)
        val captainAmerica: ImageView = view.findViewById(R.id.captainAmerica)
        val drStrange: ImageView = view.findViewById(R.id.drStrange)
        val deadpool: ImageView = view.findViewById(R.id.deadpool)
        val wolverine: ImageView = view.findViewById(R.id.wolverine)
        val hulk: ImageView = view.findViewById(R.id.hulk)


        blackWidow.setOnClickListener { operator(view, "black widow") }
        hawkeye.setOnClickListener { operator(view, "hawkeye") }
        blackPanther.setOnClickListener { operator(view, "black panther") }
        falcon.setOnClickListener { operator(view, "falcon") }
        bucky.setOnClickListener { operator(view, "bucky") }
        captainMarvel.setOnClickListener { operator(view, "captain marvel") }
        scarletWitch.setOnClickListener { operator(view, "scarlet witch") }
        thor.setOnClickListener { operator(view, "thor") }
        thanos.setOnClickListener { operator(view, "thanos") }
        starLord.setOnClickListener { operator(view, "star lord") }
        ironMan.setOnClickListener { operator(view, "iron man") }
        spiderMan.setOnClickListener { operator(view, "spider man") }
        captainAmerica.setOnClickListener { operator(view, "captain america") }
        drStrange.setOnClickListener { operator(view, "dr strange") }
        deadpool.setOnClickListener { operator(view, "deadpool") }
        wolverine.setOnClickListener { operator(view, "wolverine") }
        hulk.setOnClickListener { operator(view, "hulk") }


    }

        val avatarMap: HashMap<Int, Int> = hashMapOf<Int, Int>(
            1 to R.drawable.blackwidow,
            2 to R.drawable.hawkeye,
            3 to R.drawable.blackpanther,
            4 to R.drawable.falcon,
            5 to R.drawable.bucky,
            6 to R.drawable.captainmarvel,
            7 to R.drawable.scarletwitch,
            8 to R.drawable.thor,
            9 to R.drawable.thanos,
            10 to R.drawable.starlord,
            11 to R.drawable.ironman,
            12 to R.drawable.spiderman
        )
        val avatarMapInv: HashMap<Int, Int> = hashMapOf<Int, Int>(
            R.drawable.blackwidow to 1,
            R.drawable.hawkeye to 2,
            R.drawable.blackpanther to 3,
            R.drawable.falcon to 4,
            R.drawable.bucky to 5,
            R.drawable.captainmarvel to 6,
            R.drawable.scarletwitch to 7,
            R.drawable.thor to 8,
            R.drawable.thanos to 9,
            R.drawable.starlord to 10,
            R.drawable.ironman to 11,
            R.drawable.spiderman to 12
        )

        val db = FirebaseFirestore.getInstance()
    //       db.collection("desc").document(mauth.uid.toString()).update()
//            .addOnSuccessListener {
//                Toast.makeText(requireContext(), "Data Saved!", Toast.LENGTH_SHORT).show()
//            }


        private fun operator(view: View, name: String) {
            view.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.selected_question_option)
            val storageRef = FirebaseStorage.getInstance().reference.child("images/$name.jpeg")
            avatarChoosen(view, storageRef, name)

        }

        private fun avatarChoosen(view: View, storageRef: StorageReference, name: String) {

            val done: Button = view.findViewById(R.id.done)
            done.setOnClickListener {
                store.collection("desc").document(uid).get().addOnSuccessListener {
                    val map: HashMap<String, Any> = it.data as HashMap<String, Any>
                    map["avatar"] = name
                    store.collection("desc").document(uid).update(map)
                }
                val cons: ConstraintLayout = view.findViewById(R.id.avatarr)
                cons.visibility = View.GONE
                val fragment: Fragment = ProfilePage()
                val bundle = Bundle()
                bundle.putString("avatar", storageRef.toString())
                fragment.arguments = bundle
                val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.avatarR, fragment)
                transaction.addToBackStack("pic")
                transaction.commit()

            }


        }
    }





