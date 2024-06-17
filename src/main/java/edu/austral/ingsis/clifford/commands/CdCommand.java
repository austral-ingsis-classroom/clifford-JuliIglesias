package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.CLI;
import edu.austral.ingsis.clifford.files.Directory;
import edu.austral.ingsis.clifford.files.FileSystem;

import java.util.List;

public class CdCommand implements Command{
  private final CLI cli;

  public CdCommand(CLI cli) {
    this.cli = cli;
  }

  @Override
  public String execute(List<String> options, List<String> arguments) {
    if (!options.isEmpty()){
      return "Invalid option";
    }
    if (arguments.size() != 1){
      return "Invalid number of arguments";
    }

    String arg = arguments.get(0);
    switch (arg) {
      case "..":
        Directory parent = findDirWithName(cli.root, cli.currentDirectory.getName());
        if (parent == null){
          cli.currentDirectory = cli.root;
          return "Change Directory to: " + cli.root.getName();
        }
        cli.currentDirectory = findDirWithName(cli.root, cli.currentDirectory.getName());
        break;

      case ".":
        break;

      default:
        if (arg.startsWith("/")){
          cli.currentDirectory = cli.root;
          String[] ruta = arg.split("/");
          try {
            navigateToDirectory(ruta);
          } catch (Exception e){
            return e.getMessage();
          }
        } else {
          String[] ruta = arg.split("/");
          try {
            navigateToDirectory(ruta);
          } catch (Exception e){
            return e.getMessage();
          }
        }
        break;
    }

    return "Change Directory to: " + cli.currentDirectory.getName();
  }

  private void navigateToDirectory(String[] ruta) {
    Directory start = cli.currentDirectory;
    for (String dir : ruta){
      try {
        cli.currentDirectory = findDirWithName(start, dir);
        if (cli.currentDirectory == null){
          throw new Exception("Directory not found: " + dir);
        }
        start = cli.currentDirectory;
      } catch (Exception e){
        throw new IllegalArgumentException("Directory not found: " + dir);
      }
    }
  }

  private Directory findDirWithName(Directory dir, String name) {
    List<FileSystem> fileSystems = dir.getFileSystems();
    for (FileSystem system : fileSystems) {
      if (!(system instanceof Directory)) {
        continue;
      }
      if (system.getName().equals(name)) {
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
