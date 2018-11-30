var Web3 = require('web3')
var express = require('express')
var bodyParser = require('body-parser');
var app = express()
var fs = require('fs')
var server = require('http').createServer(app)
var net = require('net')
var web3 = new Web3(new Web3.providers.HttpProvider('http://localhost:8545'))


app.use(bodyParser.urlencoded({
    extended: true
}));

web3.eth.personal.unlockAccount("0xc18144a148a19b66b9ffc6a7cbfff92deb2ffcc0", "", 600)
    .then(function () {

        web3.eth.sendTransaction({
                from: "0xc18144a148a19b66b9ffc6a7cbfff92deb2ffcc0",
                to: "0xe64a0d68b2e87541964bac5496a739d83fb67376",
                value: "1"
                // gas: "1",
            })
            .on('transactionHash', function (hash) {
            })
    });