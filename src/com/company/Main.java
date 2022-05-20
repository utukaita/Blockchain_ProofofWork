package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Blockchain chain = new Blockchain(3);
        Block[] blocks = new Block[3];
        for (int i = 0; i < blocks.length; i++) {
            Block block = chain.create("Utu Kaita (hriru3hu8t2r2thu) transfers 1,000,000 utu to Elon Musk (fjhiu1y3t37h34q909).");
            blocks[i] = block;
        }
        int n=0;
        while(n<blocks.length) {
            for (int i = 0; i < 1; i++) {
                Miner miner = new Miner(blocks, chain, i, 7);
                miner.start();
                miner.join();
                n++;
            }
        }
        chain.traverse(chain.getRoot());
    }
}