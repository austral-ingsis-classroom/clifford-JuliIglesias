package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.files.Directory;

public class PwdCommand implements Command{
  private final Directory directory;

  public PwdCommand(Directory directory) {
    this.directory = directory;
  }

  @Override
  public void execute() {
    directory.print();
  }
}
