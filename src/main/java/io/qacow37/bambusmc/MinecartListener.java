package io.qacow37.bambusmc;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleMoveEvent;

public final class MinecartListener implements Listener {
    private final Plugin plugin;
    private final Config.Minecart config;

    public MinecartListener(Plugin plugin, Config.Minecart config) {
        this.plugin = plugin;
        this.config = config;
    }

    @EventHandler
    public void onVehicleMove(VehicleMoveEvent event) {
        Vehicle vehicle = event.getVehicle();
        if (vehicle instanceof Minecart) {
            Minecart cart = (Minecart) vehicle;

            Location Lrail = cart.getLocation();
            Location LblockBelow = cart.getLocation()
                .subtract(0.0, 1.0, 0.0);

            Block rail = Lrail.getBlock();
            Block blockBelow = LblockBelow.getBlock();

            if (cart.getMaxSpeed() < this.config.minMaxSpeed) {
                cart.setMaxSpeed(
                    this.config.minMaxSpeed
                );
            }

            if (rail.getType() == Material.POWERED_RAIL) {
                if (rail.isBlockIndirectlyPowered() || rail.isBlockPowered()) {
                    if (blockBelow.getType() == this.config.boostBlock) {
                        cart.setMaxSpeed(
                            this.config.maxMaxSpeed
                        );
                    }
                }
                else {
                    cart.setMaxSpeed(
                        this.config.minMaxSpeed
                    );
                }
            }
        }
    }
}
