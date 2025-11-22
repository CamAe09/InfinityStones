package me.nate.powercrystals;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TimeStone implements Listener {
   public static HashMap<UUID, Long> cooldown = new HashMap();

   @EventHandler
   public void onInteract(PlayerInteractEvent e) {
      Player player = e.getPlayer();
      if (e.getItem() != null && isGauntletItem(e.getItem()) && e.getAction() == Action.RIGHT_CLICK_AIR) {
         if (cooldown.containsKey(player.getUniqueId()) && System.currentTimeMillis() < (Long)cooldown.get(player.getUniqueId())) {
            int time = (int)((Long)cooldown.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000;
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.teleportCooldownMessage().replaceAll("%time%", "" + time)));
            return;
         }

         if (Config.teleportAbility() && InfinityStones.hasStone(player, (HashMap)InfinityStones.space)) {
            Block block = e.getPlayer().getTargetBlock((Set)null, 75);
            Location location = new Location(block.getWorld(), (double)block.getX() + 0.5D, (double)(block.getY() + 1), (double)block.getZ() + 0.5D, e.getPlayer().getLocation().getYaw(), e.getPlayer().getLocation().getPitch());

            while(location.getBlock().getType() != Material.AIR || location.getBlock().getRelative(0, 1, 0).getType() != Material.AIR) {
               if (location.getBlockY() == 256) {
                  return;
               }

               location.add(0.0D, 1.0D, 0.0D);
            }

            if (block.getType() != Material.AIR) {
               e.getPlayer().teleport(location);
               e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
               player.sendMessage(color(Config.spaceStoneTeleport()));
               long now = System.currentTimeMillis();
               long thirtyMillis = (long)(Config.spaceStoneTeleportCooldown() * 1000);
               long nowPlusThirty = now + thirtyMillis;
               cooldown.put(player.getUniqueId(), nowPlusThirty);
            }
         }
      }

   }

   public static boolean isGauntletItem(ItemStack item) {
      if (item == null) {
         return false;
      } else {
         Iterator var2 = Config.itemMaterial().iterator();

         while(var2.hasNext()) {
            Material mat = (Material)var2.next();
            if (item.getType().equals(mat)) {
               return true;
            }
         }

         if (!item.hasItemMeta()) {
            return false;
         } else {
            var2 = Config.itemName().iterator();

            String str;
            while(var2.hasNext()) {
               str = (String)var2.next();
               if (item.getItemMeta().getDisplayName().equals(color(str))) {
                  return true;
               }
            }

            var2 = Config.itemLocalizedName().iterator();

            while(var2.hasNext()) {
               str = (String)var2.next();
               if (item.getItemMeta().getLocalizedName().equals(color(str))) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   public static void startTimeRunnable() {
      Bukkit.getScheduler().scheduleSyncRepeatingTask(InfinityStones.getPlugin(InfinityStones.class), new Runnable() {
         public void run() {
            Iterator var2 = Bukkit.getServer().getOnlinePlayers().iterator();

            while(var2.hasNext()) {
               Player player = (Player)var2.next();
               if (Config.luckAbility() && InfinityStones.hasStone(player, (HashMap)InfinityStones.time)) {
                  player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 21, Config.luckLevel() - 1));
               }
            }

         }
      }, 0L, 20L);
   }

   public static String color(String s) {
      return ChatColor.translateAlternateColorCodes('&', s);
   }
}
