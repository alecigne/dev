import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: 'heroes', loadChildren: './modules/heroes/heroes.module#HeroesPageModule' },
  { path: 'detail/:id', loadChildren: './modules/hero-detail/hero-detail.module#HeroDetailPageModule' },
  { path: 'create', loadChildren: './modules/create-hero/create-hero.module#CreateHeroPageModule' },
  { path: 'settings', loadChildren: './modules/settings/settings.module#SettingsPageModule' },
  { path: '', redirectTo: '/heroes', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
