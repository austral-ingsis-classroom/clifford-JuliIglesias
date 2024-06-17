package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.CLI;
import edu.austral.ingsis.clifford.files.Directory;
import edu.austral.ingsis.clifford.files.FileSystem;

import java.util.List;

public class PwdCommand implements Command{
  private final CLI cli;

  public PwdCommand(CLI cli){
    this.cli = cli;
  }

  @Override
  public String execute(List<String> options, List<String> arguments) {
    StringBuilder sb = new StringBuilder("/");
    Directory inicio = cli.currentDirectory;

    while(inicio != null){
      Directory parent = findDirWithName(cli.root, inicio.getName());
      if(parent == null){
        break;
      }
      sb.insert(0, "/" + parent.getName());
      inicio = parent;
    }
    sb.append(cli.currentDirectory.getName());
    int lenghtRoot = cli.root.getName().length();
    sb.delete(0, lenghtRoot + 1);
    return sb.toString();
  }

  private Directory findDirWithName(Directory dir, String name) {
    List<FileSystem> fileSystems = dir.getFileSystems();
    for (FileSystem system : fileSystems) {
      if (!(system instanceof Directory)) {
        continue;
      }
      if (system.getName().equals(name)) {
        return dir;
      }
      Directory search = findDirWithName((Directory) system, name);
      if (search != null) {
        return search;
      }
    }
    return null;
  }


}
