package tech.nevets.dadc1.commands.strings;

import tech.nevets.dadc1.commands.CommandContext;
import tech.nevets.dadc1.commands.ICommand;

public class LCmd implements ICommand {
    @Override
    public void handle(CommandContext ctx) {

        try {
            ctx.getMessage().delete().queue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ctx.getChannel().sendTyping().queue();
        for (int i = 0; i <5; i++) {
            ctx.getChannel().sendMessage("L").queue();
        }

    }

    @Override
    public String getName() {
        return "l";
    }

    @Override
    public String getHelp() {
        return null;
    }
}
