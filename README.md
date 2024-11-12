# Grocery Booking System

## Features

- **User Functionality**:
  - View available grocery items.
  - Place orders with multiple grocery items.
  
- **Admin Functionality**:
  - Add new grocery items.
  - Get a list of all grocery items or a specific item by ID.
  - Update details of grocery items (name, price, stock).
  - Delete grocery items from the system.
  - Update stock quantities for grocery items.

## Technologies Used
- Java 17
- Spring Boot 2.7.x
- Spring Data JPA
- H2 Database (in-memory, can be replaced with other DBs)
- Spring REST for building the API
- Lombok for reducing boilerplate code

## Setup Instructions

### Prerequisites

- **Java 17** or later
- **Maven** for dependency management
- **IDE**: IntelliJ IDEA, Eclipse, or your preferred Java IDE.


## API Endpoints

### User Endpoints

#### 1. Get All Grocery Items
- **URL**: `/api/v1/user/get-grocery-item`
- **Method**: `GET`
- **Description**: Fetches a list of all available grocery items.
- **Response**: 200 OK with a list of `GroceryItem` objects.

#### 2. Place an Order
- **URL**: `/api/v1/user/place-order/{userId}`
- **Method**: `POST`
- **Description**: Allows a user to place an order by providing a list of `OrderRequest` objects containing the grocery items and their quantities.
- **Parameters**: 
    - `userId` (Path Variable) - ID of the user placing the order.
    - `orderRequests` (Body) - List of `OrderRequest` objects containing item IDs and quantities.
- **Response**: 201 Created with the saved `Orders` object.

### Admin Endpoints

#### 1. Add New Grocery Items
- **URL**: `/api/v1/admin/groceries`
- **Method**: `POST`
- **Description**: Allows an admin to add new grocery items to the system.
- **Body**: List of `GroceryItem` objects.
- **Response**: 201 Created with the list of added `GroceryItem` objects.

#### 2. Get a Specific Grocery Item by ID
- **URL**: `/api/v1/admin/groceries/{groceryItemId}`
- **Method**: `GET`
- **Description**: Fetches a grocery item by its ID.
- **Response**: 
    - 200 OK if the item exists.
    - 404 Not Found if the item does not exist.

#### 3. Get All Grocery Items
- **URL**: `/api/v1/admin/groceries`
- **Method**: `GET`
- **Description**: Fetches a list of all available grocery items.
- **Response**: 302 Found with a list of `GroceryItem` objects.

#### 4. Update a Grocery Item
- **URL**: `/api/v1/admin/groceries/{groceryItemId}`
- **Method**: `PUT`
- **Description**: Allows an admin to update the details of an existing grocery item.
- **Parameters**: 
    - `groceryItemId` (Path Variable) - ID of the grocery item to update.
    - `groceryItem` (Body) - Updated `GroceryItem` object.
- **Response**: 201 Created with the updated `GroceryItem` object.

#### 5. Delete a Grocery Item
- **URL**: `/api/v1/admin/groceries/{groceryItemId}`
- **Method**: `DELETE`
- **Description**: Allows an admin to delete a grocery item from the system.
- **Parameters**: 
    - `groceryItemId` (Path Variable) - ID of the grocery item to delete.
- **Response**: 204 No Content with a success message.

#### 6. Update Stock Quantity
- **URL**: `/api/v1/admin/groceries/{groceryItemId}`
- **Method**: `PATCH`
- **Description**: Allows an admin to update the stock quantity of a grocery item.
- **Parameters**: 
    - `groceryItemId` (Path Variable) - ID of the grocery item to update.
    - `stockQuantity` (Body) - New stock quantity for the item.
- **Response**: 202 Accepted with the updated `GroceryItem` object.

## Entity Models

### `GroceryItem`
- Represents a grocery item with the following fields:
    - `groceryItemId` (Long) - Unique identifier for the grocery item.
    - `name` (String) - Name of the grocery item.
    - `description` (String) - Description of the grocery item.
    - `price` (Double) - Price of the grocery item.
    - `stockQuantity` (Integer) - Available stock for the item.

### `OrderItem`
- Represents an individual item in an order with the following fields:
    - `orderItemId` (Long) - Unique identifier for the order item.
    - `order` (Orders) - Reference to the parent order.
    - `groceryItem` (GroceryItem) - Reference to the grocery item being ordered.
    - `quantity` (Integer) - Quantity of the item in the order.
    - `price` (Double) - Price of the item at the time of order.

### `Orders`
- Represents an order with the following fields:
    - `orderId` (Long) - Unique identifier for the order.
    - `userId` (Long) - ID of the user who placed the order.
    - `orderDate` (LocalDate) - Date when the order was placed.
    - `totalPrice` (Double) - Total price of the order.
    - `items` (List<OrderItem>) - List of items in the order.
