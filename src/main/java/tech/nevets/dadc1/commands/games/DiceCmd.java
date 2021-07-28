package tech.nevets.dadc1.commands.games;

import tech.nevets.dadc1.Config;
import tech.nevets.dadc1.commands.CommandContext;
import tech.nevets.dadc1.commands.ICommand;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DiceCmd implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        Random rand = ThreadLocalRandom.current();

        int roll = rand.nextInt(6) + 1;
        ctx.getChannel().sendTyping().queue();
        ctx.getChannel().sendMessage("Your roll: " + roll).queue();
    }

    @Override
    public String getName() {
        return "dice";
    }

    @Override
    public String getHelp() {
        String prefix = Config.getConfig().getString("bot.prefix");
        return "Returns a value from 1-6" +
                "Usage: `" + prefix + "dice`";
    }
}
