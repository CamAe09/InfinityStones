package me.nate.powercrystals;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder {
   private ItemStack is;

   public ItemBuilder(Material mat) {
      this.is = new ItemStack(mat);
   }

   public ItemBuilder(ItemStack is) {
      this.is = is;
   }

   public ItemBuilder amount(int amount) {
      this.is.setAmount(amount);
      return this;
   }

   public ItemBuilder name(String name) {
      ItemMeta meta = this.is.getItemMeta();
      meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
      this.is.setItemMeta(meta);
      return this;
   }

   public ItemBuilder lore(String name) {
      ItemMeta meta = this.is.getItemMeta();
      List<String> lore = meta.getLore();
      if (lore == null) {
         lore = new ArrayList();
      }

      ((List)lore).add(ChatColor.translateAlternateColorCodes('&', name));
      meta.setLore((List)lore);
      this.is.setItemMeta(meta);
      return this;
   }

   public ItemBuilder lore(String... lore) {
      List<String> toSet = new ArrayList();
      ItemMeta meta = this.is.getItemMeta();
      String[] var7 = lore;
      int var6 = lore.length;

      for(int var5 = 0; var5 < var6; ++var5) {
         String string = var7[var5];
         toSet.add(ChatColor.translateAlternateColorCodes('&', string));
      }

      meta.setLore(toSet);
      this.is.setItemMeta(meta);
      return this;
   }

   public ItemBuilder lore(List<String> lore) {
      List<String> toSet = new ArrayList();
      ItemMeta meta = this.is.getItemMeta();
      Iterator var5 = lore.iterator();

      while(var5.hasNext()) {
         String string = (String)var5.next();
         toSet.add(ChatColor.translateAlternateColorCodes('&', string));
      }

      meta.setLore(toSet);
      this.is.setItemMeta(meta);
      return this;
   }

   public ItemBuilder durability(int durability) {
      this.is.setDurability((short)durability);
      return this;
   }

   public ItemBuilder enchantment(Enchantment enchantment, int level) {
      this.is.addUnsafeEnchantment(enchantment, level);
      return this;
   }

   public ItemBuilder enchantment(Enchantment enchantment) {
      this.is.addUnsafeEnchantment(enchantment, 1);
      return this;
   }

   public ItemBuilder type(Material material) {
      this.is.setType(material);
      return this;
   }

   public ItemBuilder clearLore() {
      ItemMeta meta = this.is.getItemMeta();
      meta.setLore(new ArrayList());
      this.is.setItemMeta(meta);
      return this;
   }

   public ItemBuilder clearEnchantments() {
      Iterator var2 = this.is.getEnchantments().keySet().iterator();

      while(var2.hasNext()) {
         Enchantment e = (Enchantment)var2.next();
         this.is.removeEnchantment(e);
      }

      return this;
   }

   public ItemBuilder localisedName(String ln) {
      ItemMeta meta = this.is.getItemMeta();
      meta.setLocalizedName(ln);
      this.is.setItemMeta(meta);
      return this;
   }

   public ItemStack build() {
      return this.is;
   }

   public ItemBuilder color(DyeColor color) {
      this.durability(color.getWoolData());
      return this;
   }

   public ItemBuilder dyeColor(Color color) {
      LeatherArmorMeta meta = (LeatherArmorMeta)this.is.getItemMeta();
      meta.setColor(color);
      this.is.setItemMeta(meta);
      return this;
   }

   public ItemBuilder addGlow() {
      this.enchantment(Enchantment.WATER_WORKER);
      ItemMeta meta = this.is.getItemMeta();
      meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
      this.is.setItemMeta(meta);
      return this;
   }

   public ItemBuilder setUnbreakable() {
      ItemMeta meta = this.is.getItemMeta();
      meta.setUnbreakable(true);
      this.is.setItemMeta(meta);
      return this;
   }

   public ItemBuilder addItemFlag(ItemFlag flag) {
      ItemMeta meta = this.is.getItemMeta();
      meta.addItemFlags(new ItemFlag[]{flag});
      this.is.setItemMeta(meta);
      return this;
   }

   public ItemBuilder addAttributeModifier(Attribute attribute, String name, Double amount, Operation operation, EquipmentSlot slot) {
      ItemMeta meta = this.is.getItemMeta();
      meta.addAttributeModifier(attribute, new AttributeModifier(UUID.randomUUID(), name, amount, operation, slot));
      this.is.setItemMeta(meta);
      return this;
   }

   public ItemBuilder makeSkull(String url) {
      ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1, (short)3);
      if (url.isEmpty()) {
         return this;
      } else {
         SkullMeta headMeta = (SkullMeta)this.is.getItemMeta();
         GameProfile profile = new GameProfile(UUID.randomUUID(), (String)null);
         profile.getProperties().put("textures", new Property("textures", url));

         try {
            Field profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
         } catch (IllegalArgumentException var6) {
            var6.printStackTrace();
         } catch (NoSuchFieldException var7) {
            var7.printStackTrace();
         } catch (SecurityException var8) {
            var8.printStackTrace();
         } catch (IllegalAccessException var9) {
            var9.printStackTrace();
         }

         item.setItemMeta(headMeta);
         this.is = item;
         return this;
      }
   }
}
