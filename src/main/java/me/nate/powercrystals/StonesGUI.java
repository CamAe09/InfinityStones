package me.nate.powercrystals;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class StonesGUI {
   public StonesGUI(Player player) {
      String uuid = player.getUniqueId().toString();
      Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, ChatColor.translateAlternateColorCodes('&', Config.guiName().replace("%player-plural%", player.getName() + "'s").replace("%player%", player.getName())));
      ItemStack power;
      if ((Boolean)InfinityStones.power.get(uuid)) {
         power = (new ItemBuilder(Config.powerStoneItem())).name(Config.powerStoneName()).lore(Config.guiPowerStoneLore()).addGlow().build();
      } else {
         power = (new ItemBuilder(Material.GRAY_DYE)).name(this.color("&7" + ChatColor.stripColor(Config.powerStoneName()))).build();
      }

      ItemStack soul;
      if ((Boolean)InfinityStones.soul.get(uuid)) {
         soul = (new ItemBuilder(Config.soulStoneItem())).name(Config.soulStoneName()).lore(Config.guiSoulStoneLore()).addGlow().build();
      } else {
         soul = (new ItemBuilder(Material.GRAY_DYE)).name(this.color("&7" + ChatColor.stripColor(Config.soulStoneName()))).build();
      }

      ItemStack mind;
      if ((Boolean)InfinityStones.mind.get(uuid)) {
         mind = (new ItemBuilder(Config.mindStoneItem())).name(Config.mindStoneName()).lore(Config.guiMindStoneLore()).addGlow().build();
      } else {
         mind = (new ItemBuilder(Material.GRAY_DYE)).name(this.color("&7" + ChatColor.stripColor(Config.mindStoneName()))).build();
      }

      ItemStack space;
      if ((Boolean)InfinityStones.space.get(uuid)) {
         space = (new ItemBuilder(Config.spaceStoneItem())).name(Config.spaceStoneName()).lore(Config.guiSpaceStoneLore()).addGlow().build();
      } else {
         space = (new ItemBuilder(Material.GRAY_DYE)).name(this.color("&7" + ChatColor.stripColor(Config.spaceStoneName()))).build();
      }

      ItemStack reality;
      if ((Boolean)InfinityStones.reality.get(uuid)) {
         reality = (new ItemBuilder(Config.realityStoneItem())).name(Config.realityStoneName()).lore(Config.guiRealityStoneLore()).addGlow().build();
      } else {
         reality = (new ItemBuilder(Material.GRAY_DYE)).name(this.color("&7" + ChatColor.stripColor(Config.realityStoneName()))).build();
      }

      ItemStack time;
      if ((Boolean)InfinityStones.time.get(uuid)) {
         time = (new ItemBuilder(Config.timeStoneItem())).name(Config.timeStoneName()).lore(Config.guiTimeStoneLore()).addGlow().build();
      } else {
         time = (new ItemBuilder(Material.GRAY_DYE)).name(this.color("&7" + ChatColor.stripColor(Config.timeStoneName()))).build();
      }

      inv.setItem(Config.realityStoneSlot(), reality);
      inv.setItem(Config.spaceStoneSlot(), space);
      inv.setItem(Config.powerStoneSlot(), power);
      inv.setItem(Config.soulStoneSlot(), soul);
      inv.setItem(Config.timeStoneSlot(), time);
      inv.setItem(Config.mindStoneSlot(), mind);

      for(int i = 0; i < 27 && Config.fillMaterial() != Material.AIR; ++i) {
         if (inv.getItem(i) == null) {
            inv.setItem(i, (new ItemBuilder(Config.fillMaterial())).name("&7").build());
         }
      }

      player.openInventory(inv);
   }

   public String color(String s) {
      return ChatColor.translateAlternateColorCodes('&', s);
   }
}
