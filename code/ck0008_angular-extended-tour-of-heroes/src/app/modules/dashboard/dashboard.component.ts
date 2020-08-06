import { Component, OnInit } from '@angular/core';
import { Hero } from '@app/core/models';
import { HeroService } from '@app/core/services';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  heroes: Hero[];

  constructor(
    private heroService: HeroService
  ) { }

  ngOnInit() {
    this.getHeroes();
  }

  getHeroes(): void {
    this.heroService.getAll()
      .subscribe(heroes => this.heroes = heroes.slice(0, 4));
  }

}
