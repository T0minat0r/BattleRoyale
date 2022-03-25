package de.mmocraft.main;

import de.mmocraft.board.ScoreboardManager;
import de.mmocraft.commands.ConfigCommand;
import de.mmocraft.commands.LifeCommand;
import de.mmocraft.commands.RegisterCommand;
import de.mmocraft.commands.UnregisterCommand;
import de.mmocraft.listener.ChatFormat;
import de.mmocraft.listener.DeathListener;
import de.mmocraft.listener.JoinQuitListener;
import de.mmocraft.listener.RegisterNotification;
import de.mmocraft.run.TimeChecker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BattleRoyale extends JavaPlugin {
    public static BattleRoyale main;
    public BattleRoyale() {
        main = this;
    }
    public String prefix = getConfig().getString("Messages.Prefix");

    @Override
    public void onEnable() {
        registerEvents();
        registerCommands();
        saveDefaultConfig();
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (BattleRoyale.getInstance().getConfig().getConfigurationSection("PlayerData").contains(onlinePlayer.getDisplayName())) {
                ScoreboardManager.updateScoreBoard(onlinePlayer);
            } else {
                onlinePlayer.kickPlayer(BattleRoyale.main.prefix + "§cFehler beim Laden der Spielerdaten. Versuche es später erneut!");
            }
        }
        TimeChecker.checktime();
    }

    @Override
    public void onDisable() {

    }

    public void registerEvents(){
        PluginManager pl = Bukkit.getPluginManager();
        pl.registerEvents(new JoinQuitListener(), this);
        pl.registerEvents(new ChatFormat(), this);
        pl.registerEvents(new RegisterNotification(), this);
        pl.registerEvents(new DeathListener(), this);
    }
    public void registerCommands() {
        getCommand("life").setExecutor(new LifeCommand());
        getCommand("register").setExecutor(new RegisterCommand());
        getCommand("unregister").setExecutor(new UnregisterCommand());
        getCommand("config").setExecutor(new ConfigCommand());
    }

    public static BattleRoyale getInstance() {
        return main;
    }

}
