package dds.monedero.exceptions;

public class SaldoMenorException extends RuntimeException {
  public SaldoMenorException(double extraccionMaxima) {
    super("No puede sacar mas de " + extraccionMaxima + " $");
  }
}