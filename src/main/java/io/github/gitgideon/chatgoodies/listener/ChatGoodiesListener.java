package io.github.gitgideon.chatgoodies.listener;

import io.github.gitgideon.chatgoodies.ChatGoodies;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChatGoodiesListener implements Listener {

    private ChatGoodies instance;

    public ChatGoodiesListener(ChatGoodies plugin) {
        instance = plugin;
    }

    @EventHandler
    public void onDot(AsyncPlayerChatEvent event) {
        if (!event.getMessage().startsWith(".")) return;
        if (!event.getPlayer().hasPermission("chatgoodies.replacedot")) return;
        if (!instance.getConfig().getBoolean("replacedot")) return;
        if (event.getMessage().length() == 1) return;
        event.setMessage(event.getMessage().substring(1));
    }

    @EventHandler
    public void onMention(AsyncPlayerChatEvent event) {
        if (!event.getPlayer().hasPermission("chatgoodies.mention")) return;
        if (!instance.getConfig().getBoolean("mention")) return;
        instance.getServer().getOnlinePlayers().stream()
                .filter(p -> event.getMessage().contains(p.getName()))
                .forEach(p -> p.sendMessage(ChatColor.translateAlternateColorCodes('&', instance.getConfig()
                        .getString("msg-mention")
                        .replace("%player%", event.getPlayer().getDisplayName()))));
        if (!instance.getConfig().getBoolean("mentioned-sound")) return;
        event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), Sound.BLOCK_NOTE_PLING, 1.0f, 1.0f);
    }

    @EventHandler
    public void onCmdAlias(PlayerCommandPreprocessEvent event) {
        if (!event.getPlayer().hasPermission("chatgoodies.cmdalias")) return;
        if (!instance.getConfig().getBoolean("cmdalias")) return;
        String cmd = event.getMessage().split(" ")[0];
        if (!instance.getCommandAliases().containsKey(cmd)) return;
        event.setMessage(event.getMessage()
                .replace(cmd, instance.getCommandAliases().get(cmd)));
    }

    @EventHandler
    public void onMsgAlias(AsyncPlayerChatEvent event) {
        if (!event.getPlayer().hasPermission("chatgoodies.msgalias")) return;
        if (!instance.getConfig().getBoolean("msgalias")) return;
        instance.getMessageAlases().entrySet().stream().forEach(alias -> {
            if (event.getMessage().contains(alias.getKey())) {
                event.setMessage(event.getMessage().replace(alias.getKey(), alias.getValue()));
            }
        });
    }

    @EventHandler
    public void onMessageColor(AsyncPlayerChatEvent event) {
        if (!instance.getConfig().getBoolean("colorformatting")) return;
        if (!event.getPlayer().hasPermission("chatgoodies.colormsg")) return;
        event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
    }


}
