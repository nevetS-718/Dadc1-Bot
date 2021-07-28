package tech.nevets.dadc1;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.simpleyaml.configuration.file.YamlFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bot {
    private static JDA jda;
    private static YamlFile config;
    public static String prefix;
    private static final Logger LOGGER = LoggerFactory.getLogger(Bot.class);

    public static void main(String[] args) throws Exception {
        Config.loadConfig();
        String token = Config.getConfig().getString("bot.token");
        config = Config.getConfig();
        prefix = Config.getConfig().getString("bot.prefix");

        jda = JDABuilder.createDefault(token)
                .addEventListeners(new Listener())
                .build();

        getActivity();
    }

    public static void getActivity() {
        if (config.getString("bot.activity").equalsIgnoreCase("playing")) {
            jda.getPresence().setActivity(Activity.playing(config.getString("bot.action")));
        } else if (config.getString("bot.activity").equalsIgnoreCase("watching")) {
            jda.getPresence().setActivity(Activity.watching(config.getString("bot.action")));
        } else if (config.getString("bot.activity").equalsIgnoreCase("competing")) {
            jda.getPresence().setActivity(Activity.competing(config.getString("bot.action")));
        } else if (config.getString("bot.activity").equalsIgnoreCase("listening")) {
            jda.getPresence().setActivity(Activity.listening(config.getString("bot.action")));
        } else {
            jda.getPresence().setActivity(Activity.playing("videogames"));
        }
    }
}
