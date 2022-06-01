import { User } from 'src/app/authentication/dto/user';

export interface Categoria {
  id: String;
  title: String;
  description?: String;
  color: String;
  owner: User;
}
