package top.whitecola.basic;

public class HiRepeat {
    public static void repeat(int index,Runnable run){
        for(int i=0;i<index;i++){
            run.run();
        }
    }
}
