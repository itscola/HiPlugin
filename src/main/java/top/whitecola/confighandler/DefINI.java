package top.whitecola.confighandler;


import com.sun.istack.internal.NotNull;

import java.util.concurrent.ConcurrentHashMap;

public interface DefINI {
    public ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();

    @NotNull
    public default ConcurrentHashMap<String,String> getDefConfig(){
        if(!map.isEmpty()){
            return map;
        }else{
            return setDefConfig();
        }
    }

    public default ConcurrentHashMap<String,String> setDefConfig(){
        //do ini.
        return this.map;
    }


}
