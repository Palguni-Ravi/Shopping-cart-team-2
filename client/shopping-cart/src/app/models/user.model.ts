import { CartItem } from "./cartItem.model"
export interface User {
    email?: string, 
    name?: string,
    password?: string,
    // isCurrentUser?: boolean,
    role?:string,
    cartItem? : CartItem[]
}

