import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateHeroPage } from './create-hero.page';

describe('CreateHeroPage', () => {
  let component: CreateHeroPage;
  let fixture: ComponentFixture<CreateHeroPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateHeroPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateHeroPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
