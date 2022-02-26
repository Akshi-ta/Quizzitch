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

class QuizFragment: Fragment() {

    private val args = this.arguments
    private val inputData1 = args?.get("data")
    private val inputData2 = args?.get("key")
    val level = inputData1.toString()
    private val topic = inputData2.toString()

    private var score: Int = 0

    private var currentPosition: Int = 1
    private var questionList: ArrayList<QuestionData>? = null
    private var selectedOption: Int = 0

    private var choice: String? = ""

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

        setQuestion(view)

        val opt1: TextView = view.findViewById(R.id.opt_1)
        val opt2: TextView = view.findViewById(R.id.opt_2)
        val opt3: TextView = view.findViewById(R.id.opt_3)
        val opt4: TextView = view.findViewById(R.id.opt_4)
        opt1.setOnClickListener {

            selectedOptionStyle(opt1, 1)
        }
        opt2.setOnClickListener {

            selectedOptionStyle(opt2, 2)
        }
        opt3.setOnClickListener {

            selectedOptionStyle(opt3, 3)
        }
        opt4.setOnClickListener {

            selectedOptionStyle(opt4, 4)
        }
        val submit: Button = view.findViewById(R.id.submit)
        submit.setOnClickListener {
            if (selectedOption != 0) {
                val question = questionList!![currentPosition - 1]
                if (selectedOption != question.correct_ans) {
                    setColor(selectedOption, R.drawable.wrong_question_option, view)
                }else{
                    score++
                }
                setColor(question.correct_ans,R.drawable.correct_question_option, view)
                if(currentPosition==questionList!!.size)
                   submit.text= "FINISH"
                else
                    submit.text= "GO TO NEXT"
            }else{
                currentPosition++
                when{
                    currentPosition<=questionList!!.size->{
                        setQuestion(view)
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

        private fun setColor(opt: Int, color: Int, view: View) {
            when (opt) {
                1 -> {
                    val opt1: TextView = view.findViewById(R.id.opt_1)
                    opt1.background = ContextCompat.getDrawable(requireContext(), color)
                }
                2 -> {
                    val opt2: TextView = view.findViewById(R.id.opt_2)
                    opt2.background = ContextCompat.getDrawable(requireContext(), color)
                }
                3 -> {
                    val opt3: TextView = view.findViewById(R.id.opt_3)
                    opt3.background = ContextCompat.getDrawable(requireContext(), color)
                }
                4 -> {
                    val opt4: TextView = view.findViewById(R.id.opt_4)
                    opt4.background = ContextCompat.getDrawable(requireContext(), color)
                }
            }
        }




    private fun setQuestion(view: View) {
        val question = questionList!![currentPosition - 1]

        setOptionStyle(view)
        val opt1: TextView = view.findViewById(R.id.opt_1)
        val opt2: TextView = view.findViewById(R.id.opt_2)
        val opt3: TextView = view.findViewById(R.id.opt_3)
        val opt4: TextView = view.findViewById(R.id.opt_4)
        val timeBar: ProgressBar = view.findViewById(R.id.timeBar)
        val progressText: TextView = view.findViewById(R.id.progress_text)
        val quiz: TextView = view.findViewById(R.id.quiz)

        timeBar.progress = currentPosition
        timeBar.max = questionList!!.size
        progressText.text = "${currentPosition}" + "/" + "${questionList!!.size}"
        quiz.text = question.question
        opt1.text = question.option_one
        opt2.text = question.option_tw0
        opt3.text = question.option_three
        opt4.text = question.option_four
    }

    private fun setOptionStyle(view: View) {
        val opt1: TextView = view.findViewById(R.id.opt_1)
        val opt2: TextView = view.findViewById(R.id.opt_2)
        val opt3: TextView = view.findViewById(R.id.opt_3)
        val opt4: TextView = view.findViewById(R.id.opt_4)

        val optionList: ArrayList<TextView> = arrayListOf()
        optionList.add(0, opt1)
        optionList.add(1, opt2)
        optionList.add(2, opt3)
        optionList.add(3, opt4)

        for (op in optionList) {
            op.setTextColor(Color.parseColor("#555151"))
            op.background = ContextCompat.getDrawable(requireContext(), R.drawable.question_option)
            op.typeface = Typeface.DEFAULT
        }
    }

    private fun selectedOptionStyle(view: TextView, opt: Int) {

//        setOptionStyle(view)
        selectedOption = opt
        view.background = ContextCompat.getDrawable(requireContext(), R.drawable.selected_question_option)
        view.typeface = Typeface.DEFAULT_BOLD
        view.setTextColor(Color.parseColor("#000000"))

    }

}


