import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-add-element',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './add-element.component.html',
  styleUrls: ['./add-element.component.css'],
})
export class AddElementComponent implements OnInit {
  elementTitle: string = '';
  elementAuthor: string = '';
  categoryId: string = '';


  constructor(
    private route: ActivatedRoute,
    public router: Router,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.categoryId = this.route.snapshot.paramMap.get('id') || '';
  }

  onSubmit(): void {
    const newElement = {
      title: this.elementTitle,
      author: this.elementAuthor,
    };

    this.http
      .post(`http://localhost:8080/api/books/${this.categoryId}`, newElement)
      .subscribe({
        next: () => {
          console.log('New element added successfully');
          this.router.navigate(['/category-details', this.categoryId]);
        },
        error: (error) => console.error('Error adding element:', error),
      });
  }
}
