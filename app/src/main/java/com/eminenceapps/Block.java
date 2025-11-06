package com.eminenceapps;

import java.time.Instant;

public class Block {
    private int index;
    private String data;
    private int nonce;
    private int difficulty;
    private long timestamp;
    private String creator;
    private String signature;
    private String hashString;
    private String prevHashString;

    // Constructor
    public Block(int index, String data, int difficulty, String prevHashString) {
        this.index = index;
        this.data = data;
        this.difficulty = difficulty;
        this.prevHashString = prevHashString;
        // this.creator = creator;
        // this.signature = signature;
        this.timestamp = Instant.now().toEpochMilli(); // Set current timestamp
        this.hashString = calculateHash(); // Calculate initial hash

        mineBlock(); // Mine the block
    }

    // Hash calculation
    public String calculateHash() {
        String dataToHash = index + data + nonce + difficulty + timestamp + creator + signature + prevHashString;
        this.hashString = StringUtil.applySha256(dataToHash);
        return hashString;
    }

    // Mining process with Proof-of-Work
    public void mineBlock() {
        String target = "0".repeat(difficulty);
        nonce = 0;
        hashString = calculateHash();

        while (!hashString.startsWith(target)) {
            nonce++;
            hashString = calculateHash();
        }

        System.out.println("Block Mined! Hash: " + hashString);
    }

    // Getters
    public int getIndex() {
        return index;
    }

    public String getData() {
        return data;
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

    public String getCreator() {
        return creator;
    }

    public String getSignature() {
        return signature;
    }

    public String getHashString() {
        return hashString;
    }

    public String getPrevHashString() {
        return prevHashString;
    }
}
