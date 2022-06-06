import { User } from 'src/app/authentication/dto/user';

export interface Categoria {
  id: string;
  title: string;
  description?: string;
  color: string;
  owner: User;
}
