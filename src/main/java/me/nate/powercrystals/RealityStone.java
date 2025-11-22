package me.nate.powercrystals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RealityStone implements Listener {
   public static HashMap<UUID, Long> cooldown = new HashMap();
   private static List<Snowball> realitySnowballEntities = new ArrayList();
   private static List<Snowball> allStonesSnowballEntities = new ArrayList();

   public static void realityStone() {
      Bukkit.getScheduler().scheduleSyncRepeatingTask(InfinityStones.getPlugin(InfinityStones.class), new Runnable() {
         public void run() {
            Iterator var2 = Bukkit.getServer().getOnlinePlayers().iterator();

            while(var2.hasNext()) {
               Player player = (Player)var2.next();
               if (Config.speedAbility() && InfinityStones.hasStone(player, (HashMap)InfinityStones.reality)) {
                  player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 21, Config.speedLevel() - 1));
               }
            }

         }
      }, 0L, 20L);
   }

   @EventHandler
   public void onClick(PlayerInteractEvent e) {
      Player player = e.getPlayer();
      if (e.getItem() != null && TimeStone.isGauntletItem(e.getItem()) && (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK)) {
         if (cooldown.containsKey(player.getUniqueId()) && System.currentTimeMillis() < (Long)cooldown.get(player.getUniqueId())) {
            return;
         }

         final Projectile snowball;
         long now;
         long thirtyMillis;
         long nowPlusThirty;
         if (InfinityStones.hasAllStones(player)) {
            if (Config.allPowerBeam()) {
               snowball = (Projectile)player.getWorld().spawn(player.getLocation().add(0.0D, 1.5D, 0.0D), Snowball.class);
               snowball.setShooter(player);
               allStonesSnowballEntities.add((Snowball)snowball);
               snowball.setGravity(false);
               snowball.setVelocity(player.getLocation().getDirection().normalize().multiply(2));
               snowball.setFireTicks(40);
               player.getWorld().playSound(player.getLocation(), Sound.ENTITY_SNOW_GOLEM_SHOOT, 1.0F, 1.0F);
               Bukkit.getScheduler().scheduleSyncDelayedTask(InfinityStones.getPlugin(InfinityStones.class), new Runnable() {
                  public void run() {
                     snowball.remove();
                     if (RealityStone.allStonesSnowballEntities.contains(snowball)) {
                        RealityStone.allStonesSnowballEntities.remove(snowball);
                     }

                  }
               }, 40L);
               now = System.currentTimeMillis();
               thirtyMillis = (long)(Config.powerBeamCooldown() * 1000);
               nowPlusThirty = now + thirtyMillis;
               cooldown.put(player.getUniqueId(), nowPlusThirty);
            }
         } else if (InfinityStones.hasStone(player, (HashMap)InfinityStones.power) && Config.powerBeam()) {
            snowball = (Projectile)player.getWorld().spawn(player.getLocation().add(0.0D, 1.5D, 0.0D), Snowball.class);
            snowball.setShooter(player);
            realitySnowballEntities.add((Snowball)snowball);
            snowball.setGravity(false);
            snowball.setVelocity(player.getLocation().getDirection().normalize().multiply(2));
            snowball.setFireTicks(40);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_SNOW_GOLEM_SHOOT, 1.0F, 1.0F);
            Bukkit.getScheduler().scheduleSyncDelayedTask(InfinityStones.getPlugin(InfinityStones.class), new Runnable() {
               public void run() {
                  if (RealityStone.realitySnowballEntities.contains(snowball)) {
                     RealityStone.realitySnowballEntities.remove(snowball);
                  }

                  snowball.remove();
               }
            }, 40L);
            now = System.currentTimeMillis();
            thirtyMillis = (long)(Config.powerBeamCooldown() * 1000);
            nowPlusThirty = now + thirtyMillis;
            cooldown.put(player.getUniqueId(), nowPlusThirty);
         }
      }

   }

   @EventHandler
   public void onHit(ProjectileHitEvent e) {
      if (e.getEntity().getType().equals(EntityType.SNOWBALL)) {
         try {
            Entity entity;
            Iterator var3;
            if (realitySnowballEntities.contains(e.getEntity())) {
               realitySnowballEntities.remove(e.getEntity());
               var3 = e.getEntity().getWorld().getNearbyEntities(e.getEntity().getLocation(), 3.0D, 3.0D, 3.0D).iterator();

               while(var3.hasNext()) {
                  entity = (Entity)var3.next();
                  if (e.getEntity().getShooter() != entity && entity instanceof Damageable && !(entity instanceof ArmorStand)) {
                     entity.setFireTicks(Config.powerStoneBeamFireTicks());
                     ((Damageable)entity).damage(Config.powerStoneBeamDamage());
                  }
               }

               e.getEntity().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, e.getEntity().getLocation(), 1);
               e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1.0F, 1.0F);
            }

            if (allStonesSnowballEntities.contains(e.getEntity())) {
               allStonesSnowballEntities.remove(e.getEntity());
               var3 = e.getEntity().getWorld().getNearbyEntities(e.getEntity().getLocation(), 5.0D, 5.0D, 5.0D).iterator();

               while(var3.hasNext()) {
                  entity = (Entity)var3.next();
                  if (e.getEntity().getShooter() != entity && entity instanceof Damageable && !(entity instanceof ArmorStand)) {
                     entity.setFireTicks(Config.allStonesPowerBeamFireticks());
                     ((Damageable)entity).damage(Config.allStonesPowerBeamDamage());
                  }
               }

               e.getEntity().getWorld().spawnParticle(Particle.EXPLOSION_HUGE, e.getEntity().getLocation(), 1);
               e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1.0F, 1.0F);
            }
         } catch (NullPointerException | ClassCastException var4) {
         }
      }

   }

   public static void realityStoneRunabble() {
      Bukkit.getScheduler().scheduleSyncRepeatingTask(InfinityStones.getPlugin(InfinityStones.class), new Runnable() {
         public void run() {
            try {
               Iterator var2 = RealityStone.realitySnowballEntities.iterator();

               Snowball ent;
               while(var2.hasNext()) {
                  ent = (Snowball)var2.next();
                  DustOptions dustOptions = new DustOptions(Color.fromRGB(139, 0, 209), 1.0F);
                  ent.getWorld().spawnParticle(Particle.REDSTONE, ent.getLocation(), 10, 0.1D, 0.1D, 0.1D, dustOptions);
               }

               var2 = RealityStone.allStonesSnowballEntities.iterator();

               while(var2.hasNext()) {
                  ent = (Snowball)var2.next();
                  int i = (int)(Math.random() * 6.0D + 1.0D);
                  DustOptions dustOptionsx;
                  if (i == 1) {
                     dustOptionsx = new DustOptions(Color.fromRGB(227, 0, 19), 1.0F);
                     ent.getWorld().spawnParticle(Particle.REDSTONE, ent.getLocation(), 10, 0.1D, 0.1D, 0.1D, dustOptionsx);
                  }

                  if (i == 2) {
                     dustOptionsx = new DustOptions(Color.fromRGB(252, 140, 3), 1.0F);
                     ent.getWorld().spawnParticle(Particle.REDSTONE, ent.getLocation(), 10, 0.1D, 0.1D, 0.1D, dustOptionsx);
                  }

                  if (i == 3) {
                     dustOptionsx = new DustOptions(Color.fromRGB(252, 227, 3), 1.0F);
                     ent.getWorld().spawnParticle(Particle.REDSTONE, ent.getLocation(), 10, 0.1D, 0.1D, 0.1D, dustOptionsx);
                  }

                  if (i == 4) {
                     dustOptionsx = new DustOptions(Color.fromRGB(65, 252, 3), 1.0F);
                     ent.getWorld().spawnParticle(Particle.REDSTONE, ent.getLocation(), 10, 0.1D, 0.1D, 0.1D, dustOptionsx);
                  }

                  if (i == 5) {
                     dustOptionsx = new DustOptions(Color.fromRGB(3, 103, 252), 1.0F);
                     ent.getWorld().spawnParticle(Particle.REDSTONE, ent.getLocation(), 10, 0.1D, 0.1D, 0.1D, dustOptionsx);
                  }

                  if (i == 6) {
                     dustOptionsx = new DustOptions(Color.fromRGB(144, 3, 252), 1.0F);
                     ent.getWorld().spawnParticle(Particle.REDSTONE, ent.getLocation(), 10, 0.1D, 0.1D, 0.1D, dustOptionsx);
                  }
               }
            } catch (NullPointerException var5) {
            }

         }
      }, 0L, 1L);
   }
}
