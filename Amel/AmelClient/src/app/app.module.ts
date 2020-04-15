import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { HttpService } from './http.service';
import { BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MatButtonModule} from '@angular/material/button';
import { KitchenComponent} from './Kitchen';
import { LivingroomComponent } from './Livingroom';
import { CellarComponent } from './Cellar';
import { GarageComponent } from './Garage';

@NgModule({
  declarations: [
    AppComponent,
    KitchenComponent,
    LivingroomComponent,
    CellarComponent,
    GarageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    AppRoutingModule
  ],
  providers: [HttpService],
  bootstrap: [AppComponent]
})
export class AppModule { }
