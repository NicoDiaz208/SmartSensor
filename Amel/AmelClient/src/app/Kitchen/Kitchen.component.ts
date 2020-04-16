import {Component, OnInit} from '@angular/core';
import {HttpService} from '../http.service';

@Component({templateUrl: 'Kitchen.component.html'})
export class KitchenComponent implements OnInit {
  public Data = [];
  httpService: HttpService;

  constructor(httpService: HttpService) {
    // wird automatisch von Angular übergeben
    this.httpService = httpService;
  }

  ngOnInit(): void {
    this.httpService.getData().subscribe(data => {
      console.log(data);
      this.Data = data;
    });
  }
}
