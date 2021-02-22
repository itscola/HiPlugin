package top.whitecola.bridges;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;


public class HiEntity {
    Entity en;
    public HiEntity(Entity en){
        this.en = en;
    }

    public boolean rideEntity(Entity goal) throws Throwable{
        if(en instanceof Player){
            Player p = (Player)en;
            Object entityPlayer = HiReflect.getNMSPlayer(p);
            Class<?> entityClass = HiReflect.getNMSClass("Entity");
            Object nmsEntity = goal.getClass().getMethod("getHandle").invoke(goal);
            try {
                return (boolean)entityClass.getMethod("startRiding",entityClass).invoke(entityPlayer,nmsEntity);
            }catch (Throwable e){
                return false;
            }
        }
        Class<?> nmsen = HiReflect.getNMSClass("Entity");
        Object nmsenobj = HiReflect.getNMSEntity(goal);
        Object obj = nmsen.getMethod("startRiding",HiReflect.getNMSClass("Entity")).invoke(nmsenobj,goal.getClass().getMethod("getHandle").invoke(goal));
        try {
            return (boolean)obj;
        }catch (Throwable e){}
        return false;

    }

    public void stopRiding() throws Throwable{
        if(this.en instanceof Player){
            Player p = (Player)en;
            Object entityPlayer = HiReflect.getNMSPlayer(p);
            Class<?> epclass = HiReflect.getNMSClass("EntityPlayer");
            epclass.getMethod("stopRiding").invoke(entityPlayer);
            return;
        }
        Class<?> nmsen = HiReflect.getNMSClass("Entity");
        Object nmsenobj = HiReflect.getNMSEntity(en);
        nmsen.getMethod("stopRiding").invoke(nmsenobj);
        return;
    }

    public void attackEntity(Entity goalEntity,float f) throws Throwable{
        if(this.en instanceof Player){
            Player p = (Player)en;
            Class<?> cpc = p.getClass();
            Object np = HiReflect.getNMSPlayer(p);
            Class<?> npclass = HiReflect.getNMSClass("EntityPlayer");
            Method am = npclass.getMethod("attack",HiReflect.getNMSClass("Entity"));

            Class<?> ce = goalEntity.getClass();
            Object entityObj = HiReflect.getNMSEntity(goalEntity);
            am.invoke(np,entityObj);
            return;
        }

        Class<?> nmsen = HiReflect.getNMSClass("Entity");
        Object nmsenobj = HiReflect.getNMSEntity(en);
        Class<?> ds = HiReflect.getNMSClass("DamageSource").getClass();
        Method damageEntitty = nmsen.getMethod("damageEntity",ds.getClass(),float.class);
        Object obj = ds.getField("GENERIC").get(null);
        damageEntitty.invoke(nmsenobj,obj,f);
//        EntityZombie
        return;


    }
}
