import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {

        //Regex that matches one or more consecutive whitespace characters
        Pattern pattern = Pattern.compile(" {2}");

        //creat array
        String[] array = new String[10];


        //add each line of the file to the array
        Path path = Paths.get(("/Users/JuanV/OneDrive/Documentos/Universidad/6 Sexto Semestre/Programacion Avanzada de apps/Tarea1/AdvAppProg/Proyecto_1/src/Libros.txt"));

        try (Stream<String> lines = Files.lines(path)) {
            array = lines.toArray(String[]::new);

        }

        Stream<String> stream = Stream.of(array);

        ArrayList<Libro> Libros = new ArrayList<>();

        //create an array separating by pattern for each line
        stream.forEach(line -> {
            String[] temp = pattern.split(line);
            Libros.add(new Libro(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6]));
            
        });
        

        //Imprimir lista original de Libros
        Libros.stream().forEach(System.out::println);

        //Lista Ordenada por titulo, con KeyWord ordenada alfabeticamente
        System.out.printf("\n\n3)Lista Ordenada por titulo, con KeyWord ordenada alfabeticamente\n\n");

        Map<String,List<Libro>> agrupadoPorTitle = Libros.stream().sorted(Comparator.comparing(Libro::getTitulo)).collect(Collectors.groupingBy(Libro::getTitulo));

        agrupadoPorTitle.forEach((titulo,lista) -> {
            System.out.printf("%n%s:%n",titulo);
            lista.stream().sorted(Comparator.comparing(Libro::getTitulo)).forEach(e -> System.out.printf("  %s, %s, %s,%s,  %s, %s%n "
                        ,e.getAutor()
                        ,e.getNoEdicion()
                        ,e.getISBN()
                        ,e.getDate()[0] +"/"+e.getDate()[1]+"/"+e.getDate()[2]
                        ,e.getPrecio()
                        ,Arrays.stream(e.getKeyWords())
                                .sorted().collect(Collectors.toList())));
            }
        ); 

        //Imprimir lista ordenada por autor con lista de palabras  claves ordenaas inversamente

        System.out.println("\n\n4) Lista ordenada por autor con lista de palabras claves ordenaas inversamente\n\n");
        Map<String, List<Libro>> groupedbyAutor =
                Libros.stream().collect(Collectors.groupingBy(Libro::getAutor));

        groupedbyAutor.forEach(
                (autor, lista) -> {
                    System.out.printf("%n%s:%n", autor );
                    lista.forEach(
                            libro -> System.out.printf("%s , %s, %s, %s, %s%n"
                                    , libro.getTitulo()
                                    , libro.getISBN()
                                    , Arrays.stream(libro.getKeyWords()).
                                            sorted(Comparator.reverseOrder()).collect(Collectors.toList())
                                    , libro.getNoEdicion()
                                    , libro.getPrecio()));
                });
                
        //Imprimir los libros agrupados por Autor. Ordenados por año de edición para cada Autor.

        System.out.println("\n\n5) Lista agrupada por autor, ordenada por año de edicion \n\n");

        Map<String,List<Libro>> agrupadoPorAutor = Libros.stream().collect(Collectors.groupingBy(Libro::getAutor));
        
        agrupadoPorAutor.forEach(
                (autor,lista)->{
                        System.out.printf("%n%s:%n", autor);
                        lista.stream().sorted(Comparator.comparing(Libro::getFecha)).forEach(e -> 
                                System.out.printf(" Fecha de ultima edicion: %s, Numero de edicion: %s, ISBN: %s, Titulo: %s, Precio: %s, Palabras Clave: %s%n"
                                 ,e.getDate()[0] +"/"+e.getDate()[1]+"/"+e.getDate()[2]
                                 ,e.getNoEdicion()
                                 ,e.getISBN()
                                 ,e.getTitulo()
                                 ,e.getPrecio()
                                 ,Arrays.stream(e.getKeyWords())
                                         .sorted().collect(Collectors.toList())));
                }
        );

        //Imprimir los libros agrupados por año de edición y Ordenados por Titulo en cada año.        
        System.out.printf("\n\n6) Lista ordenada por anio edicion y ordenados por titulo \n\n");


        Map<Integer, List<Libro>> groupedbyanio =
                Libros.stream()
                        .sorted(Comparator.comparing(Libro::getanio))
                        .collect(Collectors.groupingBy(Libro::getanio));


        groupedbyanio.forEach(
                (anio, lista) -> {
                    System.out.printf("%n%s:%n", anio );
                    lista.stream().sorted(Comparator.comparing(Libro::getTitulo)).forEach(
                            libro -> System.out.printf("%s , %s, %s, %s, %s%n"
                                    , libro.getTitulo()
                                    , libro.getISBN()
                                    , Arrays.stream(libro.getKeyWords()).
                                            sorted(Comparator.reverseOrder()).collect(Collectors.toList())
                                    , libro.getNoEdicion()
                                    , libro.getPrecio()));
                });

         //Imprimir los libros que tienen 2 ó mas palabras que inician con "P", ordenados por ISBN y la lista de palabras que inicían con "P"
        System.out.printf("\n\n7)Libros con 2 o mas palabras con P, ordenados por ISBN y palabras que inician con P\n");
        
        //Predicate<Libro> dosoMasP = e -> (e.getKeyWords() = "A");

        
        Map<String, List<Libro>> agrupadoPorISBN = Libros.stream()
                .sorted(Comparator.comparing(Libro::getISBN)).collect(Collectors.groupingBy(Libro::getISBN));
        agrupadoPorISBN.forEach((Isbn,lista) -> {
                System.out.printf("%n%s:%n", Isbn );
                lista.forEach(e -> System.out.printf("%s , %s, %s, %s, %s, %s%n"
                , e.getTitulo()
                , e.getAutor()
                , e.getDate()[0] +"/"+e.getDate()[1]+"/"+e.getDate()[2]
                , Arrays.stream(e.getKeyWords()).
                        sorted(Comparator.reverseOrder()).collect(Collectors.toList())
                , e.getNoEdicion()
                , e.getPrecio()));
        });

                

        System.out.printf("\n\n8) Libros que no empiezan por P \n\n");

        //reasignar mapa
        Map<String, List<Libro>> groupedabyAutor =
                Libros.stream()
                .filter(libro -> !Arrays.stream(libro.getPalabrasTitulo())
                        .anyMatch(palabra -> palabra.startsWith("P") || palabra.startsWith("p")))
                .collect(Collectors.groupingBy(Libro::getAutor));

        groupedabyAutor.forEach(
                (autor, lista) -> {
                    System.out.printf("%n%s:%n", autor);
                    lista.forEach(
                            libro -> System.out.printf("%s:%n %s, ISBN:%s, %s, %s%n"
                                    , Arrays.stream(libro.getKeyWords()).
                                            sorted(Comparator.reverseOrder()).collect(Collectors.toList())
                                    , libro.getTitulo()
                                    , libro.getISBN()
                                    , libro.getNoEdicion()
                                    , libro.getPrecio()));
                });
 


}}