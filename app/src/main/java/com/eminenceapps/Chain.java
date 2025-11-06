package com.eminenceapps;

import java.util.ArrayList;

public class Chain {
    private final int difficulty = 4; // Adjust for mining difficulty
    private final ArrayList<Block> blockchain = new ArrayList<>();

    public Chain() {
        // Create the Genesis Block
        blockchain.add(new Block(0, "Genesis Block", difficulty, "0"));
    }

    public Block getLatestBlock() {
        return blockchain.get(blockchain.size() - 1);
    }

    public void addBlock(String data) {
        Block newBlock = new Block(blockchain.size(), data, difficulty, getLatestBlock().getHashString());
        blockchain.add(newBlock);
    }

    public boolean isChainValid() {
        for (int i = 1; i < blockchain.size(); i++) {
            Block currentBlock = blockchain.get(i);
            Block previousBlock = blockchain.get(i - 1);

            if (!currentBlock.getHashString().equals(currentBlock.calculateHash())) {
                System.out.println("Block " + i + " has been tampered!");
                return false;
            }

            if (!currentBlock.getPrevHashString().equals(previousBlock.getHashString())) {
                System.out.println("Block " + i + " is not linked properly!");
                return false;
            }
        }
        return true;
    }

    public void printChain() {
        for (Block block : blockchain) {
            System.out.println("Block #" + block.getIndex());
            System.out.println("Data: " + block.getData());
            System.out.println("Previous Hash: " + block.getPrevHashString());
            System.out.println("Current Hash: " + block.getHashString());
            System.out.println("------------------------");
        }
    }

    public static void main(String[] args) {
        Chain chain = new Chain();

        System.out.println("Mining block 1...");
        chain.addBlock("Transaction 1");

        System.out.println("Mining block 2...");
        chain.addBlock("Transaction 2");

        System.out.println("Mining block 3...");
        chain.addBlock("Transaction 3");

        chain.printChain();
        System.out.println("Blockchain valid: " + chain.isChainValid());
    }
}
