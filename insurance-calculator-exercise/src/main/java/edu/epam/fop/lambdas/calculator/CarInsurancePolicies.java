package edu.epam.fop.lambdas.calculator;

import edu.epam.fop.lambdas.insurance.Car;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public final class CarInsurancePolicies {

  private CarInsurancePolicies() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  public static InsuranceCalculator<Car> ageDependInsurance(LocalDate baseDate) {
    return car -> Optional.ofNullable(car)
            .map(Car::manufactureDate)
            .map(mDate -> {
              Period age = Period.between(mDate, baseDate);
              int years = age.getYears();

              if (years <= 1) {
                  return InsuranceCoefficient.MAX;
              } else if (years <= 5) {
                  return InsuranceCoefficient.of(70);
              } else if (years <= 10) {
                  return InsuranceCoefficient.of(30);
              } else {
                  return InsuranceCoefficient.MIN;
              }
            });
  }

  public static InsuranceCalculator<Car> priceAndOwningOfFreshCarInsurance(LocalDate baseDate,
                                                                           BigInteger priceThreshold,
                                                                           Period owningThreshold) {
    return car -> Optional.ofNullable(car)
            .filter(c -> c.soldDate().isEmpty())
            .filter(c -> c.price() != null && c.price().compareTo(priceThreshold) >= 0)
            .filter(c -> {
                LocalDate purchaseDate = c.purchaseDate();
                if (purchaseDate == null) return false;
                LocalDate limitDate = baseDate.minus(owningThreshold);
                return !purchaseDate.isBefore(limitDate);
            })
            .map(c -> {
                  BigInteger price = c.price();
                  BigInteger twiceThreshold = priceThreshold.multiply(BigInteger.valueOf(2));
                  BigInteger thriceThreshold = priceThreshold.multiply(BigInteger.valueOf(3));

                  if (price.compareTo(thriceThreshold) >= 0) {
                      return InsuranceCoefficient.MAX;
                  } else if (price.compareTo(twiceThreshold) >= 0) {
                      return InsuranceCoefficient.of(50);
                  } else {
                      return InsuranceCoefficient.MIN;
              }
            });
  }
}