import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.Locale;
import java.util.stream.Stream;

public class Libro{
    //datos miembro
    private String Titulo,Autor,NoEdicion,ISBN,Precio;
    private String [] KeyWords,Date;
    //constructor
    public Libro(String ISBN, String titulo, String autor, String noEdicion, String date, String keyWords, String precio) {
        Titulo = titulo;
        Autor = autor;
        NoEdicion = noEdicion;
        Date = date.split("/");
        this.ISBN = ISBN;
        Precio = precio;
        // elimina los parentesis de las palabras claves
        KeyWords = keyWords.trim().substring(1, keyWords.length() - 1).split("[,]+");
        //elimina los espacios en blanco de las palabras claves
        for (int i = 0; i < KeyWords.length; i++) {
            KeyWords[i] = KeyWords[i].trim().toLowerCase();
        }

    }

    //setters
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }
    public void setAutor(String Autor) {
        this.Autor = Autor;
    }
    public void setNoEdicion(String NoEdicion) {
        this.NoEdicion = NoEdicion;
    }
    public void setDate(String Date) {
        this.Date = Date.split("/");
    }

    public void setPrecio(String Precio){
        this.Precio = Precio;
    }
    public void setKeyWords(String keyWords) {
        KeyWords = keyWords.split(",");
    }
    //Getters
    public String getISBN() {
        return  ISBN;
    }
    public int getanio(){
        return Integer.parseInt(Date[2]);
    }

    public String getTitulo() {
        return Titulo;
    }

    public String getAutor() {
        return Autor;
    }

    public String getNoEdicion() {
        return "No:" +NoEdicion;
    }

    public String[] getDate(){
        return Date;
    }

    public String[] getKeyWords() {
        return KeyWords;
    }
    public String getPrecio(){
        return "$"+Precio;
    }
    public int getFecha(){
        String str = Date[2]+Date[1]+Date[0];
        return Integer.parseInt(str);
    }
    public int getIntIsbn(){
        return Integer.parseInt(ISBN);
    }
    public String getKeyWordsString(){
        String temp = "";
        for (int i = 0; i < KeyWords.length -1; i++) {
            temp += KeyWords[i]+"," ;
        }
        temp = temp+ KeyWords[KeyWords.length-1];
        return temp;
    }
    public String [] getPalabrasTitulo(){
        return Titulo.split(" ");
    }
    //toString para imprimir el objeto
    @Override
    public String toString(){
        return String.format("ISBN: %s\r\n Titulo del libro: %s\r\n Autor: %s\r\n Numero de Edicion: %s\r\n Ultima edicion: %s\r\n Palabras clave: %s\r\n Precio: %s\r\n" 
            , getISBN(),getTitulo(),getAutor(),getNoEdicion(),getDate()[0] +"/"+getDate()[1]+"/"+getDate()[2],getKeyWordsString(),getPrecio());
    }
    
}
