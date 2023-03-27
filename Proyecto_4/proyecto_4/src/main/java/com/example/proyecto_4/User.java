import java.io.Serializable;

public class User implements Serializable{
    private String nombre;
    private String ip;
    private String mensaje;
    private String hora;
    public User(){
    }

    public String getHora(){
        return hora;
    }
    public void setHora(String hora){
        this.hora = hora;
    }
    public String getNombre() {
        return nombre;
        
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    @Override
    public String toString() {
        return getHora() + " " + getNombre() + ">>>" + getMensaje();//colocar hora
    }
    
}
