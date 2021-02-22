package top.whitecola.threader;

public class Timer {
    public long interval_S;
    public long nowTime;
    public Runnable runa;
    public String sonId;
    public Timer(long interval_S,Runnable runa,long nowTime,String sonId){
        this.interval_S = interval_S;
        this.runa = runa;
        this.nowTime = nowTime;
        this.sonId = sonId;
    }

    public void setNowTime(){
        this.nowTime = System.currentTimeMillis();
    }
}
