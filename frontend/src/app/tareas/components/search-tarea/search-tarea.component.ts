import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Tarea } from '../../dto/tarea';
import { TareasService } from '../../tareas.service';
import { DeleteTareaComponent } from '../delete-tarea/delete-tarea.component';

@Component({
  selector: 'app-search-tarea',
  templateUrl: './search-tarea.component.html',
  styleUrls: ['./search-tarea.component.scss'],
})
export class SearchTareaComponent implements OnInit {
  // Declaración variables de clase
  tarea: Tarea;
  tareaId: string;
  constructor(
    private tareasService: TareasService,
    private activeRoute: ActivatedRoute,
    public deleteDialog: MatDialog,
    private router: Router
  ) {
    this.tarea = {} as Tarea;
    this.tareaId = '';
  }

  ngOnInit(): void {
    // Recoge el valor del id de la tarea activa
    this.activeRoute.params.subscribe({
      next: (params) => {
        this.tareaId = params['id'];
        this.tareasService.searchTarea(this.tareaId).subscribe({
          next: (tarea: Tarea) => {
            this.tarea = tarea;
            console.log(this.tarea);
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
}
