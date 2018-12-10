var express = require('express')
var router = express.Router()


router.get('/accountList', function (req, res) {
    web3.eth.getAccounts(async function (error, result) {
        if (!error) {
            var resJson = []
            for (var i = 0; i < result.length; i++) {
                var account = {}
                var address = result[i]

                balance = await web3.eth.getBalance(address)
                account.addressHexString = address
                account.balance = web3.utils.fromWei(balance)
                resJson.push(account)
            }
            res.send(JSON.stringify(resJson))
        } else
            console.log(JSON.stringify(error))
    })

})
router.post('/addAccount', function (req, res) {
    var password = req.body.password;
    web3.eth.personal.newAccount(password)
        .then(function (address) {
            res.send(address)
        });

})
router.post('/submitTransaction', function (req, res) {
    console.log('1')
    var from = req.body.from;
    var to = req.body.to;
    var value = req.body.value;
    var gas = req.body.gas;
    var password = req.body.password;

    web3.eth.personal.unlockAccount(from, null, 600)
        .then(function () {
            console.log('2')

            web3.eth.sendTransaction({
                    from: from,
                    to: to,
                    value: value
                    /* ,
                                gas:gas */
                })
                .on('transactionHash', function (hash) {
                    res.send(hash)
                })
        });
})

router.post('/historyList', function (req, res) {
    var account = req.body.account;
    console.log(account)
})

module.exports = router