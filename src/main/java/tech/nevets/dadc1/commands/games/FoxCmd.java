package tech.nevets.dadc1.commands.games;

import net.dv8tion.jda.api.EmbedBuilder;
import tech.nevets.dadc1.Config;
import tech.nevets.dadc1.commands.CommandContext;
import tech.nevets.dadc1.commands.ICommand;
import tech.nevets.dadc1.util.FoxRequest;

import java.io.IOException;

public class FoxCmd implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        try { FoxRequest.getHttpConnection(); } catch (IOException e) { e.printStackTrace(); } catch (InterruptedException e) { e.printStackTrace(); }
        EmbedBuilder eb = new EmbedBuilder();

        ctx.getChannel().sendTyping().queue();
        eb.setImage(FoxRequest.url);
        ctx.getChannel().sendMessage(eb.build()).queue();
    }

    @Override
    public String getName() {
        return "fox";
    }

    @Override
    public String getHelp() {
        String prefix = Config.getConfig().getString("bot.prefix");
        return "Sends a picture of a fox!\n" +
                "Usage: `" + prefix + "fox`";
    }
}
