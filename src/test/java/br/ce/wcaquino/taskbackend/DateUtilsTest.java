package br.ce.wcaquino.taskbackend;

import br.ce.wcaquino.taskbackend.utils.DateUtils;
import java.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {

  @Test
  public void shouldReturnTrueForFutureDates() {
    LocalDate date = LocalDate.of(2030, 01, 01);
    Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
  }

  @Test
  public void shouldReturnFalsePastDates() {
    LocalDate date = LocalDate.of(2010, 01, 01);
    Assert.assertFalse(DateUtils.isEqualOrFutureDate(date));
  }

  @Test
  public void shouldReturnTrueForCurrentDates() {
    LocalDate date = LocalDate.now();
    Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
  }
}
