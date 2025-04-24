package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.node.Directory;
import edu.austral.ingsis.clifford.node.FileSystem;
import edu.austral.ingsis.clifford.node.InMemoryFileSystem;

import java.nio.file.FileSystemException;
import java.util.List;

public class Pwd implements Command {
    @Override
    public String execute(InMemoryFileSystem fileSystem, List<String> arguments) throws FileSystemException {
        return "";
    }

    // imprime la ruta que tiene el cursor en el file system.


}
