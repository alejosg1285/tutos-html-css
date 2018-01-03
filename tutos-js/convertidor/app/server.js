import express from 'express';
import converter from '..';

var app = express();

app.get('/toCelsius', function(req, res){
  var temperature = req.query.temperature;
  res.send(200, converter.toCelsius(temperature));
});

app.get('/toFahrenheit', function(req, res){
  var temperature = req.query.temperature;
  res.send(200, converter.toFahrenheit(temperature));
});

app.get('/', (req, res) => {
  res.status(200).send(joey.friendsList());
});

class Person
{
  constructor(name, friends)
  {
    this.name = name;
    this.friends = friends;
  }

  friendsList()
  {
    var str = `Los amigos de ${this.name} son: ${this.friends.join(', ')}`;
    console.log(str);
    return str;
  }
}

var joey = new Person('Joey', ['Chandler', 'Monica', 'Phoebe', 'Rachel', 'Ross']);

app.listen(3000);
