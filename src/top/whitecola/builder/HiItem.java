package top.whitecola.builder;


import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class HiItem {
    public ItemStack is;

    public HiItem(ItemStack is){
        this.is = is;
    }

//    public HiItem(String hidata){
//
//    }



    public ItemStack getItem(){
        return this.is;
    }

    public void setItem(ItemStack is){
        this.is = is;
    }

    public ItemMeta getItemMeta(){
        return this.is.getItemMeta();
    }

    public HiItem setItemMeta(ItemMeta itemMeta){
        this.is.setItemMeta(itemMeta);
        return this;
    }

    public HiItem setDisplayName(String displayName){
        ItemMeta im = this.is.getItemMeta();
        im.setDisplayName(displayName);
        this.is.setItemMeta(im);
        return this;
    }

    public HiItem setLores(List<String> lores){
        ItemMeta im = this.is.getItemMeta();
        im.setLore(lores);
        this.is.setItemMeta(im);
        return this;
    }

    public HiItem setUnbreakable(boolean isUnbreakable){
        ItemMeta im = this.is.getItemMeta();
        im.setUnbreakable(isUnbreakable);
        this.is.setItemMeta(im);
        return this;
    }

    public HiItem setType(Material type){
        this.is.setType(type);
        return this;
    }

    public HiItem setDurability(short durability){
        this.is.setDurability(durability);
        return this;
    }

    public HiItem setAmount(int amount){
        this.is.setAmount(amount);
        return this;
    }

    public HiItem addEnchat(Enchantment enchat,int level,boolean ignoreRestrictions){
        ItemMeta meta = this.is.getItemMeta();
        meta.addEnchant(enchat,level,ignoreRestrictions);
        this.is.setItemMeta(meta);
        return this;
    }

    public HiItem removeEnchat(Enchantment enchat){
        this.is.getItemMeta().removeEnchant(enchat);
        return this;
    }



    public HiItem setHeadSkin(OfflinePlayer op){
//        CommandGive
//        MojangsonParser.parse()
        ItemMeta itemMeta = this.is.getItemMeta();
        if((itemMeta instanceof SkullMeta)){
            SkullMeta skullMeta =(SkullMeta)itemMeta;
            skullMeta.setOwningPlayer(op);
            this.is.setItemMeta(skullMeta);
        }
        return this;
    }

    public boolean equals(HiItem ht){
        if(this.getItem().equals(ht)){
            return true;
        }
        return false;
    }

    public HiItem setAttackDamage(int attackDamage){
//        this.is.getItemMeta().g;
        return this;
    }

    public HiItem setCustomModelData(int i){
        ItemMeta meta = this.is.getItemMeta();
        meta.setCustomModelData(i);
        this.is.setItemMeta(meta);
        return this;
    }


}
