import { Component, OnInit } from '@angular/core';
import { Hero } from '../../core/models';
import { HeroService } from '../../core/services';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { LoadingController } from '@ionic/angular';

@Component({
  selector: 'app-hero-detail',
  templateUrl: './hero-detail.page.html',
  styleUrls: ['./hero-detail.page.scss'],
})
export class HeroDetailPage implements OnInit {

  hero: Hero;

  constructor(
    private heroService: HeroService,
    private location: Location,
    private route: ActivatedRoute,
    private loadingController: LoadingController,
  ) { }

  ngOnInit() {
    this.getHero();
  }

  async getHero() {
    const loading = await this.loadingController.create({
      message: 'Loading'
    });
    await loading.present();
    const id = +this.route.snapshot.paramMap.get('id');
    await this.heroService.getOne(id).subscribe(h => {
      this.hero = h;
      loading.dismiss();
    });
  }

  goBack(): void {
    this.location.back();
  }

  saveHero(): void {
    this.heroService.update(this.hero)
      .subscribe(() => this.goBack());
  }

  // async delete(id) {
  //   const loading = await this.loadingController.create({
  //     content: 'Deleting'
  //   });
  //   await loading.present();
  //   await this.api.deleteClassroom(id)
  //     .subscribe(res => {
  //       loading.dismiss();
  //       this.location.back();
  //     }, err => {
  //       console.log(err);
  //       loading.dismiss();
  //     });
  // }

}
