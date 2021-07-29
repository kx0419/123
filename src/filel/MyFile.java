package filel;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class File {
    static ArrayList<String> a=new ArrayList<>();
    static ArrayList<String> b=new ArrayList<>();
    public void readFile() throws IOException {

        String fileName ="/home/asdf/桌面/kx.txt";
        FileReader fileReader = new FileReader(fileName);

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line =bufferedReader.readLine();

        while (line!=null){
            System.out.println(line);
            line = bufferedReader.readLine();
            String[] s=line.split(" ");//通过空格分成两部分
            a.add(s[0]);
            b.add(s[1]);
            //System.out.println(s[0]+s[1]);
            System.out.println(a);
            System.out.println(b);
        }
        bufferedReader.close();
        fileReader.close();
    }
}
class java{
    static String cre="";
    //private static String classn;

    public static void writerJava(ArrayList<String> a,ArrayList<String> b,String classname) throws IOException {
        String path="/home/asdf/下载/06-13(1)/06-13/homework/src/filel/"+classname+".java";
        File file=new File("/home/asdf/下载/06-13(1)/06-13/homework/src/filel/"+classname+".java");
        cre="package filel;\n"+"public class "+classname +"{\n";
        String cres="";
        for (int i = 0; i <a.size(); i++) {
            cres="private"+" "+b.get(i)+" "+a.get(i)+";\n";
            cre+=" "+cres;
        }
        cre+="}";
        byte[] y=cre.getBytes();
        OutputStream outputStream=new FileOutputStream(String.valueOf(file));
        outputStream.write(y);
        outputStream.flush();
    }
}
class reflex{
    public void method() throws ClassNotFoundException {
        String classname="/home/asdf/桌面/kx.txt";
        Class<String> cl=Class.forName(classname);
    }
}
