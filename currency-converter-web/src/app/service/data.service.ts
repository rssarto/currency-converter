import { Observable } from 'rxjs/Observable';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { Quotation } from '../model/quotation';

@Injectable()
export class DataService {

  private subject = new Subject<Quotation>();

  sendMessage(message: Quotation) {
      this.subject.next(message);
  }

  clearMessage() {
      this.subject.next();
  }

  getMessage(): Observable<Quotation> {
      return this.subject.asObservable();
  }

}
