import { Color } from '@angular-material-components/color-picker';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoriasService } from '../../categorias.service';
import { Categoria } from '../../dto/categoria';

@Component({
  selector: 'app-edit-categoria',
  templateUrl: './edit-categoria.component.html',
  styleUrls: ['./edit-categoria.component.scss'],
})
export class EditCategoriaComponent implements OnInit {
  // Declaración variables de clase
  formCategoria: FormGroup;
  categoria: Categoria;
  categoriaId: string;
  constructor(
    private activeRoute: ActivatedRoute,
    private categoriasService: CategoriasService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {
    this.categoria = {} as Categoria;
    this.categoriaId = '';
    this.formCategoria = this.formBuilder.group({
      title: [
        '',
        Validators.compose([Validators.required, Validators.maxLength(60)]),
      ],
      description: ['', Validators.compose([Validators.maxLength(1024)])],
      color: [
        '',
        Validators.compose([
          Validators.maxLength(7),
          Validators.minLength(4),
          Validators.pattern('^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$'),
        ]),
      ],
    });
  }

  ngOnInit(): void {
    // Recoge el valor del id de la categoria activa
    this.activeRoute.params.subscribe({
      next: (params) => {
        this.categoriaId = params['id'];
        this.categoriasService.searchCategoria(this.categoriaId).subscribe({
          next: (cat: Categoria) => {
            this.categoria = cat;
            console.log(this.categoria);
          },
          error: (error) => console.log(error),
        });
      },
      error: (error) => console.log('Rellena correctamente todos los campos'),
    });
  }

  editCategoria() {
    this.formCategoria.value.color =
      this.formCategoria.value.color.toHexString();
    this.categoriasService
      .editCategoria(this.categoriaId, this.formCategoria.value)
      .subscribe({
        next: (catCorrect: Categoria) => this.router.navigate(['/categoria']),
        error: (error) => console.log(error),
      });
  }

  hexStringToColor(hexColor: string): Color {
    const rgbParts = hexColor
      .substring(1)
      .match(/.{2}/g)
      ?.map((part) => parseInt(part, 16)) as Array<number>;
    return new Color(rgbParts[0], rgbParts[1], rgbParts[2]);
  }
}
