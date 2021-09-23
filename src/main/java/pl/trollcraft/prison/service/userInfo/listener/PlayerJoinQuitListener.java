package pl.trollcraft.prison.service.userInfo.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.trollcraft.prison.service.userInfo.UserInfo;
import pl.trollcraft.prison.service.userInfo.UserInfoManager;
import pl.trollcraft.prison.service.userInfo.event.ComponentLoadEvent;

public class PlayerJoinQuitListener implements Listener {

    private final UserInfoManager userInfoManager;

    public PlayerJoinQuitListener(UserInfoManager userInfoManager) {
        this.userInfoManager = userInfoManager;
    }

    @EventHandler
    public void onJoin (PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UserInfo userInfo = this.userInfoManager.loadUserInfo(player);

        ComponentLoadEvent componentLoadEvent = new ComponentLoadEvent(player, userInfo);
        Bukkit.getPluginManager().callEvent(componentLoadEvent);
    }

    @EventHandler
    public void onQuit (PlayerQuitEvent event) {
        Player player = event.getPlayer();
        this.userInfoManager.getUserInfo(player.getUniqueId())
                .ifPresent(userInfoManager::saveUser);
    }

}
