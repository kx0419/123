package com.company;

public class Listtest <T>{
    /**
     * 内部构造节点类
     *
     * @param <T>
     */
    private class Node<T> {
        private T data;
        private Node next; // 指向下一个节点的引用
        private Node prev; // 指向前一个节点的引用

        public Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head; // 模拟头结点
    private Node<T> last; // 模拟尾部节点
    private Node<T> other; // 暂定一个临时节点，用作指针节点
    private int length;

    public void DoubleLinkTest() {
        head = new Node<T>(null);
        last = head;
        length = 0;
    }

    public void DoubleLinkTest(T data) {
        head = new Node<T>(data);
        last = head;
        length = 0;
    }

    /**
     * 链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * 普通添加，往链表尾部添加
     *
     * @param data
     */
    public void add(T data) {
        if (isEmpty()) { // 链表为空，新创建一个链表
            head = new Node<T>(data);
            last = head;
            length++;
        } else {
            other = new Node<T>(data);
            other.prev = last;
            last.next = other; // 将新的节点与原来的尾部节点进行结构上的关联
            last = other; // other将成为最后一个节点
            length++;
        }
    }

    /**
     * 在指定的数据后面添加数据
     *
     * @param data
     * @param insertData
     */
    public void addAfter(T data, T insertData) {
        other = head;
        while (other != null) { // 我们假定这个head是不为空的。
            if (other.data.equals(data)) {
                Node<T> t = new Node<T>(insertData);
                t.prev = other;
                t.next = other.next;// 对新插入的数据进行一个指向的定义
                other.next = t;

                if (t.next == null) {
                    last = t;
                }
                length++;
            }
            other = other.next;
        }
    }

    /**
     * 删除，删除指定的数据
     *
     * @param data
     */
    public void remove(T data) {
        other = head;// 我们假定这个head是不为空的。
        while (other != null) {
            if (other.data.equals(data)) {
                other.prev.next = other.next;
                length--;
            }
            other = other.next;
        }

    }

    /**
     * 测试打印数据
     */
    public void printList() {
        other = head;
        for (int i = 0; i < length; i++) {
            System.out.println(other.data + "  ");
            other = other.next;
        }
    }

    public static void main(String[] args) {

        Listtest<Integer> link = new Listtest<>();
        link.add(1);
        link.add(2);
        link.add(3);
        link.add(5);
        link.add(6);
        link.add(7);
        link.printList();

        System.out.println(" ============== ");

        System.out.println(" ==== 在3后面添加一个数据开始========== ");
        link.addAfter(3, 99);
        link.printList();

        System.out.println(" ==== 在3后面添加一个数据结束========== " + "\r\n");

        System.out.println(" ==== 移除一个数据开始========== ");
        link.remove(99);
        link.printList();
        System.out.println(" \r\n");

    }
}
