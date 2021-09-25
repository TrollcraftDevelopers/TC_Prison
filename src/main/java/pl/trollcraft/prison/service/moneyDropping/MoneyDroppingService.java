package pl.trollcraft.prison.service.moneyDropping;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.trollcraft.prison.utility.NumericUtility;

import java.util.List;
import java.util.Map;

public class MoneyDroppingService {

    public enum WithdrawResponse {
        NO_MONEY,
        INV_FULL,
        OK
    }

    public enum DepositResponse {
        INVALID_ITEM,
        INVALID_DEPOSIT_VALUE,
        OK
    }

    private final Economy economy;

    public MoneyDroppingService(Economy economy) {
        this.economy = economy;
    }

    public WithdrawResponse withdrawNote(Player player, double amount) {
        if (this.economy.has(player, amount)) {
            ItemStack noteItemStack = BankNoteFactory.buildNote(amount);
            Map<Integer,ItemStack> invState = player.getInventory().addItem(noteItemStack);

            if (!invState.isEmpty()) {
                return WithdrawResponse.INV_FULL;
            }

            this.economy.withdrawPlayer(player, amount);
            return WithdrawResponse.OK;
        }
        return WithdrawResponse.NO_MONEY;
    }

    public boolean isNote(ItemStack itemStack) {
        Material type = itemStack.getType();
        boolean hasItemMeta = itemStack.getItemMeta() != null;

        return (type == Material.PAPER && hasItemMeta)
                && itemStack.getItemMeta().hasEnchant(Enchantment.DURABILITY);
    }

    public DepositResponse depositNote(Player player, ItemStack noteItemStack) {
        ItemMeta itemMeta = noteItemStack.getItemMeta();
        if (itemMeta == null) {
            return DepositResponse.INVALID_ITEM;
        }

        List<String> lore = noteItemStack.getItemMeta().getLore();
        if (lore == null || lore.isEmpty()) {
            return DepositResponse.INVALID_ITEM;
        }

        String valueString = lore.get(lore.size()-1);
        double value = NumericUtility.stringToDouble(valueString, -1D);

        this.economy.depositPlayer(player, value);

        if (value > 0) {
            int heldSlot = player.getInventory().getHeldItemSlot();
            player.getInventory().setItem(heldSlot, null);
            return DepositResponse.OK;
        }

        return DepositResponse.INVALID_DEPOSIT_VALUE;
    }

}
