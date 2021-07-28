package tech.nevets.dadc1;

import org.simpleyaml.configuration.file.YamlFile;

import java.io.IOException;

public class Config {

    private static final YamlFile ymlFile = new YamlFile("config.yml");

    public static void loadConfig() {
        try {
            if (!ymlFile.exists()) {
                System.out.println("Configuration file has been created\n");
                ymlFile.createNewFile(true);
            } else {
                System.out.println("config.yml already exists, loading configuration file...\n");
            }
            ymlFile.loadWithComments();
        } catch (final Exception e) {
            e.printStackTrace();
        }

        System.out.println("Adding default values...");
        ymlFile.addDefault("bot.prefix", "!");
        ymlFile.addDefault("bot.token", "BotToken122333444455555666666777777788888888999999999");
        ymlFile.addDefault("bot.activity", "playing");
        ymlFile.addDefault("bot.action", "with myself");
        ymlFile.addDefault("permission.staff-role-id", "000000000000000000");
        ymlFile.addDefault("permission.owner-id", "000000000000000000");
        ymlFile.addDefault("command.api.dadjoke", "https://icanhazdadjoke.com/");
        ymlFile.addDefault("command.api.cat.url", "https://api.thecatapi.com/v1/images/search");
        ymlFile.addDefault("command.api.cat.key", "00000000-0000-0000-0000-000000000000");
        ymlFile.addDefault("command.api.dog.url", "https://api.thedogapi.com/v1/images/search");
        ymlFile.addDefault("command.api.dog.key", "00000000-0000-0000-0000-000000000000");
        ymlFile.addDefault("command.api.fox.url", "https://randomfox.ca/floof/");
        ymlFile.addDefault("command.api.panda.url", "https://some-random-api.ml/img/panda");
        ymlFile.addDefault("command.api.redpanda.url", "https://some-random-api.ml/img/red_panda");
        ymlFile.addDefault("command.api.bird.url", "https://some-random-api.ml/img/birb");
        ymlFile.addDefault("command.api.pig.url", "https://pigs.p.rapidapi.com/random");
        ymlFile.addDefault("command.api.pig.host", "");
        ymlFile.addDefault("command.api.pig.key", "00000000000000000000000000000000000000000000000000");
        ymlFile.addDefault("command.api.imgur.client-id", "000000000000000");
        ymlFile.addDefault("command.api.imgur.secret", "0000000000000000000000000000000000000000");
        ymlFile.addDefault("verbose", false);


        try {
            ymlFile.save();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static YamlFile getConfig() {
        return ymlFile;
    }
}