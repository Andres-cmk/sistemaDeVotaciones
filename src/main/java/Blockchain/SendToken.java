package Blockchain;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.math.BigDecimal;

public class SendToken {

    private static final String GANACHE_URL = "http://127.0.0.1:7545"; // URL de tu instancia de Ganache
    private static final String SENDER_PRIVATE_KEY = ""; // Clave privada de la cuenta remitente

    public static void send(String destinationAddress) {
        try{

            Web3j web3j = Web3j.build(new HttpService(GANACHE_URL));
            Credentials credentials = Credentials.create(SENDER_PRIVATE_KEY);
            BigDecimal amountInEther = BigDecimal.ONE;
            TransactionReceipt transactionReceipt = Transfer.sendFunds(
                    web3j, credentials, destinationAddress, amountInEther, Convert.Unit.ETHER
            ).send();

            System.out.println("Transaction complete: " + transactionReceipt.getTransactionHash());

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
