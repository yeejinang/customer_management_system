import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { CreateCustomerComponent } from './create-customer/create-customer.component';
import { FormsModule } from '@angular/forms';
import { UpdateCustomerComponent } from './update-customer/update-customer.component';
import { CustomerDetailsComponent } from './customer-details/customer-details.component';
import { DatePipe } from '@angular/common';

@NgModule({
	declarations: [
		AppComponent,
		CustomerListComponent,
		CreateCustomerComponent,
		UpdateCustomerComponent,
		CustomerDetailsComponent
	],
	imports: [
		BrowserModule,
		AppRoutingModule,
		HttpClientModule,
		FormsModule
	],
	providers: [DatePipe],
	bootstrap: [AppComponent]
})
export class AppModule { }
