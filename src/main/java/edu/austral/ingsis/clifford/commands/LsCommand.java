package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.CLI;
import edu.austral.ingsis.clifford.files.FileSystem;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LsCommand implements Command {
  private final CLI cli;

  public LsCommand(CLI cli) {
    this.cli = cli;
  }

  @Override
  public String execute(List<String> options, List<String> arguments) {
    if (options.size() > 1 || !arguments.isEmpty()) {
      return "ls does not accept invalid arguments or options";
    }

    List<String> names = getListName(cli.currentDirectory.getFileSystems());

    if (options.isEmpty()) {
      return listNames(names);
    }

    String option = options.getFirst();

    if (option.startsWith("--ord=")) {
      switch (option.split("=")[1]) {
        case "asc":
          names.sort(String::compareTo);
          break;

        case "desc":
          names.sort(Comparator.reverseOrder());
          break;

        default:
          return "Invalid parameter: " + option.split("=")[1];
      }
      return listNames(names);
    }

    return "error";
  }

  private List<String> getListName(List<FileSystem> fileSystems) {
    List<String> names = new ArrayList<>();
    for (FileSystem fileSystem : fileSystems) {
      names.add(fileSystem.name());
    }
    return names;
  }

  private String listNames(List<String> names) {
    StringBuilder sb = new StringBuilder();
    for (String name : names) {
      sb.append(name).append(" ");
    }
    return sb.isEmpty() ? "" : sb.substring(0, sb.length() - 1);
  }
}
