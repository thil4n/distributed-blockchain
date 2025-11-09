package net.hacksland;

public class Main {

    private static Chain blockchain = new Chain();

    public static void main(String[] args) {

        blockchain.addBlock("test Data 1");
        blockchain.addBlock("test Data 2");
        blockchain.addBlock("test Data 3");

        blockchain.printChain();
    }
}