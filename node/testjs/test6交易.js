const Web3 = require('web3')
var keythereum = require("keythereum");
const transaction = require('ethereumjs-tx');

if (typeof web3 !== 'undefined') {
    var web3 = new Web3(web3.currentProvider);
} else {
    var web3 = new Web3(new Web3.providers.HttpProvider("http://localhost:8545"));
}

function ethereumSign(privateKey, nonce, toAddress, sendToBalance, sendFee) {
    if (!privateKey || !nonce || !toAddress || !sendToBalance || !sendFee) {
        console.log("one of fromAddress, toAddress, sendToBalance, sendFee is null, please give a valid param");
    } else {
        console.log("param is valid, start sign transaction");
        var transactionNonce = parseInt(nonce).toString(16);
        console.log("transaction nonce is " + transactionNonce);
        // var numBalance = parseFloat(sendToBalance);
        // var balancetoWei = web3.utils.toWei(numBalance, "ether");
        var balancetoWei = web3.utils.toWei(sendToBalance, "ether");

        console.log("balancetoWei is " + balancetoWei);
        var oxNumBalance = parseInt(balancetoWei).toString(16);
        console.log("16 oxNumBalance is " + oxNumBalance);
        // var gasFee = parseFloat(sendFee);
        // var gasFeeToWei = web3.utils.toWei(gasFee, "ether");
        var gasFeeToWei = web3.utils.toWei(sendFee, "ether");

        console.log("gas fee to wei is " + gasFeeToWei);
        var gas = gasFeeToWei / 21000;
        console.log("param gas is " + gas);
        var oxgasFeeToWei = parseInt(gas).toString(16);
        console.log("16 oxgasFeeToWei is " + oxgasFeeToWei);
        var privateKeyBuffer = Buffer.from(privateKey, 'hex');
        var rawTx = {
            nonce: '0x' + transactionNonce,
            gasPrice: '0x5208',
            gas: '0x4a817c800', //+ oxgasFeeToWei,
            to: '0x' + toAddress,
            value: '0x' + oxNumBalance,
            data: '',
            chainId: 1
        };
        var tx = new transaction(rawTx);
        tx.sign(privateKeyBuffer);
        var serializedTx = tx.serialize();
        if (serializedTx == null) {
            console.log("Serialized transaction fail")
        } else {
            console.log("Serialized transaction success and the result is " + serializedTx.toString('hex'));
            console.log("The sender address is " + tx.getSenderAddress().toString('hex'));
            if (tx.verifySignature()) {
                console.log('Signature Checks out!')
            } else {
                console.log("Signature checks fail")
            }
        }
    }
    return '0x' + serializedTx.toString('hex')
}

async function getPrivate() {

    var password = 'wheethereum'
    var keyObject = {
        address: 'f3a0580263b477502f9675886ad5bcac66b2ea66',
        crypto: {
            cipher: 'aes-128-ctr',
            ciphertext: '864c5906593f497810f1978b3ce01c7214af72602b665e4072fe12cf8de60f1a',
            cipherparams: {
                iv: 'a2759bad7f061d4872cc2c63e8585120'
            },
            mac: 'e75ebf0b9b55a4de3c23adef523e70de1878901d5dac804bbdd47b76e1eb57fd',
            kdf: 'pbkdf2',
            kdfparams: {
                c: 262144,
                dklen: 32,
                prf: 'hmac-sha256',
                salt: 'fa01d4743518a275a7b9a640c40cd88ddbe9e42ff7537d13c667967589eacbf6'
            }
        },
        id: 'e1821f04-aa37-4b59-a39c-124226e0a811',
        version: 3
    }

    var privateKey = keythereum.recover(password, keyObject);
    console.log(privateKey.toString('hex'))
    // var privateKey = keythereum.recover(password, keyObject).toString('hex');
    // console.log("===============" + privateKey)
    return privateKey.toString('hex')
}

var privateKey = getPrivate()
var nonce = 10;
var toAddress = "f3a0580263b477502f9675886ad5bcac66b2ea66";
var sendToBalance = "0.003";
var sendFee = "0.001";

var sign = ethereumSign("de847a389cded6ffbb97b0f0884fdf6e87792456a548c6a56c5ec80be7f820cc", nonce, toAddress, sendToBalance, sendFee);
console.log(sign);