package me.nate.powercrystals;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import me.nate.powercrystals.Files.DataManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class InfinityStones extends JavaPlugin implements Listener {
   public static Map<String, Boolean> power = new HashMap();
   public static Map<String, Boolean> time = new HashMap();
   public static Map<String, Boolean> mind = new HashMap();
   public static Map<String, Boolean> space = new HashMap();
   public static Map<String, Boolean> reality = new HashMap();
   public static Map<String, Boolean> soul = new HashMap();
   Particles particles = new Particles();
   private static InfinityStones instance;
   public DataManager data;

   public void onEnable() {
      this.data = new DataManager(this);
      instance = this;
      new Config(this);
      System.out.println("InfinityStones has been enabled!");
      Bukkit.getPluginManager().registerEvents(this, this);
      Bukkit.getPluginManager().registerEvents(new SpaceStone(), this);
      Bukkit.getPluginManager().registerEvents(new TimeStone(), this);
      Bukkit.getPluginManager().registerEvents(new SoulStone(), this);
      Bukkit.getPluginManager().registerEvents(new PowerStone(), this);
      Bukkit.getPluginManager().registerEvents(new MindStone(), this);
      Bukkit.getPluginManager().registerEvents(new RealityStone(), this);
      this.getCommand("getstone").setExecutor(new StoneCommand());
      this.getCommand("gauntlet").setExecutor(new StoneCommand());
      this.getCommand("removestone").setExecutor(new StoneCommand());
      this.getCommand("equipstone").setExecutor(new StoneCommand());
      this.getCommand("infinitystones").setExecutor(new StoneCommand());
      this.getCommand("isreload").setExecutor(new StoneCommand());
      this.getCommand("getstone").setTabCompleter(new StonesTabCompleter());
      if (Config.showParticles()) {
         this.particles.createParticles();
      }

      this.refreshStones();
      TimeStone.startTimeRunnable();
      MindStone.startMindRunnable();
      RealityStone.realityStone();
      RealityStone.realityStoneRunabble();
      AutoSave.AutoSaveStones();
      this.data.saveDefaultConfig();
      if (this.data.getConfig().contains("power")) {
         this.restoreInvs();
      }

      new Metrics(this, 10410);
   }

   public void onDisable() {
      if (!power.isEmpty()) {
         this.saveInvs();
      }

      this.data.saveConfig();
   }

   public static InfinityStones getInstance() {
      return instance;
   }

   public void saveInvs() {
      Iterator var2 = power.entrySet().iterator();

      Entry entry;
      while(var2.hasNext()) {
         entry = (Entry)var2.next();
         this.data.getConfig().set("power." + (String)entry.getKey(), entry.getValue());
      }

      var2 = time.entrySet().iterator();

      while(var2.hasNext()) {
         entry = (Entry)var2.next();
         this.data.getConfig().set("time." + (String)entry.getKey(), entry.getValue());
      }

      var2 = mind.entrySet().iterator();

      while(var2.hasNext()) {
         entry = (Entry)var2.next();
         this.data.getConfig().set("mind." + (String)entry.getKey(), entry.getValue());
      }

      var2 = space.entrySet().iterator();

      while(var2.hasNext()) {
         entry = (Entry)var2.next();
         this.data.getConfig().set("space." + (String)entry.getKey(), entry.getValue());
      }

      var2 = reality.entrySet().iterator();

      while(var2.hasNext()) {
         entry = (Entry)var2.next();
         this.data.getConfig().set("reality." + (String)entry.getKey(), entry.getValue());
      }

      var2 = soul.entrySet().iterator();

      while(var2.hasNext()) {
         entry = (Entry)var2.next();
         this.data.getConfig().set("soul." + (String)entry.getKey(), entry.getValue());
      }

      this.data.saveConfig();
   }

   public void restoreInvs() {
      this.data.getConfig().getConfigurationSection("power").getKeys(false).forEach((key) -> {
         Boolean content = (Boolean)this.data.getConfig().get("power." + key);
         power.put(key, content);
      });
      this.data.getConfig().getConfigurationSection("time").getKeys(false).forEach((key) -> {
         Boolean content = (Boolean)this.data.getConfig().get("time." + key);
         time.put(key, content);
      });
      this.data.getConfig().getConfigurationSection("mind").getKeys(false).forEach((key) -> {
         Boolean content = (Boolean)this.data.getConfig().get("mind." + key);
         mind.put(key, content);
      });
      this.data.getConfig().getConfigurationSection("space").getKeys(false).forEach((key) -> {
         Boolean content = (Boolean)this.data.getConfig().get("space." + key);
         space.put(key, content);
      });
      this.data.getConfig().getConfigurationSection("reality").getKeys(false).forEach((key) -> {
         Boolean content = (Boolean)this.data.getConfig().get("reality." + key);
         reality.put(key, content);
      });
      this.data.getConfig().getConfigurationSection("soul").getKeys(false).forEach((key) -> {
         Boolean content = (Boolean)this.data.getConfig().get("soul." + key);
         soul.put(key, content);
      });
   }

   public void refreshStones() {
      Iterator var2 = Bukkit.getOnlinePlayers().iterator();

      while(var2.hasNext()) {
         Player player = (Player)var2.next();
         String uuid = player.getUniqueId().toString();
         if (!power.containsKey(uuid)) {
            power.put(uuid, false);
         }

         if (!time.containsKey(uuid)) {
            time.put(uuid, false);
         }

         if (!mind.containsKey(uuid)) {
            mind.put(uuid, false);
         }

         if (!reality.containsKey(uuid)) {
            reality.put(uuid, false);
         }

         if (!space.containsKey(uuid)) {
            space.put(uuid, false);
         }

         if (!soul.containsKey(uuid)) {
            soul.put(uuid, false);
         }
      }

   }

   @EventHandler
   public void onJoin(PlayerJoinEvent e) {
      this.refreshStones();
   }

   public static boolean hasStone(Player player, HashMap<String, Boolean> map) {
      if (player.hasPermission("infinitystones.usestones")) {
         if (Config.disabledWorlds().contains(player.getWorld())) {
            return false;
         }

         if (map.containsKey(player.getUniqueId().toString()) && (Boolean)map.get(player.getUniqueId().toString())) {
            return true;
         }
      }

      return false;
   }

   public static boolean hasAllStones(Player player) {
      String uuid = player.getUniqueId().toString();
      if (Config.disabledWorlds().contains(player.getWorld())) {
         return false;
      } else {
         return (Boolean)power.get(uuid) && (Boolean)mind.get(uuid) && (Boolean)reality.get(uuid) && (Boolean)space.get(uuid) && (Boolean)soul.get(uuid) && (Boolean)time.get(uuid);
      }
   }

   @EventHandler
   public void onInteract(PlayerInteractEvent e) {
      Player player = e.getPlayer();
      String uuid = player.getUniqueId().toString();
      ItemStack item = e.getItem();
      if (item != null && item.hasItemMeta()) {
         if (item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', Config.powerStoneName()))) {
            e.setCancelled(true);
            if (((Boolean)power.get(uuid)).equals(false)) {
               if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                  power.put(uuid, true);
                  player.sendTitle(this.color(Config.powerStoneEquip()), this.color(Config.powerStoneEquipSubtitle()), 0, 40, 0);
                  player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0F, 1.0F);
                  this.particleCould(100, player.getLocation().add(0.0D, 1.0D, 0.0D), Color.fromRGB(100, 0, 194), 0.5D, 1.0D, 0.5D);
                  this.allStoneCheck(player);
                  item.setAmount(item.getAmount() - 1);
               }
            } else {
               player.sendMessage(this.color(Config.prefix() + Config.alreadyEquipped()));
            }
         } else if (item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', Config.timeStoneName()))) {
            e.setCancelled(true);
            if (((Boolean)time.get(uuid)).equals(false)) {
               if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                  time.put(uuid, true);
                  player.sendTitle(this.color(Config.timeStoneEquip()), this.color(Config.timeStoneEquipSubtitle()), 0, 40, 20);
                  player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0F, 1.0F);
                  this.particleCould(100, player.getLocation().add(0.0D, 1.0D, 0.0D), Color.fromRGB(80, 227, 73), 0.5D, 1.0D, 0.5D);
                  this.allStoneCheck(player);
                  item.setAmount(item.getAmount() - 1);
               }
            } else {
               player.sendMessage(this.color(Config.prefix() + Config.alreadyEquipped()));
            }
         } else if (item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', Config.mindStoneName()))) {
            e.setCancelled(true);
            if (((Boolean)mind.get(uuid)).equals(false)) {
               if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                  mind.put(uuid, true);
                  player.sendTitle(this.color(Config.mindStoneEquip()), this.color(Config.mindStoneEquipSubtitle()), 0, 40, 20);
                  player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0F, 1.0F);
                  this.particleCould(100, player.getLocation().add(0.0D, 1.0D, 0.0D), Color.fromRGB(255, 234, 43), 0.5D, 1.0D, 0.5D);
                  this.allStoneCheck(player);
                  item.setAmount(item.getAmount() - 1);
               }
            } else {
               player.sendMessage(this.color(Config.prefix() + Config.alreadyEquipped()));
            }
         } else if (item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', Config.spaceStoneName()))) {
            e.setCancelled(true);
            if (((Boolean)space.get(uuid)).equals(false)) {
               if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                  space.put(uuid, true);
                  player.sendTitle(this.color(Config.spaceStoneEquip()), this.color(Config.spaceStoneEquipSubtitle()), 0, 40, 20);
                  player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0F, 1.0F);
                  this.particleCould(100, player.getLocation().add(0.0D, 1.0D, 0.0D), Color.fromRGB(48, 30, 250), 0.5D, 1.0D, 0.5D);
                  this.allStoneCheck(player);
                  item.setAmount(item.getAmount() - 1);
               }
            } else {
               player.sendMessage(this.color(Config.prefix() + Config.alreadyEquipped()));
            }
         } else if (item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', Config.realityStoneName()))) {
            e.setCancelled(true);
            if (((Boolean)reality.get(uuid)).equals(false)) {
               if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                  reality.put(uuid, true);
                  player.sendTitle(this.color(Config.realityStoneEquip()), this.color(Config.realityStoneEquipSubtitle()), 0, 40, 20);
                  player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0F, 1.0F);
                  this.particleCould(100, player.getLocation().add(0.0D, 1.0D, 0.0D), Color.fromRGB(194, 0, 0), 0.5D, 1.0D, 0.5D);
                  this.allStoneCheck(player);
                  item.setAmount(item.getAmount() - 1);
               }
            } else {
               player.sendMessage(this.color(Config.prefix() + Config.alreadyEquipped()));
            }
         } else if (item.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', Config.soulStoneName()))) {
            e.setCancelled(true);
            if (((Boolean)soul.get(uuid)).equals(false)) {
               if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                  soul.put(uuid, true);
                  player.sendTitle(this.color(Config.soulStoneEquip()), this.color(Config.soulStoneEquipSubtitle()), 0, 40, 20);
                  player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0F, 1.0F);
                  this.particleCould(100, player.getLocation().add(0.0D, 1.0D, 0.0D), Color.fromRGB(227, 151, 0), 0.5D, 1.0D, 0.5D);
                  this.allStoneCheck(player);
                  item.setAmount(item.getAmount() - 1);
               }
            } else {
               player.sendMessage(this.color(Config.prefix() + Config.alreadyEquipped()));
            }
         }
      }

   }

   @EventHandler
   public void onClick(InventoryClickEvent e) {
      Player player = (Player)e.getWhoClicked();
      this.stoneClick(player, e, Config.mindStoneItem(), Config.mindStoneName(), "Mind", (HashMap)mind);
      this.stoneClick(player, e, Config.soulStoneItem(), Config.soulStoneName(), "Soul", (HashMap)soul);
      this.stoneClick(player, e, Config.spaceStoneItem(), Config.spaceStoneName(), "Space", (HashMap)space);
      this.stoneClick(player, e, Config.powerStoneItem(), Config.powerStoneName(), "Power", (HashMap)power);
      this.stoneClick(player, e, Config.timeStoneItem(), Config.timeStoneName(), "Time", (HashMap)time);
      this.stoneClick(player, e, Config.realityStoneItem(), Config.realityStoneName(), "Reality", (HashMap)reality);
   }

   @EventHandler
   public void onDeath(PlayerDeathEvent e) {
      Player player = e.getEntity();
      if (Config.deleteStonesOnDeath()) {
         power.put(player.getUniqueId().toString(), false);
         time.put(player.getUniqueId().toString(), false);
         space.put(player.getUniqueId().toString(), false);
         mind.put(player.getUniqueId().toString(), false);
         soul.put(player.getUniqueId().toString(), false);
         reality.put(player.getUniqueId().toString(), false);
      } else {
         if (Config.dropStonesOnDeath()) {
            if ((Boolean)power.get(player.getUniqueId().toString())) {
               e.getDrops().add((new ItemBuilder(Config.powerStoneItem())).name(Config.powerStoneName()).addGlow().build());
            }

            if ((Boolean)mind.get(player.getUniqueId().toString())) {
               e.getDrops().add((new ItemBuilder(Config.mindStoneItem())).name(Config.mindStoneName()).addGlow().build());
            }

            if ((Boolean)time.get(player.getUniqueId().toString())) {
               e.getDrops().add((new ItemBuilder(Config.timeStoneItem())).name(Config.timeStoneName()).addGlow().build());
            }

            if ((Boolean)space.get(player.getUniqueId().toString())) {
               e.getDrops().add((new ItemBuilder(Config.spaceStoneItem())).name(Config.spaceStoneName()).addGlow().build());
            }

            if ((Boolean)reality.get(player.getUniqueId().toString())) {
               e.getDrops().add((new ItemBuilder(Config.realityStoneItem())).name(Config.realityStoneName()).addGlow().build());
            }

            if ((Boolean)soul.get(player.getUniqueId().toString())) {
               e.getDrops().add((new ItemBuilder(Config.soulStoneItem())).name(Config.soulStoneName()).addGlow().build());
            }

            power.put(player.getUniqueId().toString(), false);
            time.put(player.getUniqueId().toString(), false);
            space.put(player.getUniqueId().toString(), false);
            mind.put(player.getUniqueId().toString(), false);
            soul.put(player.getUniqueId().toString(), false);
            reality.put(player.getUniqueId().toString(), false);
         }

      }
   }

   public void stoneClick(Player player, Event e, ItemStack stoneMaterial, String stoneName, String stonetype, HashMap<String, Boolean> map) {
      if (((InventoryEvent)e).getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', Config.guiName().replace("%player-plural%", player.getName() + "'s").replace("%player%", player.getName()))))) {
         ((InventoryInteractEvent)e).setCancelled(true);
         if (((InventoryClickEvent)e).getCurrentItem() != null && ((InventoryClickEvent)e).getCurrentItem().hasItemMeta() && ((InventoryClickEvent)e).getCurrentItem().getType() != Material.BLACK_STAINED_GLASS_PANE && ((InventoryClickEvent)e).getCurrentItem().getItemMeta().getDisplayName().equals(stoneMaterial.getItemMeta().getDisplayName()) && ((InventoryClickEvent)e).getCurrentItem().getType().equals(stoneMaterial.getType())) {
            if (((InventoryClickEvent)e).getClickedInventory() != player.getInventory()) {
               if (player.getInventory().firstEmpty() == -1) {
                  player.getWorld().dropItem(player.getLocation(), (new ItemBuilder(stoneMaterial)).name(stoneName).addGlow().build());
               } else {
                  player.getInventory().addItem(new ItemStack[]{(new ItemBuilder(stoneMaterial)).name(stoneName).addGlow().build()});
               }

               map.put(player.getUniqueId().toString(), false);
               new StonesGUI(player);
               if (stonetype.equals("Space")) {
                  player.setAllowFlight(false);
                  player.setFlying(false);
               }

               player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(this.color("&c&l-&r " + stoneName)));
               player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
            } else if (((InventoryClickEvent)e).getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', stoneName))) {
               if ((Boolean)map.get(player.getUniqueId().toString())) {
                  player.sendMessage(this.color(Config.alreadyEquipped()));
               } else {
                  map.put(player.getUniqueId().toString(), true);
                  new StonesGUI(player);
                  player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(this.color("&a&l+&r " + stoneName)));
                  player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0F, 1.0F);
                  ((InventoryClickEvent)e).getCurrentItem().setAmount(((InventoryClickEvent)e).getCurrentItem().getAmount() - 1);
               }
            }
         }
      }

   }

   public void allStoneCheck(Player player) {
      if (hasAllStones(player)) {
         player.sendTitle(this.color(Config.theEndIsNear()), this.color(Config.theEndIsNearSubtitle()), 0, 80, 0);
      }

   }

   public String color(String s) {
      return ChatColor.translateAlternateColorCodes('&', s);
   }

   public void particleCould(int amount, Location loc, Color color, double width, double length, double height) {
      DustOptions dustOptions = new DustOptions(color, 1.0F);
      loc.getWorld().spawnParticle(Particle.REDSTONE, loc, amount, width, height, length, dustOptions);
   }
}
