import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { TareasService } from '../../tareas.service';
import { MatCalendarCellClassFunction } from '@angular/material/datepicker';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { Categoria } from 'src/app/categorias/dto/categoria';
import { CategoriasService } from 'src/app/categorias/categorias.service';
import { toPublicName } from '@angular/compiler/src/i18n/serializers/xmb';
import { Tarea } from '../../dto/tarea';
import { DateTime } from 'luxon';

@Component({
  selector: 'app-new-tarea',
  templateUrl: './new-tarea.component.html',
  styleUrls: ['./new-tarea.component.scss'],
})
export class NewTareaComponent implements OnInit {
  tarea: FormGroup;
  categorias: Array<Categoria>;

  constructor(
    private tareasService: TareasService,
    private formBuilder: FormBuilder,
    private router: Router,
    private categoriasService: CategoriasService
  ) {
    this.categorias = [];
    this.tarea = this.formBuilder.group({
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

  newTarea() {
    this.tarea.value.date = (this.tarea.value.date as DateTime).toISO({
      includeOffset: false,
      suppressMilliseconds: true,
    });
    this.tareasService.newTarea(this.tarea.value).subscribe({
      next: (tarea: Tarea) => this.router.navigate(['/tarea']),
      error: (error) => console.log(error),
    });
  }
}
