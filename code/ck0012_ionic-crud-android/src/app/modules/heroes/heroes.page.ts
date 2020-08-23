import { Component, OnInit } from '@angular/core';
import { Hero } from '../../core/models';
import { HeroService } from '../../core/services';
import { LoadingController } from '@ionic/angular';
import { Router } from '@angular/router';

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.page.html',
  styleUrls: ['./heroes.page.scss'],
})
export class HeroesPage implements OnInit {

  heroes: Hero[];

  constructor(
    private heroService: HeroService,
    private loadingController: LoadingController,
    private router: Router) { }

  ngOnInit() {
  }

  // TODO: Not performant but OK for our demo - the CREATE page should send only the new element to the model
  ionViewWillEnter() {
    this.getHeroes();
  }

  async getHeroes() {
    const loading = await this.loadingController.create({
      message: 'Loading'
    });
    await loading.present();
    await this.heroService.getAll()
      .subscribe(
        heroes => {
          this.heroes = heroes;
          loading.dismiss();
        },
        err => {
          console.log(err);
          loading.dismiss();
        });
  }

  async deleteHero(hero: Hero) {
    const loading = await this.loadingController.create({
      message: 'Deleting...'
    });
    await loading.present();
    await this.heroService.delete(hero)
      .subscribe(_ => {
        this.heroes = this.heroes.filter(h => h !== hero);
        loading.dismiss();
      });
  }

  showDetail(id) {
    this.router.navigate(['/detail', JSON.stringify(id)]);
  }

}
