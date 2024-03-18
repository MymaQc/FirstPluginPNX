package myma.datas;

import cn.nukkit.Player;

public interface DefaultData {

    void setDefaultData(Player player);

    void setDefaultData(String playerName);

    Object getDefaultData();

}
