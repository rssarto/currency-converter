import { BsModalRef } from 'ngx-bootstrap/modal/bs-modal-ref.service';
import { Component, OnInit } from '@angular/core';
import { BsModalService } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-modal-content',
  templateUrl: 'modal-content.component.html'
})
export class ModalContentComponent implements OnInit {
  title: string;
  closeBtnName = 'Close';
  list: any[] = [];

  constructor(public bsModalRef: BsModalRef, private modalService: BsModalService) {}

  ngOnInit() {
  }

  openModal(message: string) {
    const initialState = {
      list: [
        message
      ],
      title: 'Message'
    };
    this.bsModalRef = this.modalService.show(ModalContentComponent, {initialState});
  }
}
