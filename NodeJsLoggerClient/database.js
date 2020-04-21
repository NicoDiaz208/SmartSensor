const MongoClient = require('mongodb').MongoClient;
const url = "mongodb://localhost";
const dbName = "sensor"
var db;
var data;




MongoClient.connect(url, function(err, client) {
	if (err) throw err;
	console.log("Database Connection established!");
	// Gets the Database by name (sensor)
	db = client.db(dbName);
	// Creates a connection to the collection named 'data'
	data = db.collection('data');
})


exports.sendDataToRest = function(req,res) {
	if(req.query.topic){
		var topic = req.query.topic.replace(/"/g,"");
		var query = {"Topic": {$regex: topic}};
		data.find(query).toArray((err, result) => {
			res.send(result);
			if (err) throw err;
			console.log('Send data from Topic: '+ topic);
		}); 
	}
	else{
		data.find({}).toArray((err, result) => {
			if (err) throw err;
			res.send(result);
			console.log('Send all Data');
		}); 
	}
	return data;
}

/*exports.getData = function(req) {
var topic = req.query.topic.replace(/"/g,"");
	var query = {"Topic": {$regex: topic}};
	returndata.find(query).toArray((err, result) => {
		res.send(result);
		if (err) throw err;
		console.log('Send data from Topic: '+ topic);
	}); 
}*/


exports.sendData = function(topic, message){
	data.insertOne({
		Timestamp: Date.now(),
		Topic: topic,
		Message: message.toString()
		},
		(err, datas) => {
			if(err){
				throw err;
		}
		});
}