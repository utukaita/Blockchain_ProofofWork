package com.company;

import java.util.ArrayList;
import java.util.Date;

public class Blockchain{
    int prefix;
    String prefixString;
    ArrayList<Block> blockchain = new ArrayList<>();
    public Blockchain(int prefix) {
        this.prefix = prefix;
        prefixString = new String(new char[prefix]).replace('\0', '0');
    }

    public void add(Block block){
        String previousHash = "0";
        if (getLength() > 0) {
            previousHash = blockchain.get(getLength() - 1).getHash();
        }
        if(block.valid(previousHash)) {
            blockchain.add(block);
        }
    }

    public Block create(String data) {
        String previousHash = "0";
        if (getLength() > 0) {
            previousHash = blockchain.get(getLength() - 1).getHash();
        }
        Block block = new Block(data, previousHash, new Date().getTime(), prefixString, prefix);
        return block;
    }

    public Block getBlock(int n) {
        return blockchain.get(n);
    }

    public int getLength() {
        return blockchain.size();
    }

    public int getPrefix() {
        return prefix;
    }


    public void setPrefix(int prefix) {
        if (prefix >= 0)
            this.prefix = prefix;
        else
            System.out.println("Prefix can't be negative!");
    }
}
