package myma.loaders.childs;

import cn.nukkit.event.Listener;
import cn.nukkit.utils.TextFormat;
import myma.Main;
import myma.listeners.EntityListeners;
import myma.listeners.PlayerListeners;
import myma.loaders.Loader;

public class ListenersLoader implements Loader {

    @Override
    public void onLoad() {
        Main main = Main.getInstance();
        Listener[] listeners = {
                new EntityListeners(),
                new PlayerListeners()
        };
        for (Listener listener : listeners) {
            main.getServer().getPluginManager().registerEvents(listener, main);
        }
        main.getLogger().info(TextFormat.GOLD + "[Listener] " + listeners.length + " listener(s) enregistré(s) !");
    }

    @Override
    public void onUnload() {}

}
