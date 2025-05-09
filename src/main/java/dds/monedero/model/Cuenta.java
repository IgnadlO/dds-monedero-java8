package dds.monedero.model;

import dds.monedero.ColeccionMovimientos;
import dds.monedero.exceptions.MaximaCantidadDepositosException;
import dds.monedero.exceptions.MaximoExtraccionDiarioException;
import dds.monedero.exceptions.MontoNegativoException;
import dds.monedero.exceptions.SaldoMenorException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cuenta {
  private final int LIMITEDIARIO = 1000;
  private final int MAXDEPODIARIO = 3;
  private double saldo = 0;
  ColeccionMovimientos movimientos = new ColeccionMovimientos();

  public Cuenta() { }

  public Cuenta(double montoInicial) {
    saldo = montoInicial;
  }

  public void poner(double cuanto) {
    if (cuanto <= 0) {
      throw new MontoNegativoException(cuanto);
    }

    if (movimientos.cantidadDepositosTalFecha(LocalDate.now()) >= MAXDEPODIARIO) {
      throw new MaximaCantidadDepositosException(MAXDEPODIARIO);
    }

    agregarMovimiento(LocalDate.now(), cuanto, true);
  }

  public void sacar(double cuanto) {
    if (cuanto <= 0) {
      throw new MontoNegativoException(cuanto);
    }

    if (getSaldo() - cuanto < 0) {
      throw new SaldoMenorException(getSaldo());
    }

    var limite = LIMITEDIARIO - getMontoExtraidoA(LocalDate.now());
    if (cuanto > limite) {
      throw new MaximoExtraccionDiarioException(LIMITEDIARIO, limite);
    }

    saldo -= cuanto;
    agregarMovimiento(LocalDate.now(), cuanto, false);
  }

  public void agregarMovimiento(LocalDate fecha, double cuanto, boolean esDeposito) {
    movimientos.add(new Movimiento(fecha, cuanto, esDeposito));
  }

  public double getMontoExtraidoA(LocalDate fecha) {
    return movimientos.montoExtraidoTalFecha(fecha);
  }

  public double getSaldo() {
    return saldo;
  }

  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }

}
