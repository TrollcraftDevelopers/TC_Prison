package pl.trollcraft.prison.service.testComponent;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pl.trollcraft.prison.service.userInfo.UserInfo;
import pl.trollcraft.prison.service.userInfo.event.ComponentLoadEvent;

import java.util.Optional;

public class ComponentListener implements Listener {

    @EventHandler
    public void onComponentLoad (ComponentLoadEvent event) {
        Bukkit.getConsoleSender().sendMessage("Loading");
        UserInfo userInfo = event.getUserInfo();

        if (userInfo.hasComponent(TestUserComponent.class)) {

            Optional<TestUserComponent> oTestUserComponent = userInfo.getComponent(TestUserComponent.class);
            oTestUserComponent.ifPresent( testUserComponent -> {

                Bukkit.getConsoleSender().sendMessage(testUserComponent.getTestMessage());

            } );

        }
        else {
            Bukkit.getConsoleSender().sendMessage("Nope");
            event.getUserInfo().getComponents().add(new TestUserComponent());
        }

    }

}
