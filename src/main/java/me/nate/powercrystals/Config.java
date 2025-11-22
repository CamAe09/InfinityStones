package me.nate.powercrystals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

public class Config {
   private static InfinityStones main;

   public Config(InfinityStones main) {
      Config.main = main;
      main.getConfig().options().copyDefaults();
      main.saveDefaultConfig();
   }

   public static void reloadConfig() {
      main.reloadConfig();
   }

   public static boolean dropStonesOnDeath() {
      return main.getConfig().getBoolean("drop-stones-on-death");
   }

   public static boolean deleteStonesOnDeath() {
      return main.getConfig().getBoolean("delete-stones-on-death");
   }

   public static boolean showParticles() {
      return main.getConfig().getBoolean("show-particles");
   }

   public static ArrayList<World> disabledWorlds() {
      ArrayList<World> world = new ArrayList();
      List<String> strings = main.getConfig().getList("disabled-worlds");
      Iterator var3 = strings.iterator();

      while(var3.hasNext()) {
         String s = (String)var3.next();
         if (Bukkit.getWorld(s) != null) {
            world.add(Bukkit.getWorld(s));
         }
      }

      return world;
   }

   public static ArrayList<Material> itemMaterial() {
      List<Material> mats = new ArrayList();
      Iterator var2 = main.getConfig().getStringList("items.material").iterator();

      while(var2.hasNext()) {
         String s = (String)var2.next();
         mats.add(Material.valueOf(s.toUpperCase()));
      }

      return (ArrayList)mats;
   }

   public static List<String> itemName() {
      return main.getConfig().getStringList("items.name");
   }

   public static List<String> itemLocalizedName() {
      return main.getConfig().getStringList("items.localized-name");
   }

   public static double powerStoneBeamDamage() {
      return main.getConfig().getDouble("power-stone-beam-damage");
   }

   public static int powerStoneBeamFireTicks() {
      return main.getConfig().getInt("power-stone-beam-fireticks");
   }

   public static double powerStoneDamageMultiplier() {
      return main.getConfig().getDouble("power-stone-damage-multiplier");
   }

   public static boolean powerBeam() {
      return main.getConfig().getBoolean("power-beam");
   }

   public static int powerBeamCooldown() {
      return main.getConfig().getInt("power-beam-cooldown");
   }

   public static boolean powerBeamDamagesPlayers() {
      return main.getConfig().getBoolean("power-beam-damages-players");
   }

   public static double realityStoneDamageReduction() {
      return main.getConfig().getDouble("reality-stone-damage-reduction");
   }

   public static boolean speedAbility() {
      return main.getConfig().getBoolean("speed-ability");
   }

   public static int speedLevel() {
      return main.getConfig().getInt("speed-level");
   }

   public static int spaceStoneTeleportCooldown() {
      return main.getConfig().getInt("space-stone-teleport-cooldown");
   }

   public static boolean doubleJump() {
      return main.getConfig().getBoolean("double-jump");
   }

   public static double doubleJumpVelocityMultiplier() {
      return main.getConfig().getDouble("double-jump-velocity-multiplier");
   }

   public static double doubleJumpYBoost() {
      return main.getConfig().getDouble("double-jump-y-boost");
   }

   public static boolean teleportAbility() {
      return main.getConfig().getBoolean("teleport-ability");
   }

   public static int mindStoneStunChance() {
      return main.getConfig().getInt("mind-stone-stun-chance");
   }

   public static boolean nightvVisionAbility() {
      return main.getConfig().getBoolean("night-vision-ability");
   }

   public static int soulStoneChokeChance() {
      return main.getConfig().getInt("soul-stone-choke-chance");
   }

   public static boolean absorbtionAbility() {
      return main.getConfig().getBoolean("absorbtion-ability");
   }

   public static boolean luckAbility() {
      return main.getConfig().getBoolean("luck-ability");
   }

   public static boolean slowFallAbility() {
      return main.getConfig().getBoolean("slow-fall-ability");
   }

   public static int luckLevel() {
      return main.getConfig().getInt("luck-level");
   }

   public static double allStonesPowerBeamDamage() {
      return main.getConfig().getDouble("all-stone-power-beam-damage");
   }

   public static int allStonesPowerBeamFireticks() {
      return main.getConfig().getInt("all-stone-power-beam-fireticks");
   }

   public static boolean allPowerBeam() {
      return main.getConfig().getBoolean("all-power-beam");
   }

   public static String prefix() {
      return main.getConfig().getString("prefix");
   }

   public static String alreadyEquipped() {
      return main.getConfig().getString("already-equipped");
   }

   public static String spaceStoneTeleport() {
      return main.getConfig().getString("space-stone-teleport");
   }

   public static String invalidStoneType() {
      return main.getConfig().getString("invalid-stone-type");
   }

   public static String noPermission() {
      return main.getConfig().getString("no-permission");
   }

   public static String teleportCooldownMessage() {
      return main.getConfig().getString("teleport-cooldown-message");
   }

   public static String theEndIsNear() {
      return main.getConfig().getString("the-end-is-near");
   }

   public static String theEndIsNearSubtitle() {
      return main.getConfig().getString("the-end-is-near-subtitle");
   }

   public static String powerStoneEquip() {
      return main.getConfig().getString("power-stone-equip");
   }

   public static String powerStoneEquipSubtitle() {
      return main.getConfig().getString("power-stone-equip-subtitle");
   }

   public static String realityStoneEquip() {
      return main.getConfig().getString("reality-stone-equip");
   }

