package me.squeaky2137.nukes.events;

import me.squeaky2137.nukes.Main;
import me.squeaky2137.nukes.recipes;
import org.bukkit.*;
import org.bukkit.block.BlastFurnace;
import org.bukkit.block.Block;
import org.bukkit.block.data.Directional;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static me.squeaky2137.nukes.nukeutil.*;

public class nukemake implements Listener {
    @EventHandler
    public static void buttonplaced(BlockPlaceEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        if (block.getType() == Material.STONE_BUTTON) {
            if (isNuke(((Directional)block.getBlockData()).getFacing(), block)) {
                if (player.hasPermission("nuke.create")) {
                    player.sendMessage(ChatColor.GREEN + "Nuke Created");
                } else {
                    player.sendMessage(ChatColor.RED + "Invalid Permissions, cannot create a nuke");
                }
            }
        }
    }

    @EventHandler
    public static void launchnuke(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        if(block == null) return;
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (block.getType() == Material.STONE_BUTTON) {
                if (isNuke(((Directional) block.getBlockData()).getFacing(), block)) {
                    if (player.hasPermission("nuke.launch")) {
                        BlastFurnace furnace = (BlastFurnace) block.getRelative(((Directional) block.getBlockData()).getFacing().getOppositeFace()).getState();
                        Inventory furnaceinv = furnace.getInventory();
                        if(furnaceinv.contains(Material.COAL, 32) && furnaceinv.contains(Material.COMPASS)) {
                            ItemStack compass = furnaceinv.getItem(0);
                            if(compass.getType() == Material.COMPASS && compass.getItemMeta().hasEnchant(Enchantment.DURABILITY)) {
                                ItemMeta compassmeta = compass.getItemMeta();
                                if(compassmeta.hasLore()) {
                                    if(!ChatColor.stripColor(compassmeta.getLore().get(0)).equalsIgnoreCase("No Location Set")) {
                                        String lore = compassmeta.getLore().get(1);
                                        lore = ChatColor.stripColor(lore);
                                        String[] cords = lore.split(", ");
                                        player.sendMessage(ChatColor.GREEN + "Nuke Launching...");
                                        spawnParticle(((Directional) block.getBlockData()).getFacing(), block);
                                        block.getWorld().playSound(block.getLocation(), Sound.ENTITY_WITHER_DEATH, 100, 10);
                                        deleteNuke(((Directional) block.getBlockData()).getFacing(), block);
                                        block.setType(Material.AIR);
                                        new BukkitRunnable() {
                                            @Override
                                            public void run() {
                                                block.getWorld().createExplosion(Integer.valueOf(cords[0]), Integer.valueOf(cords[1]), Integer.valueOf(cords[2]), 50, true);
                                                block.getWorld().createExplosion(Integer.valueOf(cords[0]) + 15, Integer.valueOf(cords[1]), Integer.valueOf(cords[2]), 30, true);
                                                block.getWorld().createExplosion(Integer.valueOf(cords[0]) - 15, Integer.valueOf(cords[1]), Integer.valueOf(cords[2]), 30, true);
                                                block.getWorld().createExplosion(Integer.valueOf(cords[0]), Integer.valueOf(cords[1]), Integer.valueOf(cords[2]) + 15, 30, true);
                                                block.getWorld().createExplosion(Integer.valueOf(cords[0]), Integer.valueOf(cords[1]), Integer.valueOf(cords[2]) - 15, 30, true);
                                                block.getWorld().createExplosion(Integer.valueOf(cords[0])+10, Integer.valueOf(cords[1]), Integer.valueOf(cords[2])+10, 30, true);
                                                block.getWorld().createExplosion(Integer.valueOf(cords[0])-10, Integer.valueOf(cords[1]), Integer.valueOf(cords[2])-10, 30, true);
                                                block.getWorld().createExplosion(Integer.valueOf(cords[0])+10, Integer.valueOf(cords[1]), Integer.valueOf(cords[2])-10, 30, true);
                                                block.getWorld().createExplosion(Integer.valueOf(cords[0])-10, Integer.valueOf(cords[1]), Integer.valueOf(cords[2])+10, 30, true);
                                                if(Main.data.getConfig().contains("radiation")) {
                                                    List<ArrayList<Object>> radiationlist = (List<ArrayList<Object>>) Main.data.getConfig().getList("radiation");
                                                    ArrayList<Object> subradiationlist = new ArrayList<>();
                                                    subradiationlist.add(new Location(block.getWorld(), Integer.valueOf(cords[0]), Integer.valueOf(cords[1]), Integer.valueOf(cords[2])));
                                                    subradiationlist.add(System.currentTimeMillis() + (48 * 60 * 60 * 1000));
                                                    radiationlist.add(subradiationlist);
                                                    Main.data.getConfig().set("radiation", radiationlist);
                                                    Main.data.saveConfig();
                                                } else {
                                                    ArrayList<Object> subradiationlist = new ArrayList<>();
                                                    List<ArrayList<Object>> radiationlist = new ArrayList<>();
                                                    subradiationlist.add(new Location(block.getWorld(), Integer.valueOf(cords[0]), Integer.valueOf(cords[1]), Integer.valueOf(cords[2])));
                                                    subradiationlist.add(System.currentTimeMillis() + (48 * 60 * 60 * 1000));
                                                    radiationlist.add(subradiationlist);
                                                    Main.data.getConfig().set("radiation", radiationlist);
                                                    Main.data.saveConfig();
                                                }
                                            }
                                        }.runTaskLater(Main.getPlugin(), 100);


                                        

                                    } else {
                                        player.sendMessage(ChatColor.DARK_BLUE + "Must set the cords by right clicking a block with the Location setter compass");
                                    }
                                } else {
                                    player.sendMessage(ChatColor.RED + "Missing Location setter");
                                }
                            } else {
                                player.sendMessage(ChatColor.RED + "Missing the Location setter");
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "You are missing 32 coal, or the Location setter in the furnace");
                        }

                    } else {
                        player.sendMessage(ChatColor.RED + "Invalid Permissions, cannot launch a nuke");
                    }
                }
            }
        }
    }


    @EventHandler
    public static void setcordds(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if(e.getItem() != null) {
            ItemStack item = e.getItem();
            ItemMeta itemmeta = item.getItemMeta();
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if(itemmeta.equals(recipes.compass.getItemMeta())) {
                    player.sendMessage(ChatColor.GREEN + "Set new Location for nukes target");
                    Block block = e.getClickedBlock();
                    List<String> lore = new ArrayList<>();
                    lore.add(ChatColor.GREEN + "Location set to: ");
                    lore.add(ChatColor.YELLOW + String.valueOf(block.getX()) + ", " + block.getY() + ", " + block.getZ());
                    itemmeta.setLore(lore);
                    item.setItemMeta(itemmeta);
                }
            }
        }
    }
}
