(function () {
    function Question(question, answers, correct) {
        this.question = question;
        this.answers = answers;
        this.correct = correct;
    }

    Question.prototype.displayQuestion = function () {
        console.log(this.question);

        var ln = this.answers.length;
        for (var i = 0; i < ln; i++) {
            console.log(i + '. ' + this.answers[i]);
        }
    }

    Question.prototype.checkAnswer = function (ans, callback) {
        var sc;

        if (ans === this.correct) {
            console.log('Correct answer');
            sc = callback(true);
        } else {
            console.log('Wrong answer, try again.');
            sc = callback(false);
        }

        this.displayScore(sc);
    }

    Question.prototype.displayScore = function (score) {
        console.log('Your current score is: ' + score);
        console.log('-------------------------------');
    }

    var q1 = new Question('Superman is from planet Krypton?', ['Yes', 'No'], 0);
    var q2 = new Question('What is the Superman real name?', ['Jor-El', 'Kara', 'Kal-El'], 2);
    var q3 = new Question('What villain get to kill Superman?', ['Lex Luthor', 'Bizarre', 'Doomsday', 'Zod'], 2);

    var questions = [q1, q2, q3];

    function score() {
        var sc = 0;

        return function (correct) {
            if (correct) {
                sc++;
            }

            return sc;
        }
    }

    var keepScore = score();

    function nextQuestion() {
        var n = Math.floor(Math.random() * questions.length);

        questions[n].displayQuestion();

        var answer = prompt('Please select the correct answer');

        if (answer !== 'exit') {
            questions[n].checkAnswer(parseInt(answer), keepScore);

            nextQuestion();
        }
    }

    nextQuestion();
})();
