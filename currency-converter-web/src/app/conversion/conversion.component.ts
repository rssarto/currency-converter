import { Currency } from './../model/currency';
import { Component, OnInit } from '@angular/core';
import { CurrencyService } from '../service/currency.service';
import { Quotation } from '../model/quotation';
import { HistoricComponent } from '../historic/historic.component';
import { DataService } from '../service/data.service';

@Component({
  selector: 'app-conversion',
  templateUrl: './conversion.component.html',
  styleUrls: ['./conversion.component.css']
})
export class ConversionComponent implements OnInit {

  currencyList: Currency[];
  currency: Currency;
  sourceCurrency: string;
  destinationCurrency: string;
  amount: number;
  result: number;

  constructor(private currencyService: CurrencyService, private dataService: DataService) { }

  ngOnInit() {
    this.currencyService.list().subscribe(
      data => {
        this.currencyList = <Currency[]> data;
      }
    );
  }

  onConversion() {
    // Create quotation to ask for conversion
    const quotation = new Quotation();
    quotation.amount = this.amount;
    quotation.source = this.sourceCurrency;
    quotation.destination = this.destinationCurrency;

    this.currencyService.quote(quotation).subscribe(
      data => {
        const newQuotation = <Quotation>data;
        this.result = newQuotation.result;
        this.announceNewQuotation(newQuotation);
      }
    );
  }

  announceNewQuotation(quotation: Quotation) {
    this.dataService.sendMessage(quotation);
    console.log('announced new quotation: ' + quotation);
  }

}
