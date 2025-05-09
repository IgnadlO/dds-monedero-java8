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

  private double saldo = 0;
  ColeccionMovimientos movimientos = new ColeccionMovimientos();

  public Cuenta() {
    saldo = 0;
  }

  public Cuenta(double montoInicial) {
    saldo = montoInicial;
  }

  public void poner(double cuanto) {
    if (cuanto <= 0) {
      throw new MontoNegativoException(cuanto);
    }

    if (movimientos.cantidadDepositosTalFecha(LocalDate.now()) >= 3) {
      throw new MaximaCantidadDepositosException(3);
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

    var montoExtraidoHoy = getMontoExtraidoA(LocalDate.now());
    var limite = 1000 - montoExtraidoHoy;
    if (cuanto > limite) {
      throw new MaximoExtraccionDiarioException(1000, limite);
    }

    saldo -= cuanto;
    agregarMovimiento(LocalDate.now(), cuanto, false);
  }

  public void agregarMovimiento(LocalDate fecha, double cuanto, boolean esDeposito) {
    var movimiento = new Movimiento(fecha, cuanto, esDeposito);
    movimientos.add(movimiento);
  }

  public double getMontoExtraidoA(LocalDate fecha) {
    return movimientos.montoExtraidoTalFecha(fecha);
  }

  public ColeccionMovimientos getMovimientos() {
    return movimientos;
  }

  public void setMovimientos(ColeccionMovimientos movimientos) {
    this.movimientos = movimientos;
  }

  public double getSaldo() {
    return saldo;
  }

  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }

}
