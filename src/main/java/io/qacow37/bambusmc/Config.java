package io.qacow37.bambusmc;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Villager.Profession;
import java.util.List;
import java.util.ArrayList;

public final class Config {
    public final class Minecart {
        public final boolean enabled;
        public final Material boostBlock;
        public final double maxMaxSpeed;
        public final double minMaxSpeed;

        public Minecart(ConfigurationSection config) {
            this.enabled = config.getBoolean("enabled");
            this.boostBlock = Registry.MATERIAL.get(
                NamespacedKey.fromString(
                    config.getString(
                        "boost-block"
                    )
                )
            );
            this.minMaxSpeed = config.getDouble("max-speed.min") / 20.0;
            this.maxMaxSpeed = config.getDouble("max-speed.max") / 20.0;
        }
    }
    public final class Anvil {
        public final boolean enabled;
        public final int tooExpensive;
        public final double costAdditional;
        public final double costMultiplier;

        public Anvil(ConfigurationSection config) {
            this.enabled = config.getBoolean("enabled");
            this.tooExpensive = config.getInt("too-expensive");
            this.costAdditional = config.getDouble("cost.additional");
            this.costMultiplier = config.getDouble("cost.multiplier");
        }
    }
    public final class Enchanting {
        public final boolean enabled;
        public final boolean levelAsCost;
        public final List<Enchantment> disabledEnchantments;

        public Enchanting(ConfigurationSection config) {
            this.enabled = config.getBoolean("enabled");
            this.levelAsCost = config.getBoolean("level-as-cost");
            this.disabledEnchantments = new ArrayList<Enchantment>();

            List<String> enchants = config.getStringList("disabled-enchantments");
            for (String enchant : enchants) {
                this.disabledEnchantments.add(
                    Registry.ENCHANTMENT.get(
                        NamespacedKey.fromString(
                            enchant
                        )
                    )
                );
            }
        }
    }
    public final class Farmland {
        public final boolean enabled;
        public final boolean protect;

        public Farmland(ConfigurationSection config) {
            this.enabled = config.getBoolean("enabled");
            this.protect = config.getBoolean("protect");
        }
    }
    public final class Villager {
        public final boolean enabled;
        public final List<Profession> disabledJobs;

        public Villager(ConfigurationSection config) {
            this.enabled = config.getBoolean("enabled");
            this.disabledJobs = new ArrayList<Profession>();

            List<String> jobs = config.getStringList("disabled-jobs");
            for (String job : jobs) {
                this.disabledJobs.add(
                    Registry.VILLAGER_PROFESSION.get(
                        NamespacedKey.fromString(
                            job
                        )
                    )
                );
            }
        }
    }

    public final Minecart minecart;
    public final Anvil anvil;
    public final Enchanting enchanting;
    public final Farmland farmland;
    public final Villager villager;

    public Config(ConfigurationSection config) {
        this.minecart = new Minecart(
            config.getConfigurationSection(
                "minecart"
            )
        );
        this.anvil = new Anvil(
            config.getConfigurationSection(
                "anvil"
            )
        );
        this.enchanting = new Enchanting(
            config.getConfigurationSection(
                "enchanting"
            )
        );
        this.farmland = new Farmland(
            config.getConfigurationSection(
                "farmland"
            )
        );
        this.villager = new Villager(
            config.getConfigurationSection(
                "villager"
            )
        );
    }
}
