import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-element-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './element-details.component.html',
  styleUrls: ['./element-details.component.css'],
})
export class ElementDetailsComponent implements OnInit {
  categoryId: string = '';
  elementId: string = '';
  elementDetails: any = {};

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.categoryId = this.route.snapshot.paramMap.get('categoryId') || '';
    this.elementId = this.route.snapshot.paramMap.get('elementId') || '';

    this.fetchElementDetails();
  }

  fetchElementDetails(): void {
    this.http
      .get<any>(
        `http://localhost:8080/api/books/${this.elementId}`
      )
      .subscribe({
        next: (data) => {
          this.elementDetails = data;
          console.log('Element details fetched:', data);
        },
        error: (error) => {
          console.error('Error fetching element details:', error);
        },
      });
  }

  navigateToCategoryDetails(): void {
    this.router.navigate(['/category-details', this.categoryId]);
  }
}
