package edu.austral.ingsis.clifford.commands;

import java.nio.file.FileSystemException;
import java.util.Optional;

public class TouchRunner implements CommandRunner {
  @Override
  public boolean supports(String command) {
    return command.trim().startsWith("touch");
  }

  @Override
  public Optional<Command> parse(String[] input) throws FileSystemException {
    String[] parts = input;
    if (parts.length != 2) {
      throw new FileSystemException("Invalid command format");
    }
    String fileName = parts[1];
    validateFileName(fileName);

    return Optional.of(new Touch(fileName));
  }

  private void validateFileName(String fileName) throws FileSystemException {
    if (fileName.contains("/") || fileName.contains(" ")) {
      throw new FileSystemException("Files can't contain '/' or spaces");
    }
  }
}
