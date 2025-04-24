package edu.austral.ingsis.clifford.commands;

import java.nio.file.FileSystemException;
import java.util.Optional;

public class CdRunner implements CommandRunner {

  @Override
  public boolean supports(String command) {
    return command.trim().startsWith("cd");
  }

  @Override
  public Optional<Command> parse(String[] input) throws FileSystemException {
    if (input.length != 2) {
      throw new FileSystemException("Invalid command format");
    }
    String command = input[1];
    return Optional.of(new Cd(command));
  }
}
