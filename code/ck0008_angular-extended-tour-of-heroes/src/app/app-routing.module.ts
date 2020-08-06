import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: 'dashboard',
    loadChildren: './modules/dashboard/dashboard.module#DashboardModule'
  },
  {
    path: 'heroes',
    loadChildren: './modules/heroes/heroes.module#HeroesModule'
  },
  {
    path: 'detail',
    loadChildren: './modules/hero-detail/hero-detail.module#HeroDetailModule'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
