package de.schnoggy.geolocator.util;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/***********************************
 * User: Malvin H.
 * Date: 22.03.2022
 * Project: BunsyDiscord
 * Product: IntelliJ IDEA
 * Copyright: Bunsy.net | Developer
 ***********************************/

public class SubCommandHandler {
    private final LinkedHashMap<Command, LinkedList<Command>> subCommands = new LinkedHashMap<>();

    public void addSubCommand(Command sub, Command main) {
        if (this.subCommands.containsKey(main)) {
            LinkedList<Command> subs = this.subCommands.get(main);
            subs.add(sub);
            this.subCommands.put(main, subs);
        } else {
            this.subCommands.put(main, new LinkedList<>(Collections.singletonList(sub)));
        }
    }

    public Command getSubCommandOfCommand(String prefix, Command command) {
        for (Command sub : this.subCommands.get(command)) {
            CommandSpecification clazz = sub.getClass().getAnnotation(CommandSpecification.class);
            if (clazz.command().equalsIgnoreCase(prefix)) {
                return sub;
            }
        }
        return null;
    }
}
