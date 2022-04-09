package de.schnoggy.geolocator.util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

/***********************************
 * User: Malvin H.
 * Date: 22.03.2022
 * Project: BunsyDiscord
 * Product: IntelliJ IDEA
 * Copyright: Bunsy.net | Developer
 ***********************************/

public class Embed {
    private final EmbedBuilder builder;

    private Embed(EmbedBuilder builder) {
        this.builder = builder;
    }

    public void addImage(String url) {
        this.builder.setImage(url);
    }

    public void addThumbnail(String url) {
        this.builder.setThumbnail(url);
    }

    public void setAuthor(String author) {
        this.builder.setAuthor(author);
    }

    public void setFooter(String footer) {
        this.builder.setFooter(footer);
    }

    public MessageEmbed build() {
        return this.builder.build();
    }

    public static Embed getEmbed(String title, String content, Color color) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(color);
        builder.setTitle(title);
        builder.setDescription(content);
        builder.setFooter("Powered by Bunsy.net");
        return new Embed(builder);
    }
}
