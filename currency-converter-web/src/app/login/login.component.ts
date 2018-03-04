import { Fault } from './../model/fault';
import { TokenStorage } from './../service/token.storage';
import { AuthService } from './../service/auth.service';
import { Component, OnInit, TemplateRef } from '@angular/core';
import { Credentials } from '../model/credentials';
import { Router } from '@angular/router';
import { BsModalService } from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal/bs-modal-ref.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userName: string;
  password: string;
  modalRef: BsModalRef;

  constructor(private authService: AuthService,
    private token: TokenStorage,
    private router: Router,
    private modalService: BsModalService) { }

  ngOnInit() {
  }

  login(): void {
    this.authService.attemptAuth(new Credentials(this.userName, this.password)).subscribe(
      data => {
        this.token.saveToken(data.token);
        this.router.navigate(['']);
      },
      errorData => {
        this.openModal('Invalid credentials.');
      }
    );
  }

  openModal(message: string) {
    const initialState = {
      list: [
        message
      ],
      title: 'Message'
    };
    this.modalRef = this.modalService.show(ModalContentComponent, {initialState});
    this.modalRef.content.closeBtnName = 'Close';  }

}

@Component({
  selector: 'app-modal-content',
  template: `
    <div class="modal-header">
      <h4 class="modal-title pull-left">{{title}}</h4>
      <button type="button" class="close pull-right" aria-label="Close" (click)="bsModalRef.hide()">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <div class="modal-body">
      <ul *ngIf="list.length">
        <li *ngFor="let item of list">{{item}}</li>
      </ul>
    </div>
    <div class="modal-footer">
      <button type="button" class="btn btn-default" (click)="bsModalRef.hide()">{{closeBtnName}}</button>
    </div>
    `
})
export class ModalContentComponent implements OnInit {
  title: string;
  closeBtnName: string;
  list: any[] = [];

  constructor(public bsModalRef: BsModalRef) {}

  ngOnInit() {
  }
}
