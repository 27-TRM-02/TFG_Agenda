import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { TareasService } from './tareas.service';
import { ListTareasComponent } from './components/list-tareas/list-tareas.component';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';
import { FlexLayoutModule } from '@angular/flex-layout';
import { DestacadasComponent } from './components/destacadas/destacadas.component';
import { DeleteTareaComponent } from './components/delete-tarea/delete-tarea.component';
import { SearchTareaComponent } from './components/search-tarea/search-tarea.component';
import { EditarTareaComponent } from './components/editar-tarea/editar-tarea.component';
import { UpcomingTareasComponent } from './components/upcoming-tareas/upcoming-tareas.component';
import { UpcomingDaysTareasComponent } from './components/upcoming-days-tareas/upcoming-days-tareas.component';
import { NewTareaComponent } from './components/new-tarea/new-tarea.component';
import { CategoriasModule } from '../categorias/categorias.module';

@NgModule({
  declarations: [
    ListTareasComponent,
    DestacadasComponent,
    DeleteTareaComponent,
    SearchTareaComponent,
    EditarTareaComponent,
    UpcomingTareasComponent,
    UpcomingDaysTareasComponent,
    NewTareaComponent,
  ],
  imports: [
    CommonModule,
    // Asigna a las rutas de las tareas
    RouterModule.forChild([
      { path: '', component: ListTareasComponent },
      { path: 'highlighted', component: DestacadasComponent },
      { path: 'upcoming', component: UpcomingTareasComponent },
      { path: 'upcoming/{days}', component: UpcomingDaysTareasComponent },
      { path: 'edit/{days}', component: EditarTareaComponent },
      { path: 'new', component: NewTareaComponent },
      { path: '{id}', component: SearchTareaComponent },
    ]),
    MatCardModule,
    MatDividerModule,
    MatButtonModule,
    FlexLayoutModule,
  ],
  providers: [TareasService],
  exports: [RouterModule, ListTareasComponent],
})
export class TareasModule {}
