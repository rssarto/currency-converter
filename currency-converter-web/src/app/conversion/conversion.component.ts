import { Currency } from './../model/currency';
import { Component, OnInit } from '@angular/core';
import { CurrencyService } from '../service/currency.service';

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

  constructor(private currencyService: CurrencyService) { }

  ngOnInit() {
    this.currencyService.list().subscribe(
      data => {
        this.currencyList = <Currency[]> data;
      }
    );
  }

  onConversion() {
    console.log('conversion');
  }

}
