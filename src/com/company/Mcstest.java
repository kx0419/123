package com.company;

import java.util.concurrent.CountDownLatch;

public class Mcstest {

    Node node=new Node(0," ");
    //public
    //static CountDownLatch
}
class Node{
    private Node pre=this;//指向前一个节点
    private Node next=this;//指向下一个节点
    private int time;
    private String thing;

    public Node(int time,String thing) {
        this.time = time;
        this.thing=thing;
    }

    public Node(){

    }
    Node node=new Node();

}