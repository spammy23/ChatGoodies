package io.github.gitgideon.ChatGoodies;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatGoodiesClearChatCE implements CommandExecutor {

    ChatGoodies main;

    public ChatGoodiesClearChatCE(ChatGoodies p) {
        main = p;
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String Label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("cc")) {
            if (args.length == 0) {
                if (s.hasPermission("chatgoodies.clearchat.all")) {
                    for (int i = 0; i < 100; ++i) {
                        main.getServer().broadcastMessage("");
                    }
                    main.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                            main.getConfig().getString("clearchat.msg_global")
                            .replace("%player%", s.getName())));
                    return true;
                } else if (s.hasPermission("chatgoodies.clearchat.self")) {
                    for (int i = 0; i < 100; ++i) {
                        s.sendMessage("");
                    }
                    s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            main.getConfig().getString("clearchat.msg_self")));
                    return true;
                } else {
                    s.sendMessage(ChatColor.RED + "You don't have permission for that command!");
                    return true;
                }
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("self")) {
                    if (s.hasPermission("chatgoodies.clearchat.self")) {
                        for (int i = 0; i < 100; ++i) {
                            s.sendMessage("");
                        }
                        s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                main.getConfig().getString("clearchat.msg_self")));
                        return true;
                    }
                } else if (main.getServer().getPlayer(args[0]) != null) {
                    if (s.hasPermission("chatgoodies.clearchat.others")) {
                        Player p = main.getServer().getPlayer(args[0]);
                        if (p.hasPermission("chatgoodies.clearchat.exclude")) {
                            if (s.hasPermission("chatgoodies.clearchat.force")) {
                                for (int i = 0; i < 100; ++i) {
                                    p.sendMessage("");
                                }
                                s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                        main.getConfig().getString("clearchat.msg_player")
                                        .replace("%player%", s.getName())));
                                return true;
                            } else {
                                s.sendMessage(ChatColor.RED + "You don't have permission to clear that player's chat!");
                                return true;
                            }
                        } else {
                            for (int i = 0; i < 100; ++i) {
                                p.sendMessage("");
                            }
                            s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                    main.getConfig().getString("clearchat.msg_player")
                                            .replace("%player%", s.getName())));
                            return true;
                        }
                    }
                } else {
                    s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            main.getConfig().getString("clearchat.msg_notfound")
                            .replace("%player%", args[0])));
                    return true;
                }
            } else {
                s.sendMessage(ChatColor.RED + "Too many arguments!");
                return true;
            }
        }
        return false;
    }
}
