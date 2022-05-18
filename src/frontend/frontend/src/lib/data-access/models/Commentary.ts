export interface Commentary {
  id?: number; // TODO optional bei id und userId?
  commentText: string;
  firstname: string;
  lastname: string;
  userId?: number;
}
