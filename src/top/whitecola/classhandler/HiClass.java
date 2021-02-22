package top.whitecola.classhandler;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class HiClass {
    public static List<Class<?>> getAllClassesByPackageName(String packageName, boolean isAll,Enumeration<URL> dirs){
        List<Class<?>> clazzs = new ArrayList<>();
        try {
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    getAllClassesByFile(packageName, filePath, isAll, clazzs);
                }
            }

        }catch (Throwable e){
            e.printStackTrace();
            System.out.println("§4读取包时发生错误,错误已打印到控制台!");
        }

        return clazzs;
    }

    private static void getAllClassesByFile(String packageName, String filePath, boolean isAll, List<Class<?>> clazzs) {
        File dir = new File(filePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        File[] dirFiles = dir.listFiles(file -> {
            boolean acceptDir = isAll && file.isDirectory();
            boolean acceptClass = file.getName().endsWith("class");
            return acceptDir || acceptClass;
        });

        for (File file : dirFiles) {
            if (file.isDirectory()) {
                getAllClassesByFile(packageName + "." + file.getName(), file.getAbsolutePath(), isAll, clazzs);
            } else {
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    clazzs.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + className));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }



//    public static ClassLoader getPluginClassLoader(JavaPlugin plugin){
//        plugin.getClass();
//        return null;
//    }
    public static String getPackageDirName(String packageName){
        return packageName.replace(".","/");
    }
}
