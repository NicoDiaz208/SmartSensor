import { Component, OnInit } from '@angular/core';
import { HttpService } from './http.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'AmelClient';
  public Data = [];
  httpService: HttpService;
  constructor(httpService: HttpService) {
    // wird automatisch von ANgular übergeben
    this.httpService = httpService;
  }
  ngOnInit(): void {
    this.httpService.getData().subscribe(data=>{console.log(data);this.Data=data});
  }
}


