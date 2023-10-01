import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { AuthService } from '../service/auth.service'; 
import { User } from '../models/user.model'; 
@Component({
  selector: 'app-add-admin-dialog',
  templateUrl: './add-admin-dialog.component.html',
  styleUrls: ['./add-admin-dialog.component.css']
})
export class AddAdminDialogComponent implements OnInit {
  adminName: string = '';
  adminEmail: string = '';
  adminPassword: string = '';
  constructor(
    private dialogRef: MatDialogRef<AddAdminDialogComponent>,
    private authService: AuthService 
  ) { }
  ngOnInit(): void { }

  addAdmin(): void {

    const adminUserData: User = {
      name: this.adminName,
      email: this.adminEmail,
      password: this.adminPassword,
      role: 'ADMIN' 
    };

    this.authService.createUserdetails(adminUserData).subscribe(
      (response: any) => {
        if (response && response.jwtToken && response.email) {
          this.dialogRef.close();
        }
      },
      (error) => {
        console.error('Error creating admin:', error);
      }
    );
  }
  cancel(): void {
    this.dialogRef.close();
  }
}