package myma.loaders.childs;

import cn.nukkit.utils.TextFormat;
import myma.Main;
import myma.loaders.Loader;
import myma.managers.IManager;
import myma.managers.childs.RankManager;

public class ManagersLoader implements Loader {

    @Override
    public void onLoad() {
        Main main = Main.getInstance();
        IManager[] managers = {
                // new RankManager(),
        };
        for (IManager manager : managers) {
            manager.onEnable();
        }
        main.getLogger().info(TextFormat.GOLD + "[Manager] " + managers.length + " manager(s) chargé(s) !");
    }

    @Override
    public void onUnload() {
        Main main = Main.getInstance();
        IManager[] managers = {
                new RankManager(),
        };
        for (IManager manager : managers) {
            manager.onDisable();
        }
        main.getLogger().info(TextFormat.GOLD + "[Manager] " + managers.length + " manager(s) déchargé(s) !");
    }

}
