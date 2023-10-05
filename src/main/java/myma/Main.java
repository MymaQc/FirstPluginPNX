package myma;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;
import myma.managers.Manager;
import myma.managers.childs.LoadersManager;

import java.util.Objects;

public class Main extends PluginBase {

    private static Main instance;

    @Override
    public void onLoad() {
        Main.instance = this;
    }

    @Override
    public void onEnable() {
        Objects.requireNonNull(Manager.getInstance(LoadersManager.class)).onEnable();
        this.getLogger().notice(TextFormat.GREEN + "Plugin activé");
    }

    @Override
    public void onDisable() {
        this.getLogger().notice(TextFormat.RED + "Plugin désactivé");
    }

    public static Main getInstance() {
        return instance;
    }

}
