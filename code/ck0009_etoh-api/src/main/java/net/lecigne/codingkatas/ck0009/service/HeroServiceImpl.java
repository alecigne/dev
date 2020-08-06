package net.lecigne.codingkatas.ck0009.service;

import java.util.List;

import net.lecigne.codingkatas.ck0009.domain.Hero;
import net.lecigne.codingkatas.ck0009.exceptions.ServiceException;
import net.lecigne.codingkatas.ck0009.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeroServiceImpl implements HeroService {

	HeroRepository heroRepository;

	@Autowired
	public HeroServiceImpl(HeroRepository heroRepository) {
		this.heroRepository = heroRepository;
	}

	@Override
	public Hero save(Hero hero) throws ServiceException {
		Hero result;
		if (!heroIsValid(hero)) {
			throw new ServiceException("Your hero is invalid!");
		} else {
			result = heroRepository.save(hero);
		}
		return result;
	}

	private boolean heroIsValid(Hero hero) {
		return (hero != null && hero.getName() != null
				&& !(hero.getName().trim().isEmpty()));
	}

	@Override
	public Hero getOne(Long id) {
		return heroRepository.getOne(id);
	}

	@Override
	public List<Hero> getAll() {
		return heroRepository.findAll();
	}

	@Override
	public void update(Hero hero) {
		heroRepository.save(hero);
	}

	@Override
	public void delete(Long id) {
		heroRepository.deleteById(id);
	}

}
