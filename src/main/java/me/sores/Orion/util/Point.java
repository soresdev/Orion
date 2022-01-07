package me.sores.Orion.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import java.text.DecimalFormat;

/**
 * Created by sores on 1/6/2022.
 */
public class Point {

    public World world;
    public double x, y, z;

    public Point(Point p) {
        this.world = p.world;
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
    }

    public Point(Location loc) {
        this.world = loc.getWorld();
        this.x = loc.getX();
        this.y = loc.getY();
        this.z = loc.getZ();
    }

    public Point(Vector vec, World world) {
        this.world = world;
        this.x = vec.getX();
        this.y = vec.getY();
        this.z = vec.getZ();
    }

    public Point(World world, double x, double y, double z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public World getWorld() {
        return world;
    }

    public Block getBlock() {
        return world.getBlockAt((int)x, (int)y, (int)z);
    }

    public boolean matches(Material material) {
        return this.getBlock().getType() == material;
    }

    public Location toLocation() {
        return new Location(this.world, this.x, this.y, this.z);
    }

    public Point difference(Point p) {
        Point d = new Point(this);
        d.x -= p.x;
        d.y -= p.y;
        d.z -= p.z;
        return d;
    }

    public Point add(Point p) {
        if(p != null && p.getWorld() == this.getWorld()) {
            double x = this.x + p.x;
            double y = this.y + p.y;
            double z = this.z + p.z;
            return new Point(p.world, x, y, z);
        } else {
            throw new IllegalArgumentException("Cannot add Locations of differing worlds");
        }
    }

    public Point add(double x, double y, double z) {
        Point p = new Point(this);
        p.x += x;
        p.y += y;
        p.z += z;
        return p;
    }

    public boolean is(Material mat) {
        return getBlock().getType() == mat;
    }

    public Point roundDown() {
        Point p = new Point(this);
        p.x = (int)p.x;
        p.y = (int)p.y;
        p.z = (int)p.z;
        return p;
    }

    public double distance(Point p) {
        Location loc1 = this.toLocation();
        Location loc2 = p.toLocation();
        return loc1.distance(loc2);
    }

    @Override
    public String toString() {
        int x = (int)this.x;
        int y = (int)this.y;
        int z = (int)this.z;

        return x + ", " + y + ", " + z;
    }

    public String toStringDoubles() {
        String format = "#0.00";
        StringBuilder s = new StringBuilder();
        s.append(new DecimalFormat(format).format(x));
        s.append(", ");
        s.append(new DecimalFormat(format).format(y));
        s.append(", ");
        s.append(new DecimalFormat(format).format(z));
        return s.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof Point) {
            Point p = (Point)o;

            boolean equal = true;
            equal = equal && Math.floor(this.x) == Math.floor(p.x);
            equal = equal && Math.floor(this.y) == Math.floor(p.y);
            equal = equal && Math.floor(this.z) == Math.floor(p.z);

            return equal;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = (int)Math.floor(x);
        hash = 49999 * hash + (int)Math.floor(y);
        hash = 49999 * hash + (int)Math.floor(z);
        return hash;
    }

}
