package myma;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;
import myma.managers.childs.LoadersManager;

public class Main extends PluginBase {

    private static Main instance;

    @Override
    public void onLoad() {
        Main.instance = this;
    }

    @Override
    public void onEnable() {
        (new LoadersManager()).onEnable();
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
