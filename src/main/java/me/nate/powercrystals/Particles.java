package me.nate.powercrystals;

import java.util.HashMap;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class Particles {
   static double angle = 0.0D;

   public void createParticles() {
      if (Config.showParticles()) {
         Bukkit.getScheduler().scheduleSyncRepeatingTask(InfinityStones.getPlugin(InfinityStones.class), new Runnable() {
            public void run() {
               Iterator var2 = Bukkit.getServer().getOnlinePlayers().iterator();

               while(var2.hasNext()) {
                  Player player = (Player)var2.next();
                  if (player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                     return;
                  }

                  float x;
                  float z;
                  Location loc;
                  Location loc2;
                  DustOptions dustOptions;
                  if (InfinityStones.hasStone(player, (HashMap)InfinityStones.power)) {
                     x = (float)(0.75D * Math.sin(Particles.angle));
                     z = (float)(0.75D * Math.cos(Particles.angle));
                     loc = player.getLocation();
                     loc2 = new Location(player.getWorld(), loc.getX() + (double)x, loc.getY() + 0.1D, loc.getZ() + (double)z);
                     dustOptions = new DustOptions(Color.fromRGB(100, 0, 194), 1.0F);
                     player.getWorld().spawnParticle(Particle.REDSTONE, loc2, 1, dustOptions);
                  }

                  if (InfinityStones.hasStone(player, (HashMap)InfinityStones.time)) {
                     x = (float)(0.75D * Math.sin(Particles.angle + 1.0D));
                     z = (float)(0.75D * Math.cos(Particles.angle + 1.0D));
                     loc = player.getLocation();
                     loc2 = new Location(player.getWorld(), loc.getX() + (double)x, loc.getY() + 0.2D, loc.getZ() + (double)z);
                     dustOptions = new DustOptions(Color.fromRGB(80, 227, 73), 1.0F);
                     player.getWorld().spawnParticle(Particle.REDSTONE, loc2, 1, dustOptions);
                  }

                  if (InfinityStones.hasStone(player, (HashMap)InfinityStones.mind)) {
                     x = (float)(0.75D * Math.sin(Particles.angle + 2.0D));
                     z = (float)(0.75D * Math.cos(Particles.angle + 2.0D));
                     loc = player.getLocation();
                     loc2 = new Location(player.getWorld(), loc.getX() + (double)x, loc.getY() + 0.3D, loc.getZ() + (double)z);
                     dustOptions = new DustOptions(Color.fromRGB(255, 234, 43), 1.0F);
                     player.getWorld().spawnParticle(Particle.REDSTONE, loc2, 1, dustOptions);
                  }

                  if (InfinityStones.hasStone(player, (HashMap)InfinityStones.space)) {
                     x = (float)(0.75D * Math.sin(Particles.angle + 3.0D));
                     z = (float)(0.75D * Math.cos(Particles.angle + 3.0D));
                     loc = player.getLocation();
                     loc2 = new Location(player.getWorld(), loc.getX() + (double)x, loc.getY() + 0.4D, loc.getZ() + (double)z);
                     dustOptions = new DustOptions(Color.fromRGB(48, 30, 250), 1.0F);
                     player.getWorld().spawnParticle(Particle.REDSTONE, loc2, 1, dustOptions);
                  }

                  if (InfinityStones.hasStone(player, (HashMap)InfinityStones.reality)) {
                     x = (float)(0.75D * Math.sin(Particles.angle + 4.0D));
                     z = (float)(0.75D * Math.cos(Particles.angle + 4.0D));
                     loc = player.getLocation();
                     loc2 = new Location(player.getWorld(), loc.getX() + (double)x, loc.getY() + 0.5D, loc.getZ() + (double)z);
                     dustOptions = new DustOptions(Color.fromRGB(194, 0, 0), 1.0F);
                     player.getWorld().spawnParticle(Particle.REDSTONE, loc2, 1, dustOptions);
                  }

                  if (InfinityStones.hasStone(player, (HashMap)InfinityStones.soul)) {
                     x = (float)(0.75D * Math.sin(Particles.angle + 5.0D));
                     z = (float)(0.75D * Math.cos(Particles.angle + 5.0D));
                     loc = player.getLocation();
                     loc2 = new Location(player.getWorld(), loc.getX() + (double)x, loc.getY() + 0.6D, loc.getZ() + (double)z);
                     dustOptions = new DustOptions(Color.fromRGB(227, 151, 0), 1.0F);
                     player.getWorld().spawnParticle(Particle.REDSTONE, loc2, 1, dustOptions);
                  }
               }

               Particles.angle += 0.15D;
               if (Particles.angle > 360.0D) {
                  Particles.angle = 0.0D;
               }

            }
         }, 0L, 1L);
      }

   }
}
