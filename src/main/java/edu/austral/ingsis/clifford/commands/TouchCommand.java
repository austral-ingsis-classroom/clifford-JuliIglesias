package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.CLI;
import edu.austral.ingsis.clifford.files.Directory;

import java.util.List;

public class TouchCommand implements Command{
  private final CLI cli;

  public TouchCommand(CLI cli){
    this.cli = cli;
  }

  @Override
  public String execute(List<String> options, List<String> arguments) {
    directory.print();
  }
}
