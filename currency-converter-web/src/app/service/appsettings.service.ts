import { AppSettings } from './../appsettings/appSettings';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';

@Injectable()
export class AppsettingsService {

  constructor() { }

  getSettings(): Observable<AppSettings> {
    const settings = new AppSettings();
    return Observable.of(settings);
  }
}
