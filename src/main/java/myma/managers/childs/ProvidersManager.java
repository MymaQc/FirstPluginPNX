package myma.managers.childs;

import cn.nukkit.utils.Config;
import myma.Main;
import myma.managers.Manager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ProvidersManager implements Manager {

    private final Map<String, Config> providers = new HashMap<>();

    @Override
    public void onEnable() {
        this.loadProviders();
    }

    private void loadProviders() {
        File pluginDataFolder = Main.getInstance().getDataFolder();
        this.addProvider("Economy", new Config(new File(pluginDataFolder, "Economy.json"), Config.JSON));
    }

    @SuppressWarnings("SameParameterValue")
    private void addProvider(String name, Config config) {
        if (!this.isAlreadyLoaded(name)) {
            this.providers.put(name, config);
        }
    }

    private boolean isAlreadyLoaded(String name) {
        return this.providers.containsKey(name);
    }

    public Config getProvider(String name) {
        return this.providers.get(name);
    }

    @Override
    public void onDisable() {}

}
