import { DatePipe } from "@angular/common";
import { NumberValueAccessor } from "@angular/forms";

export class Customer {
	id: number;
	firstName: string;
	lastName: string;
	emailId: string;
	phoneNumber: string;
	dateOfBirth: DatePipe;
	age: number;
}
