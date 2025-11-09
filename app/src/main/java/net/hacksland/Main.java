package net.hacksland;

public class Main {
    public static void main(String[] args) {
        Wallet alice = new Wallet();
        Wallet eve = new Wallet();
        Chain chain = new Chain();

        // Create a valid transaction
        Transaction tx1 = new Transaction(alice.getPublicKey(), "Bob", 50);
        tx1.signTransaction(alice.getPrivateKey());
        chain.addTransaction(tx1);

        // Try to forge a transaction (unsigned)
        Transaction fakeTx = new Transaction(alice.getPublicKey(), "Eve", 100);
        fakeTx.signTransaction(eve.getPrivateKey());
        chain.addTransaction(fakeTx); // Should be rejected

        // Mine transactions
        chain.minePendingTransactions();
        chain.printChain();
    }
}