export interface CustomerPage {
  data:Customer[];
  totalePage: number;
}

export interface Customer {
  id:string;
  name:string;
  email:string;
}
