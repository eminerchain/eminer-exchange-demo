package org;

import com.em.sdk.core.protocol.core.methods.response.EMSendTransaction;
import com.em.sdk.core.tx.ChainId;
import com.em.sdk.core.utils.EmGas;
import com.em.sdk.crypto.Credentials;
import com.em.sdk.crypto.RawTransaction;
import com.em.sdk.crypto.TransactionDecoder;
import com.em.sdk.crypto.TransactionEncoder;
import com.em.sdk.utils.Convert;
import com.em.sdk.utils.Numeric;

import java.math.BigInteger;

/**
 * @author yujian    2020/05/22 离线签名交易相关demo
 */
public class RawTransactionDemo {

    private final static String testAddress = "EM2e25d6f13330163134b9e321491ca0d45233e054";
    private final static String testPrivateKey = "b968d08d67b3664bebd6b11b517557a29186e3c5f8474959d5db7cc0442b1831";

    /**
     * em主链币转账，离线签名
     *
     * @param from             转出地址
     * @param to               转入地址，如 EMf12f2e4457f1cdd0ad7c7874e0ff25d5d495b65a
     * @param privateKey       转出地址16进制的私钥
     * @param fromAddressNonce 转出地址nonce值
     * @param emAmount         em的转账数量
     * @return 转账的离线签名字符串
     */
    public static String signEMTransaction(String from, String to, String privateKey, BigInteger fromAddressNonce,
                                           String emAmount) {
        BigInteger gas = EmGas.defaultTrxGas.toBigInteger();
        BigInteger gasPrice = EmGas.defaultGasPrice.toBigInteger();
        Credentials credentials = Credentials.create(privateKey);

        BigInteger value = Convert.toWei(emAmount, Convert.Unit.EM).toBigInteger();

        RawTransaction rawTransaction = RawTransaction.createEMTransaction(fromAddressNonce, gasPrice, gas, to, value);

        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, ChainId.MAINNET, credentials);
        return Numeric.toHexString(signedMessage);

//        EMSendTransaction emSendTransaction = web3j.emSendRawTransaction(hexValue).sendAsync().get();
//        if (emSendTransaction.getError() != null) {
//            System.err.printf("sendRawTransaction error:%s\n", emSendTransaction.getError().getMessage());
//        } else {
//            String transactionHash = emSendTransaction.getTransactionHash();
//            System.err.printf("sendRawTransaction success,trxHash:%s\n", transactionHash);
//        }
    }

    /**
     * em主链币转账，离线签名
     *
     * @param from             转出地址
     * @param to               转入地址,地址格式为子地址，如
     *                         EM8ea2354ba012628dd1dad9e44500a70075664a16202cb962ac59075b964b07152d234b70
     * @param privateKey       转出地址16进制的私钥
     * @param fromAddressNonce 转出地址nonce值
     * @param emAmount         em的转账数量
     * @return 转账的离线签名字符串
     */
    public static String signEMTransactionWithSubAddress(String from, String to, String privateKey,
                                                         BigInteger fromAddressNonce,
                                                         String emAmount) {
        return signEMTransaction(from, to, privateKey, fromAddressNonce, emAmount);
    }

    /**
     * 离线签名串反解
     *
     * @param hexValue 离线签名串
     * @return 解析出来的签名信息
     */
    public static RawTransaction decodeRawSign(String hexValue) {
        return TransactionDecoder.decode(hexValue);
    }
}
