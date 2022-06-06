import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Tarea } from '../../dto/tarea';
import { TareasService } from '../../tareas.service';
import { DeleteTareaComponent } from '../delete-tarea/delete-tarea.component';

@Component({
  selector: 'app-destacadas',
  templateUrl: './destacadas.component.html',
  styleUrls: ['./destacadas.component.scss'],
})
export class DestacadasComponent implements OnInit {
  tareas: Array<Tarea>;

  constructor(
    private tareasService: TareasService,
    public deleteDialog: MatDialog,
    private router: Router
  ) {
    this.tareas = [];
  }

  ngOnInit(): void {
    // LLamamos al servicio de tareas destacadas del usuario
    this.tareasService.findHighlighted().subscribe({
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
