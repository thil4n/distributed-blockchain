package net.hacksland;

import java.util.ArrayList;

public class Chain {
    private final int difficulty = 4;
    private final ArrayList<Block> blockchain = new ArrayList<>();

    private TransactionPool transactionPool = new TransactionPool();

    public Chain() {
        blockchain.add(new Block(0, new ArrayList<>(), difficulty, "0"));
    }

    public Block getLatestBlock() {
        return blockchain.get(blockchain.size() - 1);
    }

    public void addTransaction(Transaction tx) {
        if (tx.isValid()) {
            transactionPool.addTransaction(tx);
        } else {
            System.out.println("Invalid transaction! Rejected.");
        }
    }

    public void minePendingTransactions() {
        Block block = new Block(blockchain.size(), transactionPool.getPendingTransactions(), difficulty,
                getLatestBlock().getHash());
        blockchain.add(block);
        transactionPool.clear();
    }

    public boolean isChainValid() {
        for (int i = 1; i < blockchain.size(); i++) {
            Block currentBlock = blockchain.get(i);
            Block previousBlock = blockchain.get(i - 1);

            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                System.out.println("Block " + i + " has been tampered!");
                return false;
            }

            if (!currentBlock.getPrevHash().equals(previousBlock.getHash())) {
                System.out.println("Block " + i + " is not linked properly!");
                return false;
            }
        }
        return true;
    }

    public void printChain() {
        for (Block block : blockchain) {
            for (Transaction tx : block.getTransactions()) {
                System.out.println("   " + tx.toString());
            }
            System.out.println("Previous Hash: " + block.getPrevHash());
            System.out.println("Current Hash: " + block.getHash());
            System.out.println("------------------------");
        }
    }
}
