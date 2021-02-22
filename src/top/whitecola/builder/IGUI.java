package top.whitecola.builder;

public interface IGUI {
    default void open(){}
    default void close(){}
    default void nextPage(){}
    default void lastPage(){}
//    default void getGUI(){};
}
