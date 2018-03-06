import { Observable } from 'rxjs/Observable';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { Quotation } from '../model/quotation';

@Injectable()
export class DataService {

  private newQuotation = new Subject<Quotation>();
  private historicQuotation = new Subject<Quotation>();

  // Methods to communicate new quotation
  sendNewQuotation(message: Quotation) {
      this.newQuotation.next(message);
  }

  clearNewQuotation() {
      this.newQuotation.next();
  }

  getNewQuotation(): Observable<Quotation> {
      return this.newQuotation.asObservable();
  }

  // Methods to communicate a quotation selection from historic table
  sendHistoricQuotation(message: Quotation) {
    this.historicQuotation.next(message);
  }

  clearHistoricQuotation() {
      this.historicQuotation.next();
  }

  getHistoricQuotation(): Observable<Quotation> {
      return this.historicQuotation.asObservable();
  }

}
