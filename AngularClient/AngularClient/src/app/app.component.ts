import { Component, OnInit } from '@angular/core';
import * as mqttt from 'mqtt'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})



export class AppComponent implements OnInit {
  ngOnInit(): void {
    this.client.subscribe('topic')
    this.client.on('message', (topic,message)=>{
      console.log(message.toString())
      this.client.publish('topic','Hello mqtt')
      this.client.end;
    })

    
  }
  title = 'AngularClient';
  client = mqttt.connect('http://localhost:8080',{
    username : 'admin',
    password : 'hivemq'
  });

  
  
  constructor(){
    
  }
}
