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
        if (this.config.minecart.enabled) {
            this.getServer().getPluginManager()
                .registerEvents(
                    new MinecartListener(
                        this,
                        this.config.minecart
                    ),
                    this
                );
        }
        if (this.config.anvil.enabled) {
            this.getServer().getPluginManager()
                .registerEvents(
                    new AnvilListener(
                        this,
                        this.config.anvil
                    ),
                    this
                );
        }
        if (this.config.enchanting.enabled) {
            this.getServer().getPluginManager()
                .registerEvents(
                    new EnchantingListener(
                        this,
                        this.config.enchanting
                    ),
                    this
                );
        }
        if (this.config.farmland.enabled) {
            this.getServer().getPluginManager()
                .registerEvents(
                    new FarmlandListener(
                        this,
                        this.config.farmland
                    ),
                    this
                );
        }
        if (this.config.villager.enabled) {
            this.getServer().getPluginManager()
                .registerEvents(
                    new VillagerListener(
                        this,
                        this.config.villager
                    ),
                    this
                );
        }
    }

    @Override
    public void onEnable() {
        this.prepareConfig();
        this.registerListener();
    }
}
