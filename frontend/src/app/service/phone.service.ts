import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse , HttpParams} from "@angular/common/http";
import { Observable } from 'rxjs';
import { Phone } from '../model/phone.model';


@Injectable({
  providedIn: 'root'
})
export class PhoneService {

  url = 'http://localhost:8080/internal/v1/phones';

  constructor(private http:HttpClient) { }



  getPhones() : Observable<any>  {
    return this.http.get<Phone[]>(this.url);
  }


  getPhonesWithFilter(state:string, country:string) : Observable<any>  {

    let params = new HttpParams({
          fromObject : {
            valid: state
          }
        });
            if (country!=undefined && country!=null && country!="" && country.trim.length==0)
                  params = params.append('country', country);
        console.log(params.toString());
        return this.http.get<Phone[]>(this.url,{params});
    }
}


