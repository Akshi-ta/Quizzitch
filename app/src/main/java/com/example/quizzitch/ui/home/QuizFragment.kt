package com.example.quizzitch.ui.home

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.quizzitch.R
import kotlinx.android.synthetic.main.take_quiz.*

class QuizFragment: Fragment() {

    private val args = this.arguments
    private val inputData1 = args?.get("data")
    private val inputData2 = args?.get("key")
    val level = inputData1.toString()
    val topic = inputData2.toString()

    private var score: Int = 0

    private var currentPosition: Int = 1
    private var questionList: ArrayList<QuestionData>? = null
    private var selectedOption: Int = 0

    var choice: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        choice = arguments?.getString("topic")

//        setFragmentResultListener("requestKey") { key, bundle ->
//            // Any type can be passed via to the bundle
//            val result = bundle.getString("data")
//            // Do something with the result...
//        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.take_quiz, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tv: TextView = view.findViewById(R.id.quiz)
        tv.visibility = View.VISIBLE
        val pgbar: ProgressBar = view.findViewById(R.id.progressBar1)
        pgbar.visibility = View.GONE
        val bundle = Bundle()

        questionList = setData.getQuestion()

        setQuestion()


        opt_1.setOnClickListener {

            selectedOptionStyle(opt_1, 1)
        }
        opt_2.setOnClickListener {

            selectedOptionStyle(opt_2, 2)
        }
        opt_3.setOnClickListener {

            selectedOptionStyle(opt_3, 3)
        }
        opt_4.setOnClickListener {

            selectedOptionStyle(opt_4, 4)
        }

        submit.setOnClickListener {
            if (selectedOption != 0) {
                val question = questionList!![currentPosition - 1]
                if (selectedOption != question.correct_ans) {
                    setColor(selectedOption, R.drawable.wrong_question_option)
                }else{
                    score++
                }
                setColor(question.correct_ans,R.drawable.correct_question_option)
                if(currentPosition==questionList!!.size)
                   submit.text= "FINISH"
                else
                    submit.text= "GO TO NEXT"
            }else{
                currentPosition++
                when{
                    currentPosition<=questionList!!.size->{
                        setQuestion()
                    }
                    else->{
                        val fragment: Fragment = Result()
                        bundle.putString("data", topic)
                        fragment.arguments = bundle
                        val cons: ConstraintLayout = view.findViewById(R.id.quizr)
                        cons.visibility = View.GONE
                        val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                        transaction.replace(R.id.quizR, fragment)
                        transaction.addToBackStack("result")
                        transaction.commit()
                    }
                }
            }
            selectedOption=0
        }
    }

        fun setColor(opt: Int, color: Int) {
            when (opt) {
                1 -> {
                    opt_1.background = ContextCompat.getDrawable(requireContext(), color)
                }
                2 -> {
                    opt_2.background = ContextCompat.getDrawable(requireContext(), color)
                }
                3 -> {
                    opt_3.background = ContextCompat.getDrawable(requireContext(), color)
                }
                4 -> {
                    opt_4.background = ContextCompat.getDrawable(requireContext(), color)
                }
            }
        }




    fun setQuestion() {
        val question = questionList!![currentPosition - 1]

        setOptionStyle()

        timeBar.progress = currentPosition
        timeBar.max = questionList!!.size
        progress_text.text = "${currentPosition}" + "/" + "${questionList!!.size}"
        quiz.text = question.question
        opt_1.text = question.option_one
        opt_2.text = question.option_tw0
        opt_3.text = question.option_three
        opt_4.text = question.option_four
    }

    fun setOptionStyle() {

        var optionList: ArrayList<TextView> = arrayListOf()
        if (opt_1 != null) {
            optionList.add(0, opt_1)
        }
        if (opt_2 != null) {
            optionList.add(1, opt_2)
        }
        if (opt_3 != null) {
            optionList.add(2, opt_3)
        }
        if (opt_4 != null) {
            optionList.add(3, opt_4)
        }

        for (op in optionList) {
            op.setTextColor(Color.parseColor("#555151"))
            op.background = ContextCompat.getDrawable(requireContext(), R.drawable.question_option)
            op.typeface = Typeface.DEFAULT
        }
    }

    private fun selectedOptionStyle(view: TextView, opt: Int) {

        setOptionStyle()
        selectedOption = opt
        view.background = ContextCompat.getDrawable(requireContext(), R.drawable.selected_question_option)
        view.typeface = Typeface.DEFAULT_BOLD
        view.setTextColor(Color.parseColor("#000000"))

    }

}


