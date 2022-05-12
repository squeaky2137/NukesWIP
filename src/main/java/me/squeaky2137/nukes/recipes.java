package me.squeaky2137.nukes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class recipes {

    public static ItemStack compass;
    public static ItemStack helmit;
    public static ItemStack chestit;
    public static ItemStack legit;
    public static ItemStack bootit;

    public static void init() {
        createCompass();
        createHazmat();
    }


    //location setter
    private static void createCompass() {
        ItemStack item = new ItemStack(Material.COMPASS, 1);
        ItemMeta itemmeta = item.getItemMeta();
        itemmeta.setDisplayName(ChatColor.RED + "Location Setter");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "No Location Set");
        itemmeta.setLore(lore);
        itemmeta.addEnchant(Enchantment.DURABILITY, 1, false);
        itemmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemmeta);
        compass = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("nukecords"), item);
        sr.shape("MCM", "CNC", "MCM");
        sr.setIngredient('M', Material.MAP);
        sr.setIngredient('C', Material.COMPASS);
        sr.setIngredient('N', Material.NETHERITE_INGOT);
        Bukkit.getServer().addRecipe(sr);

    }


    //-------------hazmat suit----------------


    //helm
    private static void createHazmat(){
        //helm
        ItemStack helm = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta helmmeta = (LeatherArmorMeta) helm.getItemMeta();
        helmmeta.setDisplayName(ChatColor.GOLD + "Hazmat Helmet");
        helmmeta.addEnchant(Enchantment.DURABILITY, 2, false);
        helmmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, false);
        helmmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        helmmeta.setColor(Color.YELLOW);
        helm.setItemMeta(helmmeta);
        helmit = helm;

        //chestplate
        ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta chestmeta = (LeatherArmorMeta) chest.getItemMeta();
        chestmeta.setDisplayName(ChatColor.GOLD + "Hazmat Shirt");
        chestmeta.addEnchant(Enchantment.DURABILITY, 2, false);
        chestmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, false);
        chestmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        chestmeta.setColor(Color.YELLOW);
        chest.setItemMeta(chestmeta);
        chestit = chest;

        //leggs
        ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta legmeta = (LeatherArmorMeta) leg.getItemMeta();
        legmeta.setDisplayName(ChatColor.GOLD + "Hazmat Pants");
        legmeta.addEnchant(Enchantment.DURABILITY, 2, false);
        legmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, false);
        legmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        legmeta.setColor(Color.YELLOW);
        leg.setItemMeta(legmeta);
        legit = leg;

        //boots
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta bootmeta = (LeatherArmorMeta) boots.getItemMeta();
        bootmeta.setDisplayName(ChatColor.GOLD + "Hazmat Boots");
        bootmeta.addEnchant(Enchantment.DURABILITY, 2, false);
        bootmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, false);
        bootmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        bootmeta.setColor(Color.YELLOW);
        boots.setItemMeta(bootmeta);
        bootit = boots;


        //--------recipes-------

        //helm
        ShapedRecipe srhelm = new ShapedRecipe(NamespacedKey.minecraft("helmrecipe"), helm);
        srhelm.shape("GWG", "WSW", "   ");
        srhelm.setIngredient('G', Material.GOLD_BLOCK);
        srhelm.setIngredient('W', Material.YELLOW_WOOL);
        srhelm.setIngredient('S', Material.TINTED_GLASS);
        Bukkit.getServer().addRecipe(srhelm);

        //chest
        ShapedRecipe srchest = new ShapedRecipe(NamespacedKey.minecraft("chestrecipe"), chest);
        srchest.shape("G G", "WWW", "WWW");
        srchest.setIngredient('G', Material.GOLD_BLOCK);
        srchest.setIngredient('W', Material.YELLOW_WOOL);
        Bukkit.getServer().addRecipe(srchest);

        //legs
        ShapedRecipe srlegs = new ShapedRecipe(NamespacedKey.minecraft("legsrecipe"), leg);
        srlegs.shape("GWG", "W W", "W W");
        srlegs.setIngredient('G', Material.GOLD_BLOCK);
        srlegs.setIngredient('W', Material.YELLOW_WOOL);
        Bukkit.getServer().addRecipe(srlegs);

        //boots
        ShapedRecipe srboots = new ShapedRecipe(NamespacedKey.minecraft("bootsrecipe"), boots);
        srboots.shape("G G", "W W", "   ");
        srboots.setIngredient('G', Material.GOLD_BLOCK);
        srboots.setIngredient('W', Material.YELLOW_WOOL);
        Bukkit.getServer().addRecipe(srboots);
    }
}
