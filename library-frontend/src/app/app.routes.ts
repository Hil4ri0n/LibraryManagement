import { Routes } from '@angular/router';
import { CategoryListComponent } from './components/category-list/category-list.component';
import { AddCategoryComponent } from './components/add-category/add-category.component';
import { EditCategoryComponent } from './components/edit-category/edit-category.component';
import { CategoryDetailsComponent } from './components/category-details/category-details.component';
import { AddElementComponent } from './components/add-element/add-element.component';
import { EditElementComponent } from './components/edit-element/edit-element.component';
import { ElementDetailsComponent } from './components/element-details/element-details.component';
export const routes: Routes = [
  { path: '', redirectTo: '/categories', pathMatch: 'full' },
  { path: 'categories', component: CategoryListComponent },
  { path: 'add-category', component: AddCategoryComponent },
  { path: 'edit-category/:id', component: EditCategoryComponent },
  { path: 'category-details/:id', component: CategoryDetailsComponent },
  { path: 'add-element/:id', component: AddElementComponent },
  { path: 'edit-element/:categoryId/:elementId', component: EditElementComponent },
  { path: 'element-details/:categoryId/:elementId', component: ElementDetailsComponent },
];
