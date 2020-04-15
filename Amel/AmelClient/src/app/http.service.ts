import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Data} from './Data';

@Injectable()
export class HttpService {
  http: HttpClient;
  url = 'http://localhost:3000';

  constructor(http: HttpClient) {
    this.http = http;
  }

  getData() {
    return this.http.get<Data[]>(this.url + '/getAll');
  }

  getDatabyTopic(topic: string) {
    const params = new HttpParams().set('topic', topic);
    return this.http.get<Data[]>(this.url + '/getTopic', {params});
  }
}
