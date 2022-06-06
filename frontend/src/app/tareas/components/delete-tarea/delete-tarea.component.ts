import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Tarea } from '../../dto/tarea';
import { TareasService } from '../../tareas.service';

@Component({
  selector: 'app-delete-tarea',
  templateUrl: './delete-tarea.component.html',
  styleUrls: ['./delete-tarea.component.scss'],
})
export class DeleteTareaComponent implements OnInit {
  constructor(private tareasService: TareasService, private router: Router) {}

  ngOnInit(): void {}

  deleteTask(id: String) {
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
}
