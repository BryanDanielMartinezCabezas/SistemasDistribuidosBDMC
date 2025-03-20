
import java.io.Serializable;



public class Diploma implements Serializable {
    String nombrecompleto;
    String carrera;
    String fecha;
    String mensaje;

    public Diploma(String nombrecompleto, String carrera, String fecha, String mensaje) {
        this.nombrecompleto = nombrecompleto;
        this.carrera = carrera;
        this.fecha = fecha;
        this.mensaje = mensaje;
    }

    public String getNombrecompleto() {
        return nombrecompleto;
    }

    public String getCarrera() {
        return carrera;
    }

    public String getFecha() {
        return fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    @Override
public String toString() {
    return "Diploma{" +
           "nombrecompleto='" + nombrecompleto + '\'' +
           ", carrera='" + carrera + '\'' +
           ", fecha='" + fecha + '\'' +
           ", mensaje='" + mensaje + '\'' +
           '}';
}

    
}
