package com.elikill58.negativity.spigot.impl.item;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import com.elikill58.negativity.api.ChatColor;
import com.elikill58.negativity.api.item.Enchantment;
import com.elikill58.negativity.api.item.ItemBuilder;
import com.elikill58.negativity.api.item.Material;
import com.elikill58.negativity.api.utils.Utils;
import com.elikill58.negativity.universal.Version;

public class SpigotItemBuilder extends ItemBuilder {

    private ItemStack itemStack;
    private ItemMeta itemMeta;

    public SpigotItemBuilder(Material type) {
    	this.itemStack = new ItemStack((org.bukkit.Material) type.getDefaultMaterial());
    	this.itemMeta = (itemStack.hasItemMeta() ? itemStack.getItemMeta() : Bukkit.getItemFactory().getItemMeta(itemStack.getType()));
    }

    @Override
    public ItemBuilder displayName(@Nullable String displayName) {
        this.itemMeta.setDisplayName(ChatColor.RESET + Utils.coloredMessage(displayName));
        return this;
    }

    @Override
    public ItemBuilder resetDisplayName() {
        return this.displayName(null);
    }

    @SuppressWarnings("deprecation")
    @Override
	public ItemBuilder enchant(Enchantment enchantment, int level) {
        this.itemMeta.addEnchant(org.bukkit.enchantments.Enchantment.getByName(enchantment.name()), level, true);
        return this;
    }

    @SuppressWarnings("deprecation")
    @Override
    public ItemBuilder unsafeEnchant(Enchantment enchantment, int level) {
        this.itemMeta.addEnchant(org.bukkit.enchantments.Enchantment.getByName(enchantment.name()), level, true);
        return this;
    }

    @Override
    public ItemBuilder type(Material type) {
        this.itemStack.setType((org.bukkit.Material) type.getDefaultMaterial());
        return this;
    }

    @Override
    public ItemBuilder amount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }
    
	@SuppressWarnings("deprecation")
    @Override
	public ItemBuilder durability(short durability) {
		itemStack.setDurability(durability);
		if(Version.getVersion().isNewerOrEquals(Version.V1_13)) {
			ItemMeta meta = itemStack.getItemMeta();
			((Damageable) meta).setDamage(durability);
			itemStack.setItemMeta(meta);
		}
        return this;
    }

    @Override
    public ItemBuilder lore(List<String> lore) {
    	return lore(lore.toArray(new String[] {}));
    }

    @Override
    public ItemBuilder lore(String... lore) {
        List<String> list = this.itemMeta.hasLore() ? this.itemMeta.getLore() : new ArrayList<>();
    	for(String s : lore)
    		for(String temp : s.split("\\n"))
        		for(String tt : temp.split("/n"))
        			list.add(Utils.coloredMessage(tt));
        this.itemMeta.setLore(list);
        return this;
    }

    @Override
    public ItemBuilder addToLore(String... loreToAdd) {
        return lore(loreToAdd);
    }

    @Override
    public com.elikill58.negativity.api.item.ItemStack build() {
        this.itemStack.setItemMeta(this.itemMeta);
        return new SpigotItemStack(itemStack);
    }
}