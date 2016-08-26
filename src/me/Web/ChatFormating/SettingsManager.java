package me.Web.ChatFormating;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class SettingsManager {

    private SettingsManager() { }

    static SettingsManager instance = new SettingsManager();

    public static SettingsManager getInstance() {
        return instance;
    }

    Plugin p;

    FileConfiguration config;
    File cfile;

    FileConfiguration warps;
    File wfile;

    public void setup(Plugin p) {
        cfile = new File(p.getDataFolder(), "config.yml");
        config = p.getConfig();
        //config.options().copyDefaults(true);
        //saveConfig();

        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdir();
        }


        wfile = new File(p.getDataFolder(), "Prefixes.yml");



        if (!wfile.exists()) {
            try {
                wfile.createNewFile();
            }
            catch (IOException e) {
                Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create Prefixes.yml!");
            }
        }


        warps = YamlConfiguration.loadConfiguration(wfile);
    }



    public void savePrefixes() {
        try {
            warps.save(wfile);
        }
        catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save Prefixes.yml!");
        }
    }
    public void reloadPrefixes() {
        warps = YamlConfiguration.loadConfiguration(wfile);
    }
    public FileConfiguration getPrefixes() {
        return warps;
    }


    public FileConfiguration getConfig() {
        return config;
    }

    public void saveConfig() {
        try {
            config.save(cfile);
        }
        catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save config.yml!");
        }
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(cfile);
    }

    public PluginDescriptionFile getDesc() {
        return p.getDescription();
    }
}