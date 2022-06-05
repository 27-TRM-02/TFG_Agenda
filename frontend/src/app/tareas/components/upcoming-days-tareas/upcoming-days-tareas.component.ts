import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Tarea } from '../../dto/tarea';
import { TareasService } from '../../tareas.service';
import { DeleteTareaComponent } from '../delete-tarea/delete-tarea.component';

@Component({
  selector: 'app-upcoming-days-tareas',
  templateUrl: './upcoming-days-tareas.component.html',
  styleUrls: ['./upcoming-days-tareas.component.scss'],
})
export class UpcomingDaysTareasComponent implements OnInit {
  // Declaración variables de clase
  daysOffset: number;
  tareas: Array<Tarea>;
  constructor(
    private activeRoute: ActivatedRoute,
    private tareasService: TareasService,
    private router: Router,
    public deleteDialog: MatDialog
  ) {
    this.daysOffset = 3;
    this.tareas = [];
  }

  ngOnInit(): void {
    // Recoge el valor de los dias para cargar tareas próximas
    this.activeRoute.params.subscribe({
      next: (params) => {
        this.daysOffset = +params['days'];
        this.tareasService.findUpcomingDate(this.daysOffset).subscribe({
          next: (tareasOk: Array<Tarea>) => {
            this.tareas = tareasOk;
            console.log(this.tareas);
          },
          error: (error) => console.log(error),
        });
      },
      error: (error) => console.log(error),
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
