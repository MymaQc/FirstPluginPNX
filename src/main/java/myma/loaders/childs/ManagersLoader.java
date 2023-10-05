package myma.loaders.childs;

import cn.nukkit.utils.TextFormat;
import myma.Main;
import myma.loaders.Loader;
import myma.managers.Manager;
import myma.managers.childs.EconomyManager;
import myma.managers.childs.ProvidersManager;

public class ManagersLoader implements Loader {

    @Override
    public void onLoad() {
        Main main = Main.getInstance();
        Manager[] managers = {
                new ProvidersManager(),
                new EconomyManager(),
        };
        for (Manager manager : managers) {
            manager.onEnable();
        }
        main.getLogger().info(TextFormat.GOLD + "[Manager] " + managers.length + " manager(s) chargé(s) !");
    }

    @Override
    public void onUnload() {
        Main main = Main.getInstance();
        Manager[] managers = {
                new EconomyManager(),
        };
        for (Manager manager : managers) {
            manager.onDisable();
        }
        main.getLogger().info(TextFormat.GOLD + "[Manager] " + managers.length + " manager(s) déchargé(s) !");
    }

}
