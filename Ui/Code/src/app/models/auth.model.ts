import { DateSelectionModelChange } from "@angular/material/datepicker";

export interface LoginDetails{
    username:string;
    password:string;
    email:string;
}

export interface SignUPDetails{
    username:string;
    email:string;
    dob:Date;
    university:string;

}