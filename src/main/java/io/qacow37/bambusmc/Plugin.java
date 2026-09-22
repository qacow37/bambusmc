package io.qacow37.bambusmc;

import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin extends JavaPlugin {
    public Config config;

    private void prepareConfig() {
        this.saveDefaultConfig();
        this.config = new Config(
            this.getConfig()
                .options()
                .copyDefaults(true)
                .configuration()
        );
        this.saveConfig();
    }

    private void registerListener() {
        this.getServer().getPluginManager()
            .registerEvents(
                new MinecartListener(
                    this,
                    this.config.minecart
                ),
                this
            );
        this.getServer().getPluginManager()
            .registerEvents(
                new EnchantingListener(
                    this,
                    this.config.enchanting
                ),
                this
            );
    }

    @Override
    public void onEnable() {
        this.prepareConfig();
        this.registerListener();
    }
}
