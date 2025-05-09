package dds.monedero.exceptions;

public class MaximaCantidadDepositosException extends RuntimeException {

  public MaximaCantidadDepositosException(double cantidadMaxima) {
    super("Ya excedio los " + cantidadMaxima + " depositos diarios");
  }

}