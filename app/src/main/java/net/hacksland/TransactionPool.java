package net.hacksland;

import java.util.ArrayList;

public class TransactionPool {
    private ArrayList<Transaction> pendingTransactions = new ArrayList<>();

    public void addTransaction(Transaction tx) {
        pendingTransactions.add(tx);
    }

    public ArrayList<Transaction> getPendingTransactions() {
        return pendingTransactions;
    }

    public void clear() {
        pendingTransactions.clear();
    }
}
