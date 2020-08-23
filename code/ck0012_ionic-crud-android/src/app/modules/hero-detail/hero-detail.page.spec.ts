import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HeroDetailPage } from './hero-detail.page';

describe('HeroDetailPage', () => {
  let component: HeroDetailPage;
  let fixture: ComponentFixture<HeroDetailPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HeroDetailPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeroDetailPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
