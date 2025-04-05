
package examen_practico_sis258;
import java.io.Serializable;

public class Deuda implements Serializable {
    String CI;
    int Anio;
    Impuesto impuesto;
    double Monto;

    public Deuda(String CI, int Anio, Impuesto impuesto, double Monto) {
        this.CI = CI;
        this.Anio = Anio;
        this.impuesto = impuesto;
        this.Monto = Monto;
    }

    public String getCI() {
        return CI;
    }

    public void setCI(String CI) {
        this.CI = CI;
    }

    public int getAnio() {
        return Anio;
    }

    public void setAnio(int Anio) {
        this.Anio = Anio;
    }

    public Impuesto getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Impuesto impuesto) {
        this.impuesto = impuesto;
    }

    public double getMonto() {
        return Monto;
    }

    public void setMonto(double Monto) {
        this.Monto = Monto;
    }
    
    
    
    
    
    
    
    
    
}
