package top.whitecola.confighandler;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.plugin.Plugin;
import top.whitecola.PluginHandler.HiMsg;
import top.whitecola.datahandler.HiFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class HiConfig<T> implements IData<T>{
    public File file;
    public T config;
    public Class<T> configClass;
    public Charset charset;
    public Constructor<T> cc;
    public Gson g = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().setLenient().create();
    public Plugin pl;
    public HiConfig(String path, Class<T> configClass, Charset charset, Plugin pl){
        this.pl = pl;
        this.file = new File(path);
        this.configClass = configClass;
        try {
            this.cc = configClass.getConstructor(new Class<?>[0]);
            this.cc.setAccessible(true);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        this.charset = charset;
        try {
            loadConfig();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件加载失败!");
        }
    }
    @Override
    public void saveConfig() {
        if(!this.file.getParentFile().exists()){
            this.file.getParentFile().mkdirs();
        }
        try {
            HiFile.writeTextToFile(g.toJson(this.config,this.configClass),this.file,false,this.charset);
        } catch (Throwable e) {
            e.printStackTrace();
            HiMsg.makeDebugMsgAndSend("配置(储存)文件"+this.file.getName()+"在保存时出错.");
        }

    }

    @Override
    public void loadConfig() throws IOException {
        if(this.file.getParentFile().exists()&&file.isFile()){
            this.config =  g.fromJson(HiFile.readTextFromFile(file,charset), this.configClass);
            if(this.config ==null){
                System.out.println("§b[HiPlugin>>HiConfig]为"+this.pl.getName()+"插件 加载配置(储存)文件"+file.getName()+"失败,将使用默认配置!请检查你设置的配置文件.");
                config = getDefConfig();
            }else{
                System.out.println("§a[HiPlugin>>HiConfig]已成功为"+this.pl.getName()+"插件 加载"+file.getName()+"配置文件!");

            }
        }else{
            System.out.println("§b[HiPlugin>>HiConfig]即将为您创建"+this.pl.getName()+"插件的 "+this.file.getName()+"配置(储存)文件!");
            config = getDefConfig();
            saveConfig();
        }
    }

    @Override
    public T getDefConfig(){
        try {
            return this.cc.newInstance(new Object[0]);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public ArrayList<String> checkConfig(){
        ArrayList<String> al = new ArrayList<>();
        for(Field f : this.config.getClass().getFields()){
            try {
                Object obj = f.get(this.config);
                if(obj==null){
                    al.add(f.getName());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return al;
    }

    @Override
    public void reloadConfig() {
        try {
            this.loadConfig();
            this.saveConfig();
        } catch (IOException e) {
            System.out.println("§b[HiPlugin>>HiConfig]重载"+this.pl.getName()+"插件的"+this.file.getName()+"配置(储存)文件时失败!");
        }
    }

    @Override
    public void checkConfigs(){
        ArrayList<String> al = checkConfig();
        if(!al.isEmpty()){
            System.out.println("§c§l[HiPlugin>>debug]检测到有选项为null,被托管插件:"+this.pl.getName()+" 位置:"+file.getName()+"配置文件的"+al.toString()+"选项,如果无视,可能会导致插件运行时发生错误!");
        }
    }

    @Override
    public T getConfig(){
        checkConfigs();
        return this.config;
    }
}
