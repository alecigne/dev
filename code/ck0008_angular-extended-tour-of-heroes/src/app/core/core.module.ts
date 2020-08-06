import { NgModule, Optional, SkipSelf } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { HeroService } from './services/hero.service';
import { HeaderComponent } from './layout/header/header.component';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule
  ],
  declarations: [HeaderComponent],
  providers: [
    HeroService
  ],
  exports: [
    HeaderComponent
  ]
})
export class CoreModule {

  constructor(@Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule) {
      throw new Error(
        'CoreModule is already loaded. Import it in the AppModule only');
    }
  }

}
