import { Component } from '@angular/core';
import { NewsService } from '../service/news.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent {
  keyword: string = '';
  interval: number = 12;
  unit: string = 'hours';
  results: any = null;
  offlineMode: boolean = false;

  constructor(private newsService: NewsService) {}

  // Fetch news from the backend
  search() {
    this.newsService.searchNews(this.keyword, this.interval, this.unit).subscribe((data) => {
      this.results = data;
    });
  }

  // Toggle offline mode
  toggleOffline() {
    this.offlineMode = !this.offlineMode;
    this.newsService.toggleOfflineMode(this.offlineMode).subscribe();
  }
}
