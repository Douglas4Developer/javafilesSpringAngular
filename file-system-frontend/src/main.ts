import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter } from '@angular/router';
import { AppComponent } from './app/app.component';
import { appConfig } from './app/app.config';
import { routes } from './app/app.routes';
import { provideHttpClient } from '@angular/common/http'; 
import { importProvidersFrom } from '@angular/core';
import { MatTreeModule } from '@angular/material/tree';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes),   
    provideHttpClient(),  
    importProvidersFrom(MatTreeModule, MatIconModule, MatButtonModule, MatCardModule), 
    ...appConfig.providers ,
  ]
})
  .catch((err) => console.error(err));
