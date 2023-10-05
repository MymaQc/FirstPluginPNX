package myma.datas;

import cn.nukkit.Player;

public interface DefaultDataCache {

    void setDefaultData(Player player);

    Object getDefaultData();

}
