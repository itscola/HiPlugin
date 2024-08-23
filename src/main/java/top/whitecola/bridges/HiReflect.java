package top.whitecola.bridges;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public class HiReflect {
    public static @Nullable Class<?> getNMSClass(String className){
        Class<?> cls = null;
        try {
            cls = Class.forName(Bukkit.getServer().getClass().getPackage().getName().replace("org.bukkit.craftbukkit", "net.minecraft.server")
                    + "." + className);
        } catch (ClassNotFoundException e) { }
        return cls;
    }

    public static @Nullable Object getNMSEntity(Entity en) throws Throwable{

        return en.getClass().getMethod("getHandle").invoke(en);

    }

    public static @Nullable Object getNMSPlayer(Player p) throws Throwable{

        return p.getClass().getMethod("getHandle").invoke(p);

    }


}
