package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.files.Directory;

public class TouchCommand implements Command{
  private final Directory directory;

  public TouchCommand(Directory directory) {
    this.directory = directory;
  }

  @Override
  public void execute() {
    directory.print();
  }
}
