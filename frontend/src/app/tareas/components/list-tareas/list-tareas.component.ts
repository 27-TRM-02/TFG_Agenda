import { Component, OnInit } from '@angular/core';
import { Tarea } from '../../dto/tarea';
import { TareasService } from '../../tareas.service';
import { MatDialog } from '@angular/material/dialog';
import { DeleteTareaComponent } from '../delete-tarea/delete-tarea.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-tareas',
  templateUrl: './list-tareas.component.html',
  styleUrls: ['./list-tareas.component.scss'],
})
export class ListTareasComponent implements OnInit {
  tareas: Array<Tarea>;

  constructor(
    private tareasService: TareasService,
    public deleteDialog: MatDialog,
    private router: Router
  ) {
    this.tareas = [];
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
