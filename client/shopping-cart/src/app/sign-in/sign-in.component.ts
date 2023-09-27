import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { User } from '../models/user.model';
import { CookieService } from 'ngx-cookie-service';
import { CartService } from '../service/cart.service';
import { CartItem } from '../models/cartItem.model';
import { MatDialog } from '@angular/material/dialog';
import { AddAdminDialogComponent } from '../add-admin-dialog/add-admin-dialog.component';
import { AddProductDialogComponent } from '../add-product-dialog/add-product-dialog.component';
@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css'],
})
export class SignInComponent implements OnInit {
  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private cookieService: CookieService,
    private route: ActivatedRoute,
    private cartService: CartService,
    private dialog: MatDialog
  ) { }
  isRegisterMode: boolean = false;
  loginForm: any;
  registerValidation: string | null = null;
  loginValidation: string | null = null;
  user: User = {};
  emailId: string = '';
  isPasswordCorrect: boolean = false;
  isUser: boolean = false;
  cartItem : CartItem[]=[]
  userPresent: boolean = this.cookieService.get('currentUser') ? true : false;
  userRole: string | undefined;

  ngOnInit() {
    this.userPresent = this.cookieService.get('currentUser') ? true : false;

    if (this.userPresent) {
      if (this.cookieService.get('previousState')) {
        this.router.navigate([
          '/clothes/search',
          this.cookieService.get('previousState'),
        ]);
        this.cookieService.delete('previousState');
      }
    }
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      name: ['', [Validators.pattern(/^[a-zA-Z\s]+$/)]],
    });
    this.isUser = false;
  }

  get email() {
    return this.loginForm.get('email');
  }

  get password() {
    return this.loginForm.get('password');
  }

  get name() {
    return this.loginForm.get('name');
  }

  async login(): Promise<void> {
    if (
      this.loginForm.get('email').valid &&
      this.loginForm.get('password').valid
    ) {
      this.loginValidation = null;
      this.emailId = this.loginForm.get('email').value;
      let isUser = false;
      try {
        const response = await this.authService
          .getUserdetails(this.emailId)
          .toPromise();

        if (response) {
          isUser = response;
        }
      } catch (error) {
        console.error('Error:', error);
      }
      if (isUser) {
        try {
          const response: any = await this.authService
            .validatePassword(
              this.emailId,
              this.loginForm.get('password').value
            )
            .toPromise();

          if (response && response.jwtToken && response.email) {
            const jwtToken = response.jwtToken;
            this.cookieService.set('currentUser', jwtToken, 1);
          } else {
            console.error('Invalid response:', response);
          }
        } catch (error) {
          console.error('Error:', error);
        }
        
        if (this.cookieService.get('currentUser')) {
          this.route.queryParams.subscribe((params) => {
            const cartItemString = params['cartItem'];
             const cartItem = JSON.parse(cartItemString);
            const response =  this.cartService.addToCart(cartItem, this.cookieService.get('currentUser')).subscribe();
          });
          
          if (this.cookieService.get('previousState')) {
            this.router.navigate([
              '/clothes/search',
              this.cookieService.get('previousState'),
            ]);
            this.cookieService.delete('previousState');
          } else {
            this.router.navigate(['/']);
          }
        } else {
          this.loginValidation = 'Incorrect password';
        }
      } else {
        this.loginValidation = "User doesn't exist";
      }
    }
    

    if (this.cookieService.get('currentUser')) {
      // Check the user's role
      const roleResponse: any = await this.authService
        .getUserRole(this.emailId)
        .toPromise();

      if (roleResponse && roleResponse.role) {
        this.userRole = roleResponse.role;

        // Redirect or display additional options based on the user's role
        if (this.userRole === 'SUPER_ADMIN') {
          // Display options for adding admins and products
          
        } else {
          // Redirect or display regular user options
        }
      }
    }
    this.loginForm.reset();
  }

 
  openAdminDialog(): void {
    const dialogRef = this.dialog.open(AddAdminDialogComponent, {
      width: '300px',
      data: {} // You can pass data to the dialog if needed
    });

    dialogRef.afterClosed().subscribe(result => {
      // Handle the result from the dialog if needed
    });
  }

  openProductDialog(): void {
    const dialogRef = this.dialog.open(AddProductDialogComponent, {
      width: '300px',
      data: {} // You can pass data to the dialog if needed
    });

    dialogRef.afterClosed().subscribe(result => {
      // Handle the result from the dialog if needed
    });
  }


  async register(): Promise<void> {
    if (this.loginForm.valid) {
      this.registerValidation = null;
      this.emailId = this.loginForm.get('email').value;

      let isUser = false;
      try {
        const response = await this.authService
          .getUserdetails(this.emailId)
          .toPromise();

        if (response) {
          isUser = response;
        }
      } catch (error) {
        console.error('Error:', error);
      }
      if (!isUser) {
        const userData = {
          email: this.loginForm.get('email').value,
          name: this.loginForm.get('name').value,
          password: this.loginForm.get('password').value,
          cartItem: [],
        };
        this.authService
          .createUserdetails(userData)
          .subscribe((response: any) => {
            if (response && response.jwtToken && response.email) {
              this.cookieService.set('currentUser', response.jwtToken, 7);
            }
          });
          this.route.queryParams.subscribe((params) => {
            const cartItemString = params['cartItem'];
             const cartItem = JSON.parse(cartItemString);
            const response =  this.cartService.addToCart(cartItem, this.cookieService.get('currentUser')).subscribe();
          });
          
        if (this.cookieService.get('previousState')) {
          this.router.navigate([
            '/clothes/search',
            this.cookieService.get('previousState'),
          ]);
          this.cookieService.delete('previousState');
        } else {
          this.router.navigate(['/']);
          this.loginForm.reset();
        }
      } else {
        this.registerValidation = 'Already an user';
      }
    }
    this.loginForm.reset();
  }

  inputCheck() {
    this.registerValidation = null;
    this.loginValidation = null;
  }
  signOut() {
    this.cookieService.delete('currentUser');
    this.userPresent = false;
  }
}
