import { Component, OnInit } from '@angular/core';
import { Tarea } from '../../dto/tarea';
import { TareasService } from '../../tareas.service';

@Component({
  selector: 'app-destacadas',
  templateUrl: './destacadas.component.html',
  styleUrls: ['./destacadas.component.scss'],
})
export class DestacadasComponent implements OnInit {
  tareas: Array<Tarea>;

  constructor(private tareasService: TareasService) {
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
}
