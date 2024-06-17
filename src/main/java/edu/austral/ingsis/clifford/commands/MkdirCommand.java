package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.CLI;
import edu.austral.ingsis.clifford.files.Directory;

import java.util.List;

public class MkdirCommand implements Command{
  private final CLI cli;

  public MkdirCommand(CLI cli){
    this.cli = cli;
  }

  @Override
  public String execute(List<String> options, List<String> arguments) {
    if (options.size() + arguments.size() != 1) {
      return "mkdir: missing operand";
    }
    String directoryName = options.isEmpty() ? arguments.getFirst() : options.getFirst();

    if (directoryName.equals("/")) {
      return "mkdir: cannot create directory with '/'";
    }

    cli.currentDirectory.add(new Directory(directoryName));
    return directoryName + " directory created successfully";
  }
}
