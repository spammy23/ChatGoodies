package io.github.gitgideon.ChatGoodies;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class ChatGoodies extends JavaPlugin {
    HashMap<String, String> cmdaliasses = new HashMap<>();
    HashMap<String, String> msgaliasses = new HashMap<>();
    boolean globalChatMuted = false;
    
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        getConfig().getConfigurationSection("cmdaliasses").getKeys(false).stream().forEach(key -> {
            cmdaliasses.put(key, getConfig().getString("cmdaliasses." + key));
        });
        getConfig().getConfigurationSection("msgaliasses").getKeys(false).stream().forEach(key -> {
            msgaliasses.put(key, getConfig().getString("msgaliasses." + key));
        });
        if (getConfig().getBoolean("clearchat.enabled"))
            getCommand("cc").setExecutor(new ChatGoodiesClearChatCE(this));
        if (getConfig().getBoolean("globalmute.enabled")) {
            getCommand("gmute").setExecutor(new ChatGoodiesGlobalMuteCE(this));
            new ChatGoodiesGlobalMuteListener(this);
        }
        if (getConfig().getBoolean("staffchat.enabled"))
            new ChatGoodiesStaffChatListener(this);
        new ChatGoodiesListener(this);
    }

}
