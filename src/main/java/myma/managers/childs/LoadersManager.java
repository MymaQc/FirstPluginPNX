package myma.managers.childs;

import cn.nukkit.utils.TextFormat;
import myma.Main;
import myma.loaders.Loader;
import myma.loaders.childs.CommandsLoader;
import myma.loaders.childs.ListenersLoader;
import myma.loaders.childs.ManagersLoader;
import myma.managers.IManager;

public class LoadersManager implements IManager {

    @Override
    public void onEnable() {
        Main main = Main.getInstance();
        Loader[] loaders = {
                new ManagersLoader(),
                new CommandsLoader(),
                new ListenersLoader()
        };
        for (Loader loader : loaders) {
            loader.onLoad();
        }
        main.getLogger().info(TextFormat.GOLD + "[Loader] " + loaders.length + " loaders(s) chargé(s) !");
    }

    @Override
    public void onDisable() {}

}
