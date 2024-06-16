package edu.austral.ingsis.clifford.files;

import java.util.List;

public class Directory implements FileSystem{
  private final String name;
  private List<FileSystem> fileSystems;

  public Directory(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  public void add(FileSystem fileSystem) {
    fileSystems.add(fileSystem);
  }

  @Override
  public void print() {
    System.out.println(getName());
    for (FileSystem fileSystem : fileSystems) {
      fileSystem.print();
    }
  }
}
