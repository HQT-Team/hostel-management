function receiveWebsocket(callback) {
    const websocket = new WebSocket("ws://localhost:8080/HappyHostel/push-noti-websocket");
    websocket.onmessage = function(message) { processMessage(message); };
    websocket.onclose

    function processMessage(message) {
        callback({
            message: message.data,
            duration: 5000,
        });
    };
};

receiveWebsocket.disconnectWebSocket = () => {
    websocket && websocket.close();
};
