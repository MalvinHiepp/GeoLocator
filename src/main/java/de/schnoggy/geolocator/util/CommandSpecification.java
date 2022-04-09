package de.schnoggy.geolocator.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***********************************
 * User: Malvin H.
 * Date: 22.03.2022
 * Project: BunsyDiscord
 * Product: IntelliJ IDEA
 * Copyright: Bunsy.net | Developer
 ***********************************/

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE})
public @interface CommandSpecification {
    String command();

    String permission();

    String[] aliases();
}
