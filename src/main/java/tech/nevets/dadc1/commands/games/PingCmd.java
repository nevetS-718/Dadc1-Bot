package tech.nevets.dadc1.commands.games;

import net.dv8tion.jda.api.JDA;
import tech.nevets.dadc1.commands.CommandContext;
import tech.nevets.dadc1.commands.ICommand;

public class PingCmd implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();

        jda.getRestPing().queue((ping) -> ctx.getChannel().sendMessageFormat("Reset Ping: %sms\nWS Ping: %sms", ping, jda.getGatewayPing()).queue());

    }

    @Override
    public String getHelp() {
        return "Shows the current ping from the bot to the Discord servers";
    }

    @Override
    public String getName() {
        return "ping";
    }
}
