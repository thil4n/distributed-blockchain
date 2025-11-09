package net.hacksland;

import java.time.Instant;
import java.util.ArrayList;

public class Block {
    private ArrayList<Transaction> transactions;

    private int nonce;
    private int difficulty;
    private long timestamp;

    private String hash;
    private String prevHash;

    // Constructor
    public Block(int index, ArrayList<Transaction> transactions, int difficulty, String prevHash) {
        this.transactions = transactions;
        this.difficulty = difficulty;
        this.prevHash = prevHash;

        this.timestamp = Instant.now().toEpochMilli();

        mineBlock();
    }

    // Hash calculation
    public String calculateHash() {
        String data = "";
        for (Transaction tx : transactions) {
            data += tx.toString();
        }
        String dataToHash = data + nonce + difficulty + timestamp + prevHash;
        this.hash = CryptoUtils.applySha256(dataToHash);
        return hash;
    }

    // Mining process with Proof-of-Work
    public void mineBlock() {
        String target = "0".repeat(difficulty);
        nonce = 0;
        hash = calculateHash();

        while (!hash.startsWith(target)) {
            nonce++;
            hash = calculateHash();
        }

        System.out.println("Block Mined! Hash: " + hash);
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public int getNonce() {
        return nonce;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getHash() {
        return hash;
    }

    public String getPrevHash() {
        return prevHash;
    }
}
