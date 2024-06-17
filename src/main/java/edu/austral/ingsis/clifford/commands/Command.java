package edu.austral.ingsis.clifford.commands;

import java.util.List;

public interface Command {
  String execute(List<String> options, List<String> arguments);
}
