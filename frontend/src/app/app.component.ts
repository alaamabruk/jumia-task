import { Component, OnInit} from '@angular/core';
import { PhoneService } from './service/phone.service';
import { Phone } from './model/phone.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent{
  title = 'angular-jumia-app-frontend';



  phones:Phone[] = [];
  stateFilter:string;
  countryFilter:string;
  columnDefs = [
    { field: 'phone'},
    { field: 'country' },
    { field: 'countryCode' },
    { field: 'validityStatus'}
];

rowData:Phone[] = [];
  gridApi: any;
  gridColumnApi: any;
  noRowsTemplate:any;
  loadingTemplate:any;

  constructor(private api: PhoneService) {
    this.getPhones();
    this.stateFilter= 'All';
    this.countryFilter='';


  }
  getPhones() {
    this.api.getPhones()
    .subscribe(resp => {
      console.log(resp);
     this.phones = [];
      for (const data of resp) {
        this.phones.push(data);
      }console.log(this.phones);
    });
  }


  onButtonPressed()
  {
    console.log("state="+this.stateFilter)
    console.log("countryFilter:",this.countryFilter)
    this.api.getPhonesWithFilter(this.stateFilter,this.countryFilter)
      .subscribe(resp => {
      console.log(resp);
      this.phones = [];
      for (const data of resp) {
        this.phones.push(data);
      }
      console.log(this.phones);
    });
  }

}
