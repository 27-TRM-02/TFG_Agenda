import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoriasService } from '../../categorias.service';
import { Categoria } from '../../dto/categoria';

@Component({
  selector: 'app-search-categoria',
  templateUrl: './search-categoria.component.html',
  styleUrls: ['./search-categoria.component.scss'],
})
export class SearchCategoriaComponent implements OnInit {
  categoria: Categoria;
  categoriaId: string;
  constructor(
    private activeRoute: ActivatedRoute,
    private router: Router,
    private categoriasService: CategoriasService
  ) {
    this.categoria = {} as Categoria;
    this.categoriaId = '';
  }

  ngOnInit(): void {
    // Recoge el valor del id de la tarea activa
    this.activeRoute.params.subscribe({
      next: (params) => {
        this.categoriaId = params['id'];
        this.categoriasService.searchCategoria(this.categoriaId).subscribe({
          next: (catNueva: Categoria) => {
            this.categoria = catNueva;
            console.log(this.categoria);
          },
          error: (error) => console.log(error),
        });
      },
      error: (error) => console.log(error),
    });
  }
}
