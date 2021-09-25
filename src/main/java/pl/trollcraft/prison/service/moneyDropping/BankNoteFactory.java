package pl.trollcraft.prison.service.moneyDropping;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import pl.trollcraft.prison.utility.ItemStackBuilder;

public final class BankNoteFactory {

    public static ItemStack buildNote(double amount) {
        return new ItemStackBuilder()
                .type(Material.PAPER)
                .amount(1)
                .name(String.format("TC %.2f", amount))
                .lore(String.format(";&eKliknij trzymajac ten banknot, by;&ewplacic jego wartosc na swoje konto.;;%f", amount), ";")
                .enchant(Enchantment.DURABILITY, 1, false)
                .getItemStack();
    }

}
