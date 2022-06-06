import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterModule, ParamMap } from '@angular/router';

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
import { MatDialogModule } from '@angular/material/dialog';
import { MatChipsModule } from '@angular/material/chips';
import { AuthGuard } from '../authentication/auth.guard';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatIconModule } from '@angular/material/icon';
import { MatSelectModule } from '@angular/material/select';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { ReactiveFormsModule } from '@angular/forms';

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
      { path: '', component: ListTareasComponent, canActivate: [AuthGuard] },
      {
        path: 'highlighted',
        component: DestacadasComponent,
        canActivate: [AuthGuard],
      },
      {
        path: 'upcoming',
        component: UpcomingTareasComponent,
        canActivate: [AuthGuard],
      },
      {
        path: 'upcoming/:days',
        component: UpcomingDaysTareasComponent,
        canActivate: [AuthGuard],
      },
      {
        path: 'edit/:id',
        component: EditarTareaComponent,
        canActivate: [AuthGuard],
      },
      { path: 'new', component: NewTareaComponent, canActivate: [AuthGuard] },
      {
        path: ':id',
        component: SearchTareaComponent,
        canActivate: [AuthGuard],
      },
    ]),
    MatCardModule,
    MatDividerModule,
    MatButtonModule,
    FlexLayoutModule,
    MatDialogModule,
    MatChipsModule,
    MatInputModule,
    MatDatepickerModule,
    MatIconModule,
    MatSelectModule,
    MatSlideToggleModule,
    ReactiveFormsModule,
  ],
  providers: [TareasService],
  exports: [
    RouterModule,
    ListTareasComponent,
    DeleteTareaComponent,
    SearchTareaComponent,
  ],
})
export class TareasModule {}
