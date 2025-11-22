package me.nate.powercrystals;

import java.util.HashMap;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class PowerStone implements Listener {
   @EventHandler(
      ignoreCancelled = true
   )
   public void onHit(EntityDamageByEntityEvent e) {
      if (e.getEntity() instanceof Damageable && e.getDamager() instanceof Player) {
         Player player = (Player)e.getDamager();
         if (InfinityStones.hasStone(player, (HashMap)InfinityStones.power)) {
            Double d = e.getDamage() * (1.0D + Config.powerStoneDamageMultiplier());
            e.setDamage(d);
         }
      }

   }

   @EventHandler(
      ignoreCancelled = true
   )
   public void onDamage(EntityDamageEvent e) {
      if (e.getEntity() instanceof Player) {
         Player player = (Player)e.getEntity();
         if (InfinityStones.hasStone(player, (HashMap)InfinityStones.reality)) {
            Double amt = e.getDamage() * (1.0D - Config.realityStoneDamageReduction());
            e.setDamage(amt);
         }
      }

   }
}
