import { CartItem } from "./cartItem.model"
export interface User {
    email?: string, 
    name?: string,
    ///user?: string,
    password?: string,
    //isCurrentUser?: boolean,
    cartItem? : CartItem[],
    role?:string
}
