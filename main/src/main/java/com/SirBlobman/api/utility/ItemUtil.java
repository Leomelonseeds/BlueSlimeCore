package com.SirBlobman.api.utility;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ItemUtil {
    private static final ItemStack AIR = newItem(Material.AIR);
    public static ItemStack getAir() {
        return AIR.clone();
    }

    public static ItemStack newItem(Material mat) {
        ItemStack is = new ItemStack(mat);
        return is;
    }

    public static ItemStack newItem(Material mat, int amount) {
        ItemStack is = newItem(mat);
        if(!isAir(is)) is.setAmount(amount);
        return is;
    }

    public static ItemStack newItem(Material mat, int amount, int data) {
        ItemStack is = newItem(mat, amount);
        if(!isAir(is)) {
            short meta = (short) data;
            is.setDurability(meta);
        }
        return is;
    }

    public static ItemStack newItem(Material mat, int amount, int data, String name) {
        ItemStack is = newItem(mat, amount, data);
        if(!isAir(is)) {
            ItemMeta meta = is.getItemMeta();
            String disp = ChatColor.translateAlternateColorCodes('&', name);
            meta.setDisplayName(disp);
            meta.addItemFlags(ItemFlag.values());
            is.setItemMeta(meta);
        }
        return is;
    }

    public static ItemStack newItem(Material mat, int amount, int data, String name, String... lore) {
        ItemStack is = newItem(mat, amount, data, name);
        if(!isAir(is)) {
            ItemMeta meta = is.getItemMeta();
            for(int i = 0; i < lore.length; i++) {
                String line = lore[i];
                String color = ChatColor.translateAlternateColorCodes('&', line);
                lore[i] = color;
            }
            
            List<String> list = Arrays.asList(lore);
            meta.setLore(list);
            is.setItemMeta(meta);
        }
        return is;
    }
    
    public static ItemStack newItem(Material mat, int amount, int data, String name, List<String> lore) {
    	String[] loreArray = lore.toArray(new String[0]);
    	return newItem(mat, amount, data, name, loreArray);
    }

    public static ItemStack newPotion(PotionEffectType main, PotionEffect[] potionEffects, String disp, String... lore) {
        ItemStack is = newItem(Material.POTION, 1, 0, disp, lore);
        ItemMeta meta = is.getItemMeta();
        PotionMeta pm = (PotionMeta) meta;
        for(int i = 0; i < potionEffects.length; i++) {
            PotionEffect next = potionEffects[i];
            pm.addCustomEffect(next, false);
        }
        is.setItemMeta(pm);
        return is;
    }

    public static ItemStack conditionalMetaItem(boolean condition, Material mat, int amount, int metaIfTrue, int metaIfFalse, String disp, String... lore) {
        ItemStack is = newItem(mat, amount, (condition ? metaIfTrue : metaIfFalse), disp, lore);
        return is;
    }
    
    /** Item Checks **/
    public static boolean isAir(ItemStack item) {
        if(item == null) return true;
        if(item.equals(getAir())) return true;
        
        Material type = item.getType();
        String typeName = type.name();
        return (type == Material.AIR || typeName.endsWith("_AIR"));
    }
    
    public static boolean hasLore(ItemStack item) {
        if(isAir(item)) return false;
        if(!item.hasItemMeta()) return false;
        
        ItemMeta meta = item.getItemMeta();
        return meta.hasLore();
    }
    
    public static boolean doesAnyLoreLineContain(ItemStack item, String string) {
        if(!hasLore(item)) return false;
        
        ItemMeta meta = item.getItemMeta();
        List<String> loreList = meta.getLore();
        
        for(String lore : loreList) {
            if(!lore.contains(string)) continue;
            return true;
        }
        return false;
    }
    
    public static boolean doesAnyLoreLineStartWith(ItemStack item, String string) {
        if(!hasLore(item)) return false;
        
        ItemMeta meta = item.getItemMeta();
        List<String> loreList = meta.getLore();
        
        for(String lore : loreList) {
            if(!lore.startsWith(string)) continue;
            return true;
        }
        return false;
    }
    
    public static boolean doesAnyLoreLineEndWith(ItemStack item, String string) {
        if(!hasLore(item)) return false;
        
        ItemMeta meta = item.getItemMeta();
        List<String> loreList = meta.getLore();
        
        for(String lore : loreList) {
            if(!lore.endsWith(string)) continue;
            return true;
        }
        return false;
    }
}