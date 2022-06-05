import { NgModule, OnInit} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SideBareComponent } from './side-bare/side-bare.component';
import { UserViewComponent } from './user-view/user-view.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { CustomerTableComponent } from './customer-table/customer-table.component';
import {HttpClientModule} from "@angular/common/http";
import { ReactiveFormsModule } from '@angular/forms';
import { PageNoteFoudComponent } from './page-note-foud/page-note-foud.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SideBareComponent,
    UserViewComponent,
    LoginPageComponent,
    CustomerTableComponent,
    PageNoteFoudComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule{
}
