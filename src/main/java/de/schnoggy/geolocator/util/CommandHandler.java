package de.schnoggy.geolocator.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

/***********************************
 * User: Malvin H.
 * Date: 22.03.2022
 * Project: BunsyDiscord
 * Product: IntelliJ IDEA
 * Copyright: Bunsy.net | Developer
 ***********************************/

public class CommandHandler {
    private final LinkedList<Command> commands = new LinkedList<>();

    public Command getCommand(String command) {
        for (Command cmd : this.commands) {
            CommandSpecification clazz = cmd.getClass().getAnnotation(CommandSpecification.class);
            if (command.equalsIgnoreCase(clazz.command())) {
                return cmd;
            }

            for (String alias : clazz.aliases()) {
                if (alias.equalsIgnoreCase(command)) {
                    return cmd;
                }
            }
        }
        return null;
    }

    public void loadCommands() {
        for (Class<?> clazz : this.getAllClasses("de.schnoggy.geolocator.command")) {
            if (clazz.getSuperclass().equals(Command.class)) {
                try {
                    Command command = (Command) clazz.newInstance();
                    this.loadCommand(command);
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadCommand(Command command) {
        if (command.getClass().getAnnotations().length > 0) {
            if (command.getClass().getAnnotation(CommandSpecification.class) != null) {
                command.load();
                this.commands.add(command);
            }
        }
    }

    public Set<Class> getAllClasses(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LinkedList<Command> getCommands() {
        return commands;
    }
}