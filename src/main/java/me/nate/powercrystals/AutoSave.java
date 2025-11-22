package me.nate.powercrystals;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.Bukkit;

public class AutoSave {
   public static void AutoSaveStones() {
      Bukkit.getScheduler().scheduleSyncRepeatingTask(InfinityStones.getPlugin(InfinityStones.class), new Runnable() {
         public void run() {
            final Map<String, Boolean> power = InfinityStones.power;
            final Map<String, Boolean> time = InfinityStones.time;
            final Map<String, Boolean> mind = InfinityStones.mind;
            final Map<String, Boolean> space = InfinityStones.space;
            final Map<String, Boolean> reality = InfinityStones.reality;
            final Map<String, Boolean> soul = InfinityStones.soul;
            Bukkit.getScheduler().runTaskAsynchronously(InfinityStones.getPlugin(InfinityStones.class), new Runnable() {
               public void run() {
                  Iterator var2 = power.entrySet().iterator();

                  Entry entry;
                  while(var2.hasNext()) {
                     entry = (Entry)var2.next();
                     InfinityStones.getInstance().data.getConfig().set("power." + (String)entry.getKey(), entry.getValue());
                  }

                  var2 = time.entrySet().iterator();

                  while(var2.hasNext()) {
                     entry = (Entry)var2.next();
                     InfinityStones.getInstance().data.getConfig().set("time." + (String)entry.getKey(), entry.getValue());
                  }

                  var2 = mind.entrySet().iterator();

                  while(var2.hasNext()) {
                     entry = (Entry)var2.next();
                     InfinityStones.getInstance().data.getConfig().set("mind." + (String)entry.getKey(), entry.getValue());
                  }

                  var2 = space.entrySet().iterator();

                  while(var2.hasNext()) {
                     entry = (Entry)var2.next();
                     InfinityStones.getInstance().data.getConfig().set("space." + (String)entry.getKey(), entry.getValue());
                  }

                  var2 = reality.entrySet().iterator();

                  while(var2.hasNext()) {
                     entry = (Entry)var2.next();
                     InfinityStones.getInstance().data.getConfig().set("reality." + (String)entry.getKey(), entry.getValue());
                  }

                  var2 = soul.entrySet().iterator();

                  while(var2.hasNext()) {
                     entry = (Entry)var2.next();
                     InfinityStones.getInstance().data.getConfig().set("soul." + (String)entry.getKey(), entry.getValue());
                  }

                  InfinityStones.getInstance().data.saveConfig();
               }
            });
         }
      }, 0L, 1200L);
   }
}
