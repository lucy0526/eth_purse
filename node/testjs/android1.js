var Web3 = require('web3')
var express = require('express')
var app = express()
var fs = require('fs')
var server = require('http').createServer(app)
var net = require('net')

app.get('/accountList', function(req, res){
    res.send(JSON.stringify("result"))
    console.log('hello')
})

server.listen(8080)