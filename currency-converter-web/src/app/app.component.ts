import { Component } from '@angular/core';
import { AuthService } from './service/auth.service';
import { Router } from '@angular/router';
import { TokenStorage } from './service/token.storage';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  tokenStorage: TokenStorage;

  constructor(tokenStorage: TokenStorage) {
    this.tokenStorage = tokenStorage;
  }
}
