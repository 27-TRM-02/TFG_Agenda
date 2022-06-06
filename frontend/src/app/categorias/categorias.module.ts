import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CategoriasService } from './categorias.service';
import { ListCategoriasComponent } from './components/list-categorias/list-categorias.component';
import { NewCategoriaComponent } from './components/new-categoria/new-categoria.component';
import { DeleteCategoriaComponent } from './components/delete-categoria/delete-categoria.component';
import { SearchCategoriaComponent } from './components/search-categoria/search-categoria.component';
import { EditCategoriaComponent } from './components/edit-categoria/edit-categoria.component';
import { AuthGuard } from '../authentication/auth.guard';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatDialogModule } from '@angular/material/dialog';
import { MatChipsModule } from '@angular/material/chips';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatSelectModule } from '@angular/material/select';
import { ReactiveFormsModule } from '@angular/forms';
import {
  MAT_COLOR_FORMATS,
  NgxMatColorPickerModule,
  NGX_MAT_COLOR_FORMATS,
} from '@angular-material-components/color-picker';

@NgModule({
  declarations: [
    ListCategoriasComponent,
    NewCategoriaComponent,
    DeleteCategoriaComponent,
    SearchCategoriaComponent,
    EditCategoriaComponent,
  ],
  imports: [
    CommonModule,
    // Asigna a las rutas de las categorías
    RouterModule.forChild([
      {
        path: '',
        component: ListCategoriasComponent,
        canActivate: [AuthGuard],
      },
      {
        path: 'new',
        component: NewCategoriaComponent,
        canActivate: [AuthGuard],
      },
      {
        path: ':id',
        component: SearchCategoriaComponent,
        canActivate: [AuthGuard],
      },
      {
        path: 'edit/:id',
        component: EditCategoriaComponent,
        canActivate: [AuthGuard],
      },
    ]),
    MatCardModule,
    MatDividerModule,
    MatButtonModule,
    FlexLayoutModule,
    MatDialogModule,
    MatChipsModule,
    MatInputModule,
    MatIconModule,
    MatSelectModule,
    ReactiveFormsModule,
    NgxMatColorPickerModule,
  ],
  providers: [
    CategoriasService,
    { provide: MAT_COLOR_FORMATS, useValue: NGX_MAT_COLOR_FORMATS },
  ],
  exports: [RouterModule, DeleteCategoriaComponent, SearchCategoriaComponent],
})
export class CategoriasModule {}
