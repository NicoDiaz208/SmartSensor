var mqtt = require('mqtt');
var options = {
    port: 1883,
    host: 'localhost'
}

var client = mqtt.connect(options);

client.subscribe('#');

client.publish('#','HelloWorld')

client.on('message', function(topic, message) {
    console.log(message);
  });

client.on('connect', function(){
    console.log('Connected');
})