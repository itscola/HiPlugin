package top.whitecola.confighandler;

import java.io.IOException;
import java.util.ArrayList;

public interface IData<T> {
    default void saveConfig() throws IOException {

    }

    default void loadConfig() throws IOException {

    }

    default void reloadConfig(){}

    default T getConfig(){
        return null;
    }

    default void checkConfigs(){}

    default ArrayList<String> checkConfig(){
        return null;
    }

    default T getDefConfig(){
        return null;
    }
}
