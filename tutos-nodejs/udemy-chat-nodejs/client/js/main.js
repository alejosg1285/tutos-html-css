var socket = io.connect('http://192.168.0.101:6677', {'forceNew': true});

socket.on('messages', function(data) {
  console.log(data);
  render(data);
});

function render(data)
{
  var html = data.map(function(message, index) {
    return (`<div class='message'>
      <strong>${message.nickname}</strong>
      <p>${message.text}</p>
    </div>`);
  }).join(' ');

  var div_msg = document.getElementById('messages');
  div_msg.innerHTML = html;
  div_msg.scrollTop = div_msg.scrollHeight;

}

function addMessage(e)
{
  var message = {
    nickname: document.getElementById('message').value,
    text: document.getElementById('text').value
  };

  document.getElementById('text').style.display = 'none';
  socket.emit('add-message', message);

  return false;
}
