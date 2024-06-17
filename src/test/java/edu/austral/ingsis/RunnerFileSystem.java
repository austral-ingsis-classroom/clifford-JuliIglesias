package edu.austral.ingsis;

import edu.austral.ingsis.clifford.CLI;
import edu.austral.ingsis.clifford.files.Directory;

import java.util.ArrayList;
import java.util.List;

public class RunnerFileSystem implements FileSystemRunner{
  @Override
  public List<String> executeCommands(List<String> commands) {
    CLI cli = new CLI(new Directory("/"));
    List<String> output = new ArrayList<>();
    for (String command : commands) {
      output.add(cli.executeCommand(command));
    }
    return output;
  }
}
