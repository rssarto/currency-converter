import { Subscription } from 'rxjs/Subscription';
import { Quotation } from './../model/quotation';
import { Component, OnInit } from '@angular/core';
import { DataService } from '../service/data.service';
import { CurrencyService } from '../service/currency.service';

@Component({
  selector: 'app-historic',
  templateUrl: './historic.component.html',
  styleUrls: ['./historic.component.css']
})
export class HistoricComponent implements OnInit {
  subscription: Subscription;

  newQuotation = new Quotation();
  lastQuotations: any;

  constructor(private dataService: DataService, private currencyService: CurrencyService) {
    this.subscription = this.dataService.getNewQuotation().subscribe(
      data => {
        this.newQuotation = data;
        this.onHistoric();
      }
    );
  }

  ngOnInit() {
    this.onHistoric();
  }

  onHistoric() {
    this.currencyService.historic().subscribe(
      data => {
        this.lastQuotations = data;
      }
    );
  }

  announceHistoricQuotation(quotation: Quotation) {
    this.dataService.sendHistoricQuotation(quotation);
    console.log('announced historic quotation: ' + quotation);
  }
}
