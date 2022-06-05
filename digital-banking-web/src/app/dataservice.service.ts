import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataserviceService {

  isDarkEnable:boolean = false;

  darkmode = new BehaviorSubject<boolean>(this.isDarkEnable);

  constructor() {
  }

  changeTheme() {
    this.darkmode.next(!this.darkmode.value);
  }
}
