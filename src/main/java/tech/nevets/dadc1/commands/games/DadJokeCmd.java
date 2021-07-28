package tech.nevets.dadc1.commands.games;

import net.dv8tion.jda.api.entities.TextChannel;
import tech.nevets.dadc1.Config;
import tech.nevets.dadc1.commands.CommandContext;
import tech.nevets.dadc1.commands.ICommand;
import tech.nevets.dadc1.util.JokeRequest;

import java.io.IOException;
import java.util.List;

public class DadJokeCmd implements ICommand {

    @Override
    public void handle(CommandContext ctx) {
        try { JokeRequest.getHttpConnection(); } catch (IOException e) { e.printStackTrace(); } catch (InterruptedException e) { e.printStackTrace(); }
        List<String> args = ctx.getArgs();
        TextChannel channel = ctx.getChannel();

        if (args.isEmpty()) {
            JokeRequest jokeRequest = new JokeRequest();
            String joke = jokeRequest.joke;

            channel.sendTyping().queue();
            channel.sendMessage(joke).queue();
        }
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