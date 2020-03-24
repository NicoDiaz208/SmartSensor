var mqtt = require('mqtt');

var options = {
    port: 1883,
    host: 'localhost'
}

var topic = '#';

var client = mqtt.connect(options);

client.subscribe('#',{qos:1});

client.publish('nico/raspberry','on')

client.on('message', function(topic, message, packet) {
    console.log('Message received From Topic: '+ topic +': '+message);
    
  });

client.on('connect', function(){
    console.log('Connected');
})
