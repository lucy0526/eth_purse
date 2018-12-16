/* var bip39 = require('bip39')
var mnemonic = bip39.generateMnemonic()
console.log("-------"+mnemonic);

var bip39 = require('bip39')
var mnemonic = bip39.generateMnemonic(128)
console.log("====="+mnemonic);

var mnemonicS = bip39.generateMnemonic(128, null, bip39.wordlists.chinese_simplified);
console.log("`````"+mnemonicS); */

var bip39 = require('bip39')
var mnemonicE = bip39.generateMnemonic()
console.log('mnemonicE:'+mnemonicE);
/**生成随机数种子:
 * seed
 * seedHex
 */
var Seed= bip39.mnemonicToSeed(mnemonicE)
var seedHex= bip39.mnemonicToSeedHex(mnemonicE)
console.log('Seed:'+Seed);
console.log('seedHex:'+seedHex);
/**
 * 验证助记词
 */
var bool = bip39.validateMnemonic(mnemonicE)
console.log('bool:'+bool);

