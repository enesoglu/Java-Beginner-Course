package edu.epam.fop.lambdas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.DoubleFunction;

public interface PercentageFormatter {

  DoubleFunction<String> INSTANCE = value -> BigDecimal.valueOf(value*100)
          .setScale(1, RoundingMode.HALF_UP)
          .stripTrailingZeros()
          .toPlainString() + " %";
}
