package io.qacow37.bambusmc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.Material;
import org.bukkit.block.Block;

public final class FarmlandListener implements Listener {
    private final Plugin plugin;
    private final Config.Farmland config;

    public FarmlandListener(Plugin plugin, Config.Farmland config) {
        this.plugin = plugin;
        this.config = config;
    }

    @EventHandler
    public void protectFromPlayer(PlayerInteractEvent event) {
        if (this.config.protect) {
            if (event.getAction() == Action.PHYSICAL) {
                Block block = event.getClickedBlock();
                if (block != null && block.getType() == Material.FARMLAND) {
                    event.setCancelled(
                        true
                    );
                }
            }
        }
    }

    @EventHandler
    public void protectFromEntity(EntityChangeBlockEvent event) {
        if (this.config.protect) {
            Block source = event.getBlock();
            Material target = event.getTo();

            if (source.getType() == Material.FARMLAND) {
                if (target == Material.DIRT) {
                    event.setCancelled(
                        true
                    );
                }
            }
        }
    }
}
