package tech.nevets.dadc1.commands.strings;

import tech.nevets.dadc1.commands.CommandContext;
import tech.nevets.dadc1.commands.ICommand;

import java.util.List;

public class WelcomeBackCmd implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        ctx.getChannel().sendTyping().queue();
        ctx.getChannel().sendMessage("wb").queue();
    }

    @Override
    public String getName() {
        return "wb";
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public List<String> getAliases() {
        return List.of("welcomeback");
    }
}
