import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CategoriasService } from './categorias.service';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    // Asigna a las rutas de las categorías
    RouterModule.forChild([]),
  ],
  providers: [CategoriasService],
  exports: [RouterModule],
})
export class CategoriasModule {}
