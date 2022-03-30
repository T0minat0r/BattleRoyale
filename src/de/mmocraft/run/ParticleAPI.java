package de.mmocraft.run;

import org.bukkit.*;
import org.bukkit.util.Vector;

public class ParticleAPI {
    public static void drawLine(Location point1, Location point2, double space) {

        World world = Bukkit.getWorld("world");

        double distance = point1.distance(point2);

        Vector p1 = point1.toVector();
        Vector p2 = point2.toVector();

        Vector vector = p2.clone().subtract(p1).normalize().multiply(space);

        double covered = 0;

        for (; covered < distance; p1.add(vector)) {
            int red = 199;
            int green = 21;
            int blue = 255;
            world.spawnParticle(Particle.REDSTONE, p1.getX(), p1.getY(), p1.getZ(), 1, new Particle.DustOptions(Color.fromBGR(red, green, blue), 1));

            covered += space;
        }
    }
}
