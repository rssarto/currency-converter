import { Fault } from './../model/fault';
import { TokenStorage } from './../service/token.storage';
import { AuthService } from './../service/auth.service';
import { Component, OnInit, TemplateRef } from '@angular/core';
import { Credentials } from '../model/credentials';
import { Router } from '@angular/router';
import { BsModalService } from 'ngx-bootstrap/modal';
import { ModalService } from '../service/modal.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userName: string;
  password: string;

  constructor(private authService: AuthService,
    private token: TokenStorage,
    private router: Router,
    private appModalService: ModalService) { }

  ngOnInit() {
  }

  login(): void {
    this.authService.attemptAuth(new Credentials(this.userName, this.password)).subscribe(
      data => {
        this.token.saveToken(data.token);
        this.router.navigate(['panel']);
      },
      errorData => {
        this.appModalService.openModal('Invalid credentials.');
      }
    );
  }

  /*
  openModal(message: string) {
    const initialState = {
      list: [
        message
      ],
      title: 'Message'
    };
    this.modalRef = this.modalService.show(ModalContentComponent, {initialState});
    this.modalRef.content.closeBtnName = 'Close';
  }
  */
}
