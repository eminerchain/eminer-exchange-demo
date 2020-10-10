# EM主链接口接口文档



#### 启动主链步骤

* Git clone主链代码，假定clone后的主链文件夹为go-eminer
* cd go-miner
* make em : 编译代码，生成可运行的二进制文件，假定编译后的em二进制文件所在目录为 home
* 启动主链:  home/em --datadir home/eminer/em-data

* 连接主链对应的控制台：home/em attach  home/eminer/em-data/em.ipc



#### EM主链控制台命令

* 查询当前区块高度：em.blockNumber
* 转账：

```
// em主链币转账,转账手续费0.0001
em.sendTransaction({from: "EM43f4fc95485110683f745cd4a6362497c832a774",to: "EM8ea2354ba012628dd1dad9e44500a70075664a16",value: web3.toWei(1,"em"),action:0})

// em主链币子地址转账，子地址即 正常地址 + 32位md5值
em.sendTransaction({from: "EM43f4fc95485110683f745cd4a6362497c832a774",to: "EM8ea2354ba012628dd1dad9e44500a70075664a16202cb962ac59075b964b07152d234b70",value: web3.toWei(1,"em"),action:0})
```

* 根据交易hash查询某笔交易：em.getTransaction('trxid')
* 根据交易hash查询某笔交易收据：em.getTransactionReceipt('trxId')

* 解锁某个本地账户：personal.unlockAccount('address','password')

* 创建账户：personal.newAccount('password')

* 添加一个连接节点：admin.addPeer("enode://302d9156a3ad8ab535093b89457f7b02e2fb99b4560f135ce9779772e1fe51fe8f4b62a62aeb68b80edd8fb1fa9023ed70c57486845c3922c3a26b03e068c0d1@127.0.0.1:30303")

* 查看本地所有账户：em.accounts
* 查看某个区块信息：em.getBlock($blockNumber or blockHash)
* 查看某个地址发出的交易笔数：em.getTransactionCount($address)
* 查看某个多资产的信息：em.getAssetInfo($assetId)
* 查看某个地址主链币余额：web3.fromWei(em.getBalance($address),"em")
* 查看某个地址某个多资产的余额：em.getAssetBalance($address,assetId)



##### 交易action数值说明

* 0：主链币或多资产转账
* 1：代理注册
* 2：代理投票（AddVote）
* 3：代理投票（SubVote）
* 4：多资产发布
* 5：合约创建
* 6：合约调用