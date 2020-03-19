var mqtt = require('mqtt');
var input = process.stdin;

input.setEncoding('utf-8');

var options = {
    port: 1883,
    host: 'localhost'
}

var client = mqtt.connect(options);

client.on('message', function(topic, message, packet) {
    console.log('Message received From Topic: '+ topic +': '+message);
    
  });

client.on('connect', function(){
    console.log('Connected');
})

input.on('data', function(data){
    var str = data.toString();
    client.publish('NodeJsClient',str.slice(0,-2))
})
