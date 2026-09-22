package io.qacow37.bambusmc;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

public final class Config {

    public final class Minecart {
        public final Material boostBlock;
        public final double maxMaxSpeed;
        public final double minMaxSpeed;

        public Minecart(ConfigurationSection config) {
            this.boostBlock = Material.getMaterial(
                config.getString(
                    "boost-block"
                )
            );
            this.minMaxSpeed = config.getDouble("max-speed.min") / 20.0;
            this.maxMaxSpeed = config.getDouble("max-speed.max") / 20.0;
        }
    }

    public final class Enchanting {
        public final int tooExpensive;
        public final double repairMult;
        public final boolean useEnchantmentLevelForCost;

        public Enchanting(ConfigurationSection config) {
            this.tooExpensive = config.getInt("repair.too-expensive");
            this.repairMult = config.getDouble("repair.multiplier");
            this.useEnchantmentLevelForCost = config.getBoolean("use-enchantment-level-for-cost");
        }
    }

    public final Minecart minecart;
    public final Enchanting enchanting;

    public Config(ConfigurationSection config) {
        this.minecart = new Minecart(
            config.getConfigurationSection(
                "minecart"
            )
        );
        this.enchanting = new Enchanting(
            config.getConfigurationSection(
                "enchanting"
            )
        );
    }
}
