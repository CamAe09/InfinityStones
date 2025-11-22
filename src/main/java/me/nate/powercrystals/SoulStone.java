package me.nate.powercrystals;

import java.util.HashMap;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SoulStone implements Listener {
   @EventHandler(
      ignoreCancelled = true
   )
   public void onKill(EntityDeathEvent e) {
      try {
         Entity dead = e.getEntity();
         if (dead instanceof Damageable) {
            Player player = ((LivingEntity)dead).getKiller();
            if (Config.absorbtionAbility() && InfinityStones.hasStone(player, (HashMap)InfinityStones.soul)) {
               double amt = ((Damageable)dead).getMaxHealth() / 5.0D;
               if (amt > 20.0D) {
                  amt = 20.0D;
               }

               player.setAbsorptionAmount(amt);
            }
         }
      } catch (NullPointerException var6) {
      }

   }

   @EventHandler(
      ignoreCancelled = true
   )
   public void onHit(EntityDamageByEntityEvent e) {
      if (e.getDamager() instanceof Player) {
         Player player = (Player)e.getDamager();
         if (e.getEntity() instanceof LivingEntity && !(e.getEntity() instanceof ArmorStand)) {
            Entity ent = e.getEntity();
            if (InfinityStones.hasStone(player, (HashMap)InfinityStones.soul)) {
               int i = (int)(Math.random() * 100.0D + 1.0D);
               if (i <= Config.soulStoneChokeChance()) {
                  DustOptions dustOptions = new DustOptions(Color.fromRGB(232, 127, 7), 1.0F);
                  ent.getWorld().spawnParticle(Particle.REDSTONE, ent.getLocation().add(0.0D, 1.0D, 0.0D), 50, 0.5D, 0.5D, 0.5D, dustOptions);
                  ((LivingEntity)ent).addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 60, 0));
                  ((LivingEntity)ent).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 80, 0));
                  ((LivingEntity)ent).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 80, 2));
                  ent.getWorld().playSound(ent.getLocation(), Sound.ENTITY_DONKEY_HURT, 1.0F, 1.0F);
               }
            }
         }
      }

   }
}
