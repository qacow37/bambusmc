package io.qacow37.bambusmc;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.scheduler.BukkitRunnable;

public final class EnchantingListener implements Listener {
    private final Plugin plugin;
    private final Config.Enchanting config;

    public EnchantingListener(Plugin plugin, Config.Enchanting config) {
        this.plugin = plugin;
        this.config = config;
    }

    @EventHandler
    public void onPrepareAnvild(PrepareAnvilEvent event) {
        AnvilInventory inv =  event.getInventory();
        inv.setMaximumRepairCost(
            this.config.tooExpensive
        );

        int cost = inv.getRepairCost();
        inv.setRepairCost(
            (int) (cost * this.config.repairMult)
        );
    }

    @EventHandler
    public void onEnchantItem(EnchantItemEvent event) {
        if (this.config.useEnchantmentLevelForCost) {
            Player player = event.getEnchanter();
            int cost = event.getExpLevelCost() - (event.whichButton() + 1);

            new BukkitRunnable() {
                @Override
                public void run() {
                    player.giveExpLevels(
                        -cost
                    );
                }
            }.runTaskLater(this.plugin, 1L);
        }
    }
}
