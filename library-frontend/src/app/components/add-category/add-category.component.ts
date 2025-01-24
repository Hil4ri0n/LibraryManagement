import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-category',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css'],
})
export class AddCategoryComponent {
  categoryName: string = '';
  categoryLocation: string = '';

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit(): void {
    const newCategory = {
      name: this.categoryName,
      location: this.categoryLocation,
    };

    console.log('Category Submitted:', newCategory);

    this.http.post('http://localhost:8080/api/libraries', newCategory).subscribe({
      next: () => {
        console.log('Category added successfully');
        this.router.navigate(['/categories']);
      },
      error: (error) => {
        console.error('Error adding category:', error);
      },
    });
  }

  navigateToCategoryList(): void {
    this.router.navigate(['/categories']);
  }
}
