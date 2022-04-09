package de.schnoggy.geolocator.util;

/***********************************
 * User: Malvin H.
 * Date: 22.03.2022
 * Project: BunsyDiscord
 * Product: IntelliJ IDEA
 * Copyright: Bunsy.net | Developer
 ***********************************/

public class User {

    protected final net.dv8tion.jda.api.entities.User jdaUser;

    public User(net.dv8tion.jda.api.entities.User jdaUser) {
        this.jdaUser = jdaUser;
    }

    public boolean hasPermission(String permission) {
        return true;
    }

    public net.dv8tion.jda.api.entities.User getJdaUser() {
        return jdaUser;
    }

}
