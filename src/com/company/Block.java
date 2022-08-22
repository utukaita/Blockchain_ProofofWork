package com.company;

import java.security.MessageDigest;

public class Block {

    private String hash;
    private String previousHash;
    private String data;
    private long timeStamp;
    private int miner;
    private int nonce;
    private int prefix;
    private String prefixString;

    private Block previous;
    private Block next;

    public Block(int prefix) {
        this.prefix = prefix;
        prefixString = new String(new char[prefix]).replace('\0', '0');
    }

    public boolean mined(){
        String prevHash = "0", hashLocal = "0";
        long prevTimeStamp = 0;
        if (previous!=null) {
            prevHash = previous.hash;
            prevTimeStamp = previous.timeStamp;
        }
        if(hash!=null) hashLocal = hash;
        if ( hashLocal.substring(0, prefix).equals(prefixString)
                && prevHash == previousHash
                && hashLocal.equals(calculateBlockHash())
                && timeStamp>prevTimeStamp
                && dataValid())
        {
            return true;
        }
        else
            return false;
    }
    public boolean dataValid(){
        // Could be made more advanced but testing double spending is not the topic of the study
        // Currently a mere abstraction for distinguishing between benevolent and malevolent miners
        if(data.equals("Valid data"))
            return true;
        else
            return false;
    }

    public String calculateBlockHash() {
        String dataToHash = previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data;
        MessageDigest digest;
        byte[] bytes = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes("UTF-8"));
        } catch (Exception e) {
            System.out.println("Got exception " + e);
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            buffer.append(String.format("%02x", bytes[i]));
        }
        return buffer.toString();
    }

    @Override
    public String toString() {
        return "Block{" +
                "hash='" + hash + '\'' +
                ", previousHash='" + previousHash + '\'' +
                ", data='" + data + '\'' +
                ", timeStamp=" + timeStamp + '\'' +
                ", miner=" + miner + '\'' +
                ", nonce=" + nonce + '\'' +
                '}';
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Block getPrevious() {
        return previous;
    }

    public void setPrevious(Block previous) {
        this.previous = previous;
    }

    public Block getNext() {
        return next;
    }

    public void setNext(Block next) {
        this.next = next;
    }

    public String getData(){
        return data;
    }

    public void setData(String data){
        this.data=data;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public int getMiner() {
        return miner;
    }

    public void setMiner(int miner) {
        this.miner = miner;
    }

    public int getPrefix() {
        return prefix;
    }

    public String getPrefixString() {
        return prefixString;
    }
}