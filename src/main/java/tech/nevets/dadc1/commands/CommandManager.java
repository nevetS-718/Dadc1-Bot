package tech.nevets.dadc1.commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import tech.nevets.dadc1.Config;
import tech.nevets.dadc1.commands.administrator.ReloadConfigCmd;
import tech.nevets.dadc1.commands.games.*;
import tech.nevets.dadc1.commands.games.animals.*;
import tech.nevets.dadc1.commands.strings.GoodMorningCmd;
import tech.nevets.dadc1.commands.strings.LCmd;
import tech.nevets.dadc1.commands.strings.LanguageCmd;
import tech.nevets.dadc1.commands.strings.WelcomeBackCmd;
import tech.nevets.dadc1.commands.wiki.HelpCmd;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CommandManager {
    protected final List<ICommand> commands = new ArrayList<>();

    public CommandManager() {
        addCommand(new HelpCmd(this));
        //Admin commands
        addCommand(new ReloadConfigCmd());
        //Game commands
        addCommand(new PingCmd());
        addCommand(new DadJokeCmd());
        addCommand(new DiceCmd());
        addCommand(new CoinFlipCmd());
        //Animal Commands
        addCommand(new CatCmd());
        addCommand(new DogCmd());
        addCommand(new FoxCmd());
        addCommand(new PandaCmd());

        addCommand(new PigCmd());
        //Commands that return simple strings
        addCommand(new LanguageCmd());
        addCommand(new GoodMorningCmd());
        addCommand(new LCmd());
        addCommand(new WelcomeBackCmd());
    }

    private void addCommand(ICommand cmd) {
        boolean nameFound = this.commands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(cmd.getName()));

        if (nameFound) {
            throw new IllegalArgumentException("A command with this name is already present");
        }

        commands.add(cmd);
    }

    public List<ICommand> getCommands() {
        return commands;
    }

    @Nullable
    public ICommand getCommand(String search) {
        String searchLower = search.toLowerCase();

        for (ICommand cmd : this.commands) {
            if (cmd.getName().equals(searchLower) || cmd.getAliases().contains(searchLower)) {
                return cmd;
            }
        }

        return null;
    }

    public void handle(GuildMessageReceivedEvent event) {
        String prefix = Config.getConfig().getString("bot.prefix");

        String[] split = event.getMessage().getContentRaw()
                .replaceFirst("(?i)" + Pattern.quote(prefix), "")
                .split("\\s+");

        String invoke = split[0].toLowerCase();
        ICommand cmd = this.getCommand(invoke);

        if (cmd !=null) {
            event.getChannel().sendTyping().queue();
            List<String> args = Arrays.asList(split).subList(1, split.length);

            CommandContext ctx = new CommandContext(event, args);

            cmd.handle(ctx);
        }
    }
}