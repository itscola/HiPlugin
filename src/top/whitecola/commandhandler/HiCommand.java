package top.whitecola.commandhandler;


import org.apache.commons.lang.IllegalClassException;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import top.whitecola.annotations.ItsACommand;
import top.whitecola.utils.CommandUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class HiCommand implements TabExecutor {
    public Vector<ICommand> commands = new Vector<>();
    public String startWithCommand;
    public Plugin pl;
    private PluginCommand pluginCommand;
    public HiCommand(Plugin pl, String startWithCommand){
        this.pl = pl;
        this.startWithCommand = startWithCommand;
        registerThis(startWithCommand,pl);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!this.pl.isEnabled()){
            sender.sendMessage("§f§l[HiPlugin]>>["+this.pl.getName()+"]"+"插件并没有启动,无法使用此插件的命令命令!");
            return true;
        }
        if(args.length>0){
            ICommand ic = this.getCommandByName(args[0]);
            if(ic==null){
                sender.sendMessage("§f§l[HiPlugin]>>["+this.pl.getName()+"]§b没有在"+startWithCommand+"命令中,发现子命令"+args[0]+"!");
                return true;
            }
            if(!getCommandPremission(ic).trim().equals("") && !sender.hasPermission(getCommandPremission(ic).trim())){
                sender.sendMessage("§f§l[HiPlugin]>>["+this.pl.getName()+"]§b你没有权限这样做,你需要"+getCommandPremission(ic)+"权限!");
                return true;
            }
            boolean result = false;
            try {

                result = ic.onCommand(sender, command, label, args);
            }catch (Throwable e){
                e.printStackTrace();
                System.out.println("§f§l[HiPlugin]§c在处理"+this.pl.getName()+"插件的"+this.startWithCommand+"命令的子命令"+getCommandName(ic)+"时出现错误,错误日志已打印!");
                System.out.println("§f§l[HiPlugin]§c如果你认为那是个错误,请联系"+this.pl.getName()+"的开发者.");
            }
            if(!result){
                if(ic.getUsage().trim().equals("") || ic.getUsageDescripition().trim().equals("")){
                    return false;
                }
                sender.sendMessage("§f§l[HiPlugin]>>["+this.pl.getName()+"]§b"+"用法: "+ic.getUsage()+" - "+ic.getUsageDescripition());
                return true;
            }
                return true;


        }
        return false;
    }



    public ICommand getCommandByName(String com){
        for(ICommand ic : commands){
            if(getCommandName(ic).equalsIgnoreCase(com)){
                return ic;
            }
        }
        return null;
    }

    public void addCommand(ICommand icom){
        try {
            if (!isALegalCommand(icom)) {
                throw new IllegalClassException("§a[HiPlugin]" + getCommandName(icom) + "类,没有@ItsCommand注释,无法注册! 来源: " + pl.getName() + "插件.");
            }
            commands.add(icom);
            System.out.println("§a[HiPlugin]" + "成功为" + this.pl.getName() + "插件注册" + this.startWithCommand + "下的子命令: " + getCommandName(icom) + " §b" + "[将协助此插件更好的处理此命令与tab提示功能!]");
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    public void addCommand(ICommand icom,String permission,String permissionDefault){

        pluginCommand.setPermission(permission);
        pluginCommand.setPermissionMessage(permissionDefault);

        try {
            if (!isALegalCommand(icom)) {
                throw new IllegalClassException("§a[HiPlugin]" + getCommandName(icom) + "类,没有@ItsCommand注释,无法注册! 来源: " + pl.getName() + "插件.");
            }
            commands.add(icom);
            System.out.println("§a[HiPlugin]" + "成功为" + this.pl.getName() + "插件注册" + this.startWithCommand + "下的子命令: " + getCommandName(icom) + " §b" + "[将协助此插件更好的处理此命令与tab提示功能!]");
        }catch (Throwable e){
            e.printStackTrace();
        }
    }



    public void removeCommandByName(String com){
        this.commands.remove(getCommandByName(com));
    }

    public void removeAllCommands(){
        this.commands.clear();
        System.out.println("§a[HiPlugin]已为"+this.pl.getName()+"插件卸载"+this.pl.getName()+"插件的"+this.startWithCommand+"命令下的所有子命令!");
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if(!this.pl.isEnabled()){
            commandSender.sendMessage("§f§l[HiPlugin]>>["+this.pl.getName()+"]"+"§c插件并没有启动,无法使用此插件的命令命令!");
            return Arrays.asList("");
        }
        if(args.length>1){
            ICommand ic = getCommandByName(args[0]);
            if(ic==null){
                return Arrays.asList("");
            }


            if(commandSender instanceof Player &&!commandSender.hasPermission(getCommandPremission(ic))){
                return Arrays.asList("");
            }

            if(args.length-1>ic.getArgs().size()){
                return Arrays.asList("");
            }
            try {
                String temp = ic.getArgs().get(args.length - 2).trim();
                if(temp.contains("/")){
                    return Arrays.asList(temp.split("/"));
                }
                return ic.handleArg(commandSender,temp);
            }catch (Throwable e){
                e.printStackTrace();
                System.out.println("§c[HiPlugin]在处理"+this.pl.getName()+"插件的tab功能时出现错误!");
                System.out.println("§c[HiPlugin]为保证安全运行,HiPlugin已自动将错误tab功能取消!");
                System.out.println("§c[HiPlugin]如果您需要解决这个错误,请联系 "+this.pl.getName()+"插件的开发者!");
                System.out.println("§c[HiPlugin]错误日志: "+"§a处理插件: "+this.pl.getName()+"  处理命令"+startWithCommand+"的子命令"+getCommandName(ic)+" 处理参数号: "+(args.length - 2)+" 实际arg长度: "+args.length);
                return Arrays.asList("");
            }
        }
        return getAllCommandsName();

    }




    public List<String> getAllCommandsName(){
        List<String> list = new ArrayList<>();
        for(ICommand ic : this.commands){
            list.add(getCommandName(ic));
        }
        if(list==null || list.isEmpty()){
            return Arrays.asList("");
        }
        return list;
    }

    public static String getCommandName(ICommand icom){
        if(isALegalCommand(icom)){
            return icom.getClass().getAnnotation(ItsACommand.class).CommandNmae();
        }
        return "";
    }

    public static boolean isALegalCommand(ICommand icom){
        if(icom == null)
            return false;
        Class<?> clazz = icom.getClass();
        if(clazz.isAnnotationPresent(ItsACommand.class)){
            if(!clazz.getAnnotation(ItsACommand.class).CommandNmae().equals("")){
                return true;
            }
        }
        return false;

    }

    public static String getCommandPremission(ICommand icom){
        if(!isALegalCommand(icom)){
            return "hiplugin.example";
        }
        return icom.getClass().getAnnotation(ItsACommand.class).premission();
    }


    public void registerThis(String prefix,Plugin pl){
//        CommandUtils.registerCommandToBukkit(startWithCommand,);
        pluginCommand = CommandUtils.newAPluginCommand(startWithCommand,pl);
        pluginCommand.setExecutor(pl);
        pluginCommand.setTabCompleter(pl);
        pluginCommand.setUsage("/"+prefix +" <commands> [args] ...");
        CommandUtils.registerCommandToBukkit(prefix,pluginCommand);
    }

}
