package me.nate.powercrystals;

import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpaceStone implements Listener {
   @EventHandler
   public void onSneak(PlayerToggleSneakEvent e) {
      Player player = e.getPlayer();
      if (Config.slowFallAbility() && InfinityStones.hasStone(player, (HashMap)InfinityStones.time)) {
         if (player.isSneaking()) {
            player.removePotionEffect(PotionEffectType.SLOW_FALLING);
         }

         if (!player.isSneaking() && player.getWorld().getBlockAt(player.getLocation().subtract(0.0D, 1.0D, 0.0D)).getType() == Material.AIR) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 72000, 2));
            player.getWorld().playSound(player.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 1.0F, 0.75F);
         }
      }

   }

   @EventHandler
   public void onToggleFlight(PlayerToggleFlightEvent e) {
      Player player = e.getPlayer();
      if (InfinityStones.hasStone(player, (HashMap)InfinityStones.space)) {
         if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR) {
            return;
         }

         e.setCancelled(true);
         player.setAllowFlight(false);
         player.setFlying(false);
         player.setVelocity(player.getLocation().getDirection().multiply(Config.doubleJumpVelocityMultiplier()).setY(Config.doubleJumpVelocityMultiplier()));
         player.getWorld().playSound(player.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 0.5F, 1.5F);
      }

   }

   @EventHandler
   public void onPlayerMove(PlayerMoveEvent e) {
      Player player = e.getPlayer();
      if (Config.doubleJump() && InfinityStones.hasStone(player, (HashMap)InfinityStones.space) && player.getGameMode() != GameMode.CREATIVE && player.getGameMode() != GameMode.SPECTATOR && player.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().getType() != Material.AIR && !player.isFlying() && !player.getWorld().getBlockAt(player.getLocation().subtract(0.0D, 1.0D, 0.0D)).isPassable() && !player.getWorld().getBlockAt(player.getLocation().subtract(0.0D, 1.0D, 0.0D)).getType().name().contains("_SLAB") && (player.getWorld().getBlockAt(player.getLocation().subtract(0.0D, 1.0D, 0.0D)).getPistonMoveReaction() != PistonMoveReaction.BREAK || player.getWorld().getBlockAt(player.getLocation().subtract(0.0D, 1.0D, 0.0D)).getType().name().contains("SHULKER"))) {
         player.setAllowFlight(true);
      }

   }

   @EventHandler
   public void onLeave(InventoryCloseEvent e) {
      Player player = (Player)e.getPlayer();
      if (e.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&8" + player.getName() + "'s Gauntlet")) && player.getGameMode() == GameMode.SURVIVAL) {
         player.setAllowFlight(false);
      }

   }
}
