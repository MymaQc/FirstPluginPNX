package myma.managers.childs;

import cn.nukkit.Player;
import cn.nukkit.utils.Config;
import myma.Main;
import myma.datas.Data;
import myma.datas.DefaultData;
import myma.managers.IManager;
import myma.utils.Utils;
import myma.utils.enums.Rank;

import java.io.File;

public class RankManager implements IManager, Data, DefaultData {

    private Config config;

    @Override
    public void onEnable() {
        this.load();
    }

    @Override
    public void load() {
        File pluginDataFolder = Main.getInstance().getDataFolder();
        config = new Config(new File(pluginDataFolder, "Rank.json"), Config.JSON);
    }

    @Override
    public boolean exist(Player player) {
        String playerName = Utils.getPlayerName(player, true);
        return this.exist(playerName);
    }

    @Override
    public boolean exist(String player) {
        return this.getProvider().exists(player);
    }

    @Override
    public Object get(Player player) {
        String playerName = Utils.getPlayerName(player, true);
        return this.get(playerName);
    }

    @Override
    public Object get(String player) {
        return this.getProvider().getString(player, this.getDefaultData().toString());
    }

    @Override
    public void set(Player player, Object value) {
        String playerName = Utils.getPlayerName(player, true);
        this.set(playerName, value);
    }

    @Override
    public void set(String playerName, Object value) {
        Config provider = this.getProvider();
        if (this.exist(playerName)) {
            provider.set(playerName, value);
            provider.save();
        }
    }

    @Override
    public Config getProvider() {
        return config;
    }

    @Override
    public void setDefaultData(Player player) {
        String playerName = Utils.getPlayerName(player, true);
        this.setDefaultData(playerName);
    }

    @Override
    public void setDefaultData(String playerName) {
        Config provider = this.getProvider();
        if (!this.exist(playerName)) {
            provider.set(playerName, this.getDefaultData().toString());
            provider.save();
        }
    }

    @Override
    public Object getDefaultData() {
        return Rank.JOUEUR;
    }


    @Override
    public void onDisable() {}

}
