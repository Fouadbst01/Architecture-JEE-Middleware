import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerTableComponent } from './customer-table/customer-table.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { PageNoteFoudComponent } from './page-note-foud/page-note-foud.component';
import { UserViewComponent } from './user-view/user-view.component';

const routes: Routes = [
{ path: "", component: LandingPageComponent},
{ path: "account", component: UserViewComponent },
{ path: "login", component:LoginPageComponent},
{ path: "customer", component:CustomerTableComponent},
{path: "**" , component:PageNoteFoudComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
