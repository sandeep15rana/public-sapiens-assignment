import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NewsService {
  private apiUrl = 'http://localhost:8080/api/news';

  constructor(private http: HttpClient) {}

  // Fetch news from the backend
  searchNews(keyword: string, interval: number, unit: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/search`, {
      params: { keyword, interval: interval.toString(), unit }
    });
  }

  // Toggle offline mode
  toggleOfflineMode(mode: boolean): Observable<any> {
    return this.http.post(`${this.apiUrl}/toggle-offline`, null, {
      params: { mode: mode.toString() }
    });
  }
}
