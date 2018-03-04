import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/user';
import { AppsettingsService } from './appsettings.service';
import { AppSettings } from '../appsettings/appSettings';

@Injectable()
export class UserService {

  constructor(private http: HttpClient, private appSettingsService: AppsettingsService) {
    this.appSettingsService.getSettings().subscribe(settings => { this.appSettings = settings; });
  }

  appSettings: AppSettings;

  signUp(user: User): Observable<User> {
    return this.http.post<User>(this.appSettings.signUpUrl, user);
  }

}
