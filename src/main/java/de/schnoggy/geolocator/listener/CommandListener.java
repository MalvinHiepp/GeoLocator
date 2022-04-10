package de.schnoggy.geolocator.listener;

import de.schnoggy.geolocator.GeoLocator;
import de.schnoggy.geolocator.util.Command;
import de.schnoggy.geolocator.util.Embed;
import de.schnoggy.geolocator.util.User;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/***********************************
 * User: Malvin H.
 * Date: 22.03.2022
 * Project: BunsyDiscord
 * Product: IntelliJ IDEA
 * Copyright: Bunsy.net | Developer
 ***********************************/

public class CommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent e) {
        String msg = e.getMessage().getContentDisplay();

        if (msg.startsWith(":")) {
            Command command = GeoLocator.getInstance().getCommandHandler().getCommand(msg.replaceFirst(".", "").split(" ")[0]);

            if (command != null) {
                try {
                    command.execute(new User(e.getAuthor()), e.getTextChannel(), msg.split(" "));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                e.getTextChannel().sendMessageEmbeds(Embed.getEmbed("**Ups!**", "This command cannot be found!", Color.RED).build())
                        .queue(message -> message.delete().queueAfter(5, TimeUnit.SECONDS));
            }
        }
    }
}
