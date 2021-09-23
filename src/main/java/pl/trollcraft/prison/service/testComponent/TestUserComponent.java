package pl.trollcraft.prison.service.testComponent;

import org.bukkit.Bukkit;
import pl.trollcraft.prison.service.configuration.Configurator;
import pl.trollcraft.prison.service.userInfo.UserComponent;

public class TestUserComponent implements UserComponent {

    private static final String NAME = TestUserComponent.class.getSimpleName();

    private String testMessage;

    public TestUserComponent() {
        this.testMessage = "kacperekwafelekkk";
    }

    @Override
    public void save(Configurator configurator) {
        configurator.write(String.format("components.%s.testMessage", NAME), this.testMessage);
    }

    @Override
    public void load(Configurator configurator) {
        this.testMessage = configurator.read(String.format("components.%s.testMessage", NAME), String.class);
        Bukkit.getConsoleSender().sendMessage(this.testMessage);
    }

    public String getTestMessage() {
        return testMessage;
    }
}
