import { NgModule } from '@angular/core';
import { HeroDetailRoutingModule } from './hero-detail-routing.module';
import { HeroDetailComponent } from './hero-detail.component';
import { SharedModule } from '@app/shared/shared.module';

@NgModule({
  imports: [
    SharedModule,
    HeroDetailRoutingModule
  ],
  declarations: [HeroDetailComponent]
})
export class HeroDetailModule { }
