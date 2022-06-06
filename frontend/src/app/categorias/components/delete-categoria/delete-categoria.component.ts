import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CategoriasService } from '../../categorias.service';
import { Categoria } from '../../dto/categoria';

@Component({
  selector: 'app-delete-categoria',
  templateUrl: './delete-categoria.component.html',
  styleUrls: ['./delete-categoria.component.scss'],
})
export class DeleteCategoriaComponent implements OnInit {
  constructor(
    private categoriasServive: CategoriasService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  deleteCategory(id: String) {
    this.categoriasServive.deleteById(id).subscribe({
      next: (tareaOrError: Categoria) => {
        // Se ha borrado correctamente, vuelve a list tareas
        this.router.navigate(['/categoria']);
      },
      error: (error) => {
        // Ha habido algún error
        console.log(error);
      },
    });
  }
}
