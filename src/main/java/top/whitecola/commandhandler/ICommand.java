package top.whitecola.commandhandler;

import org.jetbrains.annotations.NotNull;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public interface ICommand extends CommandExecutor {
    @NotNull
    public default List<String> getArgs(){
        return new Vector<>();
    }

    @NotNull
    public default List<String> handleArg(CommandSender sender, String handleArg){
        return Arrays.asList("");
    }

    @NotNull
    public default String getUsage(){
        return "";
    }

    @NotNull
    public default String getUsageDescripition(){
        return "";
    }



}
