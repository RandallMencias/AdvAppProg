//Juan Diego Venegas Barreto 00209856
//Randall Mencias 00321469
// clase: Programacion Avanzada de Apps
//Proyecto 1. Leer un archivo. Usando Lambdas y streams presentar los datos


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

        Pattern pattern = Pattern.compile(" {2}");
        String[] array = new String[10];

        Path path = Paths.get(("/Users/JuanV/OneDrive/Documentos/Universidad/6 Sexto Semestre/Programacion Avanzada de apps/Tarea1/AdvAppProg/Proyecto_1/src/Libros.txt"));

        try (Stream<String> lines = Files.lines(path)) {
            //almacena el archivo en un array
            array = lines.toArray(String[]::new);
        }

        Stream<String> stream = Stream.of(array);
        ArrayList<Libro> Libros = new ArrayList<>();
        stream.forEach(line -> {
            String[] temp = pattern.split(line);
            Libros.add(new Libro(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6]));
        });

        //Imprimir lista original de Libros
        System.out.printf("\n\n2)Lista Original de Libros\n\n");
        Libros.stream().forEach(System.out::println);

        //Lista Ordenada por titulo, con KeyWord ordenada alfabeticamente
        System.out.printf("\n\n3)Lista Ordenada por titulo, con KeyWord ordenada alfabeticamente\n\n");
        //Agrupar por titulo ordenado
        Map<String,List<Libro>> agrupadoPorTitle = Libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .collect(Collectors.groupingBy(Libro::getTitulo));
        //Imprimir por titulo, con las keywords ordenadas alfabeticamente
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

        //Imprimir lista ordenada por autor con lista de palabras  claves ordenadas inversamente

        System.out.println("\n\n4) Lista ordenada por autor con lista de palabras claves ordenaas inversamente\n\n");
        Map<String, List<Libro>> groupedbyAutor =
                Libros.stream().collect(Collectors.groupingBy(Libro::getAutor)); //Agrupa por autor

        groupedbyAutor.forEach(
                (autor, lista) -> {
                    System.out.printf("%n%s:%n", autor );
                    lista.forEach(
                            libro -> System.out.printf("%s , %s, %s, %s, %s%n"
                                    , libro.getTitulo()
                                    , libro.getISBN()
                                    , Arrays.stream(libro.getKeyWords()) // convierte el array de palabras clave en un stream
                                            .sorted(Comparator.reverseOrder()).collect(Collectors.toList()) // ordena el stream de palabras clave en orden inverso
                                    , libro.getNoEdicion()
                                    , libro.getPrecio()));
                });

        //Imprimir los libros agrupados por Autor. Ordenados por año de edición para cada Autor.
        System.out.println("\n\n5) Lista agrupada por autor, ordenada por año de edicion \n\n");
        //agrupar por autor
        Map<String,List<Libro>> agrupadoPorAutor = Libros.stream().collect(Collectors.groupingBy(Libro::getAutor));
        //imprimir agrupado por autor, ordenado por fecha de edicion
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
                                         .collect(Collectors.toList())));
                }
        );

        //Imprimir los libros agrupados por año de edición y Ordenados por Titulo en cada año.
        System.out.printf("\n\n6) Lista ordenada por anio edicion y ordenados por titulo \n\n");

        Map<Integer, List<Libro>> groupedbyanio = //agrupa por anio
                Libros.stream()
                        .sorted(Comparator.comparing(Libro::getanio))
                        .collect(Collectors.groupingBy(Libro::getanio));

        groupedbyanio.forEach( // imprime agrupado por anio
                (anio, lista) -> {
                    System.out.printf("%n%s:%n", anio );
                    lista.stream().sorted(Comparator.comparing(Libro::getTitulo)).forEach(
                            libro -> System.out.printf("%s , %s, %s, %s, %s%n"
                                    , libro.getTitulo()
                                    , libro.getISBN()
                                    , Arrays.stream(libro.getKeyWords()).collect(Collectors.toList())
                                    , libro.getNoEdicion()
                                    , libro.getPrecio()));
                });


         //Imprimir los libros que tienen 2 ó mas palabras que inician con "P", ordenados por ISBN y la lista de palabras que inicían con "P"
        System.out.printf("\n\n7)Libros con 2 o mas palabras con P, ordenados por ISBN y palabras que inician con P\n");

        //Predicate para filtrar la letras que empiecen con la p o P
        Predicate<String> reglaP = e -> (e.startsWith("P") || e.startsWith("p"));
        Predicate<Libro> predicate = e->(Arrays.stream(e.getKeyWords()).filter(reglaP).count()>=2);
        //filtrar las que no tienen 2 P, agrupar por ISBN
        Map<String, List<Libro>> agrupadoPorISBN = Libros.stream().filter(predicate).sorted(Comparator.comparing(Libro::getISBN)).collect(Collectors.groupingBy(Libro::getISBN));
        //imprimir por ISBN, solo mostrando las palabras que empiezan por p
        agrupadoPorISBN.forEach(
                (Isbn,lista) -> {
                System.out.printf("%n%s:%n", Isbn );
                lista.forEach(e -> System.out.printf("%s , %s, %s, %s, %s, %s%n"
                , e.getTitulo()
                , e.getAutor()
                , e.getDate()[0] +"/"+e.getDate()[1]+"/"+e.getDate()[2]
                , Arrays.stream(e.getKeyWords()).
                        filter(reglaP).collect(Collectors.toList())
                , e.getNoEdicion()
                , e.getPrecio()));
        });

        System.out.printf("\n\n8) Libros que no empiezan por P \n\n");
        //reasignar mapa
        Map<String, List<Libro>> groupedabyAutor =
                Libros.stream()
                        .filter(libro -> !Arrays.stream(libro.getPalabrasTitulo())
                                .anyMatch(titulo -> titulo.startsWith("P") || titulo.startsWith("p"))) // compara letras inicales titulo
                        .filter(libro -> !Arrays.stream(libro.getKeyWords())
                                .anyMatch(palabra -> palabra.startsWith("P") || palabra.startsWith("p"))) //compara letras iniciales palabras clave
                        .collect(Collectors.groupingBy(Libro::getAutor));


        groupedabyAutor.forEach(
                (autor, lista) -> {
                    System.out.printf("%n%s:%n", autor);
                    lista.forEach(
                            libro -> System.out.printf("%s:%n %s, ISBN:%s, %s, %s%n"
                                    , libro.getKeyWordsString()
                                    , libro.getTitulo()
                                    , libro.getISBN()
                                    , libro.getNoEdicion()
                                    , libro.getPrecio()));
                });
}}