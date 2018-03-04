import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { TokenStorage } from '../service/token.storage';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  ngOnInit() {
  }

  constructor(private authService: AuthService, private router: Router, private tokenStorage: TokenStorage) {
    setTimeout(() => {
      this.logout();
    }, 3000);
  }

  logout() {
    this.tokenStorage.signOut();
    this.router.navigate(['']);
  }
}
