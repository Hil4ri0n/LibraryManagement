import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-category-details',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './category-details.component.html',
  styleUrls: ['./category-details.component.css'],
})
export class CategoryDetailsComponent implements OnInit {
  categoryId: string = '';
  categoryName: string = '';
  categoryLocation: string = '';
  elements: any[] = [];

  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.categoryId = this.route.snapshot.paramMap.get('id') || '';


    this.http
      .get<any>(`http://localhost:8080/api/libraries/${this.categoryId}`)
      .subscribe({
        next: (data) => {
          this.categoryName = data.name;
          this.categoryLocation = data.location;
        },
        error: (error) => console.error('Error fetching category:', error),
      });


    this.http
      .get<any[]>(`http://localhost:8080/api/books/library/${this.categoryId}`)
      .subscribe({
        next: (data) => (this.elements = data),
        error: (error) => console.error('Error fetching elements:', error),
      });
  }

  deleteElement(elementId: string): void {
    this.http
      .delete(`http://localhost:8080/api/books/${elementId}`)
      .subscribe(() => {
        console.log('Element deleted');
        this.elements = this.elements.filter((el) => el.id !== elementId);
      });
  }

  navigateToCategoryList(): void {
    this.router.navigate(['/categories']);
  }
}
