package dds.monedero.exceptions;

public class MaximoExtraccionDiarioException extends RuntimeException {
  public MaximoExtraccionDiarioException(double limiteFijo, double limiteActual) {
    super("No puede extraer mas de $ " + limiteFijo + " diarios, " + "límite: " + limiteActual);
  }
}