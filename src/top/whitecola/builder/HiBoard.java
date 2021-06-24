package top.whitecola.builder;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import top.whitecola.utils.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;


//todo Warning: this class still under development
public class HiBoard {
    private Scoreboard scb;
    private Objective objective;
    private String id;
    private Vector<String> contexts = new Vector<>(18);

    public HiBoard(Scoreboard scb,String id,String title){
        this.id = id;
        this.scb = scb;
        this.objective = scb.registerNewObjective(id,"dummy",title);
    }

    public HiBoard(String id,String title){
        this(Bukkit.getScoreboardManager().getNewScoreboard(),id,title);
    }

    public HiBoard setEntireContext18Solts(Vector<String> contexts){
        this.contexts = contexts;
        flush();
        return this;
    }

    public HiBoard setContext1Solt(String context,int index){
        this.contexts.set(index,context);
        flush();
        return this;
    }

//    public HiBoard Clean

    public HiBoard flush(){


        return this;
    }

    public HiBoard setDisplaySolt(DisplaySlot solt){
        this.objective.setDisplaySlot(solt);
        return this;
    }

    public Scoreboard getScoreBoard(){
        return scb;
    }

    public Scoreboard setScoreBoard(Scoreboard scb){
        this.scb = scb;
        return scb;
    }

    public HiBoard setTitle(String title){
        this.scb.getObjective(id).setDisplayName(title);
        return this;
    }

    private Vector<String> getNeededVector(Vector<String> contexts){
        int first = getTheFirstElementIndex(contexts);
        int last = getTheLastestElementIndex(contexts);
        return (Vector<String>) CollectionUtils.copyOfRange(contexts,first,last);
    }

    private int getTheLastestElementIndex(Vector<String> contexts){
        Vector<String> reverseVector = new Vector<>(contexts);
        Collections.reverse(reverseVector);
        return contexts.size() - getTheFirstElementIndex(reverseVector);
    }

    private int getTheFirstElementIndex(Vector<String> contexts){
        for(int i=0;i<contexts.size();i++){
            if(contexts.get(i)!=null)
                return i;
        }
        return -1;
    }
}
