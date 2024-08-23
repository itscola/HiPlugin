package top.whitecola.bridges;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

public class HiPlayer extends HiEntity{
    public Player p;

    public HiPlayer(Player p){
        super(p);
        this.p = p;
    }



    public int getPlayerPing() throws Throwable{
        Object np = HiReflect.getNMSPlayer(p);
        Class<?> npclass = HiReflect.getNMSClass("EntityPlayer");
        Field pingfield = npclass.getField("ping");
        Object obj = pingfield.get(np);
        int ping = 0;
        try {
            ping = (int) obj;
        }catch (Throwable e){}
        return ping;
    }

//    public void disconnectPlayer(String reason) throws Throwable{
//        Class<?> cpc = p.getClass();
//        Method disconnectMethod = cpc.getMethod("disconnect",String.class);
//        disconnectMethod.invoke(p,reason);
//    }


    public void attackPlayer(Player p) throws Throwable{
        super.attackEntity(p,1);
    }

    public void attackEntity(Entity goalEntity) throws Throwable {
        super.attackEntity(goalEntity, 0);
    }

    public boolean ridePlayer(Player p) throws Throwable{
        return super.rideEntity(p);
    }

    @Override
    public void stopRiding() throws Throwable {
        super.stopRiding();

    }

    @Override
    public boolean rideEntity(Entity goal) throws Throwable {
        return super.rideEntity(goal);
    }

    public void respawnPlayer() throws Throwable{
        this.p.getClass().getMethod("respawn").invoke(this.p);
    }

    //    public static void setPlayerCustomName(String name,Player p) throws Throwable{
//        Class<?> cpc = p.getClass();
//        Method handle = cpc.getMethod("getHandle");
//        Object np = handle.invoke(p);
//        Class<?> enclass = HiReflect.getNMSClass("Entity");
//        Method getDWMethod = enclass.getMethod("getDataWatcher");
//        Object dataWatcher = getDWMethod.invoke(np);
//        Class<?> dwclass = HiReflect.getNMSClass("DataWatcher");
//        Class<?> dwo = HiReflect.getNMSClass("DataWatcherObject");
//        EntityPlayer
//        dwclass.getMethod("set");
//        EntityPlayer
//    }
//    public static void setPlayerDisplayName(String name, Player p) throws Throwable{
//        Class<?> cpc = p.getClass();
//        Method met = cpc.getMethod("setDisplayName",String.class);
//        met.invoke(p,name);
//        p.setCustomName();
//        EntityCow
//
//    }

}
