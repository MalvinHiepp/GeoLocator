package de.schnoggy.geolocator.command;

import de.schnoggy.geolocator.GeoLocator;
import de.schnoggy.geolocator.util.*;
import net.dv8tion.jda.api.entities.TextChannel;
import org.json.JSONObject;

import java.awt.*;
import java.io.IOException;

/***********************************
 * User: Malvin H.
 * Date: 09.04.2022
 * Project: GeoLocator
 * Product: IntelliJ IDEA
 * Copyright: Bunsy.net | Developer
 ***********************************/

@CommandSpecification(
        command = "locate",
        permission = "",
        aliases = {"location", "loc", "locator", "locate"}
)
public class LocateCommand extends Command {

    @Override
    public void onCommand(User user, TextChannel channel, String[] args) throws IOException {
        switch (args.length) {
            case 0: {
                sendEmbed(Embed.getEmbed("**Location**", "Please specify a ip", Color.RED));
                break;
            }
            case 1: {
                LocateService locateService = GeoLocator.getInstance().getLocateService();
                JSONObject jsonObject = locateService.queryIP(args[0]);

                try {
                    sendEmbed(Embed.getEmbed("**Location**",
                            "Country: " + jsonObject.getString("country") +
                                    "\n City: " + jsonObject.getString("city") +
                                    "\n Countrycode: " + jsonObject.getString("countryCode") +
                                    "\n Region: " + jsonObject.getString("region") +
                                    "\n Regionname: " + jsonObject.getString("regionName") +
                                    "\n Isp: " + jsonObject.getString("isp") +
                                    "\n Asn: " + jsonObject.getString("as") +
                                    "\n Timezone: " + jsonObject.getString("timezone") +
                                    "\n Organisation: " + jsonObject.getString("org") +
                                    "\n Zip: " + jsonObject.getInt("zip") +
                                    "\n Lat: " + jsonObject.getFloat("lat") + ", Lon: " + jsonObject.getFloat("lon"),
                            Color.GREEN));
                } catch (Exception e) {
                    if (jsonObject.getString("status").equals("fail")) {
                        sendEmbed(Embed.getEmbed("**Location**", "IP not found!",
                                Color.RED));
                    }
                }

                break;
            }
            default: {
                sendEmbed(Embed.getEmbed("**Location**", "Too many arguments", Color.RED));
            }
        }
    }
}
