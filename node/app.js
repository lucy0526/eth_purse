var Web3 = require('web3')
var express = require('express')
var bodyParser = require('body-parser');
var app = express()
var fs = require('fs')
var server = require('http').createServer(app)
var net = require('net')
var path = require('path')
var web3 = new Web3(new Web3.providers.HttpProvider('http://localhost:8545'))

var router1 = require(path.join(__dirname, 'routers', "session_router.js"))
var router2 = require(path.join(__dirname, 'routers', "router.js"))

app.use(bodyParser.urlencoded({
    extended: true
}));
app.use(bodyParser.json())

app.use(router1)
app.use(router2)

server.listen(8080)