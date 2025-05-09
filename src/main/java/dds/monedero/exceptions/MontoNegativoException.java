package dds.monedero.exceptions;

public class MontoNegativoException extends RuntimeException {
  public MontoNegativoException(Double cantidad) {
    super(cantidad + ": el monto a ingresar debe ser un valor positivo");
  }
}