import { Component, OnInit } from '@angular/core';
import { HeroService } from '../../core/services';
import { Hero } from '../../core/models';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoadingController } from '@ionic/angular';

@Component({
  selector: 'app-create-hero',
  templateUrl: './create-hero.page.html',
  styleUrls: ['./create-hero.page.scss'],
})
export class CreateHeroPage implements OnInit {

  private heroForm: FormGroup;

  constructor(
    private heroService: HeroService,
    private formBuilder: FormBuilder,
    private router: Router,
    private loadingController: LoadingController,
  ) { }

  ngOnInit() {
    this.buildForm();
  }

  buildForm(): void {
    this.heroForm = this.formBuilder.group({
      name: ['', Validators.required]
    });
  }

  async addHero() {
    const loading = await this.loadingController.create({
      message: 'Adding your hero...'
    });
    await loading.present();
    await this.heroService.add(this.heroForm.value as Hero)
      .subscribe(_ => {
        loading.dismiss();
        this.router.navigate(['/heroes']);
      });
  }

}
