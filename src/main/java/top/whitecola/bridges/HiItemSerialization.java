package top.whitecola.bridges;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import top.whitecola.builder.HiItem;
import top.whitecola.datahandler.HiFile;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.Charset;


public class HiItemSerialization {
        public static boolean createItemSaving(JavaPlugin pl, HiItem ht, String id,boolean recover) throws Throwable {
        File file = new File(pl.getDataFolder()+"/HiPlugin_Hitems/"+id+".hitem");
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if(recover){
            if(!file.isFile()){
                file.createNewFile();
            }
            HiFile.writeTextToFile(getItemNBTTagString(ht.getItem()),file,false, Charset.forName("utf8"));
            return true;

        }else{
            if(!file.isFile()){
                HiFile.writeTextToFile(getItemNBTTagString(ht.getItem()),file,false, Charset.forName("utf8"));
                return true;
            }else{
                return false;
            }
        }
    }





    public static HiItem getItemSaving(JavaPlugin pl, String id,Material mat) throws Throwable{
        ItemStack is = null;
        File file = new File(pl.getDataFolder()+"/HiPlugin_Hitems/"+id+".hitem");
        if(file.getParentFile().exists()){
            if(file.isFile()){
                String itemNBTTagSaving = HiFile.readTextFromFile(file,Charset.forName("utf8"));
                if(!itemNBTTagSaving.equals("NoNBTTag")){
                    Object nbtTag = getNBTbyNbtText(itemNBTTagSaving);
                    is = getItemByNbt(nbtTag,mat);
                    return new HiItem(is);
                }
            }
        }
        return new HiItem(new ItemStack(mat));
    }



    public static String getItemNBTTagString(ItemStack is) throws Throwable{
        Object tagData = getItemNBTTag(is);
        if(tagData!=null){
            Class<?> tagclass = tagData.getClass();
            Method toStringMethod = tagclass.getDeclaredMethod("toString");
            toStringMethod.setAccessible(true);
            Object nbt = toStringMethod.invoke(tagData);
            if(nbt instanceof String){
                return (String)nbt;
            }
        }
        return "NoNBTTag";

    }

    public static boolean hasItemNBTTag(ItemStack is) throws Throwable{
        Class<?> nmsisClass = HiReflect.getNMSClass("ItemStack");
        Object nmsis = getNMSItemStackByItem(is);
        Method hasTagMethod = nmsisClass.getMethod("hasTag");
        try {
            return (boolean) hasTagMethod.invoke(nmsis);
        }catch (Throwable e){
            return false;
        }
    }

    public static Object getItemNBTTag(ItemStack is) throws Throwable{
//        Class<?> cls2 = Class.forName(Bukkit.getServer().getClass().getPackage().getName()+".inventory.CraftItemStack");
//        Method asnms = cls2.getMethod("asNMSCopy",is.getClass());
//        asnms.setAccessible(true);
//        net.minecraft.server.v1_12_R1.ItemStack
        if(!hasItemNBTTag(is)){
            return null;
        }
        Object nmsis = getNMSItemStackByItem(is);
        Class<?> nmsclass = nmsis.getClass();
        Field tagfield = nmsclass.getDeclaredField("tag");
        tagfield.setAccessible(true);
        Object tagData = tagfield.get(nmsis);
        return tagData;
    }

    public static Object getNBTbyNbtText(String NBTtext) throws Throwable{
//        NBTTagCompound
//        NBTBase
//        NBTTagString
//        MojangsonParser.parse()
//        try {
//            return MojangsonParser.parse(NBTtext);
//        } catch (MojangsonParseException e) {
//            e.printStackTrace();
//        }
//        Class<?> MojangsonParserClass = Class
        Class<?> mojangsonParserClass = HiReflect.getNMSClass("MojangsonParser");
        Method parseMethod = mojangsonParserClass.getMethod("parse",String.class);
        Object NBTTagCompoundObejct = parseMethod.invoke(null,NBTtext);
        return NBTTagCompoundObejct;
    }

    public static ItemStack getItemByNbt(Object NBTTagCompoundObejct, Material material) throws Throwable{
//        CraftItemStack
        ItemStack is = new ItemStack(material);
        Object nmsItem = getNMSItemStackByItem(is);
        Class<?> nmsItemClass = nmsItem.getClass();
//        net.minecraft.server.v1_12_R1.ItemStack
        Method setTagMethod = nmsItemClass.getMethod("setTag",NBTTagCompoundObejct.getClass());
        setTagMethod.invoke(nmsItem,NBTTagCompoundObejct);
        return getItemStackByNMSItem(nmsItem);
    }

    public static Object getNMSItemStackByItem(ItemStack is) throws Throwable{
        Class<?> cls2 = Class.forName(Bukkit.getServer().getClass().getPackage().getName()+".inventory.CraftItemStack");
        Method asnms = cls2.getMethod("asNMSCopy",is.getClass());
        asnms.setAccessible(true);
        return asnms.invoke(null,is);
    }

    public static ItemStack getItemStackByNMSItem(Object nmsItem) throws Throwable{
        ItemStack is = null;
        Class<?> cls2 = Class.forName(Bukkit.getServer().getClass().getPackage().getName()+".inventory.CraftItemStack");
        Method asnms = cls2.getMethod("asBukkitCopy",nmsItem.getClass());
        Object isObj = asnms.invoke(null,nmsItem);
        if(isObj instanceof ItemStack){
            return (ItemStack)isObj;
        }
        return null;
    }
}

