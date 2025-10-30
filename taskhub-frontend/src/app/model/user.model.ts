import { Role } from "./role.model";

export interface User{
    userId : number,
    username : String,
    email : String,
    userPassword : String,
    allRoles : Role[],
    createdAt : Date,
    updatedAt : Date
}