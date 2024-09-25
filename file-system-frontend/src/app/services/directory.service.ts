import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DirectoryService {
  constructor(private http: HttpClient) {}

  getDirectories(): Observable<any[]> {
    return this.http.get<any[]>('http://localhost:8080/api/diretorios');
  }
}
