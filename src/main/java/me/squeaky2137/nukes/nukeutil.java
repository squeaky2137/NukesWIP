package me.squeaky2137.nukes;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

public class nukeutil {
    public static boolean isNuke(BlockFace blockface, Block block) {
        BlockFace oppdir = blockface.getOppositeFace();
        BlockFace left = BlockFace.SOUTH;

        if(oppdir == BlockFace.NORTH)
            left = BlockFace.WEST;
        else if(oppdir == BlockFace.EAST)
            left = BlockFace.NORTH;
        else if(oppdir == BlockFace.SOUTH)
            left = BlockFace.EAST;

        BlockFace right = left.getOppositeFace();
        try {
            if (block.getRelative(blockface.getOppositeFace()).getType() == Material.BLAST_FURNACE) {
                block = block.getRelative(oppdir);
                if (block.getRelative(BlockFace.DOWN).getType() == Material.IRON_BLOCK) {
                    if (block.getRelative(oppdir).getType() == Material.REDSTONE_BLOCK) {
                        block = block.getRelative(oppdir);
                        if (block.getRelative(left).getType() == Material.IRON_BLOCK) {
                            block = block.getRelative(left);
                            if (block.getRelative(BlockFace.DOWN).getType() == Material.IRON_BLOCK) {
                                block = block.getRelative(right);
                                if (block.getRelative(right).getType() == Material.IRON_BLOCK) {
                                    block = block.getRelative(right);
                                    if (block.getRelative(BlockFace.DOWN).getType() == Material.IRON_BLOCK) {
                                        block = block.getRelative(left);
                                        if (block.getRelative(BlockFace.UP).getType() == Material.IRON_BLOCK) {
                                            block = block.getRelative(BlockFace.UP);
                                            if (block.getRelative(BlockFace.UP).getType() == Material.NETHERITE_BLOCK) {
                                                block = block.getRelative(BlockFace.DOWN);
                                                if (block.getRelative(oppdir).getType() == Material.IRON_BLOCK) {
                                                    block = block.getRelative(oppdir);
                                                    return (block.getRelative(BlockFace.DOWN).getType() == Material.IRON_BLOCK);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        } catch(Exception e) {
            return false;
        }
    }

    public static void deleteNuke(BlockFace blockface, Block block) {
        BlockFace oppdir = blockface.getOppositeFace();
        BlockFace left = BlockFace.SOUTH;

        if (oppdir == BlockFace.NORTH)
            left = BlockFace.WEST;
        else if (oppdir == BlockFace.EAST)
            left = BlockFace.NORTH;
        else if (oppdir == BlockFace.SOUTH)
            left = BlockFace.EAST;

        BlockFace right = left.getOppositeFace();
        //blast furnace
        Block block1 = block.getRelative(oppdir);
        //iron under furnace
        Block block2 = block1.getRelative(BlockFace.DOWN);
        //redstone block
        Block block3 = block1.getRelative(oppdir);
        //iron to left of redstone
        Block block4 = block3.getRelative(left);
        //iron under left iron
        Block block5 = block4.getRelative(BlockFace.DOWN);
        //iron right of redstone
        Block block6 = block3.getRelative(right);
        Block block7 = block6.getRelative(BlockFace.DOWN);
        //above redstone
        Block block8 = block3.getRelative(BlockFace.UP);
        //netherite
        Block block9 = block8.getRelative(BlockFace.UP);
        //very front wing top iron
        Block block10 = block3.getRelative(oppdir);
        //bottum iron front wing
        Block block11 = block10.getRelative(BlockFace.DOWN);

        Block[] blockarr = {block1, block2, block3, block4, block5, block6, block7, block8, block9, block10, block11};

        for(Block blocks : blockarr) {
            blocks.setType(Material.AIR);
        }


    }

    public static void spawnParticle(BlockFace blockface, Block block) {
        BlockFace oppdir = blockface.getOppositeFace();
        BlockFace left = BlockFace.SOUTH;

        if (oppdir == BlockFace.NORTH)
            left = BlockFace.WEST;
        else if (oppdir == BlockFace.EAST)
            left = BlockFace.NORTH;
        else if (oppdir == BlockFace.SOUTH)
            left = BlockFace.EAST;

        BlockFace right = left.getOppositeFace();

        Block furnace = block.getRelative(oppdir);
        Block redstone = block.getRelative(oppdir);

        Location loc = redstone.getLocation();

        block.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, loc, 3, 0, 0, 0, 0);
        loc.add(0,4,0);
        block.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, loc, 3, 0, 0, 0, 0);
        loc.add(0,4,0);
        block.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, loc, 3, 0, 0, 0, 0);
        loc.add(0,4,0);
        block.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, loc, 3, 0, 0, 0, 0);
        loc.add(0,4,0);
        block.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, loc, 3, 0, 0, 0, 0);
        loc.add(0,4,0);
        block.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, loc, 3, 0, 0, 0, 0);
        loc.add(0,4,0);
        block.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, loc, 3, 0, 0, 0, 0);
        loc.add(0,4,0);
        block.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, loc, 3, 0, 0, 0, 0);
        loc.add(0,4,0);
        block.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, loc, 3, 0, 0, 0, 0);
        loc.add(0,4,0);
        block.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, loc, 3, 0, 0, 0, 0);
        loc.add(0,4,0);
        block.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, loc, 3, 0, 0, 0, 0);
        loc.add(0,4,0);
        block.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, loc, 3, 0, 0, 0, 0);
    }




    //checking if wearing a hazmat suit
    public static boolean checkArmor(ItemStack[] armorcontents) {
        int totalhaz = 0;
        for(ItemStack armor : armorcontents) {
            if(armor == null)
                return false;
            if(armor.getItemMeta().equals(recipes.helmit.getItemMeta()) || armor.getItemMeta().equals(recipes.chestit.getItemMeta()) || armor.getItemMeta().equals(recipes.legit.getItemMeta()) || armor.getItemMeta().equals(recipes.bootit.getItemMeta())) {
                totalhaz += 1;
            }
        }
        if(totalhaz == 4)
            return true;
        else
            return false;
    }
}
