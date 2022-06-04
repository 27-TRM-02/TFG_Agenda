import { Component, OnInit } from '@angular/core';
import { Tarea } from '../../dto/tarea';
import { TareasService } from '../../tareas.service';

@Component({
  selector: 'app-search-tarea',
  templateUrl: './search-tarea.component.html',
  styleUrls: ['./search-tarea.component.scss'],
})
export class SearchTareaComponent implements OnInit {
  constructor(private tareasService: TareasService) {}

  ngOnInit(): void {}
}
