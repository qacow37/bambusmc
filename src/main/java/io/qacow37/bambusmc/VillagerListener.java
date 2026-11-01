package io.qacow37.bambusmc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public final class VillagerListener implements Listener {
    private final Plugin plugin;
    private final Config.Villager config;

    public VillagerListener(Plugin plugin, Config.Villager config) {
        this.plugin = plugin;
        this.config = config;
    }

    @EventHandler
    public void onTrade(InventoryOpenEvent event) {
        Inventory inv = event.getInventory();
        if (inv.getType() == InventoryType.MERCHANT) {
            InventoryHolder holder = inv.getHolder();
            if (holder instanceof Villager) {
                Villager villager = (Villager) holder;

                Profession job = villager.getProfession();
                for (Profession disabledJob : this.config.disabledJobs) {
                    if (job == disabledJob) {
                        event.setCancelled(
                            true
                        );
                        break;
                    }
                }
            }
        }
    }
}
