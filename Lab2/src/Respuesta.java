

import java.io.Serializable;


public class Respuesta implements Serializable {
    
    boolean estado;
    String mensaje;

    @Override
    public String toString() {
        return "Respuesta{" + "estado=" + estado + '}';
    }

    public Respuesta(boolean estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
