package net.lecigne.codingkatas.ck0009.service;

import java.util.List;

import net.lecigne.codingkatas.ck0009.domain.Hero;
import net.lecigne.codingkatas.ck0009.exceptions.ServiceException;

public interface HeroService {

	Hero save(Hero hero) throws ServiceException;

	Hero getOne(Long id);

	List<Hero> getAll();
	
	void update(Hero hero);

	void delete(Long id);

}
