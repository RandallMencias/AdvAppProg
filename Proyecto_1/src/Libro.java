

public class Libro {
    //datos miembro
    private String Titulo,Autor,NoEdicion,Date,KeyWords,ISBN,Precio;;
    //constructoR
    public Libro(String ISBN, String titulo, String autor, String noEdicion, String date, String keyWords, String precio) {
        Titulo = titulo;
        Autor = autor;
        NoEdicion = noEdicion;
        Date = date;
        KeyWords = keyWords;
        this.ISBN = ISBN;
        Precio = precio;
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
    public void setKeyWords(String KeyWords) {
        this.KeyWords = KeyWords;
    }
    public void setPrecio(String Precio){
        this.Precio = Precio;
    }
    //Getters
    public String getISBN() {
        return ISBN;
    }

    public String getTitulo() {
        return Titulo;
    }

    public String getAutor() {
        return Autor;
    }

    public String getNoEdicion() {
        return NoEdicion;
    }

    public String getDate() {
        return Date;
    }

    public String getKeyWords() {
        return KeyWords;
    }

    public String getPrecio(){
        return Precio;
    }

    @Override
    public String toString(){
        return String.format("ISBN: %d\r\n Titulo del libro: %s\r\n Autor: %s\r\n Numero de Edicion: %s\r\n Ultima edicion: %s\r\n Palabras clave: %s\r\n Precio: %d\r\n",getISBN(),getTitulo(),getAutor(),getNoEdicion(),getDate(),getKeyWords(),getPrecio());
    }
}
