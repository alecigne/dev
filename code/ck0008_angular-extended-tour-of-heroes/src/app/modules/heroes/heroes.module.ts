import { NgModule } from '@angular/core';
import { HeroesRoutingModule } from './heroes-routing.module';
import { HeroesComponent } from './heroes.component';
import { SharedModule } from '@app/shared/shared.module';

@NgModule({
  imports: [
    SharedModule,
    HeroesRoutingModule
  ],
  declarations: [HeroesComponent]
})
export class HeroesModule { }
