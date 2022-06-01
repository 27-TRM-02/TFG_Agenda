import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { NewTarea } from './dto/new-tarea';
import { Tarea } from './dto/tarea';

@Injectable({
  providedIn: 'root',
})
export class TareasService {
  constructor(private httpClient: HttpClient, private router: Router) {}

  // Método que lista todas las tareas del usuario activo
  public listaTareas(): Observable<Array<Tarea>> {
    return this.httpClient.get<Array<Tarea>>(`${environment.apiUrl}/tarea`);
  }

  // Método para crear una nueva tarea
  public newTarea(newTarea: NewTarea): Observable<Tarea> {
    return this.httpClient.post<Tarea>(
      `${environment.apiUrl}/tarea/new`,
      newTarea
    );
  }

  // Método para buscar por id
  public searchTarea(id: String): Observable<Tarea> {
    return this.httpClient.get<Tarea>(`${environment.apiUrl}/tarea/${id}`);
  }

  // Método para editar tarea por su id
  public editTarea(id: String, tareaActualizada: NewTarea): Observable<Tarea> {
    return this.httpClient.patch<Tarea>(
      `${environment.apiUrl}/tarea/edit/${id}`,
      tareaActualizada
    );
  }

  // Método listar tareas destacadas
  public findHighlighted(): Observable<Array<Tarea>> {
    return this.httpClient.get<Array<Tarea>>(
      `${environment.apiUrl}/tarea/highlighted`
    );
  }

  // Método listar tareas cercanas a la fecha actual
  public findUpcomingDate(days: number): Observable<Array<Tarea>> {
    return this.httpClient.get<Array<Tarea>>(
      `${environment.apiUrl}/tarea/upcoming/${days}`
    );
  }

  // Método para eliminar tarea por su id
  public deleteById(id: String): Observable<Tarea> {
    return this.httpClient.delete<Tarea>(
      `${environment.apiUrl}/tarea/delete/${id}`
    );
  }

  // Método para buscar tareas por su categoria
  public findByCategory(id: String): Observable<Array<Tarea>> {
    return this.httpClient.get<Array<Tarea>>(
      `${environment.apiUrl}/tarea/category/${id}`
    );
  }
}
