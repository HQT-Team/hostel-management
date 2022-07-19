const websocket = new WebSocket("ws://localhost:8080/HappyHostel/push-noti-websocket");
function sendMessage(message) {
    // let i = 0;
    // do {
    //     if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
    //         websocket.send(message);
    //     }
    // } while (websocket.readyState != WebSocket.OPEN)
    setTimeout(() => {
            if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
                websocket.send(message);
            }
    }, 1000)
}

function sendToWebSocket({ sender, receiver, hostel_receiver_id = null, account_receiver_id = null, messages }){
    const message = `{"sender":"${sender}","receiver":"${receiver}","hostel_receiver_id":"${hostel_receiver_id}","account_receiver_id":"${account_receiver_id}","message":"${messages}"}`;
    console.log(sender, receiver, hostel_receiver_id, account_receiver_id, messages, message)
    sendMessage(message);
}