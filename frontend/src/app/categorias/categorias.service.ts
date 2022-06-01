import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Categoria } from './dto/categoria';
import { NewCategoria } from './dto/new-categoria';

@Injectable({
  providedIn: 'root',
})
export class CategoriasService {
  constructor(private httpClient: HttpClient, private router: Router) {}

  // Método que lista todas las categorias
  public listaCategorias(): Observable<Array<Categoria>> {
    return this.httpClient.get<Array<Categoria>>(
      `${environment.apiUrl}/categoria`
    );
  }

  // Método para crear una nueva categoria
  public newTarea(newCategoria: NewCategoria): Observable<Categoria> {
    return this.httpClient.post<Categoria>(
      `${environment.apiUrl}/categoria/new`,
      newCategoria
    );
  }

  // Método para buscar por id una categoria
  public searchCategoria(id: String): Observable<Categoria> {
    return this.httpClient.get<Categoria>(
      `${environment.apiUrl}/categoria/${id}`
    );
  }

  // Método para editar categoria por su id
  public editTarea(
    id: String,
    categoriaActualizada: NewCategoria
  ): Observable<Categoria> {
    return this.httpClient.patch<Categoria>(
      `${environment.apiUrl}/categoria/edit/${id}`,
      categoriaActualizada
    );
  }

  // Método para eliminar categoria por su id
  public deleteById(id: String): Observable<Categoria> {
    return this.httpClient.delete<Categoria>(
      `${environment.apiUrl}/categoria/delete/${id}`
    );
  }
}
