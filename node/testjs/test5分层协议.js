var keythereum = require("keythereum");

// var params = { keyBytes: 32, ivBytes: 16 };

// keythereum.create(params, function (dk) {
//     var options = {
//       kdf: "pbkdf2",
//       cipher: "aes-128-ctr",
//       kdfparams: {
//         c: 262144,
//         dklen: 32,
//         prf: "hmac-sha256"
//       }
//     };
//     var password = "wheethereum";
//     var kdf = "pbkdf2"; 
//     /**
//      * dk.privateKey:明文的私钥
//      */
//     keythereum.dump(password, dk.privateKey, dk.salt, dk.iv, options, function (keyObject) {
//       console.log(keyObject.address)//生成以太坊keystore
//       keythereum.exportToFile(keyObject);//打开生成的以太坊keystore
//   });
// });

// var datadir = "./";
// var address = 'f3a0580263b477502f9675886ad5bcac66b2ea66';
// var keyObject = keythereum.importFromFile(address, datadir);
// console.log(keyObject)
// keythereum.importFromFile(address, datadir, function (keyObject) {
//    console.log(keyObject)
// });

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
console.log(privateKey)
keythereum.recover(password, keyObject, function (privateKey) {
    console.log(privateKey)
});


// /* 
// var bip39 = require('bip39')
// var hdkey = require('ethereumjs-wallet/hdkey')
// var util = require('ethereumjs-util')

// var mnemonic = bip39.generateMnemonic()
// var seed = bip39.mnemonicToSeed(mnemonic)
// var hdWallet = hdkey.fromMasterSeed(seed)

// var key1 = hdWallet.derivePath("m/44'/60'/0'/0/0")
// /**
//  * 以太坊 私钥
//  */
// var private_key = key1._hdkey._privateKey.toString('hex')
// console.log('私钥:' + private_key)
// /**
//  * 以太坊地址
//  */
// var address1 = util.pubToAddress(key1._hdkey._publicKey, true).toString('hex')
// console.log('地址:' + address1)

// var encrypt = function (str, pwd) {
//     if (pwd == null || pwd.length <= 0) {
//         console.log("Please enter a password with which to encrypt the message");
//         return null;
//     }
//     var prand = "";
//     for (var i = 0; i < pwd.length; i++) {
//         prand += pwd.charCodeAt(i).toString();
//     }
//     var sPos = Math.floor(prand.length / 5);
//     var mult = parseInt(prand.charAt(sPos) + prand.charAt(sPos * 2) + prand.charAt(sPos * 3) + prand.charAt(sPos * 4) + prand.charAt(sPos * 5));
//     var incr = Math.ceil(pwd.length / 2);
//     var modu = Math.pow(2, 31) - 1;
//     if (mult < 2) {
//         console.log("Algorithm cannot find a suitable hash. Please choose a different password. \nPossible considerations are to choose a more complex or longer password.");
//         return null;
//     }
//     var salt = Math.round(Math.random() * 1000000000) % 100000000;
//     prand += salt;
//     while (prand.length > 10) {
//         prand = (parseInt(prand.substring(0, 10)) + parseInt(prand.substring(10, prand.length))).toString();
//     }
//     prand = (mult * prand + incr) % modu;
//     var enc_chr = "";
//     var enc_str = "";
//     for (var i = 0; i < str.length; i++) {
//         enc_chr = parseInt(str.charCodeAt(i) ^ Math.floor((prand / modu) * 255));
//         if (enc_chr < 16) {
//             enc_str += "0" + enc_chr.toString(16);
//         } else enc_str += enc_chr.toString(16);
//         prand = (mult * prand + incr) % modu;
//     }
//     salt = salt.toString(16);
//     while (salt.length < 8) salt = "0" + salt;
//     enc_str += salt;
//     return enc_str;
// }

// var decrypt = function (str, pwd) {
//     if (str == null || str.length < 8) {
//         console.log("A salt value could not be extracted from the encrypted message because it's length is too short. The message cannot be decrypted.");
//         return null;
//     }
//     if (pwd == null || pwd.length <= 0) {
//         console.log("Please enter a password with which to decrypt the message.");
//         return null;
//     }
//     var prand = "";
//     for (var i = 0; i < pwd.length; i++) {
//         prand += pwd.charCodeAt(i).toString();
//     }
//     var sPos = Math.floor(prand.length / 5);
//     var mult = parseInt(prand.charAt(sPos) + prand.charAt(sPos * 2) + prand.charAt(sPos * 3) + prand.charAt(sPos * 4) + prand.charAt(sPos * 5));
//     var incr = Math.round(pwd.length / 2);
//     var modu = Math.pow(2, 31) - 1;
//     var salt = parseInt(str.substring(str.length - 8, str.length), 16);
//     str = str.substring(0, str.length - 8);
//     prand += salt;
//     while (prand.length > 10) {
//         prand = (parseInt(prand.substring(0, 10)) + parseInt(prand.substring(10, prand.length))).toString();
//     }
//     prand = (mult * prand + incr) % modu;
//     var enc_chr = "";
//     var enc_str = "";
//     for (var i = 0; i < str.length; i += 2) {
//         enc_chr = parseInt(parseInt(str.substring(i, i + 2), 16) ^ Math.floor((prand / modu) * 255));
//         enc_str += String.fromCharCode(enc_chr);
//         prand = (mult * prand + incr) % modu;
//     }
//     return enc_str;
// }


// console.log('私钥:' + decrypt('123456789', encrypt('123456789', private_key)).toString('hex'))          */