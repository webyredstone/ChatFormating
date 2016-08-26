package me.Web.ChatFormating;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Main extends JavaPlugin {

    SettingsManager settings = SettingsManager.getInstance();


    @Override
    public void onEnable() {
        settings.setup(this);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("setPrefix")) {
            if (args.length < 2) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',""));
            }
            if (args.length == 2) {
                settings.getPrefixes().set("Prefixes." + args[0] + ".Prefix", args[1]);
                settings.savePrefixes();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Prefix: &f" + args[1] + " &3was assigned" +
                        " to " + args[0]));

            }
        }

        return false;
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        String format = e.getFormat();
        Player p = e.getPlayer();
        String pn = p.getName();
        String msg = e.getMessage();
        String cp =  settings.getPrefixes().getString("Prefixes." + pn + ".Prefix");
        e.setFormat(getConfig().getString("ChatFormat"));
        format.replaceAll("<Prefix>", cp);
        format.replaceAll("<Name>", pn);
        format.replaceAll("<msg>", msg);
        format.replaceAll("<2dots>", ":");
    }

    @EventHandler
    public void onPlayerLoginEvent (PlayerLoginEvent e) {
        Player p = e.getPlayer();
        String pn = p.getName();
        if (!p.hasPlayedBefore()) {
            settings.getPrefixes().set("Prefixes." + pn + ".Prefix", "");
        }
    }

}
