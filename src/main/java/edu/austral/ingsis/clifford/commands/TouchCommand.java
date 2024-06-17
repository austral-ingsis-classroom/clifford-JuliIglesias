package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.CLI;
import edu.austral.ingsis.clifford.files.Directory;
import edu.austral.ingsis.clifford.files.File;

import java.util.List;

public class TouchCommand implements Command{
  private final CLI cli;

  public TouchCommand(CLI cli){
    this.cli = cli;
  }

  @Override
  public String execute(List<String> options, List<String> arguments) {
    if (options.size() + arguments.size() !=1){
      return "missing argument";
    }

    String name = options.isEmpty() ? arguments.getFirst() : options.getFirst();
    if (name.contains("/")){
      return "file cannot contain a '/'";
    }
    cli.currentDirectory.add(new File(name));
    return "'" + name + "' file created";
  }
}
