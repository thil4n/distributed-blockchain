package net.hacksland;

import static spark.Spark.*;
import com.google.gson.*;
import java.util.*;

public class NodeServer {
    private Chain chain;
    private Set<String> peers = new HashSet<>();

    public NodeServer(int port) {
        port(port);
        this.chain = new Chain();

        Gson gson = new Gson();

        // Endpoint: Get the current blockchain
        get("/chain", (req, res) -> {
            res.type("application/json");
            return gson.toJson(chain);
        });

        // Endpoint: Add a new peer
        post("/peers", (req, res) -> {
            String peer = req.body();
            peers.add(peer);
            return "Peer added: " + peer;
        });

        // Endpoint: Receive a new block
        post("/receive-block", (req, res) -> {
            Block newBlock = gson.fromJson(req.body(), Block.class);
            chain.addExternalBlock(newBlock);
            return "Received new block";
        });

        // Endpoint: Add a transaction
        post("/transactions", (req, res) -> {
            Transaction tx = gson.fromJson(req.body(), Transaction.class);
            chain.addTransaction(tx);
            return "Transaction added";
        });

        System.out.println("Node running on port " + port);
    }

    public Set<String> getPeers() {
        return peers;
    }

    public Chain getChain() {
        return chain;
    }
}
