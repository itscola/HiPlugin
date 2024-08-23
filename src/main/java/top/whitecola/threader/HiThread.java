package top.whitecola.threader;


import java.util.concurrent.CopyOnWriteArrayList;

public class HiThread {
    public Thread th;
    public String hiThreadId;
    public int waitingSpace;
    private final CopyOnWriteArrayList<Runnable> tasks=new CopyOnWriteArrayList<>();

    public HiThread(String hiThreadId,int waitingSpace){
        this.hiThreadId = hiThreadId;
        this.waitingSpace = waitingSpace;
        th = new Thread(()->{
            while (true){
                synchronized (this){
                    try {
                        for (Runnable th : tasks) {
                            th.run();
                        }
                        tasks.clear();
                        Thread.sleep(waitingSpace);
                    }catch (Throwable e){
                        if(e instanceof ThreadDeath){
                            return;
                        }
                        e.printStackTrace();
                        System.out.println("§e[HiPlugin>>HiThread]"+hiThreadId+"的线程运行时出现问题,已打印.");
                    }
                }

            }
        });
    }

    public String getThisThreadId(){
        return hiThreadId;
    }

    public void addTask(Runnable th){
        tasks.add(th);
    }


    public void cleanTask(){
        tasks.clear();
    }

    public void start(){
        th.start();
    }

    public void stop(){
        th.stop();
    }

}
