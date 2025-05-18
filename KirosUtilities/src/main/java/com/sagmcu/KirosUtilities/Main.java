package com.sagmcu.KirosUtilities;

import com.sagmcu.KirosUtilities.mechanisms.PistonBlocker;
import com.sagmcu.KirosUtilities.commands.ReloadCommand;
import com.sagmcu.KirosUtilities.filters.TabCompletionFilter;
import com.sagmcu.KirosUtilities.handlers.JoinFullServers;
import com.sagmcu.KirosUtilities.utils.ChatColorTranslator;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private PistonBlocker pistonBlocker;
    private TabCompletionFilter tabCompletionFilter;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        pistonBlocker = new PistonBlocker(this);
        getServer().getPluginManager().registerEvents(pistonBlocker, this);
        tabCompletionFilter = new TabCompletionFilter(this);
        getServer().getPluginManager().registerEvents(tabCompletionFilter, this);
        getCommand("kirosutilities").setExecutor(new ReloadCommand(this));
        // Register JoinFullServers listener
        getServer().getPluginManager().registerEvents(new JoinFullServers(), this);
        String enabledMessage = ChatColorTranslator.translate("&aKirosUtilities &7has been &aenabled&7! &6:D");
        this.getServer().getConsoleSender().sendMessage(enabledMessage);
    }

    @Override
    public void onDisable() {
        String disabledMessage = ChatColorTranslator.translate("&cKirosUtilities &7has been &cdisabled&7! :(");
        this.getServer().getConsoleSender().sendMessage(disabledMessage);
    }

    public PistonBlocker getPistonBlocker() {
        return pistonBlocker;
    }

    public TabCompletionFilter getTabCompletionFilter() {
        return tabCompletionFilter;
    }
}
