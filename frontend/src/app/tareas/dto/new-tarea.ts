import { Categoria } from 'src/app/categorias/dto/categoria';

export interface NewTarea {
  title: String;
  date: Date;
  description?: String;
  highlighted: boolean;
  categorias: Array<Categoria>;
}
