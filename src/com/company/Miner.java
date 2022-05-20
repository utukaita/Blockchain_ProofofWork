package com.company;

public class Miner extends Thread {
    private Block[] blocks;
    private Blockchain chain;
    private int n;
    private int id;

    public Miner(Block[] blocks, Blockchain chain, int n, int id){
        this.blocks = blocks;
        this.chain = chain;
        this.id = id;
    }

    @Override
    public void run() {
            if (n==0) chain.add(blocks[0], null);
            else chain.add(blocks[n], blocks[n-1]);
            System.out.println("finished by thread " + id);
    }
}