import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoriasService } from '../../categorias.service';
import { Categoria } from '../../dto/categoria';
import { DeleteCategoriaComponent } from '../delete-categoria/delete-categoria.component';

@Component({
  selector: 'app-search-categoria',
  templateUrl: './search-categoria.component.html',
  styleUrls: ['./search-categoria.component.scss'],
})
export class SearchCategoriaComponent implements OnInit {
  categoria: Categoria;
  categoriaId: string;
  constructor(
    private activeRoute: ActivatedRoute,
    private router: Router,
    private categoriasService: CategoriasService,
    public deleteDialog: MatDialog
  ) {
    this.categoria = {} as Categoria;
    this.categoriaId = '';
  }

  ngOnInit(): void {
    // Recoge el valor del id de la tarea activa
    this.activeRoute.params.subscribe({
      next: (params) => {
        this.categoriaId = params['id'];
        this.categoriasService.searchCategoria(this.categoriaId).subscribe({
          next: (catNueva: Categoria) => {
            this.categoria = catNueva;
            console.log(this.categoria);
          },
          error: (error) => console.log(error),
        });
      },
      error: (error) => console.log(error),
    });
  }

  // funcion que abre el diálogo delete categoría
  openDeleteDialog(id: String) {
    const dialogRef = this.deleteDialog.open(DeleteCategoriaComponent);
    dialogRef.afterClosed().subscribe((result) => {
      if (result === true) {
        this.categoriasService.deleteById(id).subscribe({
          next: (catOrError: Categoria) => {
            // Se ha borrado correctamente, vuelve a list tareas
            this.router.navigate(['/categoria']);
          },
          error: (error) => {
            // Ha habido algún error
            console.log(error);
          },
        });
      }
    });
  }
}
