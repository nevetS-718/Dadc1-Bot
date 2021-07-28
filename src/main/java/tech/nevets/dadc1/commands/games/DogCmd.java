package tech.nevets.dadc1.commands.games;

import net.dv8tion.jda.api.EmbedBuilder;
import tech.nevets.dadc1.Config;
import tech.nevets.dadc1.commands.CommandContext;
import tech.nevets.dadc1.commands.ICommand;
import tech.nevets.dadc1.util.CatRequest;
import tech.nevets.dadc1.util.DogRequest;

import java.io.IOException;

public class DogCmd implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        try { DogRequest.getHttpConnection(); } catch (IOException e) { e.printStackTrace(); } catch (InterruptedException e) { e.printStackTrace(); }
        EmbedBuilder eb = new EmbedBuilder();

        ctx.getChannel().sendTyping().queue();
        eb.setImage(DogRequest.url);
        ctx.getChannel().sendMessage(eb.build()).queue();
    }

    @Override
    public String getName() {
        return "dog";
    }

    @Override
    public String getHelp() {
        String prefix = Config.getConfig().getString("bot.prefix");
        return "Sends a picture of a dog!\n" +
                "Usage: `" + prefix + "dog`";
    }
}
