package io.github.gitgideon.chatgoodies.command;

import io.github.gitgideon.chatgoodies.ChatGoodies;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatGoodiesClearChatCE implements CommandExecutor {

    ChatGoodies instance;

    public ChatGoodiesClearChatCE(ChatGoodies plugin) {
        instance = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("cc")) {
            if (args.length == 0) {
                if (sender.hasPermission("chatgoodiesender.clearchat.all")) {
                    for (int i = 0; i < 100; ++i) {
                        instance.getServer().broadcastMessage("");
                    }
                    instance.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                            instance.getConfig().getString("clearchat.msg_global")
                                    .replace("%player%", sender.getName())));
                    return true;
                } else if (sender.hasPermission("chatgoodiesender.clearchat.self")) {
                    for (int i = 0; i < 100; ++i) {
                        sender.sendMessage("");
                    }
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            instance.getConfig().getString("clearchat.msg_self")));
                    return true;
                } else {
                    sender.sendMessage(ChatColor.RED + "You don't have permission for that command!");
                    return true;
                }
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("self")) {
                    if (sender.hasPermission("chatgoodiesender.clearchat.self")) {
                        for (int i = 0; i < 100; ++i) {
                            sender.sendMessage("");
                        }
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                instance.getConfig().getString("clearchat.msg_self")));
                        return true;
                    }
                } else if (instance.getServer().getPlayer(args[0]) != null) {
                    if (sender.hasPermission("chatgoodiesender.clearchat.others")) {
                        Player p = instance.getServer().getPlayer(args[0]);
                        if (p.hasPermission("chatgoodiesender.clearchat.exclude")) {
                            if (sender.hasPermission("chatgoodiesender.clearchat.force")) {
                                for (int i = 0; i < 100; ++i) {
                                    p.sendMessage("");
                                }
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                        instance.getConfig().getString("clearchat.msg_player")
                                                .replace("%player%", sender.getName())));
                                return true;
                            } else {
                                sender.sendMessage(ChatColor.RED + "You don't have permission to clear that player's chat!");
                                return true;
                            }
                        } else {
                            for (int i = 0; i < 100; ++i) {
                                p.sendMessage("");
                            }
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                    instance.getConfig().getString("clearchat.msg_player")
                                            .replace("%player%", sender.getName())));
                            return true;
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            instance.getConfig().getString("clearchat.msg_notfound")
                                    .replace("%player%", args[0])));
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Too many arguments!");
                return true;
            }
        }
        return false;
    }
}
