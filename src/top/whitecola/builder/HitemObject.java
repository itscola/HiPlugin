package top.whitecola.builder;

public class HitemObject {
//    CraftPlayer
//    public static void writeHitemToFile(HiItem ht,File file) throws IOException{
//        ObjectOutputStream ops = new ObjectOutputStream(new FileOutputStream(file,false));
//        ops.writeObject(ht);
//        ops.flush();
//        ops.close();
//    }
//
//    public static HiItem readObjectToHiItem(File file) throws IOException,ClassNotFoundException {
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
//        Object obj = ois.readObject();
//        ois.close();
//        if(obj instanceof HiItem){
//            return (HiItem)obj;
//        }
//        return null;
//
//    }
//
//    public static void CreateHitemSaving(JavaPlugin pl,HiItem ht,String id){
//        File file = new File(pl.getDataFolder()+"/HiPlugin_Hitems/"+id+".hitem");
//        if(!file.getParentFile().exists()){
//            file.getParentFile().mkdirs();
//        }
//        if(!file.isFile()){
//            try {
//                file.createNewFile();
//                ObjectOutputStream ops = new ObjectOutputStream(new FileOutputStream(file,false));
//                ops.writeObject(ht);
//                ops.flush();
//                ops.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//                System.out.println("§b[HiPlugin]§r[HitemObject]>>>在为"+pl.getName()+"插件创建物品存储时出现错误,错误已打印!");
//                System.out.println("[HiPlugin]日志: 调用插件命:"+pl.getName()+" 创建物品储存名: "+ht.getItemMeta().getDisplayName()+" 目标路径: "+pl.getDataFolder()+"/HiPlugin_Hitems/"+"_"+id+".hitem");
//            }
//        }else{
//            throw new RuntimeException("[HiPlugin]>>>["+pl.getName()+"]"+"物品id: "+id+" 已存在!");
//        }
//
//
//    }
//
//    public static HiItem getHitemFromSaving(JavaPlugin pl,String id){
//        File file = new File(pl.getDataFolder()+"/HiPlugin_Hitems/"+id+".hitem");
//        if(file.isFile()){
//            try {
//                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
//                try {
//                    Object obj = ois.readObject();
//                    if(obj instanceof HiItem){
//                        return (HiItem)obj;
//                    }
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                    System.out.println("[HiPlugin]>>>"+pl.getName()+" : "+id);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                System.out.println("[HiPlugin]: "+pl.getName()+"插件在获取id为"+id+"的物品时获取失败! 找不到目标文件");
//            }
//        }
//        return null;
//    }
}
