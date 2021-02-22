package top.whitecola.threader;


import top.whitecola.PluginHandler.HiMsg;

import java.util.Vector;

public class HiTimer {
    Thread th = null;
    Vector<Timer> ts = new Vector<>();
    public HiTimer(String timerId){
        th = new Thread(()->{
            HiMsg.makeDebugMsgAndSend("ÒÑ¿ªÆô"+timerId+"!");
            while(true){
                try {
                    ts.forEach(t->{
                        if(toSecond(System.currentTimeMillis()-t.nowTime)>=t.interval_S){
                            t.runa.run();
                            t.setNowTime();
                        }
                    });

                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private long toSecond(long now){
        return (now/1000);
    }

    public void addTimer(Runnable th,int interval_S,String sonId){
        ts.add(new Timer(interval_S,th,System.currentTimeMillis(),sonId));
    }

    public Timer getTimer(String sonId){
        for(Timer t : ts){
            if(sonId.equalsIgnoreCase(t.sonId)){
                return t;
            }
        }
        return null;
    }

    public void removeTimer(String sonId){
        Timer ti = getTimer(sonId);
        if(ti==null){
            return;
        }
        this.ts.remove(ti);
    }

    public void cleanAll(){
        ts.clear();
    }

    public void cancle(){
        th.stop();
    }

    public void start(){
        th.start();
    }

}
