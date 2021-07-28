package tech.nevets.dadc1.commands.games.animals;

import net.dv8tion.jda.api.EmbedBuilder;
import tech.nevets.dadc1.Bot;
import tech.nevets.dadc1.commands.CommandContext;
import tech.nevets.dadc1.commands.ICommand;
import tech.nevets.dadc1.util.httprequests.PigRequest;

import java.io.IOException;
import java.util.List;

public class EyeBleachCmd implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        try { PigRequest.getHttpConnection(); } catch (IOException | InterruptedException e) { e.printStackTrace(); }
        EmbedBuilder eb = new EmbedBuilder();

        ctx.getChannel().sendTyping().queue();
        eb.setImage(PigRequest.url);
        ctx.getChannel().sendMessageEmbeds(eb.build()).queue();
    }

    @Override
    public String getName() {
        return "pig";
    }

    @Override
    public String getHelp() {
        return "Sends a picture of something cute and wholesome!\n" +
                "Usage: `" + Bot.prefix + "eyebleach`";
    }

    @Override
    public List<String> getAliases() {
        return List.of("bleach", "cleaneyes", "");
    }
}
