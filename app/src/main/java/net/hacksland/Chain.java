package net.hacksland;

import java.util.ArrayList;

public class Chain {
    private final int difficulty = 4;
    private final ArrayList<Block> blockchain = new ArrayList<>();

    public Chain() {
        blockchain.add(new Block(0, "Genesis block", difficulty, "0"));
    }

    public void addBlock(String data) {
        Block newBlock = new Block(blockchain.size(), data, difficulty, getLatestBlock().getHash());
        blockchain.add(newBlock);
    }

    public Block getLatestBlock() {
        return blockchain.get(blockchain.size() - 1);
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
        System.out.println("------------------------");
        System.out.println("Printing the blocks");

        for (int i = 0; i < blockchain.size(); i++) {
            System.out.println();
            System.out.println("Block : " + i);
            System.out.println("Previous Hash : " + blockchain.get(i).getPrevHash());
            System.out.println("Current Hash  : " + blockchain.get(i).getHash());
        }
        System.out.println("------------------------");
    }
}
