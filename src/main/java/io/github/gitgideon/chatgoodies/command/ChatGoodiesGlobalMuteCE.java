package io.github.gitgideon.chatgoodies.command;

import io.github.gitgideon.chatgoodies.ChatGoodies;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ChatGoodiesGlobalMuteCE implements CommandExecutor {

    ChatGoodies instance;

    public ChatGoodiesGlobalMuteCE(ChatGoodies pluin) {
        instance = pluin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!cmd.getName().equalsIgnoreCase("gmute")) return false;
        if (args.length == 0) {
            if (sender.hasPermission("chatgoodiesender.globalmute.set")) {
                if (!instance.isGlobalChatMuted()) {
                    instance.setGlobalChatMuted(true);
                    instance.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                            instance.getConfig().getString("globalmute.msg_announcemute")
                                    .replace("%player%", sender.getName())));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            instance.getConfig().getString("globalmute.playermsg_mute")));
                    return true;
                } else {
                    instance.setGlobalChatMuted(false);
                    instance.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                            instance.getConfig().getString("globalmute.msg_announceunmute")
                                    .replace("%player%", sender.getName())));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            instance.getConfig().getString("globalmute.playermsg_unmute")));
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.RED + "You don't have permission for that command!");
                return true;
            }
        }
        return false;
    }
}
