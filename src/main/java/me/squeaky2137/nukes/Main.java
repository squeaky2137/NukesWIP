package me.squeaky2137.nukes;

import me.squeaky2137.nukes.events.RadiationManager;
import me.squeaky2137.nukes.events.nukemake;
import org.bukkit.plugin.java.JavaPlugin;

import javax.xml.crypto.Data;


public final class Main extends JavaPlugin {

    public static Main getPlugin() {
        return plugin;
    }
    private static Main plugin;
    public static DataManager data;

    @Override
    public void onEnable() {
        plugin = this;
        recipes.init();
        getDataFolder().mkdir();
        this.data = new DataManager(this);
        getLogger().info("Nukes plugin working! ready to start WW3...");
        getServer().getPluginManager().registerEvents(new nukemake(), this);
        //getServer().getPluginManager().registerEvents(new RadiationManager(), this);
        RadiationManager.checkRadiation();
        RadiationManager.checkExpired();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
