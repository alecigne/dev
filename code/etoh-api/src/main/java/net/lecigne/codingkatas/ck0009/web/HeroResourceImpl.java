package net.lecigne.codingkatas.ck0009.web;

import net.lecigne.codingkatas.ck0009.domain.Hero;
import net.lecigne.codingkatas.ck0009.exceptions.ServiceException;
import net.lecigne.codingkatas.ck0009.service.HeroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class HeroResourceImpl implements HeroResource {

	HeroServiceImpl heroService;

	@Autowired
	public HeroResourceImpl(HeroServiceImpl heroService) {
		this.heroService = heroService;
	}

	@PostMapping("/heroes")
	public Hero save(@RequestBody Hero hero) {
		Hero result = null;
		try {
			result = heroService.save(hero);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@GetMapping("/heroes/{id}")
	public Hero getOne(@PathVariable Long id) {
		return heroService.getOne(id);
	}
	
    @GetMapping("/heroes")
    public List<Hero> getAll() {
        return heroService.getAll();
    }
    
    @PutMapping("/heroes/")
    public void update(@RequestBody Hero hero) {
    	heroService.update(hero);
    }
    
    @DeleteMapping("/heroes/{id}")
    public void delete(@PathVariable Long id) {
    	heroService.delete(id);
    }
	
}
