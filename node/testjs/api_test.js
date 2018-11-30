var Web3 = require('web3')
var express = require('express')
var app = express()
var fs = require('fs')
var server = require('http').createServer(app)
var net = require('net')
var web3 = new Web3(new Web3.providers.HttpProvider('http://localhost:8545'))

/* web3.eth.getAccounts(function (error, result) {
    
    if (!error) {
        var flag = false

        var resJson = []
        var account = {}
        for (var i = 0; i < result.length; i++) {
            var address = result[i]

            web3.eth.getBalance(address)
                .then(function (balance) {

                    account.addressHexString = address
                    account.balance = web3.utils.fromWei(balance)
                    resJson.push(account)

                    if (resJson.length == result.length) {
                        flag = true
                    }

                })
            }
            
            console.log(flag);

    } else {
        console.log(JSON.stringify(error))
    }
}) */

//eth 可用性检测
web3.eth.personal.newAccount('!@superpassword')
.then(function(address){
    console.log(address)
});