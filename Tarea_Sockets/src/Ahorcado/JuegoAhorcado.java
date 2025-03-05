package Ahorcado;

import java.util.HashSet;
import java.util.Set;

// JuegoAhorcado - Logica del juego
public class JuegoAhorcado {
    private String palabraSecreta;
    private char[] palabraAdivinada;
    private Set<Character> letrasUsadas;
    private int errores;
    private final int MAX_ERRORES = 7;
    
    public JuegoAhorcado(String palabraSecreta) {
        this.palabraSecreta = palabraSecreta.toUpperCase();
        this.palabraAdivinada = new char[palabraSecreta.length()];
        for (int i = 0; i < palabraAdivinada.length; i++) {
            if (Character.isLetter(palabraSecreta.charAt(i))) {
                palabraAdivinada[i] = '_';
            } else {
                palabraAdivinada[i] = palabraSecreta.charAt(i);
            }
        }
        this.letrasUsadas = new HashSet<>();
        this.errores = 0;
    }
    
    // jugarLetra - Procesa la letra ingresada y actualiza el estado del juego
    public synchronized String jugarLetra(char letra) {
        letra = Character.toUpperCase(letra);
        if (letrasUsadas.contains(letra)) {
            return "Letra ya usada. Intenta otra.\n" + getEstado();
        }
        letrasUsadas.add(letra);
        
        boolean acierto = false;
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                palabraAdivinada[i] = letra;
                acierto = true;
            }
        }
        if (!acierto) {
            errores++;
        }
        
        return getEstado();
    }
    
    // getEstado - Devuelve el estado actual del juego
    public synchronized String getEstado() {
        StringBuilder sb = new StringBuilder();
        sb.append("Palabra: ");
        for (char c : palabraAdivinada) {
            sb.append(c).append(" ");
        }
        sb.append("\nErrores: ").append(errores).append(" de ").append(MAX_ERRORES);
        sb.append("\nLetras usadas: ").append(letrasUsadas);
        return sb.toString();
    }
    
    // juegoTerminado - Indica si el juego ha finalizado
    public synchronized boolean juegoTerminado() {
        return errores >= MAX_ERRORES || esGanado();
    }
    
    // esGanado - Verifica si se han adivinado todas las letras
    public synchronized boolean esGanado() {
        for (char c : palabraAdivinada) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }
}
