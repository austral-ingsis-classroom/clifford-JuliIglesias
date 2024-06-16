package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.files.Directory;

public class CdCommand implements Command{
  private final Directory directory;

  public CdCommand(Directory directory) {
    this.directory = directory;
  }

  @Override
  public void execute() {
    directory.print();
  }
}
