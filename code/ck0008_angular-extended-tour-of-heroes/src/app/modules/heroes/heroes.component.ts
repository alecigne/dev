import { Component, OnInit } from '@angular/core';
import { Hero } from '@app/core/models';
import { HeroService } from '@app/core/services';

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.scss']
})
export class HeroesComponent implements OnInit {

  heroes: Hero[];

  constructor(
    private heroService: HeroService
  ) { }

  ngOnInit() {
    this.getHeroes();
  }

  addHero(name: string): void {
    name = name.trim();
    if (!name) { return; }
    this.heroService.add({ name } as Hero)
      .subscribe(hero => {
        this.heroes.push(hero);
      });
  }

  getHeroes(): void {
    this.heroService.getAll()
      .subscribe(heroes => this.heroes = heroes);
  }

  deleteHero(hero: Hero): void {
    this.heroService.delete(hero)
      .subscribe(
        _ => this.heroes = this.heroes.filter(h => h !== hero)
      );
  }

}
