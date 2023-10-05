package myma.utils;

import cn.nukkit.Player;
import cn.nukkit.level.Position;
import cn.nukkit.network.protocol.PlaySoundPacket;

public class Utils {

    public static String getPlayerName(String player, boolean upperName) {
        return upperName ? player.replace(" ", "_") : player.replace("_", " ");
    }

    public static String getPlayerName(Player player, boolean upperName) {
        String name = player.getName();
        return upperName ? name.replace(" ", "_") : name.replace("_", " ");
    }

    public static void playSound(Player player, String soundName) {
        Position playerPosition = player.getPosition();
        PlaySoundPacket playSoundPacket = new PlaySoundPacket();
        playSoundPacket.name = soundName;
        playSoundPacket.x = (int) playerPosition.x;
        playSoundPacket.y = (int) playerPosition.y;
        playSoundPacket.z = (int) playerPosition.z;
        playSoundPacket.volume = 50;
        playSoundPacket.pitch = 1;
        player.dataPacket(playSoundPacket);
    }

}
