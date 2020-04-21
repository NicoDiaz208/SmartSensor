const mqtt = require('mqtt');
const options = {
    port: 1883,
    host: 'broker.mqttdashboard.com'
}
const client = mqtt.connect(options);
const express = require('express'),
app = express(),
port = 9020;
const cors = require('cors')
app.use(cors())
database = require('./database.js')


// Subscribes to all Topics
client.subscribe('Htl-Leonding2020NVS/#');

// If the client receives data it will be inserted into the mongodb
client.on('message', function(topic, message, packet) {
	console.log("Topic: " + topic.toString());
	console.log("Message: " + message.toString());
	database.sendData(topic,message);
});


// Sets the port the app will listen to (3000)
app.listen(port);
console.log('app listening on port: '+port);


// Gets all the data in the database
app.get('/getAll', function (req, res) {
	database.sendDataToRest(req,res);
});

// Gets all the data in the database from the requested topic
app.get('/getTopic/', function (req, res) {
	database.sendDataToRest(req,res);	
});  