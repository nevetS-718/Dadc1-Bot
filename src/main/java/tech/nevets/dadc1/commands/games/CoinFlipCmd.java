package tech.nevets.dadc1.commands.games;

import tech.nevets.dadc1.Config;
import tech.nevets.dadc1.commands.CommandContext;
import tech.nevets.dadc1.commands.ICommand;

import java.util.List;
import java.util.Random;

public class CoinFlipCmd implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        Random r = new Random();
        int max = 2;

        int i = r.nextInt(max);
        if (i == 0) {
            ctx.getChannel().sendTyping().queue();
            ctx.getChannel().sendMessage("The coin landed on **heads**").queue();
        } else {
            ctx.getChannel().sendTyping().queue();
            ctx.getChannel().sendMessage("The coin landed on **tails**").queue();
        }
    }

    @Override
    public String getName() {
        return "coinflip";
    }

    @Override
    public String getHelp() {
        String prefix = Config.getConfig().getString("bot.prefix");
        return "Returns a heads or tails value" +
                "Usage: `" + prefix + "coinflip`";
    }

    @Override
    public List<String> getAliases() {
        return List.of("coin", "flip");
    }
}
