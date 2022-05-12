package me.squeaky2137.nukes.events;

import me.squeaky2137.nukes.Main;
import me.squeaky2137.nukes.recipes;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static me.squeaky2137.nukes.nukeutil.checkArmor;

public class RadiationManager {

    public static void checkRadiation() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (Main.data.getConfig().contains("radiation")) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        for(ArrayList<Object> list : (List<ArrayList<Object>>) Main.data.getConfig().getList("radiation")) {
                            if (((Location) list.get(0)).distance(player.getLocation()) <= 75) {
                                if (!checkArmor(player.getInventory().getArmorContents())) {
                                    player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 1));
                                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD + "You are in a radiation zone"));
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(Main.getPlugin(), 10, 10);
    }


    public static void checkExpired() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if(Main.data.getConfig().contains("radiation")) {
                    for(ArrayList<Object> list: (List<ArrayList<Object>>) Main.data.getConfig().getList("radiation")) {
                        if(System.currentTimeMillis() >= (Long) list.get(1)) {
                            List<ArrayList<Object>> radiationlist = (List<ArrayList<Object>>) Main.data.getConfig().getList("radiation");
                            radiationlist.remove(list);
                            Main.data.getConfig().set("radiation", radiationlist);
                            Main.data.saveConfig();
                        }
                    }
                }
            }
        }.runTaskTimerAsynchronously(Main.getPlugin(), 0,100);
    }

}
