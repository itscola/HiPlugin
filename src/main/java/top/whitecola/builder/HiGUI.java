package top.whitecola.builder;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;


public class HiGUI implements IGUI{
    public Inventory inv;
    public Player player;
    private int currentPage=0;
    private int MaxPagesNum;
    private final static int ONE_INVENTORY_MAX = 45;
    private final static int ONE_INVENTORY_MAX_ITEMS = 32;

    public HiGUI(){

    }

    public HiGUI(Player player,Inventory inv){
        player = player;
        inv = inv;
        init();
    }

    private void init(){

    }

    public void flushPage(){
        int pageNum = 0;


    }

    @Override
    public void open() {

    }

    public void openPage(int page){

    }

    public int getPage(){
        return currentPage;
    }

    @Override
    public void close() {

    }

    @Override
    public HiItem addItem(HiItem hiItem) {
        return hiItem;
    }

    @Override
    public HiItem addItem(HiItem hiItem, int index) {
        return hiItem;
    }

    @Override
    public HiItem setItem(HiItem hiItem, int index) {
        flushPage();
        return hiItem;
    }

    @Override
    public void nextPage() {

    }

    @Override
    public void lastPage() {

    }


}
