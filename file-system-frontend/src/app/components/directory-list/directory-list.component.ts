import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DirectoryService } from '../../services/directory.service';
import { MatListModule } from '@angular/material/list';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-directory-list',
  standalone: true,
  imports: [CommonModule, MatListModule, MatCardModule],
  templateUrl: './directory-list.component.html',
  styleUrls: ['./directory-list.component.scss']
})
export class DirectoryListComponent implements OnInit {
  directories: any[] = [];

  constructor(private directoryService: DirectoryService) {}

  ngOnInit(): void {
    this.directoryService.getDirectories().subscribe((data) => {
      this.directories = data;
    });
  }
}
