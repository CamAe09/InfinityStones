package me.nate.powercrystals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class StonesTabCompleter implements TabCompleter {
   List<String> arguments = new ArrayList();

   public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
      this.arguments.add("SPACE");
      this.arguments.add("TIME");
      this.arguments.add("REALITY");
      this.arguments.add("SOUL");
      this.arguments.add("POWER");
      this.arguments.add("MIND");
      this.arguments.add("ALL");
      this.arguments.add("GAUNTLET");
      List<String> result = new ArrayList();
      if (args.length == 1) {
         Iterator var7 = this.arguments.iterator();

         while(var7.hasNext()) {
            String a = (String)var7.next();
            if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
               result.add(a);
            }
         }

         return result;
      } else {
         return null;
      }
   }
}
