package net.lecigne.codingkatas.ck0009.repository;

import static org.assertj.core.api.Assertions.assertThat;

import net.lecigne.codingkatas.ck0009.domain.Hero;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class HeroRepositoryTest {

	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private HeroRepository heroRepository;
	
    @Test
	public void whenFindByName_thenReturnEmployee() {
	    // given
	    Hero hero = new Hero("Dusty");
	    entityManager.persist(hero);
	    entityManager.flush();
	 
	    // when
	    Hero foundHero = heroRepository.findByName(hero.getName());
	 
	    // then
	    assertThat(foundHero.getName()).isEqualTo(hero.getName());
	}

}
