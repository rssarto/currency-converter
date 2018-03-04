import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Credentials } from '../model/credentials';
import { Token } from '../model/token';
import { AppsettingsService } from './appsettings.service';
import { AppSettings } from '../appsettings/appSettings';

@Injectable()
export class AuthService {

  constructor(private http: HttpClient, private appSettingsService: AppsettingsService) {
    appSettingsService.getSettings().subscribe(
      settings => {
        this.appSettings = settings;
      }
    );
  }

  appSettings: AppSettings;

  attemptAuth(credentials: Credentials) {
    return this.http.post<Token>(this.appSettings.authenticationUrl, credentials);
  }

}
