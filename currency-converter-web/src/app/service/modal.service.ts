import { Injectable } from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { ModalContentComponent } from '../modal-content/modal-content.component';

@Injectable()
export class ModalService {
  constructor(private modalService: BsModalService) { }

  modalRef: BsModalRef;

  openModal(message: string) {
    const initialState = {
      list: [
        message
      ],
      title: 'Message'
    };
    this.modalRef = this.modalService.show(ModalContentComponent, {initialState});
    // this.modalRef.content.closeBtnName = 'Close';
  }
}
