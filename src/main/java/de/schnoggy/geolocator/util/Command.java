package de.schnoggy.geolocator.util;

import de.schnoggy.geolocator.GeoLocator;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.TextChannel;

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

public abstract class Command {

    protected String permission;
    protected TextChannel tempChannel;

    public Command() {

    }

    public void load() {
        CommandSpecification clazz = this.getClass().getAnnotation(CommandSpecification.class);
        this.permission = clazz.permission();
        this.initialize();
    }

    public void execute(User user, TextChannel channel, String[] args) throws IOException {
        if (this.permission != null && !this.permission.equals("")) {
            if (!user.hasPermission(this.permission)) {
                channel.sendMessageEmbeds(Embed.getEmbed("**No Permissions!**", "You lack the permission: **" + this.permission + "**", Color.RED).build())
                        .queue(message -> this.deleteAfter(message, 5));

                return;
            }
        }

        String[] newArgs = new String[args.length - 1];
        this.tempChannel = channel;

        System.arraycopy(args, 1, newArgs, 0, args.length - 1);
        this.onCommand(user, channel, newArgs);
    }

    public void executeSubCommand(User user, TextChannel channel, String[] args) throws IOException {
        if (GeoLocator.getInstance().getSubCommandHandler().getSubCommandOfCommand(args[0], this) == null) {
            this.sendEmbed(Embed.getEmbed("**Ups!**", "This command cannot be found!", Color.RED));
            return;
        }

        Command sub = GeoLocator.getInstance().getSubCommandHandler().getSubCommandOfCommand(args[0], this);
        sub.execute(user, channel, args);
    }

    public void sendEmbed(Embed embed) {
        this.sendEmbed(embed, false);
    }

    public void sendEmbed(Embed embed, boolean autoDelete) {
        if (this.tempChannel != null) {
            if (autoDelete) {
                this.tempChannel.sendMessageEmbeds(embed.build()).queue(msg -> this.deleteAfter(msg, 5));
            } else {
                this.tempChannel.sendMessageEmbeds(embed.build()).queue();
            }
        }
    }

    public void sendMessage(String message) {
        this.sendMessage(message, false);
    }

    public void sendMessage(String message, boolean autoDelete) {
        if (this.tempChannel != null) {
            if (autoDelete) {
                this.tempChannel.sendMessage(message).queue(msg -> this.deleteAfter(msg, 5));
            } else {
                this.tempChannel.sendMessage(message).queue();
            }
        }
    }

    public void deleteAfter(Message message, int delay) {
        message.delete().queueAfter(delay, TimeUnit.SECONDS);
    }

    public void initialize() {

    }

    public abstract void onCommand(User user, TextChannel channel, String[] args) throws IOException;

}
