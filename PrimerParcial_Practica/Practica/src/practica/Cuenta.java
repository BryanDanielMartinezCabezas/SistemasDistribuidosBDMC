
package practica;

import java.io.Serializable;
public class Cuenta implements Serializable{
    Banco banco;
    String ci;
    String nombres;
    String apellidos;
    double saldo;
    String nrocuenta;

    public Cuenta(Banco banco, String ci, String nombres, String apellidos, double saldo, String nrocuenta) {
        this.banco = banco;
        this.ci = ci;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.saldo = saldo;
        this.nrocuenta = nrocuenta;
    }
    

    public Banco getBanco() {
        return banco;   
    }

    @Override
    public String toString() {
        return "Cuenta{" + "banco=" + banco + ", ci=" + ci + ", nombres=" + nombres + ", apellidos=" + apellidos + ", saldo=" + saldo + ", nrocuenta=" + nrocuenta + '}';
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNrocuenta() {
        return nrocuenta;
    }

    public void setNrocuenta(String nrocuenta) {
        this.nrocuenta = nrocuenta;
    }
    
    
}
