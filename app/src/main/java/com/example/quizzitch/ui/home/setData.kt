package com.example.quizzitch.ui.home

object setData {

    const val score:String="score"

    fun getQuestion():ArrayList<QuestionData>{
        var que:ArrayList<QuestionData> = arrayListOf()

        var question1 = QuestionData(
            1,
            "Q.1. Who discovered Penicillin?",
            "Louis Pasteur",
            "Marie Curie",
            "Alfred Nobel",
            "Alexander Fleming",
            4
        )

        var question2 = QuestionData(
            2,
            "Q.2. Who was the Prime Minister of Japan when Japan declared war on the US?",
            "Michinomiya Hirohito",
            "Isoroku Yamamoto",
            "Fumimaro Konoe",
            "Hideki Tojo",
            4
            )

        var question3 = QuestionData(
            3,
            "Q.3. Who was the first prime minister of Canada?",
            "John Abbott",
            "John Macdonald",
            "Alexander Mackenzie",
            "Robert Borden",
            2
            )
        var question4 = QuestionData(
            4,
            "Q.4. What is the historical name of Sri Lanka?",
            "Myanmar",
            "Colombo",
            "Ceylon",
            "Badulla",
            3
            )
        var question5 = QuestionData(
            5,
            "Q.5. To what political party did Abraham Lincoln belong when elected POTUS?",
            "Whig",
            "Democratic",
            "Republican",
            "Independent",
            2
            )
        var question6 = QuestionData(
            6,
            "Q.6. In what year was the M1911 pistol designed?",
            "1911",
            "1907",
            "1899",
            "1917",
            1
            )
        var question7 = QuestionData(
            7,
            "Q.7. What was the name commonly given to the ancient trade routes that connected the East and West of Eurasia?",
            "Spice Road",
            "Clay Road",
            "Salt Road",
            "Silk Road",
            1
            )
        var question8 = QuestionData(
            8,
            "Q.8. How many manned moon landings have there been?",
            "6",
            "5",
            "7",
            "8",
            1
            )
        var question9 = QuestionData(
            9,
            "Q.9. Who was the first president of the United States?",
            "James Madison",
            "Thomas Jefferson",
            "James K. Polk",
            "George Washington",
            4
        )
        var question10 = QuestionData(
            10,
            "Q.10. Which of the following was Brazil was a former colony under?",
            "Portugal",
            "Spain",
            "The Netherlands",
            "France",
            1
        )

        que.add(question1)
        que.add(question2)
        que.add(question3)
        que.add(question4)
        que.add(question5)
        que.add(question6)
        que.add(question7)
        que.add(question8)
        que.add(question9)
        que.add(question10)

        return que
    }
}