import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Tarea } from '../../dto/tarea';
import { TareasService } from '../../tareas.service';
import { DeleteTareaComponent } from '../delete-tarea/delete-tarea.component';

@Component({
  selector: 'app-upcoming-tareas',
  templateUrl: './upcoming-tareas.component.html',
  styleUrls: ['./upcoming-tareas.component.scss'],
})
export class UpcomingTareasComponent implements OnInit {
  // Declaración variables de clase
  tareas: Array<Tarea>;
  constructor(
    private tareasService: TareasService,
    private router: Router,
    public deleteDialog: MatDialog
  ) {
    this.tareas = [];
  }

  ngOnInit(): void {
    // LLamamos al servicio de listar tareas próximos (3 dias) del usuario
    this.tareasService.findUpcoming().subscribe({
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
            this.router.navigate(['/tarea/upcoming']);
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

  openUpcomingDays(days: string) {
    this.router.navigate(['/tarea/upcoming', days]);
  }
}
