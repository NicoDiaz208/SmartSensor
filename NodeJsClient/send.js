var mqtt = require('mqtt');
var input = process.stdin;

input.setEncoding('utf-8');

var options = {
    port: 1883,
    host: 'broker.mqttdashboard.com'
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
    client.publish('Htl-Leonding2020NVS/SmartHome/Test',str.slice(0,-2))
})
