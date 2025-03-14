
import java.io.Serializable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Briller
 */
public class Deuda implements Serializable {
    String CI;
    Impuesto impuesto;
    int Anio;
    double Monto;
    
    public Deuda(String ci, int anio, Impuesto impuesto, double monto) {
        this.CI = ci;
        this.Anio = anio;
        this.impuesto = impuesto;
        this.Monto = monto;
    }    

    public String getCI() {
        return CI;
    }

    public Impuesto getImpuesto() {
        return impuesto;
    }

    public int getAnio() {
        return Anio;
    }

    public double getMonto() {
        return Monto;
    }

    public void setCI(String CI) {
        this.CI = CI;
    }

    public void setImpuesto(Impuesto impuesto) {
        this.impuesto = impuesto;
    }

    public void setAnio(int Anio) {
        this.Anio = Anio;
    }

    public void setMonto(double Monto) {
        this.Monto = Monto;
    }
    
    
    



}
