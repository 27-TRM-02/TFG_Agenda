import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './authentication/auth.guard';
import { AuthenticationModule } from './authentication/authentication.module';
import { HomeComponent } from './components/home/home.component';
import { TareasModule } from './tareas/tareas.module';

// rutas base de la aplicación
const routes: Routes = [
  { path: '', component: HomeComponent, canActivate: [AuthGuard] },
  {
    path: 'auth',
    loadChildren: () => AuthenticationModule,
  },
  {
    path: 'task',
    loadChildren: () => TareasModule,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
