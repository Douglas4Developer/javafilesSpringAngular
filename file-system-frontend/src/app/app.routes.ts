import { Routes } from '@angular/router';
import { DirectoryListComponent } from './components/directory-list/directory-list.component';
import { SobreMimComponent } from './components/sobre-mim/sobre-mim.component';


export const routes: Routes = [
  { path: '', redirectTo: '/directories', pathMatch: 'full' },
  { path: 'sobre-mim', component: SobreMimComponent },

  { path: 'directories', component: DirectoryListComponent }
];
