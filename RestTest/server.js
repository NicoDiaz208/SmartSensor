var express = require('express'),
app = express(),
port = 3000;
const cors = require('cors')
const bodyParser = require('body-parser')
app.use(cors())
app.use(bodyParser.text())


var data = [
{
	timestamp: 1583842622334,
	topic: 'SmartHome/Light',
	message: 'on'
},
{
	timestamp: 1583842622350,
	topic: 'SmartHome/Light',
	message: 'off'
}
,
{
	timestamp: 1583842622400,
	topic: 'SmartHome/Temperatur',
	message: '30'
}
]


app.get('/getAll', function (req, res) {
  res.send(data);
});
app.get('/getTopic', function (req, res) {
  res.send(data.filter(d => d.topic === req.body.replace(/"/g,"")));
});

app.listen(port);
console.log('todo list RESTful API server started on: ' + port);