import { Component, OnInit } from '@angular/core';
import { Tarea } from '../../dto/tarea';
import { TareasService } from '../../tareas.service';

@Component({
  selector: 'app-list-tareas',
  templateUrl: './list-tareas.component.html',
  styleUrls: ['./list-tareas.component.scss'],
})
export class ListTareasComponent implements OnInit {
  tareas: Array<Tarea>;

  constructor(private tareasService: TareasService) {
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
}
