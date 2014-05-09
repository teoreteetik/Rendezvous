var ws = new WebSocket("wss://ec2-54-213-178-112.us-west-2.compute.amazonaws.com/back/topicsocket");
ws.onmessage = function (evt)
{
  var received_msg = evt.data;
  var li = document.createElement("li");
  li.innerHTML = received_msg;

  document.getElementById("messages").appendChild(li);

};