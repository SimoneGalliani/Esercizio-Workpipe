import { Component, OnInit } from '@angular/core';
import { UserService, User } from '../utente.service';

@Component({
  selector: 'app-utenti',
  templateUrl: './utenti.component.html',
  styleUrls: ['./utenti.component.css']
})
export class UtentiComponent implements OnInit {
  users: User[] = [];
  totalUsers: number = 0;
  pageSize: number = 5;
  currentPage: number = 0;
  newUser: User = { id: 0, nome: '', cognome: '', email: '', dataNascita: null };

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.loadUsers(this.currentPage, this.pageSize);
  }

  loadUsers(page: number, size: number) {
    this.userService.getUsers(page, size).subscribe({
      next: (data) => {
        this.users = data.content;
        this.totalUsers = data.totalElements;
      },
      error: (err) => console.error('Errore durante il caricamento degli utenti:', err)
    });
  }

  editUser(user: User) {
    this.newUser = { ...user };
  }

  cancelEdit() {
    this.resetForm();
  }

  resetForm() {
    this.newUser = { id: 0, nome: '', cognome: '', email: '', dataNascita: null };
  }

  addUser() {
    if (this.newUser.dataNascita) {
      const dataNascita = new Date(this.newUser.dataNascita);
      dataNascita.setHours(12);
      this.newUser.dataNascita = dataNascita;
    }
  
    this.userService.addUser(this.newUser).subscribe(() => {
      this.loadUsers(this.currentPage, this.pageSize);
      this.resetForm();
    });
  }

  deleteUser(user: User) {
    this.userService.deleteUser(user.id.toString()).subscribe(() => {
      this.loadUsers(this.currentPage, this.pageSize);
    });
  }

  onPaginateChange(event: any) {
    this.currentPage = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadUsers(this.currentPage, this.pageSize);
  }
}
