package annotation;

import org.junit.Test;

public class Demo {
    @Before(parameter = 0)
    public void bbb(){
        System.out.println("ccc");
    }
    @Before(parameter = 1)
    public void bb(){
        System.out.println("ddd");
    }
    @Before(parameter = 2)
    public void b(){
        System.out.println("eee");
    }
    @Test()
    public void xxx(){
        System.out.println("aaa");
    }
    @Test()
    public void xx(){
        System.out.println("bbb");
    }
    @After(pm = 1)
    public void aaa(){
        System.out.println("fff");
    }
    @After(pm = 0)
    public void a(){
        System.out.println("ggg");
    }
    @After(pm = 2)
    public void aa(){
        System.out.println("hhh");
    }
}
