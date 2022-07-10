package net.lecigne.currency.business;

import java.time.LocalDate;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lecigne.currency.config.Configuration;
import net.lecigne.currency.domain.DailyExchangeRate;
import net.lecigne.currency.repository.EcbRepository;

@RequiredArgsConstructor
@Slf4j
public class EcbBusiness {

  private final Configuration.Business businessConfig;
  private final EcbRepository repository;

  public void synchronize() throws Exception {
    repository.getNextFromDate().ifPresentOrElse(
        this::doSync,
        () -> doSync(LocalDate.parse(businessConfig.getFullSyncFrom()))
    );
  }

  private void doSync(LocalDate localDate) {
    try {
      Set<DailyExchangeRate> ratesFrom = repository.getRatesFrom(localDate);
      repository.insertRates(ratesFrom);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
