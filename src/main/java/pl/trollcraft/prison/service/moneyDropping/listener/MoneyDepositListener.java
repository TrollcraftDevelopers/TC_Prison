package pl.trollcraft.prison.service.moneyDropping.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import pl.trollcraft.prison.service.moneyDropping.MoneyDroppingService;

public class MoneyDepositListener implements Listener {

    private final MoneyDroppingService moneyDroppingService;

    public MoneyDepositListener(MoneyDroppingService moneyDroppingService) {
        this.moneyDroppingService = moneyDroppingService;
    }

    @EventHandler
    public void onInteract (PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getInventory().getItemInMainHand();

        if (this.moneyDroppingService.isNote(itemStack)) {

            event.setCancelled(true);
            MoneyDroppingService.DepositResponse response = this.moneyDroppingService.depositNote(player, itemStack);;

            switch (response) {
                case INVALID_DEPOSIT_VALUE:
                    player.sendMessage("Niewlasciwa wartosc banknotu.");
                case INVALID_ITEM:
                    player.sendMessage("Niewlasciwy przedmiot");
                case OK:
                    player.sendMessage("Wplacono!");
            }
        }


    }

}
