import { Component, OnInit } from '@angular/core';
import { Tarea } from '../../dto/tarea';
import { TareasService } from '../../tareas.service';
import { MatDialog } from '@angular/material/dialog';
import { DeleteTareaComponent } from '../delete-tarea/delete-tarea.component';
import { Router } from '@angular/router';
import { CategoriasService } from 'src/app/categorias/categorias.service';
import { Categoria } from 'src/app/categorias/dto/categoria';

@Component({
  selector: 'app-list-tareas',
  templateUrl: './list-tareas.component.html',
  styleUrls: ['./list-tareas.component.scss'],
})
export class ListTareasComponent implements OnInit {
  tareas: Array<Tarea>;
  categorias: Array<Categoria>;
  constructor(
    private tareasService: TareasService,
    public deleteDialog: MatDialog,
    private router: Router,
    private categoriasService: CategoriasService
  ) {
    this.tareas = [];
    this.categorias = [];
  }

  ngOnInit(): void {
    // LLamamos al servicio de listar tareas del usuario
    this.tareasService.listaTareas().subscribe({
      next: (tareas: Array<Tarea>) => (this.tareas = tareas),
      error: (error) => {
        // Ha habido algún error
        console.log(error);
      },
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

  openSearchByCategory(id: string) {
    this.router.navigate(['/tarea/category', id]);
  }
}
