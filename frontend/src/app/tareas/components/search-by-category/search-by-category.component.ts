import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoriasService } from 'src/app/categorias/categorias.service';
import { Categoria } from 'src/app/categorias/dto/categoria';
import { Tarea } from '../../dto/tarea';
import { TareasService } from '../../tareas.service';
import { DeleteTareaComponent } from '../delete-tarea/delete-tarea.component';

@Component({
  selector: 'app-search-by-category',
  templateUrl: './search-by-category.component.html',
  styleUrls: ['./search-by-category.component.scss'],
})
export class SearchByCategoryComponent implements OnInit {
  // Declaración variables de clase
  tareas: Array<Tarea>;
  categorias: Array<Categoria>;
  idCategory: string;
  constructor(
    private tareasService: TareasService,
    private router: Router,
    public deleteDialog: MatDialog,
    private activeRoute: ActivatedRoute,
    private categoriasService: CategoriasService
  ) {
    this.tareas = [];
    this.idCategory = '';
    this.categorias = [];
  }

  ngOnInit(): void {
    // Busca las tareas que tengan la categoria buscada
    this.activeRoute.params.subscribe({
      next: (params) => {
        this.idCategory = params['id'];
        this.tareasService.findByCategory(this.idCategory).subscribe({
          next: (tareasOk: Array<Tarea>) => {
            this.tareas = tareasOk;
            console.log(this.tareas);
          },
          error: (error) => console.log(error),
        });
      },
      error: (error) => console.log(error),
    });

    // Recoge id del titulo de las categorias para seleccionar
    this.categoriasService.listaCategorias().subscribe({
      next: (categoriasArray: Array<Categoria>) => {
        // Se ha creado correctamente, vuelve a list tareas
        this.categorias = categoriasArray;
      },
      error: (error) => {
        // Ha habido algún error
        console.log(error);
      },
    });
  }

  openSearchByCategory(id: string) {
    this.router.navigate(['/tarea/category', id]);
  }

  // funcion que abre el diálogo delete tarea
  openDeleteDialog(id: String) {
    const dialogRef = this.deleteDialog.open(DeleteTareaComponent);
    dialogRef.afterClosed().subscribe((result) => {
      if (result === true) {
        this.tareasService.deleteById(id).subscribe({
          next: (tareaOrError: Tarea) => {
            // Se ha borrado correctamente, vuelve a list tareas
            this.router.navigate(['/tarea']);
          },
          error: (error) => {
            // Ha habido algún error
            console.log(error);
          },
        });
      }
    });
  }

  // Función que abre search tarea
  openTarea(id: String) {
    this.router.navigate([`/tarea/${id}`]);
  }
}
