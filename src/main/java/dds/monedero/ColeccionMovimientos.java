package dds.monedero;

import dds.monedero.model.Movimiento;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ColeccionMovimientos {
  private List<Movimiento> movimientos = new ArrayList<>();

  public long cantidadDepositosTalFecha(LocalDate fecha) {
    return movimientos.stream()
        .filter(movimiento -> movimiento.fueDepositadoTalFecha(fecha))
        .count();
  }

  public void add (Movimiento movimiento) {
    movimientos.add(movimiento);
  }

  public double montoExtraidoTalFecha(LocalDate fecha) {
    return movimientos.stream()
        .filter(movimiento -> movimiento.fueExtraidoTalFecha(fecha))
        .mapToDouble(Movimiento::getMonto)
        .sum();
  }
}
