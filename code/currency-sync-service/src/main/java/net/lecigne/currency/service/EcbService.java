package net.lecigne.currency.service;

import net.lecigne.currency.dto.EcbDataDto;

public interface EcbService {
  EcbDataDto getDailyRates();
  EcbDataDto get90DaysRates();
  EcbDataDto getFullRates();
}
