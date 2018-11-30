var Web3 = require('web3')
var express = require('express')
var app = express()
var fs = require('fs')
var server = require('http').createServer(app)
var net = require('net')
var web3 = new Web3(new Web3.providers.HttpProvider('http://localhost:8545'))

//eth 额外方法的检测
web3.eth.getAccounts(async function (error, result) {

    if (!error) {

        var resJson = []
        for (var i = 0; i < result.length; i++) {
            var account = {}
            var address = result[i]
            
            account.addressHexString = address         
            
            balance = await web3.eth.getBalance(address)
            account.balance = web3.utils.fromWei(balance)
            resJson.push(account)
        }
        console.log(resJson);

    } else {
        console.log(JSON.stringify(error))
    }
})