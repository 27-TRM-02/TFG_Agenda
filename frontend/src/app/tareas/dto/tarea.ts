import { User } from 'src/app/authentication/dto/user';
import { Categoria } from 'src/app/categorias/dto/categoria';

export interface Tarea {
  id: String;
  title: String;
  date: Date;
  description?: String;
  highlighted: boolean;
  categorias: Array<Categoria>;
  owner: User;
}
