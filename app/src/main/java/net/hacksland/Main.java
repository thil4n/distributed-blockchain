package net.hacksland;

public class Main {
    public static void main(String[] args) {
        // Node 1
        new Thread(() -> new NodeServer(4444)).start();

        // Node 2
        new Thread(() -> new NodeServer(5555)).start();
    }
}
