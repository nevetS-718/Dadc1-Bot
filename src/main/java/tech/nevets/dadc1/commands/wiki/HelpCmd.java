package tech.nevets.dadc1.commands.wiki;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import tech.nevets.dadc1.Bot;
import tech.nevets.dadc1.Config;
import tech.nevets.dadc1.commands.CommandContext;
import tech.nevets.dadc1.commands.CommandManager;
import tech.nevets.dadc1.commands.ICommand;

import java.util.List;

public class HelpCmd implements ICommand {

    private final CommandManager manager;

    public HelpCmd(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void handle(CommandContext ctx) {
        List<String> args = ctx.getArgs();
        TextChannel channel = ctx.getChannel();

        if (args.isEmpty()) {
            StringBuilder builder = new StringBuilder();

            EmbedBuilder eb = new EmbedBuilder();

            eb.setTitle("List of Commands");

            manager.getCommands().stream().map(ICommand::getName).forEach(
                    (it) -> builder.append('`').append(Bot.prefix).append(it).append("`\n")
            );

            eb.addField("", builder.toString(), false);
            channel.sendMessageEmbeds(eb.build()).queue();

            return;
        }

        String search = args.get(0);
        ICommand command = manager.getCommand(search);

        if (command == null) {
            channel.sendMessage("Nothing found for " + search);
            return;
        }

        channel.sendMessage(command.getHelp()).queue();
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getHelp() {
        return "Shows the list of bot commands\n" +
                "Usage: `" + Bot.prefix + "help [command]`";
    }

    @Override
    public List<String> getAliases() {
        return List.of("commands", "cmds", "commandlist");
    }
}
