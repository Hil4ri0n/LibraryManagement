import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-edit-category',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './edit-category.component.html',
  styleUrls: ['./edit-category.component.css'],
})
export class EditCategoryComponent implements OnInit {
  categoryId: string = '';
  categoryName: string = '';
  categoryLocation: string = '';

  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    public router: Router
  ) {}

  ngOnInit(): void {

    this.categoryId = this.route.snapshot.paramMap.get('id') || '';


    this.http
      .get<any>(`http://localhost:8080/api/libraries/${this.categoryId}`)
      .subscribe({
        next: (data) => {
          console.log('Fetched category:', data);
          this.categoryName = data.name;
          this.categoryLocation = data.location;
        },
        error: (error) => console.error('Error fetching category:', error),
      });
  }


  onSubmit(): void {
    const updatedCategory = {
      name: this.categoryName,
      location: this.categoryLocation,
    };

    this.http
      .put(`http://localhost:8080/api/libraries/${this.categoryId}`, updatedCategory)
      .subscribe({
        next: () => {
          console.log('Category updated successfully');
          this.router.navigate(['/categories']);
        },
        error: (error) => console.error('Error updating category:', error),
      });
  }
}
