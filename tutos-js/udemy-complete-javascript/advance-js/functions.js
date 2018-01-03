// Receive a function as parameter.
var years = [1979, 1980, 1985];

function arrCalc(arr, fn) {
    var arrRes = [];
    var ln = arr.length;

    for (var i = 0; i < ln; i++) {
        arrRes.push(fn(arr[i]));
    }

    return arrRes;
}

function calculataAge(el) {
    return 2017 - el;
}

function isFullAges(el) {
    return el >= 18;
}

function maxHeartRate(el) {
    if (el >= 18 && el <= 81) {
        return Math.round(206.9 - (0.67 * el));
    } else {
        return -1;
    }
}

var ages = arrCalc(years, calculataAge);
console.log(ages);
var fullAges = arrCalc(ages, isFullAges);
console.log(fullAges);
var rates = arrCalc(ages, maxHeartRate);
console.log(rates);

// Function that return a function.
function interviewQuestion(job) {
    if (job === 'designer') {
        return function (name) {
            console.log(name + ', can you please explain what ux design is?');
        };
    } else if (job === 'teacher') {
        return function (name) {
            console.log('what subject do you teach, ' + name + '?');
        };
    } else {
        return function (name) {
            console.log('hi, ' + name + ' what do you do?');
        }
    }
}

var teacherQuestion = interviewQuestion('teacher');
teacherQuestion('Maria');
interviewQuestion('designer')('Andrea');

//inmediatelly invoke function expression IIFE
(function () {
    var score = Math.random() * 10;
    console.log(score >= 5);
})();
(function (goodLuck) {
    var score = Math.random() * 10;
    console.log(score >= 5 - goodLuck);
})(3);

// Closure
function interviewClosure(job) {
    return function (name) {
        if (job === 'designer') {
            console.log(name + ', can you please explain what ux design is?');
        } else if (job === 'teacher') {
            console.log('what subject do you teach, ' + name + '?');
        } else {
            console.log('hi, ' + name + ' what do you do?');
        }
    }
}

interviewClosure('designer')('Orion');

// Call, bind and apply
var onix = {
    name: 'Onix',
    age: 4,
    job: 'lawyer',
    presentation: function (style, timeOfDay) {
        if (style === 'formal') {
            console.log('Good ' + timeOfDay + ', ladies and gentlement! I\'m ' + this.name + ', I\'m a ' + this.job + ' and I\'m ' + this.age + ' years old.');
        } else if (style === 'friendly') {
            console.log('Hey! what \'s up? I\'m ' + this.name + ', I\'m a ' + this.job + ' and I\'m ' + this.age + ' years old. Have a nice ' + timeOfDay + '.');
        }
    }
};
onix.presentation('formal', 'morning');

var perla = {
    name: 'Perla',
    age: '2',
    job: 'Teacher'
};
onix.presentation.call(perla, 'friendly', 'afternoon');

var onixFriendly = onix.presentation.bind(onix);
onixFriendly('friendly', 'night');
onixFriendly('formal', 'night');

function isFullAgesLimit(limit, el) {
    return el >= limit;
}
var fullJapan = arrCalc(ages, isFullAgesLimit.bind(this, 20));
console.log(fullJapan);
