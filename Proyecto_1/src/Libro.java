

public class Libro {
    //datos miembro
    private String Titulo,Autor,NoEdicion,Date,ISBN,Precio;
    private String [] KeyWords;
    //constructoR
    public Libro(String ISBN, String titulo, String autor, String noEdicion, String date, String keyWords, String precio) {
        Titulo = titulo;
        Autor = autor;
        NoEdicion = noEdicion;
        Date = date;
        this.ISBN = ISBN;
        Precio = precio;
        KeyWords = keyWords.split("[\\(||\\)||,]");

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
        this.Date = Date;
    }
    public void setPrecio(String Precio){
        this.Precio = Precio;
    }
    public void setKeyWords(String keyWords) {
        KeyWords = keyWords.split(",");
    }
    //Getters
    public String getISBN() {
        return "ISBN: "+ISBN;
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

    public String getDate() {
        return Date;
    }

    public String[] getKeyWords() {
        return KeyWords;
    }
    public String getPrecio(){
        return "$"+Precio;
    }

    public String getKeyWordsString(){
        String temp = "";
        for (String word : KeyWords) {
            temp += word+"," ;
        }
        return temp;
        //new StringBuffer(temp).deleteCharAt(temp.length()-1).toString();
    }

    @Override
    public String toString(){
        return String.format("ISBN: %s\r\n Titulo del libro: %s\r\n Autor: %s\r\n Numero de Edicion: %s\r\n Ultima edicion: %s\r\n Palabras clave: %s\r\n Precio: %s\r\n" , getISBN(),getTitulo(),getAutor(),getNoEdicion(),getDate(),getKeyWordsString(),getPrecio());
    }
}
