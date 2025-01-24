import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-edit-element',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './edit-element.component.html',
  styleUrls: ['./edit-element.component.css'],
})
export class EditElementComponent implements OnInit {
  elementId: string = '';
  categoryId: string = '';
  elementTitle: string = '';
  elementAuthor: string = '';

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.categoryId = this.route.snapshot.paramMap.get('categoryId') || '';
    this.elementId = this.route.snapshot.paramMap.get('elementId') || '';

    this.http
      .get<any>(`http://localhost:8080/api/books/${this.elementId}`)
      .subscribe({
        next: (data) => {
          console.log('Element details fetched:', data);
          this.elementTitle = data.title;
          this.elementAuthor = data.author;
        },
        error: (error) => console.error('Error fetching element details:', error),
      });
  }

  onSubmit(): void {
    const updatedElement = {
      title: this.elementTitle,
      author: this.elementAuthor,
    };

    this.http
      .put(`http://localhost:8080/api/books/${this.elementId}`, updatedElement)
      .subscribe({
        next: () => {
          console.log('Element updated successfully');
          this.navigateToCategoryDetails();
        },
        error: (error) => console.error('Error updating element:', error),
      });
  }

  navigateToCategoryDetails(): void {
    this.router.navigate(['/category-details', this.categoryId]);
  }
}
