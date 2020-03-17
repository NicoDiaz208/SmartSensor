// MQTT
const mqtt = require('mqtt');
const options = {
    port: 1883,
    host: '172.17.217.90'
}
const client = mqtt.connect(options);






// Mongo DB
const MongoClient = require('mongodb').MongoClient;
// For Docker Toolbox
// const url = "mongodb://192.168.99.100:27017";

// For Docker Desktop
const url = "mongodb://localhost:27017";
const dbName = "sensor"
var db;
var data;





client.subscribe('#');

// sends data.
// client.publish('nico/raspberry','HelloWorld')

client.on('message', function(topic, message, packet) {
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

client.on('connect', function(){
    console.log('Connected');
})


MongoClient.connect(url, function(err, client) {
  if (err) throw err;
  console.log("Database Connection established!");

  db = client.db(dbName);
  
  data = db.collection('data');
  //GetAll
  data.find({}).toArray((err, result) => {
  if (err) throw err;

  console.log(result);
  });  
})
  