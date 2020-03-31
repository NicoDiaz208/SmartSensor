var mqtt = require('mqtt');

var options = {
    port: 1883,
    host: 'broker.mqttdashboard.com'
}

var topic = '#';

var client = mqtt.connect(options);

client.subscribe('Htl-Leonding2020NVS/SmartHome/Livingroom/Light',{qos:1});

client.publish('Htl-Leonding2020NVS/SmartHome/Livingroom/Light','on')

client.on('message', function(topic, message, packet) {
    console.log('Message received From Topic: '+ topic +': '+message);
    
  });

client.on('connect', function(){
    console.log('Connected');
})
