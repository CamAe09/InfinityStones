package me.nate.powercrystals;

import java.util.HashMap;
import java.util.Iterator;
import org.bukkit.Bukkit;
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MindStone implements Listener {
   @EventHandler(
      ignoreCancelled = true
   )
   public void onHit(EntityDamageByEntityEvent e) {
      if (e.getDamager() instanceof Player) {
         Player player = (Player)e.getDamager();
         if (e.getEntity() instanceof Damageable && e.getEntity() instanceof LivingEntity && !(e.getEntity() instanceof ArmorStand)) {
            Entity ent = e.getEntity();
            if (InfinityStones.hasStone(player, (HashMap)InfinityStones.mind)) {
               int i = (int)(Math.random() * 100.0D + 1.0D);
               if (i <= Config.mindStoneStunChance()) {
                  ((LivingEntity)ent).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 9));
                  DustOptions dustOptions = new DustOptions(Color.fromRGB(250, 243, 37), 1.0F);
                  ent.getWorld().spawnParticle(Particle.REDSTONE, ent.getLocation().add(0.0D, 1.0D, 0.0D), 50, 0.5D, 0.5D, 0.5D, dustOptions);
                  player.getWorld().playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1.0F, 1.0F);
               }
            }
         }
      }

   }

   public static void startMindRunnable() {
      Bukkit.getScheduler().scheduleSyncRepeatingTask(InfinityStones.getPlugin(InfinityStones.class), new Runnable() {
         public void run() {
            if (Config.nightvVisionAbility()) {
               Iterator var2 = Bukkit.getServer().getOnlinePlayers().iterator();

               while(var2.hasNext()) {
                  Player player = (Player)var2.next();
                  if (InfinityStones.hasStone(player, (HashMap)InfinityStones.mind)) {
                     player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 240, 0));
                  }
               }
            }

         }
      }, 0L, 20L);
   }
}
