package edu.austral.ingsis.clifford.files;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Directory implements FileSystem{
  private final String name;
  private final List<FileSystem> fileSystems;

  public Directory(String name) {
    this.name = name;
    this.fileSystems = new ArrayList<>();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void print() {
    System.out.println(getName());
    for (FileSystem fileSystem : fileSystems) {
      fileSystem.print();
    }
  }

  public void add(FileSystem fileSystem) {
    fileSystems.add(fileSystem);
  }

  public void delete(String name) {
    Optional<FileSystem> fileSystem = fileSystems.stream().
        filter(fs -> fs.getName().equals(name))
        .findFirst();

    if (fileSystem.isPresent()) {
      fileSystems.remove(fileSystem.get());
    } else {
      throw new IllegalArgumentException("File or Directory not found: " + name);
    }
  }

  public void sort() {
    fileSystems.sort(Comparator.comparing(FileSystem::getName));
  }

  public void createDirectory(String name) {
    Directory directory = new Directory(name);
    add(directory);
  }

  public void createFile(String name) {
    File file = new File(name);
    add(file);
  }

  public List<FileSystem> getFileSystems() {
    return fileSystems;
  }

  public FileSystem getFileSystem(String name) {
    Optional<FileSystem> fileSystem = fileSystems.stream()
        .filter(fs -> fs.getName().equals(name))
        .findFirst();

    if (fileSystem.isPresent()) {
      return fileSystem.get();
    } else {
      throw new IllegalArgumentException("File or Directory not found: " + name);
    }
  }

  // todavia no se si hace falta, tengo que ver bien los tests
  public FileSystem changeDirectory(String name) {
    Optional<FileSystem> fileSystem = fileSystems.stream()
        .filter(fs -> fs.getName().equals(name))
        .findFirst();

    if (fileSystem.isPresent() && fileSystem.get() instanceof Directory) {
      return fileSystem.get();
    } else {
      throw new IllegalArgumentException("Directory not found: " + name);
    }
  }
}
