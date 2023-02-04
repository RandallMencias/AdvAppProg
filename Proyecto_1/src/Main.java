import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
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
        Path path = Paths.get(("/Users/randall/Documents/Programacion 4/AdvAppProg/Proyecto_1/src/Libros.txt"));

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
        //libros.stream().forEach(System.out::println);


        //Imprimir lista ordenada por autor con lista de palabras  claves ordenaas inversamente

//        System.out.println("%n4) Lista ordenada por autor con lista de palabras claves ordenaas inversamente%n");
//        Map<String, List<Libro>> groupedbyAutor =
//                libros.stream().collect(Collectors.groupingBy(Libro::getAutor));
//
//
//        //
//        groupedbyAutor.forEach(
//                (autor, lista) -> {
//                    System.out.printf("%n%s:%n", autor );
//                    lista.forEach(
//                            libro -> System.out.printf("%s , %s, %s, %s, %s%n"
//                                    , libro.getTitulo()
//                                    , libro.getISBN()
//                                    , Arrays.stream(libro.getKeyWords()).
//                                            sorted(Comparator.reverseOrder()).collect(Collectors.toList())
//                                    , libro.getNoEdicion()
//                                    , libro.getPrecio()));
//                });

//        System.out.printf("%n6) Lista ordenada por anio edicion y ordenados por titulo %n");
//
//
//        Map<Integer, List<Libro>> groupedbyanio =
//                libros.stream()
//                        .sorted(Comparator.comparing(Libro::getanio))
//                        .collect(Collectors.groupingBy(Libro::getanio));
//
//
//        groupedbyanio.forEach(
//                (anio, lista) -> {
//                    System.out.printf("%n%s:%n", anio );
//                    lista.forEach(
//                            libro -> System.out.printf("%s , %s, %s, %s, %s%n"
//                                    , libro.getTitulo()
//                                    , libro.getISBN()
//                                    , Arrays.stream(libro.getKeyWords()).
//                                            sorted(Comparator.reverseOrder()).collect(Collectors.toList())
//                                    , libro.getNoEdicion()
//                                    , libro.getPrecio()));
//                });



        //











}}