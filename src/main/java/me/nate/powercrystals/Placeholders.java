package me.nate.powercrystals;

import java.util.HashMap;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class Placeholders extends PlaceholderExpansion {
   public boolean canRegister() {
      return true;
   }

   public String getAuthor() {
      return "Natecb13";
   }

   public String getIdentifier() {
      return "infinitystones";
   }

   public String getPlugin() {
      return null;
   }

   public String getVersion() {
      return InfinityStones.getInstance().getDescription().getVersion();
   }

   public String onPlaceholderRequest(Player p, String identifier) {
      String s;
      if (identifier.equals("gauntlet")) {
         s = "";
         if (InfinityStones.hasStone(p, (HashMap)InfinityStones.soul)) {
            s = s + "&6⬤ ";
         } else {
            s = s + "&7⬤ ";
         }

         if (InfinityStones.hasStone(p, (HashMap)InfinityStones.reality)) {
            s = s + "&c⬤ ";
         } else {
            s = s + "&7⬤ ";
         }

         if (InfinityStones.hasStone(p, (HashMap)InfinityStones.space)) {
            s = s + "&9⬤ ";
         } else {
            s = s + "&7⬤ ";
         }

         if (InfinityStones.hasStone(p, (HashMap)InfinityStones.mind)) {
            s = s + "&e⬤ ";
         } else {
            s = s + "&7⬤ ";
         }

         if (InfinityStones.hasStone(p, (HashMap)InfinityStones.power)) {
            s = s + "&5⬤ ";
         } else {
            s = s + "&7⬤ ";
         }

         if (InfinityStones.hasStone(p, (HashMap)InfinityStones.time)) {
            s = s + "&a⬤ ";
         } else {
            s = s + "&7⬤ ";
         }

         return s;
      } else if (identifier.equals("soul_icon")) {
         s = "";
         if (InfinityStones.hasStone(p, (HashMap)InfinityStones.soul)) {
            s = s + "&6⬤ ";
         } else {
            s = s + "&7⬤ ";
         }

         return s;
      } else if (identifier.equals("reality_icon")) {
         s = "";
         if (InfinityStones.hasStone(p, (HashMap)InfinityStones.reality)) {
            s = s + "&c⬤ ";
         } else {
            s = s + "&7⬤ ";
         }

         return s;
      } else if (identifier.equals("space_icon")) {
         s = "";
         if (InfinityStones.hasStone(p, (HashMap)InfinityStones.space)) {
            s = s + "&9⬤ ";
         } else {
            s = s + "&7⬤ ";
         }

         return s;
      } else if (identifier.equals("mind_icon")) {
         s = "";
         if (InfinityStones.hasStone(p, (HashMap)InfinityStones.mind)) {
            s = s + "&e⬤ ";
         } else {
            s = s + "&7⬤ ";
         }

         return s;
      } else if (identifier.equals("power_icon")) {
         s = "";
         if (InfinityStones.hasStone(p, (HashMap)InfinityStones.power)) {
            s = s + "&5⬤ ";
         } else {
            s = s + "&7⬤ ";
         }

         return s;
      } else if (identifier.equals("time_icon")) {
         s = "";
         if (InfinityStones.hasStone(p, (HashMap)InfinityStones.time)) {
            s = s + "&a⬤ ";
         } else {
            s = s + "&7⬤ ";
         }

         return s;
      } else {
         int time;
         if (identifier.equals("teleport_cooldown")) {
            if (TimeStone.cooldown.containsKey(p.getUniqueId()) && System.currentTimeMillis() < (Long)TimeStone.cooldown.get(p.getUniqueId())) {
               time = (int)((Long)TimeStone.cooldown.get(p.getUniqueId()) - System.currentTimeMillis()) / 1000;
               return String.valueOf(time);
            } else {
               return "0";
            }
         } else if (identifier.equals("beam_cooldown")) {
            if (RealityStone.cooldown.containsKey(p.getUniqueId()) && System.currentTimeMillis() < (Long)RealityStone.cooldown.get(p.getUniqueId())) {
               time = (int)((Long)RealityStone.cooldown.get(p.getUniqueId()) - System.currentTimeMillis()) / 1000;
               return String.valueOf(time);
            } else {
               return "0";
            }
         } else {
            return null;
         }
      }
   }
}
