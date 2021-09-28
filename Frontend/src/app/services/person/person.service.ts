import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PersonService {
  private API_SERVER = "http://localhost:8080/person/"
  constructor(private httpClient: HttpClient) { }

  public getAllPerson(){
    return this.httpClient.get(this.API_SERVER);
  }

  public savePerson(person:any): Observable<any>{
    return this.httpClient.post(this.API_SERVER,person,{observe : 'response'});
  }

  public deletePerson(id:any): Observable<any>{
    return this.httpClient.delete(this.API_SERVER + "delete/"+id,{observe : 'response'});
  }
}
