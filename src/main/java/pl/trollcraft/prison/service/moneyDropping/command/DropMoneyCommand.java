package pl.trollcraft.prison.service.moneyDropping.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.trollcraft.prison.service.moneyDropping.MoneyDroppingService;
import pl.trollcraft.prison.utility.CommandUtility;
import pl.trollcraft.prison.utility.NumericUtility;

public class DropMoneyCommand implements CommandExecutor {

    private final MoneyDroppingService moneyDroppingService;

    public DropMoneyCommand(MoneyDroppingService moneyDroppingService) {
        this.moneyDroppingService = moneyDroppingService;
    }

    @Override
    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args) {

        if (!CommandUtility.isPlayer(sender)) {
            sender.sendMessage("Komenda jedynie dla graczy online.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(String.format("Uzycie: /%s <suma>", label));
            return true;
        }

        double valueToWithdraw = NumericUtility.stringToDouble(args[0], -1D);
        valueToWithdraw = NumericUtility.round(valueToWithdraw, 2);

        if (valueToWithdraw <= 0D) {
            player.sendMessage("Mozesz wyplacic sume wieksza od 0.");
            return true;
        }

        MoneyDroppingService.WithdrawResponse response = moneyDroppingService.withdrawNote(player, valueToWithdraw);
        switch (response) {
            case NO_MONEY:
                player.sendMessage("Brak srodkow");
                break;

            case INV_FULL:
                player.sendMessage("Brak miejsca w ekwipunku");
                break;

            case OK:
                player.sendMessage(String.format("Wyplacono TC %.2f do banknotu.", valueToWithdraw));
        }

        return true;
    }
}
