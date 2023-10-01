import { NgModule, CUSTOM_ELEMENTS_SCHEMA  } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { CartComponent } from './nav-bar/cart/cart.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutModule } from '@angular/cdk/layout';
import { MaterialModule } from './material/material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { ListItemsComponent } from './list-items/list-items.component';
import { FilterItemsComponent } from './filter-items/filter-items.component';
import { FormsModule } from '@angular/forms'
import { IndexPageComponent } from './index-page/index-page.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { MatSnackBarModule } from '@angular/material/snack-bar'
import { ScrollingModule } from '@angular/cdk/scrolling';
import { NgxSpinnerModule } from 'ngx-spinner';
import { CookieService } from 'ngx-cookie-service';
import { BreakpointObserverOverviewExampleComponent } from './breakpoint-observer-overview-example/breakpoint-observer-overview-example.component';
import { AddAdminDialogComponent } from './add-admin-dialog/add-admin-dialog.component';
import { AddProductDialogComponent } from './add-product-dialog/add-product-dialog.component';
import { MatDialogModule } from '@angular/material/dialog';

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    CartComponent,
    ListItemsComponent,
    FilterItemsComponent,
    IndexPageComponent,
    SignInComponent,
    BreakpointObserverOverviewExampleComponent,
    AddAdminDialogComponent,
    AddProductDialogComponent,

  ],schemas: [CUSTOM_ELEMENTS_SCHEMA],

  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LayoutModule,
    MaterialModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    MatSnackBarModule,
    ScrollingModule,
    NgxSpinnerModule,
    MatDialogModule
  ],
  providers: [CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
