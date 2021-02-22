package top.whitecola.confighandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class HiINI implements IData{
    public ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();
    public Charset charset;
    public File file;
    public DefINI defINI;
    public Properties psp;

    public HiINI(String path, DefINI defini, Charset charset){
        this.file = new File(path);
        this.charset = charset;
        this.defINI = defini;
    }


    public Properties mapToINI(ConcurrentHashMap<String,String> map){
        Properties psp = new Properties();
        map.forEach((k,v)->{
            psp.setProperty(k,v);
        });
        return psp;
    }

    public ConcurrentHashMap<String,String> INIToMap(Properties psp,DefINI ini){
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();
        ini.getDefConfig().forEach((k,v)->{
            map.put(k,psp.getProperty(k));
        });
        return map;
    }


    @Override
    public void saveConfig() throws IOException {
        if(!this.file.getParentFile().exists()){
            this.file.getParentFile().mkdirs();
        }
        psp = mapToINI(this.map);
        psp.store(new FileOutputStream(this.file,false),null);
    }

    @Override
    public void loadConfig() throws IOException {
        if(this.file.getParentFile().exists()&&file.isFile()){
            psp.load(new FileInputStream(file));
            this.map = INIToMap(psp,defINI);
        }else{
            System.out.println("§b[HiPlugin>>HiINI]即将为您创建"+this.file.getName()+"配置文件!");
            psp = mapToINI(DefINI.map);
            saveConfig();
        }
    }


    public void setINI(String key,String value){
        this.map.put(key,value);
        this.psp = mapToINI(this.map);
    }

    public String getPropertyByKey(String keyName){
        return map.get(keyName);
    }
}
