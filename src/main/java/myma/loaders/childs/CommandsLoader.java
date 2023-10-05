package myma.loaders.childs;

import cn.nukkit.command.Command;
import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.utils.TextFormat;
import myma.Main;
import myma.commands.PingCommand;
import myma.loaders.Loader;

import java.util.List;

public class CommandsLoader implements Loader {

    private final String[] commandsToUnregister = {
            "ban-ip", "banlist", "clear", "defaultgamemode",
            "difficulty", "dumpmemory", "gc", "kill", "me",
            "pardon", "pardon-ip", "particle", "save",
            "save-on", "save-off", "say", "seed", "spawnpoint", "suicide",
            "tell", "title", "transferserver", "version"
    };

    @Override
    public void onLoad() {
        Main main = Main.getInstance();
        Command[] commands = {
                new PingCommand()
        };
        SimpleCommandMap commandMap = main.getServer().getCommandMap();
        for (String commandToUnregister : commandsToUnregister) {
            Command defaultCommandToUnregister = commandMap.getCommand(commandToUnregister);
            if (defaultCommandToUnregister != null) {
                defaultCommandToUnregister.unregister(commandMap);
            }
        }
        main.getLogger().info(TextFormat.GOLD + "[Command] " + commandsToUnregister.length + " commande(s) par défaut retirée(s) !");
        for (Command command : commands) {
            for (Command defaultCommand : commandMap.getCommands().values()) {
                if (command.getName().equals(defaultCommand.getName())) {
                    defaultCommand.unregister(commandMap);
                }
            }
        }
        commandMap.registerAll("myma", List.of(commands));
        main.getLogger().info(TextFormat.GOLD + "[Command] " + commands.length + " nouvelle(s) commande(s) ajoutée(s) !");
    }

    @Override
    public void onUnload() {}

}
