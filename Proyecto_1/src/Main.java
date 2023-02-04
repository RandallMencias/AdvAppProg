import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {

        //Regex that matches one or more consecutive whitespace characters
        Pattern pattern = Pattern.compile(" {2}");

        //creat array
        String[] array = new String[10];


        //add each line of the file to the array
        Path path = Paths.get(("C:\\Users\\Randall\\Documents\\GitHub\\AdvAppProg\\Proyecto_1\\src\\Libros.txt"));

        try (Stream<String> lines = Files.lines(path)) {
            array = lines.toArray(String[]::new);

        }

        Stream<String> stream = Stream.of(array);

        ArrayList<Libro> libros = new ArrayList<>();

        //create an array separating by pattern for each line
        stream.forEach(line -> {
            String[] temp = pattern.split(line);
            libros.add(new Libro(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6]));
        });
        //Imprimir lista original de libros
        libros.forEach(libro -> System.out.println(libro.getTitulo()));
    }
}