   public static String realityStoneEquipSubtitle() {
      return main.getConfig().getString("reality-stone-equip-subtitle");
   }

   public static String mindStoneEquip() {
      return main.getConfig().getString("mind-stone-equip");
   }

   public static String mindStoneEquipSubtitle() {
      return main.getConfig().getString("mind-stone-equip-subtitle");
   }

   public static String spaceStoneEquip() {
      return main.getConfig().getString("space-stone-equip");
   }

   public static String spaceStoneEquipSubtitle() {
      return main.getConfig().getString("space-stone-equip-subtitle");
   }

   public static String timeStoneEquip() {
      return main.getConfig().getString("time-stone-equip");
   }

   public static String timeStoneEquipSubtitle() {
      return main.getConfig().getString("time-stone-equip-subtitle");
   }

   public static String soulStoneEquip() {
      return main.getConfig().getString("soul-stone-equip");
   }

   public static String soulStoneEquipSubtitle() {
      return main.getConfig().getString("soul-stone-equip-subtitle");
   }

   public static String powerStoneName() {
      return main.getConfig().getString("power-stone-name");
   }

   public static String timeStoneName() {
      return main.getConfig().getString("time-stone-name");
   }

   public static String spaceStoneName() {
      return main.getConfig().getString("space-stone-name");
   }

   public static String mindStoneName() {
      return main.getConfig().getString("mind-stone-name");
   }

   public static String realityStoneName() {
      return main.getConfig().getString("reality-stone-name");
   }

   public static String soulStoneName() {
      return main.getConfig().getString("soul-stone-name");
   }

   public static ItemStack powerStoneItem() {
      return main.getConfig().getString("power-stone-item").length() < 150 ? (new ItemBuilder(Material.valueOf(main.getConfig().getString("power-stone-item").toUpperCase()))).name(powerStoneName()).build() : (new ItemBuilder(Material.PLAYER_HEAD)).makeSkull(main.getConfig().getString("power-stone-item")).name(powerStoneName()).build();
   }

   public static ItemStack timeStoneItem() {
      return main.getConfig().getString("time-stone-item").length() < 150 ? (new ItemBuilder(Material.valueOf(main.getConfig().getString("time-stone-item").toUpperCase()))).name(timeStoneName()).build() : (new ItemBuilder(Material.PLAYER_HEAD)).makeSkull(main.getConfig().getString("time-stone-item")).name(timeStoneName()).build();
   }

   public static ItemStack spaceStoneItem() {
      return main.getConfig().getString("space-stone-item").length() < 150 ? (new ItemBuilder(Material.valueOf(main.getConfig().getString("space-stone-item").toUpperCase()))).name(spaceStoneName()).build() : (new ItemBuilder(Material.PLAYER_HEAD)).makeSkull(main.getConfig().getString("space-stone-item")).name(spaceStoneName()).build();
   }

   public static ItemStack mindStoneItem() {
      return main.getConfig().getString("mind-stone-item").length() < 150 ? (new ItemBuilder(Material.valueOf(main.getConfig().getString("mind-stone-item").toUpperCase()))).name(mindStoneName()).build() : (new ItemBuilder(Material.PLAYER_HEAD)).makeSkull(main.getConfig().getString("mind-stone-item")).name(mindStoneName()).build();
   }

   public static ItemStack realityStoneItem() {
      return main.getConfig().getString("reality-stone-item").length() < 150 ? (new ItemBuilder(Material.valueOf(main.getConfig().getString("reality-stone-item").toUpperCase()))).name(realityStoneName()).build() : (new ItemBuilder(Material.PLAYER_HEAD)).makeSkull(main.getConfig().getString("reality-stone-item")).name(realityStoneName()).build();
   }

   public static ItemStack soulStoneItem() {
      return main.getConfig().getString("soul-stone-item").length() < 150 ? (new ItemBuilder(Material.valueOf(main.getConfig().getString("soul-stone-item").toUpperCase()))).name(soulStoneName()).build() : (new ItemBuilder(Material.PLAYER_HEAD)).makeSkull(main.getConfig().getString("soul-stone-item")).name(soulStoneName()).build();
   }

   public static String guiName() {
      return main.getConfig().getString("gauntlet-gui-name");
   }

   public static int spaceStoneSlot() {
      return main.getConfig().getInt("space-stone-slot");
   }

   public static int realityStoneSlot() {
      return main.getConfig().getInt("reality-stone-slot");
   }

   public static int powerStoneSlot() {
      return main.getConfig().getInt("power-stone-slot");
   }

   public static int mindStoneSlot() {
      return main.getConfig().getInt("mind-stone-slot");
   }

   public static int timeStoneSlot() {
      return main.getConfig().getInt("time-stone-slot");
   }

   public static int soulStoneSlot() {
      return main.getConfig().getInt("soul-stone-slot");
   }

   public static Material fillMaterial() {
      return Material.valueOf(main.getConfig().getString("fill-material"));
   }

   public static List<String> guiPowerStoneLore() {
      return main.getConfig().getList("power-stone-lore");
   }

   public static List<String> guiSoulStoneLore() {
      return main.getConfig().getList("soul-stone-lore");
   }

   public static List<String> guiMindStoneLore() {
      return main.getConfig().getList("mind-stone-lore");
   }

   public static List<String> guiTimeStoneLore() {
      return main.getConfig().getList("time-stone-lore");
   }

   public static List<String> guiRealityStoneLore() {
      return main.getConfig().getList("reality-stone-lore");
   }

   public static List<String> guiSpaceStoneLore() {
      return main.getConfig().getList("space-stone-lore");
   }
}
