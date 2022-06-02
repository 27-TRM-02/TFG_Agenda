import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CategoriasService } from './categorias.service';
import { ListCategoriasComponent } from './components/list-categorias/list-categorias.component';
import { NewCategoriaComponent } from './components/new-categoria/new-categoria.component';
import { DeleteCategoriaComponent } from './components/delete-categoria/delete-categoria.component';
import { SearchCategoriaComponent } from './components/search-categoria/search-categoria.component';
import { EditCategoriaComponent } from './components/edit-categoria/edit-categoria.component';

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
      { path: '', component: ListCategoriasComponent },
      { path: 'new', component: NewCategoriaComponent },
      { path: '{id}', component: SearchCategoriaComponent },
      { path: 'edit/{id}', component: EditCategoriaComponent },
    ]),
  ],
  providers: [CategoriasService],
  exports: [RouterModule],
})
export class CategoriasModule {}
