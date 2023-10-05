package myma.listeners;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerJumpEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.level.Sound;
import myma.managers.Manager;
import myma.managers.childs.EconomyManager;
import myma.utils.Utils;

import java.util.Objects;
import java.util.Random;

public class PlayerListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Objects.requireNonNull(Manager.getInstance(EconomyManager.class)).setDefaultData(player);
        event.setJoinMessage("§8[§a+§8] §a" + player.getName());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage("§8[§c+§8] §c" + player.getName());
    }

    @EventHandler
    public void onJump(PlayerJumpEvent event) {
        Player player = event.getPlayer();
        String playerName = Utils.getPlayerName(player, true);
        int moneyToAdd = new Random().nextInt(10) + 1;
        EconomyManager economyApi = Objects.requireNonNull(Manager.getInstance(EconomyManager.class));
        economyApi.add(playerName, moneyToAdd);
        player.sendTip("§l§q» §r§a+" + moneyToAdd + "$ §8(§7" + economyApi.get(playerName) + "$§8) §l§q«§r");
        player.getLevel().addSound(player.getPosition(), Sound.RANDOM_LEVELUP, 50, 1, player);
    }

}
