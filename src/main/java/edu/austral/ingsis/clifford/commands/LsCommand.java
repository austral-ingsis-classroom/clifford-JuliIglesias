package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.files.Directory;
import edu.austral.ingsis.clifford.files.FileSystem;

public class LsCommand implements Command {
  private final Directory directory;

  public LsCommand(Directory directory) {
    this.directory = directory;
  }

  @Override
  public void execute() {
    directory.print();
  }
}
