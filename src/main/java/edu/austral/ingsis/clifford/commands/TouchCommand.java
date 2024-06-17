package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.CLI;
import edu.austral.ingsis.clifford.files.File;
import edu.austral.ingsis.clifford.files.FileSystem;
import java.util.List;
import java.util.Optional;

public class TouchCommand implements Command {
  private final CLI cli;

  public TouchCommand(CLI cli) {
    this.cli = cli;
  }

  @Override
  public String execute(List<String> options, List<String> arguments) {
    if (options.size() + arguments.size() != 1) {
      return "missing argument";
    }

    String name = options.isEmpty() ? arguments.getFirst() : options.getFirst();
    if (name.contains("/")) {
      return "file cannot contain a '/'";
    }

    // Check if a file with the same name already exists
    Optional<FileSystem> existingFile =
        cli.currentDirectory.getFileSystems().stream()
            .filter(fs -> fs.name().equals(name) && fs instanceof File)
            .findFirst();

    // If it exists, remove it
    if (existingFile.isPresent()) {
      cli.currentDirectory.delete(name);
    }

    cli.currentDirectory.add(new File(name));
    return "'" + name + "' file created";
  }
}
