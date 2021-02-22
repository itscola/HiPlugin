package top.whitecola.bridges;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class HiObeject {
    ConcurrentHashMap<String,Object> objects = new ConcurrentHashMap<>();

    public void add(String name,Object obj){
        this.add(name,obj);
    }

    public Object getObjByName(String name){
        for(String na : objects.keySet()){
            if(na.equals(name)){
                return na;
            }
        }
        return null;
    }

    public void removeObjectByName(String name){
        this.objects.remove(getObjByName(name));
    }

    public void cleanAll(){
        this.objects.clear();
    }

    public List<String> getAllName(){
        List<String> list = new ArrayList<>();
        objects.forEach((k,v)->{
            list.add(k);
        });
        return list;
    }

    public List<Object> getAllKey(){
        List<Object> list = new ArrayList<>();
        objects.forEach((k,v)->{
            list.add(v);
        });
        return list;
    }
}
