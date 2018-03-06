import { DataService } from './service/data.service';
import { AppsettingsService } from './service/appsettings.service';
import { NgModule, Injectable, ErrorHandler } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HttpErrorResponse, HTTP_INTERCEPTORS } from '@angular/common/http';

import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { TooltipModule } from 'ngx-bootstrap/tooltip';
import { ModalModule } from 'ngx-bootstrap/modal';
import { CollapseModule } from 'ngx-bootstrap/collapse';
import { BsModalService } from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal/bs-modal-ref.service';

import { AppRoutingModule } from './app.routing.module';
import { AuthService } from './service/auth.service';
import { AppComponent } from './app.component';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { ModalContentComponent } from './modal-content/modal-content.component';
import { HomeComponent } from './home/home.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { TokenStorage } from './service/token.storage';
import { LogoutComponent } from './logout/logout.component';
import { UserService } from './service/user.service';
import { ModalService } from './service/modal.service';
import { UIErrorHandler } from './app.uierrorhandler';
import { PanelComponent } from './panel/panel.component';
import { ConversionComponent } from './conversion/conversion.component';
import { HistoricComponent } from './historic/historic.component';
import { CurrencyService } from './service/currency.service';
import { Interceptor } from './app.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    SignupComponent,
    HomeComponent,
    NotfoundComponent,
    ModalContentComponent,
    LoginComponent,
    LogoutComponent,
    PanelComponent,
    ConversionComponent,
    HistoricComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BsDropdownModule.forRoot(),
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    HttpClientModule,
    FormsModule
  ],
  entryComponents: [
    // Add here components that are created dynamically.
    ModalContentComponent
  ],
  providers: [AuthService, TokenStorage, AppsettingsService, BsModalService, BsModalRef, UserService, ModalService, CurrencyService,
              DataService,
             { provide: ErrorHandler, useClass: UIErrorHandler },
             { provide: HTTP_INTERCEPTORS, useClass: Interceptor, multi: true } ],
  bootstrap: [AppComponent]
})
export class AppModule { }
