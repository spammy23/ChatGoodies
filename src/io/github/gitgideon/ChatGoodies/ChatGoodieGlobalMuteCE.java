package io.github.gitgideon.ChatGoodies;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ChatGoodiesGlobalMuteCE implements CommandExecutor {

    ChatGoodies main;
    public ChatGoodiesGlobalMuteCE(ChatGoodies p) {
        main = p;
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String Label, String[] args) {

        if (!cmd.getName().equalsIgnoreCase("gmute")) return false;
        if (args.length == 0) {
            if (s.hasPermission("chatgoodies.globalmute.set")) {
                if (!main.globalChatMuted) {
                    main.globalChatMuted = true;
                    main.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                            main.getConfig().getString("globalmute.msg_announcemute")
                            .replace("%player%", s.getName())));
                    s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            main.getConfig().getString("globalmute.playermsg_mute")));
                    return true;
                } else {
                    main.globalChatMuted = false;
                    main.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                            main.getConfig().getString("globalmute.msg_announceunmute")
                            .replace("%player%", s.getName())));
                    s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            main.getConfig().getString("globalmute.playermsg_unmute")));
                    return true;
                }
            } else {
                s.sendMessage(ChatColor.RED + "You don't have permission for that command!");
                return true;
            }
        }
        return false;
    }
}
