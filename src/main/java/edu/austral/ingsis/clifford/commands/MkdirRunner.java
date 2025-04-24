package edu.austral.ingsis.clifford.commands;

import java.nio.file.FileSystemException;
import java.util.Optional;

public class MkdirRunner implements CommandRunner {

  @Override
  public boolean supports(String command) {
    return command.trim().startsWith("mkdir");
  }

  @Override
  public Optional<Command> parse(String[] input) throws FileSystemException {
    if (input.length < 2) {
      throw new FileSystemException("Mkdir requires two arguments");
    }
    String directoryName = String.join(" ", java.util.Arrays.copyOfRange(input, 1, input.length));
    return Optional.of(new Mkdir(directoryName));
  }
}
