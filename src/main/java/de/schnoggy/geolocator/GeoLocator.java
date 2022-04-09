package de.schnoggy.geolocator;

import de.schnoggy.geolocator.listener.CommandListener;
import de.schnoggy.geolocator.util.CommandHandler;
import de.schnoggy.geolocator.util.LocateService;
import de.schnoggy.geolocator.util.SubCommandHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

/***********************************
 * User: Malvin H.
 * Date: 09.04.2022
 * Project: GeoLocator
 * Product: IntelliJ IDEA
 * Copyright: Bunsy.net | Developer
 ***********************************/

public class GeoLocator {

    private static GeoLocator instance;

    private JDABuilder builder;
    private JDA jda;

    private CommandHandler commandHandler;
    private SubCommandHandler subCommandHandler;
    private LocateService locateService;

    public final String version = "0.0.1";

    public static void main(String[] args) throws LoginException {
        new GeoLocator().start();
    }

    private void start() throws LoginException {
        instance = this;

        builder = JDABuilder.createDefault("");
        builder.setActivity(Activity.watching("some IP's - :help for help"));
        jda = builder.build();

        jda.addEventListener(new CommandListener());

        subCommandHandler = new SubCommandHandler();
        commandHandler = new CommandHandler();
        commandHandler.loadCommands();
        locateService = new LocateService();
    }

    public static GeoLocator getInstance() {
        return instance;
    }

    public JDABuilder getBuilder() {
        return builder;
    }

    public JDA getJda() {
        return jda;
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public SubCommandHandler getSubCommandHandler() {
        return subCommandHandler;
    }

    public String getVersion() {
        return version;
    }

    public LocateService getLocateService() {
        return locateService;
    }
}
