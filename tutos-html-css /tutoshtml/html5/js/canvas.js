function makeCanvas()
{
  //Canvas #1
  var canvas1 = document.getElementById("canvas1");
  var context1 = canvas1.getContext('2d');

  context1.font = '28pt Arial';
  context1.fillStyle = 'DeepSkyBlue';
  context1.strokeStyle = 'black';
  context1.fillText('Mucho para aprender y poco timepo', 25, 135);
  context1.strokeText('Mucho para aprender y poco timepo', 25, 135);

  //Canvas #2.
  var canvas2 = document.getElementById('canvas2');
  var context2 = canvas2.getContext('2d');

  context2.fillStyle = "#ff0000";
  context2.strokeStyle = '#000000';
  context2.lineWidth = 10;

  context2.fillRect(45, 5, 135, 135);
  context2.strokeRect(45, 5, 135, 135);

  context2.fillRect(200, 150, 135, 135);
  context2.strokeRect(200, 150, 135, 135);

  context2.fillStyle = '#666666';
  context2.fillRect(200, 5, 135, 135);

  context2.fillRect(45, 150, 135, 135);

  //Canvas #3.
  var canvas3 = document.getElementById('canvas3');
  var context3 = canvas3.getContext('2d');

  context3.strokeFill = '#666666';
  context3.fillStyle = '#ff0000';
  context3.lineWidth = 5;

  context3.beginPath();
  context3.moveTo(100, 100);
  context3.lineTo(150, 200);
  context3.lineTo(200, 200);
  context3.lineTo(200, 290);
  context3.lineTo(290, 290);
  context3.lineTo(290, 100);
  context3.lineTo(100, 100);
  context3.stroke();
  context3.fill();
  context3.closePath();

  //Canvas #4.
  var canvas4 = document.getElementById('canvas4');
  var context4 = canvas4.getContext('2d');

  context4.fillStyle = 'blue';

  context4.beginPath();
  context4.arc(200, 30, 25, 0, Math.PI * 2);
  context4.fill();
  context4.closePath();

  context4.fillStyle = '#ff0000';

  context4.beginPath();
  context4.arc(200, 100, 45, 0, Math.PI * 2);
  context4.fill();
  context4.closePath();

  context4.fillStyle = '#000000';

  context4.beginPath();
  context4.arc(200, 220, 75, 0, Math.PI * 2);
  context4.fill();
  context4.closePath();

  //Canvas #5.
  var canvas5 = document.getElementById('canvas5');
  var context5 = canvas5.getContext('2d');

  var postY = 0;
  var postX = 0;
  setInterval(function(){
    postX += 1;
    postX += 1
    context5.fillStyle = '#000000';
    context5.fillRect(0, 0, canvas5.width, canvas5.height);

    context5.fillStyle = '#fff';
    context5.beginPath()
    context5.arc(postX, 170, 55, 0, Math.PI * 2);
    context5.fill();
    context5.closePath();

    context5.fillStyle = '#ff0000';
    context5.beginPath()
    context5.arc(150, postX, 55, 0, Math.PI * 2);
    context5.fill();
    context5.closePath();
  }, 30);
}
