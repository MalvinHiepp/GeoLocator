package de.schnoggy.geolocator.command;

import de.schnoggy.geolocator.util.Command;
import de.schnoggy.geolocator.util.CommandSpecification;
import de.schnoggy.geolocator.util.Embed;
import de.schnoggy.geolocator.util.User;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

/***********************************
 * User: Malvin H.
 * Date: 09.04.2022
 * Project: GeoLocator
 * Product: IntelliJ IDEA
 * Copyright: Bunsy.net | Developer
 ***********************************/

@CommandSpecification(
        command = "help",
        permission = "",
        aliases = {"h", "halp", "?"}
)

public class HelpCommand extends Command {

    @Override
    public void onCommand(User user, TextChannel channel, String[] args) {
        sendEmbed(Embed.getEmbed("**Help**", ":country <ip> " +
                        "\n :city <ip> " +
                        "\n :countrycode <ip> " +
                        "\n :region <ip> " +
                        "\n :regionname <ip> " +
                        "\n :isp <ip> " +
                        "\n :asn <ip> " +
                        "\n :timezone <ip> " +
                        "\n :organisation <ip> " +
                        "\n :locate <ip>" +
                        "\n :latlong <ip> " +
                        "\n :zip <ip> " +
                        "\n :info",
                Color.GREEN));
    }
}
