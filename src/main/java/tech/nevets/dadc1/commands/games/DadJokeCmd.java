package tech.nevets.dadc1.commands.games;

import tech.nevets.dadc1.Config;
import tech.nevets.dadc1.commands.CommandContext;
import tech.nevets.dadc1.commands.ICommand;
import tech.nevets.dadc1.util.httprequests.JokeRequest;

import java.io.IOException;
import java.util.List;

public class DadJokeCmd implements ICommand {

    @Override
    public void handle(CommandContext ctx) {
        try { JokeRequest.getHttpConnection(); } catch (IOException | InterruptedException e) { e.printStackTrace(); }

        ctx.getChannel().sendTyping().queue();
            ctx.getChannel().sendMessage(JokeRequest.joke).queue();
    }

    @Override
    public String getName() {
        return "dadjoke";
    }

    @Override
    public String getHelp() {
        String prefix = Config.getConfig().getString("bot.prefix");
        return "Sends a dad joke :P\n" +
                "Usage: `" + prefix + "dadjoke`";
    }

    @Override
    public List<String> getAliases() {
        return List.of("joke");
    }
}