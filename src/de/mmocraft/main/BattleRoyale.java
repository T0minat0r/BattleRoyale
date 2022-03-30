package de.mmocraft.main;

import de.mmocraft.board.ScoreboardManager;
import de.mmocraft.commands.*;
import de.mmocraft.listener.*;
import de.mmocraft.listener.KickManager;
import de.mmocraft.run.LootdropOpener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

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
    }

    @Override
    public void onDisable() {

    }

    public void registerEvents(){
        PluginManager pl = Bukkit.getPluginManager();
        pl.registerEvents(new JoinQuitListener(), this);
        pl.registerEvents(new ChatFormat(), this);
        pl.registerEvents(new DeathListener(), this);
        pl.registerEvents(new KickManager(), this);
        pl.registerEvents(new LootdropOpener(), this);
    }
    public void registerCommands() {
        getCommand("life").setExecutor(new LifeCommand());
        getCommand("register").setExecutor(new RegisterCommand());
        getCommand("unregister").setExecutor(new UnregisterCommand());
        getCommand("config").setExecutor(new ConfigCommand());
        getCommand("lootdrop").setExecutor(new LootdropCommand());
    }

    public static BattleRoyale getInstance() {
        return main;
    }

}
