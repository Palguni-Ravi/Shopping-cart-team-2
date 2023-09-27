import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { AuthService } from '../service/auth.service'; // Import your AuthService
import { User } from '../models/user.model'; // Import the User model

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
    private authService: AuthService // Inject your AuthService
  ) { }

  ngOnInit(): void { }

  // Implement your admin addition logic here
  addAdmin(): void {
    // Create the admin user data with the "ADMIN" role
    const adminUserData: User = {
      name: this.adminName,
      email: this.adminEmail,
      password: this.adminPassword,
      role: 'ADMIN' // Set the role to "ADMIN"
    };

    // Call the createUserdetails method to store the admin details
    this.authService.createUserdetails(adminUserData).subscribe(
      (response: any) => {
        if (response && response.jwtToken && response.email) {
          // Successfully created the admin user
          this.dialogRef.close();
        }
      },
      (error) => {
        // Handle errors, e.g., display an error message
        console.error('Error creating admin:', error);
      }
    );
  }

  // Close the dialog without adding an admin
  cancel(): void {
    this.dialogRef.close();
  }
}
