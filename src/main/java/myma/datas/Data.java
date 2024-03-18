package myma.datas;

import cn.nukkit.Player;
import cn.nukkit.utils.Config;

public interface Data {

    void load();

    boolean exist(Player player);

    boolean exist(String playerName);

    Object get(Player player);

    Object get(String playerName);

    void set(Player player, Object value);

    void set(String playerName, Object value);

    Config getProvider();

}
