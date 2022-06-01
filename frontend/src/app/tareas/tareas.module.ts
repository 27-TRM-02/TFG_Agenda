import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { TareasService } from './tareas.service';
import { ListTareasComponent } from './components/list-tareas/list-tareas.component';
import { MatCardModule } from '@angular/material/card';

@NgModule({
  declarations: [ListTareasComponent],
  imports: [
    CommonModule,
    // Asigna a las rutas de las tareas
    RouterModule.forChild([]),
    MatCardModule,
  ],
  providers: [TareasService],
  exports: [RouterModule],
})
export class TareasModule {}
