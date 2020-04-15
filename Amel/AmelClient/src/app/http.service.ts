import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Data} from './Data';

@Injectable()
export class HttpService {
  http: HttpClient;
  url = "http://localhost:3000";
  constructor(http: HttpClient) {
    this.http = http;
  }
  getData(){
    return this.http.get<Data[]>(this.url+'/getAll');
  } 
  //getDatabyTopic(topic: String){
  //  return this.http.get<Data[]>(this.url+'/getTopic',topic);
  //}
}