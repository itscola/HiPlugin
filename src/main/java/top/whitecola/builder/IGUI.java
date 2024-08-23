package top.whitecola.builder;

public interface IGUI {
    default void open(){}
    default void close(){}
    default void nextPage(){}
    default void lastPage(){}
    default HiItem addItem(HiItem hiItem){return hiItem;}
    default HiItem addItem(HiItem hiItem,int index){return hiItem;}
    default HiItem setItem(HiItem hiItem,int index){return hiItem;}

//    default void getGUI(){};
}
