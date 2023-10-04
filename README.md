# Shopping-cart-team-2
This is a Ecommerce clothing website created using Angular and Java. The application uses json file to store the products data and the login and cart details are stored in local storage.

# Tech Stack Used
- Angular
- Material UI
- Spring Boot
- Elastic Search
- MongoDb
- MySQL

# Features
- Sign in and Register with validation
- Filters- Colors, Brand, Rating ,Price, Gender, Category
- Sort By - Price Low to High, Price High to Low, Rating Low to High and Rating High to Low
- Availability check with pincode.
- Search by multiple colors, brands, categories and  gender or any combinations of these.
- Add product to cart
- Delete product from cart
- Edit Products in cart
- View cart products
- Feedback collection in footer


# Steps to run the application:
Clone the repo:
`git clone https://github.com/Palguni-Ravi/Shopping-cart-team-2.git`

In visual studio code 
File -> Open Folder -> Open folder named **shopping-cart-team-2** from the location where the repo got cloned

Change directory:
`cd shopping-cart`

Install node modules:
`npm install`
`ngx-cookie-service`

# To run the Application in client side:
`ng serve`

To run in different port `ng serve --host 0.0.0.0 --port 5000`

# To run Elastic Search:

- Download the elastic-search-7.15.2.zip from the below link
- [https://drive.google.com/file/d/1pe017JiBTcVWCtUztk-ggPmfm-qVCYIC/view?usp=drive_link](url)
- Unzip it - > Open bin -> Run elasticsearch.bat

# To run the application in server side 
- Open server-side -> src/main/java -> com.example.shoppingcart -> ShoppingCartApplication.java
- Right click-> Run As -> Java Application
- To run server along with mongoDb database in application.properties set
* `spring.profiles.active=mongodb`
* `app.base-package=com.example.shoppingcart.MongoDb`

- To run server along with mysql database in application.properties set
* `spring.profiles.active=mysql`
* `app.base-package=com.example.shoppingcart.Mysql`

Note- Currently the database is present in our cluster. If you need to connect to your MongoDB server then change the credentials in the application.properties file.

Follow the following steps:
* Create a database with name `shopping-cart`
* Insert the json files of brands, category, gender, clothing attached in the Data folder to the collection with the same name.
* Create collections with name as user, carts, cartItems.

# To store data in MySQL
- Open MySQL command line client
- Create a schema with name `shopping-cart`
- In ServerMySQL -> src/main/resources -> application.properties, replace the below lines with your mysql username and password
`spring.datasource.username= <your-mysql-username>`
`spring.datasource.password= <your-mysql-password>`
- Now navigate to ServerMySQL -> src/main/java -> com.example.Shoppingsql -> ShoppingSqlApplication.java
- Right click in the file -> Run As -> Java Application

Adding data to tables:
- Right click on each table -> Table Data Import Wizard -> Browse Json file from `Data` Folder -> Check Use existing table -> Next

# Development Server
- Port for Client-side: `http://localhost:5000`
- Port for Server-side: `http://localhost:8080`
- Port for Elastic-search: `http://localhost:9200`
