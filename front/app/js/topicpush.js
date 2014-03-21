var ws = new WebSocket("wss://localhost:8443/back/topicsocket");
ws.onmessage = function (evt)
{
  var received_msg = evt.data;
  var li = document.createElement("li");
  li.innerHTML = received_msg;

  document.getElementById("messages").appendChild(li);

};