package net.hacksland;

import java.util.ArrayList;

public class Chain {
    private final int difficulty = 4;
    private final ArrayList<Block> blockchain = new ArrayList<>();

    private TransactionPool pool = new TransactionPool();

    public Chain() {
        // Create the Genesis Block
        blockchain.add(new Block(0, new ArrayList<>(), difficulty, "0"));
    }

    public Block getLatestBlock() {
        return blockchain.get(blockchain.size() - 1);
    }

    public void addBlock(ArrayList<Transaction> transactions) {
        Block newBlock = new Block(blockchain.size(), transactions, difficulty, getLatestBlock().getHash());
        blockchain.add(newBlock);
    }

    public void minePendingTransactions() {
        Block block = new Block(blockchain.size(), pool.getPendingTransactions(), difficulty,
                getLatestBlock().getHash());
        blockchain.add(block);
        pool.clear();
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

    public static void main(String[] args) {
        Chain chain = new Chain();

        ArrayList<Transaction> t1 = new ArrayList<>();
        t1.add(new Transaction("Alice", "Bob", 5));
        chain.addBlock(t1);

        ArrayList<Transaction> t2 = new ArrayList<>();
        t2.add(new Transaction("Bob", "Charlie", 2));
        t2.add(new Transaction("Alice", "Dave", 3));
        chain.addBlock(t2);

        chain.printChain();
    }

}
