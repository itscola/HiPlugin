package top.whitecola.bridges;
import java.lang.reflect.Method;

public class HiItemNBT {
    Object itemNBT;
    public Class<?> nbtclass;


    public HiItemNBT() throws Throwable{
        this.itemNBT = HiReflect.getNMSClass("NBTTagCompound").newInstance();
        nbtclass = itemNBT.getClass();
    }

    public HiItemNBT(Object nbt) throws Throwable{
        this.itemNBT = nbt;
        nbtclass = itemNBT.getClass();
    }

    public HiItemNBT set(String key,String value) throws Throwable{
        Method sm = nbtclass.getMethod("set",String.class,HiReflect.getNMSClass("NBTBase"));
        sm.invoke(itemNBT,key,HiReflect.getNMSClass("NBTTagString").getConstructor(String.class).newInstance(value));
        return this;
    }

    public HiItemNBT removeKey(String key) throws Throwable{
        nbtclass.getMethod("remove",String.class).invoke(key);
        return this;
    }

    public Object getNBT(){
        return itemNBT;
    }

//    public static Map


}
