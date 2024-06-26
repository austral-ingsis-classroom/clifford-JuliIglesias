package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.CLI;
import edu.austral.ingsis.clifford.files.Directory;
import edu.austral.ingsis.clifford.files.FileSystem;
import java.util.List;

public class CdCommand implements Command {
  private final CLI cli;

  public CdCommand(CLI cli) {
    this.cli = cli;
  }

  @Override
  public String execute(List<String> options, List<String> arguments) {
    if (!options.isEmpty()) {
      return "no options allowed";
    }
    if (arguments.size() != 1) {
      return "invalid arguments";
    }

    String arg = arguments.getFirst();
    switch (arg) {
      case "..":
        Directory parent = findDirWithName(cli.root, cli.currentDirectory.name());
        if (parent == null) {
          cli.currentDirectory = cli.root;
          return "moved to directory '" + cli.root.name() + "'";
        }
        cli.currentDirectory = findDirWithName(cli.root, cli.currentDirectory.name());
        break;

      case ".":
        break;

      default:
        if (arg.startsWith("/")) {
          cli.currentDirectory = cli.root;
          String[] ruta = arg.split("/");
          try {
            navigateToDirectory(ruta);
          } catch (Exception e) {
            return e.getMessage();
          }
        } else {
          String[] ruta = arg.split("/");
          try {
            navigateToDirectory(ruta);
          } catch (Exception e) {
            return e.getMessage();
          }
        }
        break;
    }

    return "moved to directory '" + cli.currentDirectory.name() + "'";
  }

  private void navigateToDirectory(String[] ruta) {
    Directory start = cli.currentDirectory;
    for (String dir : ruta) {
      try {
        FileSystem nextDir = cli.currentDirectory.getFileSystem(dir);
        cli.currentDirectory = (Directory) nextDir;
      } catch (Exception e) {
        cli.currentDirectory = start;
        throw new IllegalArgumentException("'" + dir + "' directory does not exist");
      }
    }
  }

  private Directory findDirWithName(Directory dir, String name) {
    List<FileSystem> fileSystems = dir.getFileSystems();
    for (FileSystem system : fileSystems) {
      if (!(system instanceof Directory)) {
        continue;
      }
      if (system.name().equals(name)) {
        return dir;
      }
      Directory search = findDirWithName((Directory) system, name);
      if (search != null) {
        return search;
      }
    }
    return null;
  }
}
