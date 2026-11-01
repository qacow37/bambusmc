package io.qacow37.bambusmc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import java.util.Map;

public final class EnchantingListener implements Listener {
    private final Plugin plugin;
    private final Config.Enchanting config;

    public EnchantingListener(Plugin plugin, Config.Enchanting config) {
        this.plugin = plugin;
        this.config = config;
    }

    @EventHandler
    public void setEnchantingCost(EnchantItemEvent event) {
        if (this.config.levelAsCost) {
            Player player = event.getEnchanter();
            int cost = event.getExpLevelCost() - (event.whichButton() + 1);

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (event.isCancelled() == false) {
                        player.giveExpLevels(
                            -cost
                        );
                    }
                }
            }.runTaskLater(this.plugin, 1);
        }
    }

    @EventHandler
    void disableEnchantments(EnchantItemEvent event) {
        Map<Enchantment, Integer> enchants = event.getEnchantsToAdd();
        for (Enchantment enchant : this.config.disabledEnchantments) {
            enchants.remove(
                enchant
            );
        }
    }
    @EventHandler
    void disableEnchantments(PrepareAnvilEvent event) {
        AnvilInventory inventory = event.getInventory();
        ItemStack item = inventory.getItem(1);

        if (item != null && item.getType() == Material.ENCHANTED_BOOK) {
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
            Map<Enchantment, Integer> enchants = meta.getStoredEnchants();

            for (Enchantment enchant : this.config.disabledEnchantments) {
                if (enchants.containsKey(enchant)) {
                    event.setResult(new ItemStack(
                        Material.AIR
                    ));
                    return;
                }
            }
        }
    }
}
