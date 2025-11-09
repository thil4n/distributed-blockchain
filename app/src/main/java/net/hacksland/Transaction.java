package net.hacksland;

import java.security.PrivateKey;
import java.security.PublicKey;

public class Transaction {
    private PublicKey sender;
    private String recipient;
    private double amount;

    private byte[] signature;

    public Transaction(PublicKey sender, String recipient, double amount) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
    }

    private String getData() {
        return CryptoUtils.getStringFromKey(sender) + recipient + amount;
    }

    public void signTransaction(PrivateKey privateKey) {
        String data = getData();
        signature = CryptoUtils.applySignature(privateKey, data);
    }

    public boolean isValid() {
        if (signature == null)
            return false;
        return CryptoUtils.verifySignature(sender, getData(), signature);
    }

    @Override
    public String toString() {
        return sender + " -> " + recipient + ": " + amount;
    }
}
