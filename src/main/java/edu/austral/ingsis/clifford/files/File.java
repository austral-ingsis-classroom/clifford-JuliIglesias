package edu.austral.ingsis.clifford.files;

public class File implements FileSystem{
  private final String name;

  public File(String name) {
    this.name = name;
  }
  @Override
  public String getName() {
    return name;
  }

  @Override
  public void print() {
    System.out.println(getName());
  }
}
