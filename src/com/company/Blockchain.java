package com.company;

public class Blockchain extends Thread{

    private Block root;
    private Block[] blocks;
    private Miner[] miners;
    private int block_count;
    private int miner_count;
    private int malevolentRatio;
    private int prefix;
    private String name;
    private Long time;

    public Blockchain(int block_count, int miner_count, int malevolentRatio, int prefix, String name) {
        this.block_count = block_count;
        this.miner_count = miner_count;
        this.malevolentRatio = malevolentRatio;
        this.prefix = prefix;
        this.name = name;
        blocks = new Block[block_count];
        miners = new Miner[miner_count];
        for (int i = 0; i < block_count; i++) {
            Block block = new Block(prefix);
            blocks[i] = block;
        }
    }

    @Override
    public void run(){
        long startTime = System.currentTimeMillis();
        int n=0;
        boolean continuing = true;
        Block currentBlock = null;
        while(n<block_count) {
            if (continuing){
                for (int i = 0; i < miner_count; i++) {
                    boolean benevolent = true;
                    if (i<miner_count*malevolentRatio/100) benevolent = false;
                    Miner miner = new Miner(blocks, this, i, benevolent, n);
                    miner.start();
                    miners[i] = miner;
                }
                continuing = false;
            }
            if(root!=null && n==0){
                currentBlock = root;
                if(currentBlock.mined()) {
                    continuing = quit(miners);
                    n++;
                }
            }
            else if (currentBlock != null) {
                if (currentBlock.getNext() != null) {
                    if (currentBlock.getNext().mined() == true) {
                        currentBlock = currentBlock.getNext();
                        continuing = quit(miners);
                        n++;
                    }
                }
            }
        }
        time = System.currentTimeMillis()-startTime;
    }
    public static boolean quit(Miner[] miners){
        for (int j = 0; j < miners.length; j++) {
            if (miners[j].isAlive()) miners[j].stop();
        }
        return true;
    }
    public void traverse(Block block){
        StringBuffer tab = new StringBuffer();
        for (int i = 0; i < getLength(block)-1; i++)
            tab.append("    ");
        System.out.println(tab.toString() + block);
        if(block.getNext()!=null)
            traverse(block.getNext());
    }

    @Override
    public String toString() {
        return name + " with malevolent ratio " + malevolentRatio + " and time " + time;
    }

    public String getChainName(){
        return name;
    }
    public Block getRoot() {
        return root;
    }

    public void setRoot(Block root){
        this.root = root;
    }

    public int getLength(Block block) {
        int counter = 1;
        while (block.getPrevious()!=null){
               counter++;
               block = block.getPrevious();
        }
        return counter;
    }
}
