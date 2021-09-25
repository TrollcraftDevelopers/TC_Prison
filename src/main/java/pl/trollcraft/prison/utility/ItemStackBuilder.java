package pl.trollcraft.prison.utility;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.trollcraft.prison.constants.Constants;

import java.util.Arrays;
import java.util.List;

public final class ItemStackBuilder {

    private final ItemStack itemStack;
    private ItemMeta itemMeta;

    public ItemStackBuilder() {
        this.itemStack = new ItemStack(Material.AIR);
    }

    public ItemStackBuilder type(Material type) {
        this.itemStack.setType(type);
        this.itemMeta = itemStack.getItemMeta();
        return this;
    }

    public ItemStackBuilder amount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemStackBuilder name(String name){
        this.itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes(Constants.COLOR_CHARACTER, name));
        return this;
    }

    public ItemStackBuilder lore(String lore, String separator){
        String loreColored = ChatColor.translateAlternateColorCodes(Constants.COLOR_CHARACTER, lore);
        String[] loreArr = loreColored.split(separator);
        List<String> list = Arrays.asList(loreArr);
        this.itemMeta.setLore(list);
        return this;
    }

    public ItemStackBuilder enchant(Enchantment enchantment, int level, boolean safe) {
        this.itemMeta.addEnchant(enchantment, level, safe);
        return this;
    }

    public ItemStack getItemStack() {
        this.itemStack.setItemMeta(this.itemMeta);
        return this.itemStack;
    }
}
