//模拟实现MCS锁
//实际开发中这样写的MCS是不能用的！！！
public class MCS_v1 {

    //MCS队列锁，DEMO
    static  long no = 0;
    static MCS_v1 mcs = new MCS_v1();
    public static void main(String[] args) throws InterruptedException {

        Method_1 method1 = new Method_1();
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(method1);
        Thread t2 = new Thread(method1);
        Thread t3 = new Thread(method1);
        Thread t4 = new Thread(method1);
        //Thread t5 = new Thread(method1);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        //t5.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        //t5.join();

        long end = System.currentTimeMillis();
        System.out.println(no);
        System.out.println((end - start));
    }

    static class Method_1  implements Runnable{

        @Override
        public void run() {
            for(int i = 0; i < 1000000; i++)
            {
                mcs.lock();
                no = no +1;
                mcs.unlock();
            }
        }
    }
    private final ThreadLocal<Node> node = ThreadLocal.withInitial(Node::new);
    private final AtomicReference<Node> tail = new AtomicReference<>();

    private static class Node {
        private volatile boolean locked = false;
        private volatile Node next = null;
    }

    public void lock() {
        //多个线程同时将自己的Node节点的lock上锁【设为true】
        Node node = this.node.get();
        node.locked = true;

        //只有多个线程中只有一个线程的Node可以作为Tail后的第一个节点
        Node pre = tail.getAndSet(node);

        //第一个线程的Node添加到tail后执行业务代码了。
        //第2，3，4...n个线程的Node的pre节点不为空，拿到前驱，将其next指向自己，自己自旋等待
        if (pre != null) {
            pre.next = node;
            while (node.locked) ;
        }
    }

    public void unlock() {
        //第一个线程 获取到自己的节点
        Node node = this.node.get();
        //只有最后一个节点执行
        if (node.next == null) {
            //将尾指针设为空，使最后一个节点脱离，
            //尾节点不需要设置next指针相关的状态因此return了
            if (tail.compareAndSet(node, null)) {
                return;
            }
            //如果此时有新线程进入，当前节点自旋，以等待其他线程将新节点加入到尾节点
            while (node.next == null) ;
            //其他线程加入节点完毕后，next不为空，停止自旋，之后按不是尾节点的代码逻辑执行
        }
        //第一个线程将下一个线程的节点的状态设置为false，解除线程自旋
        node.next.locked = false;
        //当前线程节点断开连接。
        //由于代码中有显式的Node node = this.node.get，ThreadLocal弱引用对象被强引用指向，遭遇GC时候不会被回收
        //该线程下次在lock方法中调用Node node = this.node.get();可将该节点再次取得，循环使用
        node.next = null;
    }
}