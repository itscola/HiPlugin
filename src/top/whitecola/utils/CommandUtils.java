package top.whitecola.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.PluginCommandYamlParser;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class CommandUtils {

    public static void registerCommandToBukkit(String prefix, Command command) {
        try {
            Field comMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            comMapField.setAccessible(true);
            Object commap = comMapField.get(Bukkit.getServer());
            SimpleCommandMap simpleCommandMap = (SimpleCommandMap) commap;
            simpleCommandMap.register(prefix,command);



        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public static PluginCommand newAPluginCommand(String prefix,Plugin pl){
        try {
            Constructor con = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            con.setAccessible(true);
            return (PluginCommand) con.newInstance(prefix,pl);

        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }

    }

}
