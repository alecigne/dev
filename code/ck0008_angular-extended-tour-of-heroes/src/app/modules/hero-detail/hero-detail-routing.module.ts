import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HeroDetailComponent } from '@app/modules/hero-detail';

const routes: Routes = [
  {
    path: ':id',
    component: HeroDetailComponent,
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HeroDetailRoutingModule { }
