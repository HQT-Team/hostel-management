function receiveWebsocket(selector) {
    const element = document.querySelector(selector);

    const websocket = new WebSocket("ws://localhost:8080/HappyHostel/push-noti-websocket");
    websocket.onmessage = function(message) { processMessage(message); };
    websocket.onclose

    function processMessage(message) {
        console.log(message);
        element.value += message.data + " \n";
    }
}

receiveWebsocket.disconnectWebSocket = () => {
    websocket && websocket.close();
}
