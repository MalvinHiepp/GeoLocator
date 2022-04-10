package de.schnoggy.geolocator.command;

import de.schnoggy.geolocator.GeoLocator;
import de.schnoggy.geolocator.util.Command;
import de.schnoggy.geolocator.util.CommandSpecification;
import de.schnoggy.geolocator.util.Embed;
import de.schnoggy.geolocator.util.User;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

/***********************************
 * User: Malvin H.
 * Date: 22.03.2022
 * Project: BunsyDiscord
 * Product: IntelliJ IDEA
 * Copyright: Bunsy.net | Developer
 ***********************************/

@CommandSpecification(
        command = "info",
        permission = "",
        aliases = {"version", "author", "github", "founder", "servercount", "invite"}
)
public class InfoCommand extends Command {

    @Override
    public void onCommand(User user, TextChannel channel, String[] args) {
        sendEmbed(Embed.getEmbed("**Info**", "Author: <@593072039003160595>" +
                "\nVersion: " + GeoLocator.getInstance().getVersion() +
                "\nGitHub-Project: https://github.com/schnoggy/GeoLocator" +
                "\nServercount: " + GeoLocator.getInstance().getJda().getGuilds().size() +
                "\nInvite: https://discord.com/oauth2/authorize?client_id=962333791798575155&scope=bot&permissions=274877975552",
                Color.GREEN));
    }
}