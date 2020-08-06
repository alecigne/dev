package net.lecigne.codingkatas.ck0009.repository;

import net.lecigne.codingkatas.ck0009.domain.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {

	Hero findByName(String name);
	
}
