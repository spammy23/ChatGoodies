package io.github.gitgideon.chatgoodies.listener;

import io.github.gitgideon.chatgoodies.ChatGoodies;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatGoodiesGlobalMuteListener implements Listener {

    ChatGoodies instance;

    public ChatGoodiesGlobalMuteListener(ChatGoodies plugin) {
        instance = plugin;
    }

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent event) {
        if (!instance.isGlobalChatMuted()) return;
        if (event.getPlayer().hasPermission("chatgoodies.globalmute.talk")) return;
        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
                instance.getConfig().getString("globalmute.msg_notalk")));
        event.setCancelled(true);
        instance.getServer().getOnlinePlayers().stream().filter(p -> p.hasPermission("chatgoodies.globalmute.read"))
                .forEach(player -> player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        instance.getConfig().getString("globalmute.msg_format")
                                .replace("%msg%", event.getMessage())
                                .replace("%player%", event.getPlayer().getName()))));
    }

}