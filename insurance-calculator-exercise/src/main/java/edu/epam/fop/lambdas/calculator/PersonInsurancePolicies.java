package edu.epam.fop.lambdas.calculator;

import edu.epam.fop.lambdas.insurance.Accommodation;
import edu.epam.fop.lambdas.insurance.Accommodation.EmergencyStatus;
import edu.epam.fop.lambdas.insurance.Currency;
import edu.epam.fop.lambdas.insurance.Employment;
import edu.epam.fop.lambdas.insurance.Family;
import edu.epam.fop.lambdas.insurance.Injury;
import edu.epam.fop.lambdas.insurance.Person;

import java.math.BigInteger;
import java.util.Optional;
import java.util.Set;

public final class PersonInsurancePolicies {

  private PersonInsurancePolicies() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  public static InsuranceCalculator<Person> childrenDependent(int childrenCountThreshold) {
    return person -> Optional.ofNullable(person)
            .flatMap(Person::family)
            .map(Family::children)
            .map(Set::size)
            .filter(size -> size > 0)
            .map(count -> {
              int result = (count * 100) / childrenCountThreshold;
              return InsuranceCoefficient.of(Math.min(100, result));
            })
            .or(() -> Optional.of(InsuranceCoefficient.MIN));
  }

  public static InsuranceCalculator<Person> employmentDependentInsurance(BigInteger salaryThreshold,
                                                                         Set<Currency> currencies) {
    return person -> Optional.ofNullable(person)
            .filter(p -> p.employmentHistory() != null && p.employmentHistory().size() >= 4)
            .filter(p -> p.account() != null && p.account().size() > 1)
            .filter(p -> p.injuries() == null || p.injuries().isEmpty())
            .filter(p -> p.accommodations() != null && !p.accommodations().isEmpty())
            .flatMap(p -> {
              Employment lastJob = p.employmentHistory().last();
              return Optional.ofNullable(lastJob)
                      .filter(job -> job.endDate().isEmpty())
                      .flatMap(Employment::salary)
                      .filter(salary -> currencies.contains(salary.currency()))
                      .filter(salary -> salary.amount().compareTo(salaryThreshold) >= 0);
            })
            .map(s -> InsuranceCoefficient.of(50));
  }

  public static InsuranceCalculator<Person> accommodationEmergencyInsurance(Set<EmergencyStatus> statuses) {
    return person -> Optional.ofNullable(person)
            .map(Person::accommodations)
            .filter(accs -> !accs.isEmpty())
            .flatMap(accs -> {
              Accommodation smallest = accs.first();
              return smallest.emergencyStatus()
                      .filter(statuses::contains)
                      .map(status -> {
                        int ordinal = status.ordinal();
                        int length = EmergencyStatus.values().length;
                        int coeff = 100 * (length - ordinal) / length;
                        return InsuranceCoefficient.of(coeff);
                      });
            });
  }

  public static InsuranceCalculator<Person> injuryAndRentDependentInsurance(BigInteger rentThreshold) {
    return person -> Optional.ofNullable(person)
            .filter(p -> p.injuries() != null && !p.injuries().isEmpty())
            .flatMap(p -> {
              Injury lastInjury = p.injuries().last();
              boolean isCulprit = lastInjury.culprit()
                      .filter(c -> c.equals(p))
                      .isPresent();
              if (!isCulprit) return Optional.empty();

              if (p.accommodations() == null || p.accommodations().isEmpty()) return Optional.empty();
              Accommodation largestAcc = p.accommodations().last();

              return largestAcc.rent()
                      .filter(rent -> rent.currency() == Currency.GBP)
                      .map(rent -> {
                        BigInteger rawCoeff = rent.amount()
                                .multiply(BigInteger.valueOf(100))
                                .divide(rentThreshold);
                        int val = rawCoeff.intValue();
                        return InsuranceCoefficient.of(Math.min(100, val));
                      });
            });
  }
}