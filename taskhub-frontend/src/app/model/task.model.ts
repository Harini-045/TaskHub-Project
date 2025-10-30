import { Label } from "./label.model";
import { Project } from "./project.model";
import { User } from "./user.model";

export interface Task {
    taskId : number,
    project : Project,
    taskTitle : String,
    taskDescription : String,
    user : User,
    priority : String,
    startDate : Date,
    dueDate : Date,
    createdAt : Date,
    updatedAt : Date,
    label : Label
}