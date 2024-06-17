package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.CLI;
import edu.austral.ingsis.clifford.files.Directory;
import edu.austral.ingsis.clifford.files.FileSystem;
import java.util.List;

public class RmCommand implements Command {
  private final CLI cli;

  public RmCommand(CLI cli) {
    this.cli = cli;
  }

  @Override
  public String execute(List<String> options, List<String> arguments) {
    if (options.isEmpty() && arguments.isEmpty()) {
      return "missing arguments";
    }
    if (!options.isEmpty() && options.getFirst().equals("--recursive") && arguments.isEmpty()) {
      cli.currentDirectory.delete(arguments.getFirst());
      return "'--recursive' removed";
    } else if (!options.isEmpty() && options.getFirst().equals("--recursive")) {
      cli.currentDirectory.delete(arguments.getFirst());
    } else {
      if (options.isEmpty()) {
        FileSystem obj = cli.currentDirectory.getFileSystem(arguments.getFirst());
        if (!(obj instanceof Directory)) {
          cli.currentDirectory.delete(arguments.getFirst());
        } else {
          return "cannot remove '" + arguments.getFirst() + "', is a directory";
        }
      }
    }
    return "'" + arguments.getFirst() + "' removed";
  }
}
