package edu.austral.ingsis.clifford.commands;

import java.nio.file.FileSystemException;
import java.util.Optional;

public class LsRunner implements CommandRunner {

  @Override
  public boolean supports(String command) {
    return command.trim().startsWith("ls");
  }

  @Override
  public Optional<Command> parse(String[] input) throws FileSystemException {
    if (input.length == 1 && input[0].equals("ls")) {
      return Optional.of(new Ls(Optional.empty()));
    }

    if (input.length == 2 && input[0].equals("ls") && input[1].startsWith("--ord=")) {
      String[] parts = input[1].split("=");
      if (parts.length == 2) {
        String order = parts[1];
        return Optional.of(new Ls(parseOrder(order)));
      } else {
        throw new FileSystemException("Invalid ls command: missing order value");
      }
    }

    throw new FileSystemException("Unknown command!");
  }

  private Optional<LsOptions> parseOrder(String value) throws FileSystemException {
    return switch (value) {
      case "asc" -> Optional.of(LsOptions.ASC);
      case "desc" -> Optional.of(LsOptions.DESC);
      default -> throw new FileSystemException("Invalid order option: " + value);
    };
  }
}
