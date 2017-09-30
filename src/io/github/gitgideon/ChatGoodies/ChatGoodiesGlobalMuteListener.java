package io.github.gitgideon.ChatGoodies;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatGoodiesGlobalMuteListener implements Listener {

    ChatGoodies main;
    
    public ChatGoodiesGlobalMuteListener(ChatGoodies p) {
        p.getServer().getPluginManager().registerEvents(this, p);
        main = p;
    }

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent e) {
        if (!main.globalChatMuted) return;
        if (e.getPlayer().hasPermission("chatgoodies.globalmute.talk")) return;
        e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
                main.getConfig().getString("globalmute.msg_notalk")));
        e.setCancelled(true);
        main.getServer().getOnlinePlayers().stream().filter(p -> p.hasPermission("chatgoodies.globalmute.read"))
                .forEach(p -> {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            main.getConfig().getString("globalmute.msg_format")
                            .replace("%msg%", e.getMessage())
                            .replace("%player%", e.getPlayer().getName())));
                });
    }

}