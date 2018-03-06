import { Quotation } from './../model/quotation';
import { Observable } from 'rxjs/Observable';
import { Currency } from './../model/currency';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppsettingsService } from './appsettings.service';
import { AppSettings } from '../appsettings/appSettings';

@Injectable()
export class CurrencyService {

  constructor(private http: HttpClient, private appSettingsService: AppsettingsService) {
    this.appSettingsService.getSettings().subscribe(settings => { this.appSettings = settings; });
   }

  appSettings: AppSettings;

  list(): Observable<Currency[]> {
    return this.http.get<Currency[]>(this.appSettings.currencyListUrl);
  }

  quote(quotation: Quotation) {
    return this.http.post<Quotation>(this.appSettings.quotationUrl, quotation);
  }

  historic() {
    return this.http.get<Quotation[]>(this.appSettings.historicUrl);
  }
}
