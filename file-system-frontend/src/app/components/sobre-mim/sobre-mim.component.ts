import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatTreeModule } from '@angular/material/tree';

@Component({
  selector: 'app-sobre-mim',
  standalone: true,  imports: [
    MatTreeModule,
    MatIconModule,
    MatButtonModule,
    MatCardModule
  ],
  templateUrl: './sobre-mim.component.html',
  styleUrls: ['./sobre-mim.component.scss']
})
export class SobreMimComponent {}
