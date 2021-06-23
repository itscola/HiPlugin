package top.whitecola.builder;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import top.whitecola.PluginHandler.HiMsg;

import java.util.Vector;
import java.util.function.Consumer;

public class HiInventory {
    public Inventory inv;
    public JavaPlugin pl;
    public Vector<ItemStack> clickableItems = new Vector<>();

    public HiInventory(String invName, int size, JavaPlugin pl){
        this.inv = Bukkit.createInventory(null,size,invName);
        this.pl = pl;
    }

    public Inventory getInventory(){
        return this.inv;
    }

    public void open(Player p){
        if(!p.isOnline()){
            return;
        }
        p.openInventory(this.inv);
    }

    public HiInventory removeItem(int index) throws IllegalArgumentException{
        if(index<0 || index>inv.getSize()-1){
            throw new IllegalArgumentException("The index < 0 or > the size of the inventory. ");
        }
        ItemStack item = inv.getItem(index);
        if(clickableItems.contains(item))
            clickableItems.remove(item);

        item.setAmount(0);

        return this;
    }

    public HiInventory addItem(ItemStack is, Consumer<InventoryClickEvent> clickEvent){
        this.inv.addItem(is);
        checkAndDo(is,clickEvent);

        return this;
    }

    public HiInventory setItem(ItemStack is,int index, Consumer<InventoryClickEvent> clickEvent){
        this.inv.setItem(index,is);

        checkAndDo(is,clickEvent);

        return this;
    }

    public HiInventory addItem(HiItem ht, Consumer<InventoryClickEvent> clickEvent){
        addItem(ht.getItem(),null);
        checkAndDo(ht.getItem(),clickEvent);

        return this;
    }

    public HiInventory setItem(HiItem ht,int index, Consumer<InventoryClickEvent> clickEvent){
        this.inv.setItem(index,ht.getItem());


        checkAndDo(ht.getItem(),clickEvent);


        return this;
    }

    public boolean equals(HiInventory hin){
        if(hin.inv.equals(hin)){
            return true;
        }
        return false;
    }

    public void checkAndDo(ItemStack item,Consumer<InventoryClickEvent> consumer){
        if(consumer==null)
            return;

        clickableItems.add(item);

        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onClickListner(InventoryClickEvent e){
                try {



                    if(!clickableItems.contains(e.getCurrentItem()))
                        return;


                    if(!e.getInventory().equals(inv) || !e.getCurrentItem().equals(item))
                        return;

                    consumer.accept(e);
                }catch (Throwable ee){
                    ee.printStackTrace();
                    HiMsg.sendPluginMessage("在处理 "+pl.getName()+"插件的物品点击事件时出现问题. 异常已打印,请联系此插件作者.");
                    return;
                }

            }
        },pl);


    }

}
