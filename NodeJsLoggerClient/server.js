const mqtt = require('mqtt');
const options = {
    port: 1883,
    host: 'broker.mqttdashboard.com'
}
const client = mqtt.connect(options);
const MongoClient = require('mongodb').MongoClient;
const url = "mongodb://localhost";
const dbName = "sensor"
var db;
var data;
const express = require('express'),
app = express(),
port = 3000;
const cors = require('cors')
app.use(cors())


// Subscribes to all Topics
client.subscribe('Htl-Leonding2020NVS/#');

// If the client receives data it will be inserted into the mongodb
client.on('message', function(topic, message, packet) {
	console.log(message.toString());
	data.insert({
		Timestamp: Date.now(),
		Topic: topic,
		Message: message.toString()
		},
		(err, datas) => {
			if(err){
				throw err;
		}
	});
});


// Connects to the MongoClient
MongoClient.connect(url, function(err, client) {
	if (err) throw err;
	console.log("Database Connection established!");
	// Gets the Database by name (sensor)
	db = client.db(dbName);
	// Creates a connection to the collection named 'data'
	data = db.collection('data');
})






// Sets the port the app will listen to (3000)
app.listen(port);
console.log('app listening on port: '+port);


// Gets all the data in the database
app.get('/getAll', function (req, res) {
	data.find({}).toArray((err, result) => {
		if (err) throw err;
		res.send(result);
		console.log('Send all Data');
  }); 
});

// Gets all the data in the database from the requested topic
app.get('/getTopic/', function (req, res) {
	var query = {"Topic": req.query.topic.replace(/"/g,"")};
	data.find(query).toArray((err, result) => {
		res.send(result);
		if (err) throw err;
		console.log('Send data from Topic: '+req.query.topic.replace(/"/g,""));
  }); 
});  