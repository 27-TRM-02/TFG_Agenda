import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatCalendarCellClassFunction } from '@angular/material/datepicker';
import { ActivatedRoute, Router } from '@angular/router';
import { DateTime } from 'luxon';
import { CategoriasService } from 'src/app/categorias/categorias.service';
import { Categoria } from 'src/app/categorias/dto/categoria';
import { Tarea } from '../../dto/tarea';
import { TareasService } from '../../tareas.service';

@Component({
  selector: 'app-editar-tarea',
  templateUrl: './editar-tarea.component.html',
  styleUrls: ['./editar-tarea.component.scss'],
})
export class EditarTareaComponent implements OnInit {
  // Declaración variables de clase
  tarea: Tarea;
  tareaId: string;
  formTarea: FormGroup;
  categorias: Array<Categoria>;
  // Declaración y funcionalidad del datePicker
  constructor(
    private activeRoute: ActivatedRoute,
    private tareasService: TareasService,
    private router: Router,
    private categoriasService: CategoriasService,
    private formBuilder: FormBuilder
  ) {
    this.categorias = [];
    this.tarea = {} as Tarea;
    this.tareaId = '';
    this.formTarea = this.formBuilder.group({
      title: [
        '',
        Validators.compose([Validators.required, Validators.maxLength(60)]),
      ],
      date: [
        '',
        Validators.compose([Validators.maxLength(40), Validators.required]),
      ],
      description: ['', Validators.compose([Validators.maxLength(1024)])],
      highlighted: [false, Validators.compose([Validators.required])],
      categories: [[]],
    });
  }

  ngOnInit(): void {
    // Recoge el valor del id de la tarea activa
    this.activeRoute.params.subscribe({
      next: (params) => {
        this.tareaId = params['id'];
        this.tareasService.searchTarea(this.tareaId).subscribe({
          next: (tarea: Tarea) => {
            this.tarea = tarea;
            this.formTarea.setValue({
              title: this.tarea.title,
              date: this.tarea.date,
              description: this.tarea.description,
              highlighted: tarea.highlighted,
              categories: this.getTareaCategoriesIds(tarea.categories),
            });
            console.log(this.tarea);
          },
          error: (error) => console.log(error),
        });
      },
      error: (error) => console.log('Rellena correctamente todos los campos'),
    });
    // Recoge titulo de las categorias para seleccionar
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

  editTarea() {
    this.formTarea.value.date = (this.formTarea.value.date as DateTime).toISO({
      includeOffset: false,
      suppressMilliseconds: true,
    });
    this.tareasService.editTarea(this.tareaId, this.formTarea.value).subscribe({
      next: (tareaCorrecta: Tarea) => this.router.navigate(['/tarea']),
      error: (error) => console.log(error),
    });
  }

  getTareaCategoriesIds(categories: Array<Categoria>): Array<string> {
    return categories.map((c) => c.id);
  }
}
