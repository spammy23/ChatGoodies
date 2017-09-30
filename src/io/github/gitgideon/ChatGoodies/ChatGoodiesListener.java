package io.github.gitgideon.ChatGoodies;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChatGoodiesListener implements Listener {

    ChatGoodies main;
    
    public ChatGoodiesListener(ChatGoodies p) {
        p.getServer().getPluginManager().registerEvents(this, p);
        main = p;
    }

    @EventHandler
    public void onDot(AsyncPlayerChatEvent e) {
        if (!e.getMessage().startsWith(".")) return;
        if (!e.getPlayer().hasPermission("chatgoodies.replacedot")) return;
        if (!main.getConfig().getBoolean("replacedot")) return;
        if (e.getMessage().length() == 1) return;
        e.setMessage(e.getMessage().substring(1));
    }

    @EventHandler
    public void onMention(AsyncPlayerChatEvent e) {
        if (!e.getPlayer().hasPermission("chatgoodies.mention")) return;
        if (!main.getConfig().getBoolean("mention")) return;
        main.getServer().getOnlinePlayers().stream()
                .filter(p -> e.getMessage().contains(p.getName()))
                .forEach(p-> {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig()
                            .getString("msg-mention")
                            .replace("%player%", e.getPlayer().getDisplayName())));
                });
        if (!main.getConfig().getBoolean("mentioned-sound")) return;
        e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.BLOCK_NOTE_PLING, 1.0f, 1.0f);
    }

    @EventHandler
    public void onCmdAlias(PlayerCommandPreprocessEvent e) {
        if (!e.getPlayer().hasPermission("chatgoodies.cmdalias")) return;
        if (!main.getConfig().getBoolean("cmdalias")) return;
        String cmd = e.getMessage().split(" ")[0];
        if (!main.cmdaliasses.containsKey(cmd)) return;
        e.setMessage(e.getMessage()
                .replace(cmd, main.cmdaliasses.get(cmd)));
    }

    @EventHandler
    public void onMsgAlias(AsyncPlayerChatEvent e) {
        if (!e.getPlayer().hasPermission("chatgoodies.msgalias")) return;
        if (!main.getConfig().getBoolean("msgalias")) return;
        main.msgaliasses.entrySet().stream().forEach(alias -> {
            if (e.getMessage().contains(alias.getKey())) {
                e.setMessage(e.getMessage().replace(alias.getKey(), alias.getValue()));
            }
        });
    }

    @EventHandler
    public void onMessageColor(AsyncPlayerChatEvent e) {
        if (!main.getConfig().getBoolean("colorformatting")) return;
        if (!e.getPlayer().hasPermission("chatgoodies.colormsg")) return;
        e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
    }


}
