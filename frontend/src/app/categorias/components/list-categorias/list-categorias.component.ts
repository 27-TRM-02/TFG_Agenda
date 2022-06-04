import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { CategoriasService } from '../../categorias.service';
import { Categoria } from '../../dto/categoria';
import { DeleteCategoriaComponent } from '../delete-categoria/delete-categoria.component';

@Component({
  selector: 'app-list-categorias',
  templateUrl: './list-categorias.component.html',
  styleUrls: ['./list-categorias.component.scss'],
})
export class ListCategoriasComponent implements OnInit {
  categorias: Array<Categoria>;

  constructor(
    private categoriasService: CategoriasService,
    public deleteDialog: MatDialog,
    private router: Router
  ) {
    this.categorias = [];
  }

  ngOnInit(): void {
    // Llamamos al servicio listar categorias del usuario
    this.categoriasService.listaCategorias().subscribe({
      next: (categorias: Array<Categoria>) => (this.categorias = categorias),
      error: (error) => {
        // Ha habido algún error
        console.log(error);
      },
    });
  }

  // funcion que abre el diálogo delete tarea
  openDeleteDialog(id: String) {
    const dialogRef = this.deleteDialog.open(DeleteCategoriaComponent);
    dialogRef.afterClosed().subscribe((result) => {
      if (result === true) {
        this.categoriasService.deleteById(id).subscribe({
          next: (tareaOrError: Categoria) => {
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
