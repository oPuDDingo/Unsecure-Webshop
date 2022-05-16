export interface User {
  id?: number;
  mail: string;
  firstname: string;
  lastname: string;
  newsletter?: boolean;
  salutation?: string;
  title?: string;
  profilePicture?: string;
  description?: string;
  password?: string;
}
