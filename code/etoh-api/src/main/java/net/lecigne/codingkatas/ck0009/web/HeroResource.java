package net.lecigne.codingkatas.ck0009.web;

import java.util.List;

import net.lecigne.codingkatas.ck0009.domain.Hero;

public interface HeroResource {

	Hero save(Hero hero);
	
	Hero getOne(Long id);
	
    List<Hero> getAll();
    
    void update(Hero hero);
    
    void delete(Long id);
	
}
