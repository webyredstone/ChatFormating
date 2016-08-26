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

    private SettingsManager() {
    }

    static SettingsManager instance = new SettingsManager();

    public static SettingsManager getInstance() {
        return instance;
    }

    Plugin p;

    FileConfiguration config;
    File cfile;

    FileConfiguration prefixes;
    File prfile;

    public void setup(Plugin p) {
        cfile = new File(p.getDataFolder(), "config.yml");
        config = p.getConfig();
        //config.options().copyDefaults(true);
        //saveConfig();

        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdir();
        }


        prfile = new File(p.getDataFolder(), "warps.yml");


        if (!prfile.exists()) {
            try {
                prfile.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create prfile.yml!");
            }
        }


        prefixes = YamlConfiguration.loadConfiguration(prfile);
    }


    public void savePrefixes() {
        try {
            prefixes.save(prfile);
        } catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save prfile.yml!");
        }
    }

    public void reloadprefixes() {
        prefixes = YamlConfiguration.loadConfiguration(prfile);
    }

    public FileConfiguration getprefixes() {
        return prefixes;
    }


    public FileConfiguration getConfig() {
        return config;
    }

    public void saveConfig() {
        try {
            config.save(cfile);
        } catch (IOException e) {
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