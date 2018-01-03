//Constructor function
var Person = function (name, yearOfBirth, job) {
    this.name = name
    this.yearOfBirth = yearOfBirth;
    this.job = job;

    /*this.calculateAge = function () {
        console.log(2017 - this.yearOfBirth);
    };*/
}

//Attach methods to construct prototype.
Person.prototype.calculateAge = function () {
    console.log(2017 - this.yearOfBirth);
};
//Attach properties to construct prototype
Person.prototype.lastName = 'Saa';

var oreo = new Person('Oreo', 2017, 'Kitty');
oreo.calculateAge();
var luna = new Person('Luna', 2001, 'Designer');
luna.calculateAge();
var onix = new Person('Onix', 2013, 'Architect');
onix.calculateAge();

console.log(oreo.lastName);
console.log(luna.lastName);
console.log(onix.lastName);

//Create object using Object.create
var personProto = {
    calculateAge: function () {
        console.log(2017 - this.yearOfBirth);
    }
};
var isma = Object.create(personProto);
isma.name = 'Isma';
isma.yearOfBirth = 2010;
isma.calculateAge();

var isis = Object.create(personProto, {
    name: {
        value: 'Isis'
    },
    yearOfBirth: {
        value: 2010
    },
    job: {
        value: 'Doctor'
    },
});
console.log(isis);
isis.calculateAge();
