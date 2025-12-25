package edu.epam.fop.lambdas.calculator;

import edu.epam.fop.lambdas.insurance.Accommodation;
import edu.epam.fop.lambdas.insurance.Currency;

import java.math.BigInteger;
import java.time.Period;
import java.util.Optional;

public final class AccommodationInsurancePolicies {

  private AccommodationInsurancePolicies() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  public static InsuranceCalculator<Accommodation> rentDependentInsurance(BigInteger divider) {
    return accommodation -> Optional.ofNullable(accommodation)
            .flatMap(Accommodation::rent)
            .filter(rent -> Period.ofMonths(1).equals(rent.unit()))
            .filter(rent -> Currency.USD.equals(rent.currency()))
            .filter(rent -> rent.amount().compareTo(BigInteger.ZERO) > 0)
            .map(rent -> {
              BigInteger coefficient = rent.amount()
                      .multiply(BigInteger.valueOf(100))
                      .divide(divider);
              if (coefficient.compareTo(BigInteger.valueOf(100)) >= 0) {
                return InsuranceCoefficient.MAX;
              }
              return InsuranceCoefficient.of(coefficient.intValue());
            });
  }

  public static InsuranceCalculator<Accommodation> priceAndRoomsAndAreaDependentInsurance(BigInteger priceThreshold,
                                                                                          int roomsThreshold,
                                                                                          BigInteger areaThreshold) {
    return accommodation -> {
      if (accommodation == null) {
        return Optional.of(InsuranceCoefficient.MIN);
      }
      boolean priceMet = Optional.ofNullable(accommodation.price())
              .map(p -> p.compareTo(priceThreshold) >= 0).orElse(false);
      boolean roomsMet = Optional.ofNullable(accommodation.rooms())
              .map(r -> r >= roomsThreshold).orElse(false);
      boolean areaMet = Optional.ofNullable(accommodation.area())
              .map(a -> a.compareTo(areaThreshold) >= 0).orElse(false);

      if (priceMet && roomsMet && areaMet) {
        return Optional.of(InsuranceCoefficient.MAX);
      } else {
        return Optional.of(InsuranceCoefficient.MIN);
      }
    };
  }
}