package edu.austral.ingsis.clifford;

import edu.austral.ingsis.clifford.commands.*;
import edu.austral.ingsis.clifford.files.Directory;
import java.util.ArrayList;
import java.util.List;

public class CLI {
  public final Directory root;
  public Directory currentDirectory;

  public CLI(Directory root) {
    this.root = root;
    currentDirectory = root;
  }

  public String executeCommand(String command) {
    String[] commandParts = command.split(" ");
    String commandName = commandParts[0];

    List<String> options = new ArrayList<>();

    List<String> args = new ArrayList<>();

    for (int i = 1; i < commandParts.length; i++) {
      if (commandParts[i].startsWith("--")) {
        if (args.isEmpty()) {
          options.add(commandParts[i]);
        } else {
          args.add(commandParts[i]);
        }
      } else {
        args.add(commandParts[i]);
      }
    }
    switch (commandName) {
      case "cd":
        return new CdCommand(this).execute(options, args);
      case "ls":
        return new LsCommand(this).execute(options, args);
      case "touch":
        return new TouchCommand(this).execute(options, args);
      case "mkdir":
        return new MkdirCommand(this).execute(options, args);
      case "rm":
        return new RmCommand(this).execute(options, args);
      case "pwd":
        return new PwdCommand(this).execute(options, args);
      default:
        return "Command not found: " + commandName;
    }
  }
}
