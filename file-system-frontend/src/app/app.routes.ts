import { Routes } from '@angular/router';
import { DirectoryListComponent } from './components/directory-list/directory-list.component';

export const routes: Routes = [
  { path: '', redirectTo: '/directories', pathMatch: 'full' },
  { path: 'directories', component: DirectoryListComponent }
];
