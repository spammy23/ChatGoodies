package io.github.gitgideon.chatgoodies;

import io.github.gitgideon.chatgoodies.command.ChatGoodiesClearChatCE;
import io.github.gitgideon.chatgoodies.command.ChatGoodiesGlobalMuteCE;
import io.github.gitgideon.chatgoodies.listener.ChatGoodiesGlobalMuteListener;
import io.github.gitgideon.chatgoodies.listener.ChatGoodiesListener;
import io.github.gitgideon.chatgoodies.listener.ChatGoodiesStaffChatListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class ChatGoodies extends JavaPlugin {
    private HashMap<String, String> commandAliases = new HashMap<>();
    private HashMap<String, String> messageAliases = new HashMap<>();
    private boolean globalChatMuted = false;

    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        getConfig().getConfigurationSection("cmdaliasses").getKeys(false).stream().forEach(key -> {
            commandAliases.put(key, getConfig().getString("cmdaliasses." + key));
        });
        getConfig().getConfigurationSection("msgaliasses").getKeys(false).stream().forEach(key -> {
            messageAliases.put(key, getConfig().getString("msgaliasses." + key));
        });
        if (getConfig().getBoolean("clearchat.enabled"))
            getCommand("cc").setExecutor(new ChatGoodiesClearChatCE(this));
        if (getConfig().getBoolean("globalmute.enabled")) {
            getCommand("gmute").setExecutor(new ChatGoodiesGlobalMuteCE(this));
            new ChatGoodiesGlobalMuteListener(this);
        }
        if (getConfig().getBoolean("staffchat.enabled"))
            new ChatGoodiesStaffChatListener(this);
        Bukkit.getPluginManager().registerEvents(new ChatGoodiesListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ChatGoodiesGlobalMuteListener(this), this);
    }

    public HashMap<String, String> getCommandAliases() {
        return commandAliases;
    }

    public HashMap<String, String> getMessageAlases() {
        return messageAliases;
    }

    public void setGlobalChatMuted(boolean globalChatMuted) {
        this.globalChatMuted = globalChatMuted;
    }

    public boolean isGlobalChatMuted() {
        return globalChatMuted;
    }
}
