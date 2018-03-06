import { HttpErrorResponse } from '@angular/common/http';
import { Injectable, ErrorHandler, Injector } from '@angular/core';
import { ModalService } from './service/modal.service';

@Injectable()
export class UIErrorHandler extends ErrorHandler {
  constructor(private injector: Injector) {
    super();
  }
  handleError(error) {
    super.handleError(error);
    if (error instanceof HttpErrorResponse) {
      if ( error.error.message === undefined ) {
        this.modalService.openModal(`${error.statusText}`);
      } else {
        this.modalService.openModal(`${error.error.message}`);
      }
    } else {
      this.modalService.openModal('An Unexpected fail ocurred. Please try again later.');
    }
  }

  public get modalService(): ModalService {
    return this.injector.get(ModalService);
  }
}
