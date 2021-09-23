package pl.trollcraft.prison.service.userInfo.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import pl.trollcraft.prison.service.userInfo.UserInfo;

public class ComponentLoadEvent extends Event {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private final Player player;
    private final UserInfo userInfo;

    public ComponentLoadEvent(Player player,
                              UserInfo userInfo) {
        this.player = player;
        this.userInfo = userInfo;
    }

    public Player getPlayer() {
        return player;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

}
