package myma.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.command.tree.ParamList;
import cn.nukkit.command.utils.CommandLogger;
import cn.nukkit.utils.TextFormat;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class PingCommand extends Command {

    public PingCommand() {
        super("ping", "Connaître la latence d'un joueur", "");
        this.getCommandParameters().clear();
        this.getCommandParameters().put("default", new CommandParameter[] {
                CommandParameter.newType("joueur", true, CommandParamType.TARGET)
        });
        this.enableParamTree();
    }

    @Override
    public int execute(CommandSender sender, String commandLabel, Map.Entry<String, ParamList> result, CommandLogger log) {
        if (sender instanceof Player player) {
            ParamList paramList = result.getValue();
            Function<Integer, String> formatPing = (Integer ping) -> {
                if (ping > 201) {
                    return "§c" + ping + " ms";
                } else if (ping >= 101 && ping < 200) {
                    return "§6" + ping + " ms";
                } else {
                    return "§a" + ping + " ms";
                }
            };
            if (result.getKey().equals("default")) {
                if (paramList.hasResult(0)) {
                    List<Player> players = paramList.getResult(0);
                    Player target = players.get(0);
                    if (target.isOnline()) {
                        int targetPing = target.getPing();
                        String formattedTargetPing = formatPing.apply(targetPing);
                        player.sendMessage("§fLe joueur §a" + target.getName() + " §fpossède " + formattedTargetPing + " §f!");
                    } else {
                        player.sendMessage("§cLe joueur que vous avez mentionné n'est pas connecté");
                    }
                } else {
                    int senderPing = player.getPing();
                    String formattedSenderPing = formatPing.apply(senderPing);
                    player.sendMessage("§fVous possédez " + formattedSenderPing + " §f!");
                }
            } else {
                sender.sendMessage(TextFormat.RED + "Une erreur est survenue lors de l'éxécution de la commande.");
            }
        } else {
            sender.sendMessage(TextFormat.RED + "Vous ne pouvez pas utiliser cette commande depuis la console.");
        }
        return 0;
    }

}
