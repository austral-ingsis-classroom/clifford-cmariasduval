package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.node.Directory;
import edu.austral.ingsis.clifford.node.FileSystem;
import edu.austral.ingsis.clifford.node.InMemoryFileSystem;
import edu.austral.ingsis.clifford.node.Node;

import java.nio.file.FileSystemException;
import java.util.List;

public class Cd implements Command {

    //    cambia el directorio actual por el directorio que recibe como parámetro. Falla si el
    // directorio no existe o es un archivo. Tené en cuenta los siguientes casos:
    //
    //    Si el parámetro recibido es "..", se debe mover al directorio padre.
    //    Si el parámetro es ".", se debe quedar en el directorio actual pero no debe fallar.
    //    El parámetro puede ser el nombre de un directorio o una ruta hacia un directorio. Los
    // componentes de una ruta van separadas por el caracter "/".
    //    Si la ruta empieza con "/", tendrán que empezar desde el root del filesystem.

    private final String parameter;

    public Cd(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public String execute(InMemoryFileSystem fileSystem, List<String> arguments) throws FileSystemException {
        validatePath(parameter);
        Directory newDirectory = findDirectory(fileSystem, parameter);

        fileSystem.setPosition(newDirectory);


        String path = getCurrentPath(fileSystem);

        if (path.equals("/")) {
            return "moved to directory '" + path + "'";
        }
        return "moved to directory '" + newDirectory.getName() + "'";
    }



    private String getCurrentPath(InMemoryFileSystem fileSystem) {
        Directory currentDirectory = fileSystem.getCurrentPosition();
        if (currentDirectory != null) {
            String path = currentDirectory.getFullPath();
            if (path == null || path.isEmpty()) {
                return "/";
            }

            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            return path;
        }
        return "/";
    }

    private void validatePath(String input) throws FileSystemException {
        if (input.contains(" ")) {
            throw new FileSystemException("Input can't contain spaces ");
        } else if (input.startsWith("/")) {
            return;
        } else if (input.contains("/")) {
            return;
        }
    }

    private Directory findDirectory(InMemoryFileSystem fileSystem, String input) throws FileSystemException {
        Directory current = fileSystem.getCurrentPosition();

        if (input.equals("..")) {
            return moveToParent(current);
        }

        if (input.equals(".")) {
            return current;
        }

        if (input.startsWith("/")) {
            current = fileSystem.getRoot();
        }
        return moveThroughInput(current, input);
    }

    private Directory moveThroughInput(Directory current, String input) throws FileSystemException {
        String[] parts = input.split("/");
        for (String part : parts) {
            if (!part.isEmpty()) {
                current = moveToChild(current, part);
            }
        }
        return current;

    }

    private Directory moveToChild(Directory current, String part) throws FileSystemException {
        for (Node child : current.getChildren()) {
            if (child instanceof Directory && child.getName().equals(part)) {
                return (Directory) child;
            }
        }
        throw new FileSystemException("'" + part + "' directory does not exist");
    }

    private Directory moveToParent(Directory current) throws FileSystemException {
        if (current.getParent() == null) {
            return current;
        }
        return current.getParent();
    }
}