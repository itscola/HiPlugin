package top.whitecola.utils;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtils {
    public static <T> List<T> copyOfRange(List<T> list,int start,int to){
        List<T> list1 = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            if(i>=start & i<=to){
                list1.add(list.get(i));
            }
        }
        return list;
    }
}
