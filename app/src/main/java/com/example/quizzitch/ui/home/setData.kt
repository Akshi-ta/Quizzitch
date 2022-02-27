package com.example.quizzitch.ui.home

object setData {


    const val score:String="score"

    fun eHistory():ArrayList<QuestionData>{
        val que:ArrayList<QuestionData> = arrayListOf()

        val question1 = QuestionData(
            1, "Q.1. Who discovered Penicillin?", "Louis Pasteur", "Marie Curie", "Alfred Nobel", "Alexander Fleming", 4
        )

        val question2 = QuestionData(
            2, "Q.2. Who was the Prime Minister of Japan when Japan declared war on the US?", "Michinomiya Hirohito", "Isoroku Yamamoto", "Fumimaro Konoe", "Hideki Tojo", 4
            )

        val question3 = QuestionData(
            3, "Q.3. Who was the first prime minister of Canada?", "John Abbott", "John Macdonald", "Alexander Mackenzie", "Robert Borden", 2
            )
        val question4 = QuestionData(
            4, "Q.4. What is the historical name of Sri Lanka?", "Myanmar", "Colombo", "Ceylon", "Badulla", 3
            )
        val question5 = QuestionData(
            5, "Q.5. To what political party did Abraham Lincoln belong when elected POTUS?", "Whig", "Democratic", "Republican", "Independent", 2
            )
        val question6 = QuestionData(
            6, "Q.6. In what year was the M1911 pistol designed?", "1911", "1907", "1899", "1917", 1
            )
        val question7 = QuestionData(
            7, "Q.7. What was the name commonly given to the ancient trade routes that connected the East and West of Eurasia?", "Spice Road", "Clay Road", "Salt Road", "Silk Road", 1
            )
        val question8 = QuestionData(
            8, "Q.8. How many manned moon landings have there been?", "6", "5", "7", "8", 1
            )
        val question9 = QuestionData(
            9, "Q.9. Who was the first president of the United States?", "James Madison", "Thomas Jefferson", "James K. Polk", "George Washington", 4
        )
        val question10 = QuestionData(
            10, "Q.10. Which of the following was Brazil was a former colony under?", "Portugal", "Spain", "The Netherlands", "France", 1
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

    fun aHistory():ArrayList<QuestionData>{
        var que:ArrayList<QuestionData> = arrayListOf()

        var question1 = QuestionData(
            1, "In what year was the last natural case of smallpox documented?", "1977", "1982", "1980", "1990", 1
        )

        var question2 = QuestionData(
            2, "Q.2. Which of these countries was NOT a part of the Soviet Union?", "Turkmenistan", "Afghanistan", "Kazakhstan", "Uzbekistan", 2
        )

        var question3 = QuestionData(
            3, "Q.3. When did the Battle of the Bulge end?", "August 6, 1945", "December 7, 1941", "December 16, 1944", "January 25 1945", 4
        )
        var question4 = QuestionData(
            4, "Q.4. What is the mnemonic device for remembering the fates of the wives of Henry VIII ?", "Divorced, Beheaded, Died, Divorced, Beheaded, Survived", "Beheaded, Died, Divorced, Divorced, Beheaded, Survived", "Died, Beheaded, Divorced, Beheaded, Survived, Divorced", "Survived, Beheaded, Died, Divorced, Divorced, Beheaded", 1
        )
        var question5 = QuestionData(
            5, "Q.5. What year is considered to be the year that the British Empire ended ?", "1986", "1981", "1971", "1997", 4
        )
        var question6 = QuestionData(
            6, "Q.6.In World War I, what was the name of the alliance of Germany, Austria-Hungary, the Ottoman Empire, and Bulgaria ?", "The Central Powers", "The Axis Powers", "The Federation of Empires", "Authoritarian Alliance", 1
        )
        var question7 = QuestionData(
            7, "Q.7. Which country was an allied power in World War II ?", "Soviet Union", "Italy", "Germany", "Japan", 1
        )
        var question8 = QuestionData(
            8, "Q.8. Which of the following is not in the Indo-European language family ?", "English", "Finnish", "Hindi", "Russian", 2
        )
        var question9 = QuestionData(
            9, "Q.9. Who assassinated Archduke Franz Ferdinand ?", "Nedeljko Čabrinović", "Oskar Potiorek", "Ferdinand Cohen-Blind", "Gavrillo Princip", 4
        )
        var question10 = QuestionData(
            10, "Which of these founding fathers of the United States of America later became president ?", "Samuel Adams", "Alexander Hamilton", "James Monroe", "Roger Sherman", 3
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

    fun hHistory(): ArrayList<QuestionData>{

            var que:ArrayList<QuestionData> = arrayListOf()

            var question1 = QuestionData(
                1, "Q.1. On which day did ARPANET suffer a 4 hour long network crash?", "October 27, 1980", "November 21, 1969", "October 29, 1969", "December 9, 1991", 1
            )

            var question2 = QuestionData(
                2, "Q.2. When was the SS or Schutzstaffel established?", "February 21st, 1926", "September 1st, 1941", "April 4th, 1925", "March 8th, 1935", 3
            )

            var question3 = QuestionData(
                3, "Q.3. What year was the United States Declaration of Independence signed?", "1775", "1774", "1777", "1776", 4
            )
            var question4 = QuestionData(
                4, "Q.4. What was Napoleon Bonaparte's name before he changed it ?", "Napoleone di Buonaparte", "Naapolion van Bonijpaart", "Napole&atilde;o do Boaparte", "Napoleona de Buenoparte", 1
            )
            var question5 = QuestionData(
                5, "Q.5. The Battle of Hastings was fought in which year ?", "1066", "911", "1204", "1420", 1
            )
            var question6 = QuestionData(
                6, "Q.6.When was the city of Rome, Italy founded ?", "902 BCE", "524 BCE", "753 BCE", "697 BCE", 3
            )
            var question7 = QuestionData(
                7, "Q.7. How long did the Warsaw Uprising during World War II last ?", "63 Days", "20 Days", "55 Days", "224 Days", 1
            )
            var question8 = QuestionData(
                8, "Q.8. What did the abbreviation &quot;RMS&quot; stand for in the RMS Titanic in 1912 ?", "Royal Majesty Service", "Royal Mail Ship", "Regular Maritime Schedule", "Regulated Maelstrom Sensor", 2
            )
            var question9 = QuestionData(
                9, "Q.9. What was the last colony the UK ceded marking the end of the British Empire ?", "India", "Australia", "Ireland", "Hong Kong", 4
            )
            var question10 = QuestionData(
                10, "From 1940 to 1942, what was the capital-in-exile of Free France ?", "Algiers", "Paris", "Brazzaville", "Tunis", 3
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

   fun ePolitics(): ArrayList<QuestionData>{

           var que:ArrayList<QuestionData> = arrayListOf()

           var question1 = QuestionData(
               1, "Q.1. Who was the British Prime Minister at the outbreak of the Second World War?", "Clement Attlee", "Winston Churchill", "Stanley Baldwin","Neville Chamberlain" ,4
           )

           var question2 = QuestionData(
               2, "Q.2. Which president erased the national debt of the United States?", "Turkmenistan", "Ronald Reagan", "Andrew Jackson", "John F. Kennedy", 2
           )

           var question3 = QuestionData(
               3, "Q.3. Who was the only president to not be in office in Washington D.C?", "George Washington", "Abraham Lincoln", "Richard Nixon", "Thomas Jefferson", 1
           )
           var question4 = QuestionData(
               4, "Q.4. Due to the Nagoya Resolution, China agreed to allow Taiwan to compete separately in international sporting events under what name ?", "Chine Taipei","Chinese Taiwan", "Republic of Taiwan", "Republic of Taipei ", 1
           )
           var question5 = QuestionData(
               5, "Q.5. Who was elected leader of the UK Labour Party in September 2015 ?", "Jeremy Corbyn", "Ed Miliband", "David Cameron", "Theresa May", 4
           )
           var question6 = QuestionData(
               6, "Q.6. Who was the 40th President of the USA ?", "Ronald Reagan",         "Jimmy Carter", "Bill Clinton", "Richard Nixon", 1
           )
           var question7 = QuestionData(
               7, "Q.7. The 2014 movie &quot;The Raid 2: Berandal&quot; was mainly filmed in which Asian country ?", "Indonesia",         "Thailand", "Brunei", "Malaysia", 1
           )
           var question8 = QuestionData(
               8, "Q.8. Which of the following is not in the Indo-European language family ?", "English", "Finnish", "Hindi", "Russian", 2
           )
           var question9 = QuestionData(
               9, "Q.9. Who assassinated Archduke Franz Ferdinand ?", "Nedeljko Čabrinović", "Oskar Potiorek", "Ferdinand Cohen-Blind", "Gavrillo Princip", 4
           )
           var question10 = QuestionData(
               10, "Which of these founding fathers of the United States of America later became president ?", "Samuel Adams", "Alexander Hamilton", "James Monroe", "Roger Sherman", 3
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

    fun aPolitics(): ArrayList<QuestionData> {
        val que:ArrayList<QuestionData> = arrayListOf()

        val question1 = QuestionData(
            1, "Q.1. Who discovered Penicillin?", "Louis Pasteur", "Marie Curie", "Alfred Nobel", "Alexander Fleming", 4
        )

        val question2 = QuestionData(
            2, "Q.2. Who was the Prime Minister of Japan when Japan declared war on the US?", "Michinomiya Hirohito", "Isoroku Yamamoto", "Fumimaro Konoe", "Hideki Tojo", 4
        )

        val question3 = QuestionData(
            3, "Q.3. Who was the first prime minister of Canada?", "John Abbott", "John Macdonald", "Alexander Mackenzie", "Robert Borden", 2
        )
        val question4 = QuestionData(
            4, "Q.4. What is the historical name of Sri Lanka?", "Myanmar", "Colombo", "Ceylon", "Badulla", 3
        )
        val question5 = QuestionData(
            5, "Q.5. To what political party did Abraham Lincoln belong when elected POTUS?", "Whig", "Democratic", "Republican", "Independent", 2
        )
        val question6 = QuestionData(
            6, "Q.6. In what year was the M1911 pistol designed?", "1911", "1907", "1899", "1917", 1
        )
        val question7 = QuestionData(
            7, "Q.7. What was the name commonly given to the ancient trade routes that connected the East and West of Eurasia?", "Spice Road", "Clay Road", "Salt Road", "Silk Road", 1
        )
        val question8 = QuestionData(
            8, "Q.8. How many manned moon landings have there been?", "6", "5", "7", "8", 1
        )
        val question9 = QuestionData(
            9, "Q.9. Who was the first president of the United States?", "James Madison", "Thomas Jefferson", "James K. Polk", "George Washington", 4
        )
        val question10 = QuestionData(
            10, "Q.10. Which of the following was Brazil was a former colony under?", "Portugal", "Spain", "The Netherlands", "France", 1
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

    fun hPolitics(): ArrayList<QuestionData> {
        val que:ArrayList<QuestionData> = arrayListOf()

        val question1 = QuestionData(
            1, "Q.1. Who discovered Penicillin?", "Louis Pasteur", "Marie Curie", "Alfred Nobel", "Alexander Fleming", 4
        )

        val question2 = QuestionData(
            2, "Q.2. Who was the Prime Minister of Japan when Japan declared war on the US?", "Michinomiya Hirohito", "Isoroku Yamamoto", "Fumimaro Konoe", "Hideki Tojo", 4
        )

        val question3 = QuestionData(
            3, "Q.3. Who was the first prime minister of Canada?", "John Abbott", "John Macdonald", "Alexander Mackenzie", "Robert Borden", 2
        )
        val question4 = QuestionData(
            4, "Q.4. What is the historical name of Sri Lanka?", "Myanmar", "Colombo", "Ceylon", "Badulla", 3
        )
        val question5 = QuestionData(
            5, "Q.5. To what political party did Abraham Lincoln belong when elected POTUS?", "Whig", "Democratic", "Republican", "Independent", 2
        )
        val question6 = QuestionData(
            6, "Q.6. In what year was the M1911 pistol designed?", "1911", "1907", "1899", "1917", 1
        )
        val question7 = QuestionData(
            7, "Q.7. What was the name commonly given to the ancient trade routes that connected the East and West of Eurasia?", "Spice Road", "Clay Road", "Salt Road", "Silk Road", 1
        )
        val question8 = QuestionData(
            8, "Q.8. How many manned moon landings have there been?", "6", "5", "7", "8", 1
        )
        val question9 = QuestionData(
            9, "Q.9. Who was the first president of the United States?", "James Madison", "Thomas Jefferson", "James K. Polk", "George Washington", 4
        )
        val question10 = QuestionData(
            10, "Q.10. Which of the following was Brazil was a former colony under?", "Portugal", "Spain", "The Netherlands", "France", 1
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