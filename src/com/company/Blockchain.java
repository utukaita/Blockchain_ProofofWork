package com.company;


import java.util.Date;

public class Blockchain{
    private int prefix;
    private String prefixString;

    private Block root;

    public Blockchain(int prefix) {
        this.prefix = prefix;
        prefixString = new String(new char[prefix]).replace('\0', '0');
    }

    public void add(Block block, Block previous){
        String previousHash = "0";
        if (previous != null) {
            previousHash = previous.getHash();
            previous.setNext(block);
        }
        else
            root = block;
        block.setPreviousHash(previousHash);
        block.setTimeStamp(new Date().getTime());
        block.setHash(block.mineBlock());
        block.setPrevious(previous);
    }

    public Block create(String data) {
        Block block = new Block(data, prefixString, prefix);
        return block;
    }

    public void traverse(Block block){
        System.out.println(block);
        if(block.getNext()!=null){
            for (int i = 0; i < block.getNext().size() ; i++)
                traverse(block.getNext().get(i));
        }
    }

    public Block getRoot() {
        return root;
    }

    public int getLength(Block block) {
        int counter = 1;
        while (block.getPrevious()!=null){
               counter++;
               block = block.getPrevious();
        }
        return counter;
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
