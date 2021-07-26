package tech.nevets.dadc1.commands.administrator;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import tech.nevets.dadc1.Bot;
import tech.nevets.dadc1.Config;
import tech.nevets.dadc1.commands.CommandContext;
import tech.nevets.dadc1.commands.ICommand;

import java.util.List;

public class ReloadConfigCmd implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        List<String> args = ctx.getArgs();
        TextChannel channel = ctx.getChannel();
        User user = ctx.getAuthor();

        if (user.getId().equals(Config.getConfig().getString("permission.owner-id"))) {

            if (args.isEmpty()) {
                Config.loadConfig();
                Bot.getActivity();

                channel.sendTyping().queue();
                channel.sendMessage("Config has successfully been reloaded").queue();

                return;
            }
        }
    }

    @Override
    public String getName() {
        return "reloadconfig";
    }

    @Override
    public String getHelp() {
        return null;
    }
}
