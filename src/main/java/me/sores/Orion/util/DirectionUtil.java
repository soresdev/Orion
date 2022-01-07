package me.sores.Orion.util;

import org.bukkit.entity.Player;

/**
 * Created by LavaisWatery on 2017-07-06.
 */
public class DirectionUtil {

    public static Direction getExactDirection(Player player) {
        float yaw = player.getLocation().getYaw();
        yaw /= 90.0F;
        yaw = (float)Math.round(yaw);
        return yaw != -4.0F && yaw != 0.0F && yaw != 4.0F?(yaw != -1.0F && yaw != 3.0F?(yaw != -2.0F && yaw != 2.0F?(yaw != -3.0F && yaw != 1.0F?null: Direction.WEST): Direction.NORTH): Direction.EAST): Direction.SOUTH;
    }

    public static enum Direction {
        SOUTH,
        EAST,
        NORTH,
        WEST;

        private Direction() {
        }
    }

}
