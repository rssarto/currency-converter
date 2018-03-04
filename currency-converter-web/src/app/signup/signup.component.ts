import { Fault } from './../model/fault';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../model/user';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  user = new User();

  constructor(private router: Router, private userService: UserService) {
  }

  ngOnInit() {
  }

  onSignUp() {
    this.userService.signUp(this.user).subscribe(
      data => {
        const returnedUser = <User>data;
        this.user = new User();
        this.router.navigate(['login']);
      }
    );
  }
}
