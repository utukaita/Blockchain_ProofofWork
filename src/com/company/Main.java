package com.company;

public class Main {

    public static void main(String[] args) {
        final int prefix = 5;
        final int block_count = 100;
        final int miner_count = 12;
        final int privateMiner_count = 8;
        final int malevolentRatio = 0;
        final int privateMalevolentRatio = 0;
        final String name = "public";
        final String privateName = "private";
        Blockchain chain = new Blockchain(block_count, miner_count, malevolentRatio, prefix, name);
        Blockchain privateChain = new Blockchain(block_count, privateMiner_count, privateMalevolentRatio, prefix, privateName);
        chain.start();
        privateChain.start();
        try {
            chain.join();
            privateChain.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        chain.traverse(chain.getRoot());
        System.out.println(chain);
        privateChain.traverse(privateChain.getRoot());
        System.out.println(privateChain);



    }
}