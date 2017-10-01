package io.github.gitgideon.chatgoodies.listener;

import io.github.gitgideon.chatgoodies.ChatGoodies;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatGoodiesStaffChatListener implements Listener {

    ChatGoodies main;

    public ChatGoodiesStaffChatListener(ChatGoodies p) {
        main = p;
        p.getServer().getPluginManager().registerEvents(this, p);
    }

    @EventHandler
    public void onStaffChat(AsyncPlayerChatEvent event) {
        if (!event.getMessage().startsWith(main.getConfig().getString("staffchat.command"))) return;
        if (!event.getPlayer().hasPermission("chatgoodies.staffchat.send")) return;
        main.getServer().getOnlinePlayers().stream().filter(p -> p.hasPermission("chatgoodies.staffchat.receive"))
                .forEach(p -> p.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig()
                        .getString("staffchat.msg_format"))
                        .replace("%player%", event.getPlayer().getName())
                        .replace("%msg%", event.getMessage())));
        event.setCancelled(true);
    }

}
