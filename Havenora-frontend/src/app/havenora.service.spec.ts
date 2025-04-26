import { TestBed } from '@angular/core/testing';

import { IstayService } from './havenora.service';

describe('IstayService', () => {
  let service: IstayService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IstayService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
