package me.nate.powercrystals;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class StoneCommand implements CommandExecutor {
   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
      Player target;
      if (label.equalsIgnoreCase("gauntlet")) {
         if (sender instanceof Player) {
            target = (Player)sender;
            new StonesGUI(target);
         } else {
            sender.sendMessage("You can only use this command in game!");
         }
      } else if (label.equalsIgnoreCase("getstone")) {
         try {
            if (args.length >= 2) {
               target = Bukkit.getPlayer(args[1]);
               if (sender.hasPermission("infinitystones.getstones")) {
                  if (args[0].equalsIgnoreCase("all")) {
                     target.getInventory().addItem(new ItemStack[]{(new ItemBuilder(Config.mindStoneItem())).name(Config.mindStoneName()).addGlow().build()});
                     target.getInventory().addItem(new ItemStack[]{(new ItemBuilder(Config.realityStoneItem())).name(Config.realityStoneName()).addGlow().build()});
                     target.getInventory().addItem(new ItemStack[]{(new ItemBuilder(Config.soulStoneItem())).name(Config.soulStoneName()).addGlow().build()});
                     target.getInventory().addItem(new ItemStack[]{(new ItemBuilder(Config.timeStoneItem())).name(Config.timeStoneName()).addGlow().build()});
                     target.getInventory().addItem(new ItemStack[]{(new ItemBuilder(Config.spaceStoneItem())).name(Config.spaceStoneName()).addGlow().build()});
                     target.getInventory().addItem(new ItemStack[]{(new ItemBuilder(Config.powerStoneItem())).name(Config.powerStoneName()).addGlow().build()});
                  } else if (args[0].equalsIgnoreCase("space")) {
                     target.getInventory().addItem(new ItemStack[]{(new ItemBuilder(Config.spaceStoneItem())).name(Config.spaceStoneName()).addGlow().build()});
                  } else if (args[0].equalsIgnoreCase("mind")) {
                     target.getInventory().addItem(new ItemStack[]{(new ItemBuilder(Config.mindStoneItem())).name(Config.mindStoneName()).addGlow().build()});
                  } else if (args[0].equalsIgnoreCase("reality")) {
                     target.getInventory().addItem(new ItemStack[]{(new ItemBuilder(Config.realityStoneItem())).name(Config.realityStoneName()).addGlow().build()});
                  } else if (args[0].equalsIgnoreCase("soul")) {
                     target.getInventory().addItem(new ItemStack[]{(new ItemBuilder(Config.soulStoneItem())).name(Config.soulStoneName()).addGlow().build()});
                  } else if (args[0].equalsIgnoreCase("time")) {
                     target.getInventory().addItem(new ItemStack[]{(new ItemBuilder(Config.timeStoneItem())).name(Config.timeStoneName()).addGlow().build()});
                  } else if (args[0].equalsIgnoreCase("power")) {
                     target.getInventory().addItem(new ItemStack[]{(new ItemBuilder(Config.powerStoneItem())).name(Config.powerStoneName()).addGlow().build()});
                  } else if (args[0].equalsIgnoreCase("gauntlet")) {
                     target.getInventory().addItem(new ItemStack[]{(new ItemBuilder(Material.HONEYCOMB)).name("&cInf&6ini&ety &aGau&9ntl&5et").addGlow().localisedName("gauntlet").build()});
                  }
               } else {
                  sender.sendMessage(this.color(Config.prefix() + "&cYou can't use this command!"));
               }
            } else {
               sender.sendMessage(this.color(Config.prefix() + "&cImproper Usage! Use: /getstone <stone> <player>"));
            }
         } catch (NullPointerException var8) {
            sender.sendMessage(this.color("&cPlayer not found!"));
         }
      } else if (label.equalsIgnoreCase("removestone")) {
         if (args.length >= 2) {
            if (sender.hasPermission("infinitystones.removestones")) {
               try {
                  target = Bukkit.getPlayer(args[0]);
                  if (args[1].equalsIgnoreCase("mind")) {
                     InfinityStones.mind.put(target.getUniqueId().toString(), false);
                     sender.sendMessage(this.color("&7Removed the &eMind Stone&7 from &6" + target.getName()));
                  } else if (args[1].equalsIgnoreCase("space")) {
                     InfinityStones.space.put(target.getUniqueId().toString(), false);
                     sender.sendMessage(this.color("&7Removed the &9Space Stone&7 from &6" + target.getName()));
                     target.setAllowFlight(false);
                  } else if (args[1].equalsIgnoreCase("time")) {
                     InfinityStones.time.put(target.getUniqueId().toString(), false);
                     sender.sendMessage(this.color("&7Removed the &aTime Stone&7 from &6" + target.getName()));
                  } else if (args[1].equalsIgnoreCase("power")) {
                     InfinityStones.power.put(target.getUniqueId().toString(), false);
                     sender.sendMessage(this.color("&7Removed the &5Power Stone&7 from &6" + target.getName()));
                  } else if (args[1].equalsIgnoreCase("reality")) {
                     InfinityStones.reality.put(target.getUniqueId().toString(), false);
                     sender.sendMessage(this.color("&7Removed the &cReality Stone&7 from &6" + target.getName()));
                  } else if (args[1].equalsIgnoreCase("soul")) {
                     InfinityStones.soul.put(target.getUniqueId().toString(), false);
                     sender.sendMessage(this.color("&7Removed the &6Soul Stone&7 from &6" + target.getName()));
                  } else if (args[1].equalsIgnoreCase("all")) {
                     InfinityStones.soul.put(target.getUniqueId().toString(), false);
                     InfinityStones.mind.put(target.getUniqueId().toString(), false);
                     InfinityStones.space.put(target.getUniqueId().toString(), false);
                     InfinityStones.time.put(target.getUniqueId().toString(), false);
                     InfinityStones.power.put(target.getUniqueId().toString(), false);
                     InfinityStones.reality.put(target.getUniqueId().toString(), false);
                     sender.sendMessage(this.color("&7Removed &cA&6ll &eSt&ao&9n&5es&7 from &6" + target.getName()));
                  } else {
                     sender.sendMessage(this.color("&6" + args[1] + Config.invalidStoneType()));
                  }
               } catch (NullPointerException var7) {
                  sender.sendMessage(this.color("&cCouldn't find the player &6" + args[0] + "&c!"));
               }
            } else {
               sender.sendMessage(this.color(Config.noPermission()));
            }
         } else {
            sender.sendMessage(this.color(Config.prefix() + "&cImproper Usage! Use: /removestone <player> <stone>"));
         }
      } else if (label.equalsIgnoreCase("equipstone")) {
         if (args.length >= 2) {
            if (sender.hasPermission("infinitystones.equipstones")) {
               try {
                  target = Bukkit.getPlayer(args[0]);
                  if (args[1].equalsIgnoreCase("mind")) {
                     InfinityStones.mind.put(target.getUniqueId().toString(), true);
                     sender.sendMessage(this.color(Config.prefix() + "&7Equipped the &eMind Stone&7 for &6" + target.getName()));
                  } else if (args[1].equalsIgnoreCase("space")) {
                     InfinityStones.space.put(target.getUniqueId().toString(), true);
                     sender.sendMessage(this.color(Config.prefix() + "&7Equipped the &9Space Stone&7 for &6" + target.getName()));
                  } else if (args[1].equalsIgnoreCase("time")) {
                     InfinityStones.time.put(target.getUniqueId().toString(), true);
                     sender.sendMessage(this.color(Config.prefix() + "&7Equipped the &aTime Stone&7 for &6" + target.getName()));
                  } else if (args[1].equalsIgnoreCase("power")) {
                     InfinityStones.power.put(target.getUniqueId().toString(), true);
                     sender.sendMessage(this.color(Config.prefix() + "&7Equipped the &5Power Stone&7 for &6" + target.getName()));
                  } else if (args[1].equalsIgnoreCase("reality")) {
                     InfinityStones.reality.put(target.getUniqueId().toString(), true);
                     sender.sendMessage(this.color(Config.prefix() + "&7Equipped the &cReality Stone&7 for &6" + target.getName()));
                  } else if (args[1].equalsIgnoreCase("soul")) {
                     InfinityStones.soul.put(target.getUniqueId().toString(), true);
                     sender.sendMessage(this.color(Config.prefix() + "&7Equipped the &6Soul Stone&7 for &6" + target.getName()));
                  } else if (args[1].equalsIgnoreCase("all")) {
                     InfinityStones.soul.put(target.getUniqueId().toString(), true);
                     InfinityStones.mind.put(target.getUniqueId().toString(), true);
                     InfinityStones.space.put(target.getUniqueId().toString(), true);
                     InfinityStones.time.put(target.getUniqueId().toString(), true);
                     InfinityStones.power.put(target.getUniqueId().toString(), true);
                     InfinityStones.reality.put(target.getUniqueId().toString(), true);
                     sender.sendMessage(this.color(Config.prefix() + "&7Equipped &cA&6ll &eSt&ao&9n&5es&7 for &6" + target.getName()));
                  } else {
                     sender.sendMessage(this.color("&6" + args[1] + Config.invalidStoneType()));
                  }
               } catch (NullPointerException var6) {
                  sender.sendMessage(this.color(Config.prefix() + "&cCouldn't find the player &6" + args[0] + "&c!"));
               }
            } else {
               sender.sendMessage(this.color(Config.noPermission()));
            }
         } else {
            sender.sendMessage(this.color(Config.prefix() + "&cImproper Usage! Use: /equipstone <player> <stone>"));
         }
      } else if (label.equalsIgnoreCase("infinitystones")) {
         if (sender instanceof Player) {
            target = (Player)sender;
            target.sendMessage(this.color("&6-----------------&f[ &cIn&6fi&eni&aty &bSt&9on&5es&f ]&6-----------------"));
            target.sendMessage(this.color("&7Made by Natecb13                                          v" + InfinityStones.getInstance().getDescription().getVersion()));
            target.sendMessage(" ");
            target.sendMessage(this.color("&6Spigot Page:&7 https://www.spigotmc.org/resources/authors/natecb13.956518/ "));
            target.sendMessage(this.color("&d&lPlugin Support Discord: &7https://discord.gg/tPzaPKPmNU"));
            target.sendMessage(" ");
            target.sendMessage(this.color("&6&lCommands:"));
            target.sendMessage(this.color("&b&l/gauntlet&7 - opens the infinity gauntlet."));
            if (target.hasPermission("infninitystones.getstone")) {
               target.sendMessage(this.color("&b&l/getstone <stone>&7 - used to give the player the infinity stones"));
               target.sendMessage(" ");
            }

            if (target.hasPermission("infinitystones.removestone")) {
               target.sendMessage(this.color("&b&l/removestone <player> <stone>&7 - used to remove stones from a player"));
               target.sendMessage(" ");
            }

            if (target.hasPermission("infinitystones.equipstone")) {
               target.sendMessage(this.color("&b&l/equipstone <player> <stone>&7 - used to equip stones for a player"));
               target.sendMessage(" ");
            }

            target.sendMessage(this.color("&6------------------------------------------------"));
         }
      } else if (label.equalsIgnoreCase("isreload")) {
         if (sender.hasPermission("infinitystones.reload")) {
            Config.reloadConfig();
            sender.sendMessage(this.color(Config.prefix() + "&aConfig reloaded!"));
         } else {
            sender.sendMessage(Config.noPermission());
         }
      }

      return false;
   }

   public String color(String s) {
      return ChatColor.translateAlternateColorCodes('&', s);
   }
}
