package top.whitecola.PluginHandler;

import org.bukkit.command.CommandSender;


public class HiMsg {
    public static void makeDebugMsgAndSend(String msg){
        HiMsg.sendDebugMessage(msg,new Throwable().getStackTrace()[1].toString());
    }

    public static void sendDebugMessage(String msg,String[] arg,String command,String callMsg){
            System.out.println("§l[HiPlugin][debug]§d"+msg+"[Command:"+command+" "+getAllArr(arg)+"][CallMessage]: "+callMsg);
    }
    public static void sendDebugMessage(String msg,String callMsg){
            System.out.println("§l[HiPlugin][debug]§d" + msg + "[CallMessage]: "+callMsg);
    }
    public static String getAllArr(String[] arr){
        String All = "";
        for(String str : arr){
            All+=str+" ";
        }
        return All;
    }

    public static boolean hasNullString(String[] arg3, CommandSender arg0,Boolean allowLast){
        int index = allowLast==true?arg3.length:arg3.length-1;
        for(int i=0;i<index;i++){
            if(arg3[i].equals("")){
                if(arg0!=null) {
                    arg0.sendMessage("第" + (i + 1) + "个参数,不能为空! index :" + i + " 内容为 :" + arg3[1]);
                }
                return true;
            }
        }
        return false;
    }

    public static void sendPluginMessage(String msg){
        System.out.println("§c[HiPlugin]"+msg);
    }

}
