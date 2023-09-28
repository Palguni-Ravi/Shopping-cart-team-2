export class Category {
    name: string;
    image: string; // URL of the category image
  
    constructor(_id: string, name: string, image: string) {
      this.name = name;
      this.image = image;
    }
  }