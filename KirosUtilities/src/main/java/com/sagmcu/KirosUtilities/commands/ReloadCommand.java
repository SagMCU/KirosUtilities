package com.sagmcu.KirosUtilities.commands;

import com.sagmcu.KirosUtilities.Main;
import com.sagmcu.KirosUtilities.utils.ChatColorTranslator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand implements CommandExecutor {
    private final Main plugin;

    public ReloadCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String reloadSuccessMessage = plugin.getConfig().getString("messages.reload_success");
        String noPermissionMessage = plugin.getConfig().getString("messages.no_permission");

        reloadSuccessMessage = ChatColorTranslator.translate(reloadSuccessMessage);
        noPermissionMessage = ChatColorTranslator.translate(noPermissionMessage);

        Player player = (Player) sender;

        if (!player.hasPermission("kirosutilities.admin")) {
            player.sendMessage(noPermissionMessage);
            return false;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            plugin.getPistonBlocker().reloadBlockedMaterials();
            if (plugin.getTabCompletionFilter() != null) {
                plugin.getTabCompletionFilter().reloadConfig();
            }
            sender.sendMessage(reloadSuccessMessage);
            return true;
        }

        return false;
    }
}
