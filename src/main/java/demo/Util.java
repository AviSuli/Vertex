package demo;

public class Util {
    public static void echo(String msg){
        System.out.println(String.format("%s: %s", msg, Thread.currentThread().getName()));
    }

}
