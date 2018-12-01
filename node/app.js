var Web3 = require('web3')
var express = require('express')
var bodyParser = require('body-parser');
var app = express()
var fs = require('fs')
var server = require('http').createServer(app)
var net = require('net')
var web3 = new Web3(new Web3.providers.HttpProvider('http://localhost:8545'))

var router = require('./router')

app.use(bodyParser.urlencoded({
    extended: true
}));
app.use(bodyParser.json())

app.use(router)

server.listen(8080)