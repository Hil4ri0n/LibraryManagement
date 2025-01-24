import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';

@Component({
  selector: 'app-category-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css'],
})
export class CategoryListComponent implements OnInit {
  categories: any[] = [];

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.loadCategories();
  }


  loadCategories(): void {
    this.http.get<any[]>('http://localhost:8080/api/libraries').subscribe({
      next: (data) => {
        console.log('Categories loaded:', data);
        this.categories = data;
      },
      error: (error) => {
        console.error('Error fetching categories:', error);
      },
    });
  }


  deleteCategory(id: string): void {
    this.http.delete(`http://localhost:8080/api/libraries/${id}`).subscribe({
      next: () => {
        console.log('Category deleted');
        this.loadCategories(); // Reload categories after deletion
      },
      error: (error) => console.error('Error deleting category:', error),
    });
  }


  navigateToAddCategory(): void {
    this.router.navigate(['/add-category']);
  }


  navigateToEditCategory(id: string): void {
    this.router.navigate([`/edit-category`, id]);
  }
}
