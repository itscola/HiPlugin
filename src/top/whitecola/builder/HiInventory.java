package top.whitecola.builder;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class HiInventory {
    public Inventory inv;

    public HiInventory(String invName,int size){
        this.inv = Bukkit.createInventory(null,size,invName);
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

    public HiInventory addItem(ItemStack is){
        this.inv.addItem(is);
        return this;
    }

    public HiInventory setItem(ItemStack is,int index){
        this.inv.setItem(index,is);
        return this;
    }

    public HiInventory addItem(HiItem ht){
        this.inv.addItem(ht.getItem());
        return this;
    }

    public HiInventory setItem(HiItem ht,int index){
        this.inv.setItem(index,ht.getItem());
        return this;
    }

    public boolean equals(HiInventory hin){
        if(hin.inv.equals(hin)){
            return true;
        }
        return false;
    }

}
