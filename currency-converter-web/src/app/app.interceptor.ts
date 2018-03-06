import { TokenStorage } from './service/token.storage';
import { Injectable } from '@angular/core';
import {HttpInterceptor, HttpRequest, HttpHandler, HttpSentEvent, HttpHeaderResponse, HttpProgressEvent,
  HttpResponse, HttpUserEvent, HttpErrorResponse, HttpEvent, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';
import 'rxjs/add/operator/do';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class Interceptor implements HttpInterceptor {

  constructor(private token: TokenStorage, private router: Router) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log('intercepting request');
    let authReq = req;
    if (this.token.getToken() != null) {
      console.log('inserting token');
      authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + this .token.getToken())});
      // authorization header bearer token not found springboot
      console.log(authReq);
    }
    console.log('calling');
    return next.handle(authReq).do(
      (err: any) => {
        if (err instanceof HttpErrorResponse) {
          console.log('retorno');
          console.log(err);
          if (err.status === 401) {
            this.router.navigate(['user']);
          }
        }
      }
    );
  }
}
