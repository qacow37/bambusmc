package io.qacow37.bambusmc;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;

public final class AnvilListener implements Listener {
    private final Plugin plugin;
    private final Config.Anvil config;

    public AnvilListener(Plugin plugin, Config.Anvil config) {
        this.plugin = plugin;
        this.config = config;
    }

    @EventHandler
    public void setRepairCost(PrepareAnvilEvent event) {
        AnvilInventory inv =  event.getInventory();

        int cost = (int) (
            inv.getRepairCost()
            * this.config.costMultiplier
            + this.config.costAdditional
        );
        inv.setRepairCost(cost);

        if (cost >= this.config.tooExpensive) {
            event.setResult(new ItemStack(
                Material.AIR
            ));
        }
        inv.setMaximumRepairCost(this.config.tooExpensive);
    }
}
