package myma.managers.childs;

import cn.nukkit.Player;
import cn.nukkit.utils.Config;
import myma.datas.DataCache;
import myma.datas.DefaultDataCache;
import myma.managers.Manager;
import myma.utils.Utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class EconomyManager implements Manager, DataCache, DefaultDataCache {

    private final Map<String, Integer> cache = new HashMap<>();

    @Override
    public void onEnable() {
        this.loadCache();
    }

    @Override
    public void loadCache() {
        Map<String, Object> providerData = this.getProvider().getAll();
        for (Map.Entry<String, Object> entry : providerData.entrySet()) {
            String playerName = Utils.getPlayerName(entry.getKey(), true);
            Integer value = (Integer) entry.getValue();
            this.cache.put(playerName, value);
        }
    }

    @Override
    public Map<String, Integer> getCache() {
        return this.cache;
    }


    @Override
    public void setDefaultData(Player player) {
        String playerName = Utils.getPlayerName(player, true);
        if (!this.exist(playerName)) {
            this.cache.put(playerName, (Integer) this.getDefaultData());
        }
    }

    public boolean exist(String playerName) {
        return this.cache.containsKey(playerName);
    }

    public int get(String playerName) {
        return this.cache.getOrDefault(playerName, (Integer) this.getDefaultData());
    }

    public void add(String playerName, int amount) {
        if (this.exist(playerName)) {
            this.cache.put(playerName, this.get(playerName) + amount);
        }
    }

    @SuppressWarnings("unused")
    public void reduce(String playerName, int amount) {
        if (this.exist(playerName)) {
            this.cache.put(playerName, Math.max(0, this.get(playerName) - amount));
        }
    }

    @SuppressWarnings("unused")
    public void set(String playerName, int amount) {
        if (this.exist(playerName)) {
            this.cache.put(playerName, amount);
        }
    }

    @Override
    public void unloadCache() {
        Config provider = this.getProvider();
        LinkedHashMap<String, Object> cacheData = new LinkedHashMap<>(this.getCache());
        provider.setAll(cacheData);
        provider.save();
    }

    @Override
    public Config getProvider() {
        return Objects.requireNonNull(Manager.getInstance(ProvidersManager.class)).getProvider("Economy");
    }

    @Override
    public Object getDefaultData() {
        return 0;
    }

    @Override
    public void onDisable() {
        this.unloadCache();
    }

}
