package myma.datas;

import cn.nukkit.utils.Config;

import java.util.Map;

public interface DataCache {

    void loadCache();

    Map<String, Integer> getCache();

    void unloadCache();

    Config getProvider();

}
