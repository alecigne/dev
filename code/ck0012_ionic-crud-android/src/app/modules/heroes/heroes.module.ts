import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HeroesPage } from './heroes.page';
import { SharedModule } from '../../shared/shared.module';

const routes: Routes = [
  {
    path: '',
    component: HeroesPage
  }
];

@NgModule({
  imports: [
    SharedModule,
    RouterModule.forChild(routes)
  ],
  declarations: [HeroesPage]
})
export class HeroesPageModule {}
