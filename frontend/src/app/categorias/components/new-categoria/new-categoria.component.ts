import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CategoriasService } from '../../categorias.service';
import { Categoria } from '../../dto/categoria';

@Component({
  selector: 'app-new-categoria',
  templateUrl: './new-categoria.component.html',
  styleUrls: ['./new-categoria.component.scss'],
})
export class NewCategoriaComponent implements OnInit {
  categoria: FormGroup;
  constructor(
    private categoriasService: CategoriasService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {
    this.categoria = this.formBuilder.group({
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

  ngOnInit(): void {}

  newCategoria() {
    this.categoria.value.color = this.categoria.value.color.toHexString();
    this.categoriasService.newCategoria(this.categoria.value).subscribe({
      next: (newCategoria: Categoria) => this.router.navigate(['/categoria']),
      error: (error) => console.log(error),
    });
  }
}
