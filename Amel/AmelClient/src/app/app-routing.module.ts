import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { KitchenComponent} from './Kitchen';
import { LivingroomComponent } from './Livingroom';
import { CellarComponent } from './Cellar';
import { GarageComponent } from './Garage';

const routes: Routes = [
  { path: '', component: KitchenComponent} ,
  { path: 'Livingroom', component: LivingroomComponent},
  { path: 'Cellar', component: CellarComponent},
  { path: 'Garage', component: GarageComponent},


  { path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
