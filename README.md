# My Star Project

A simple Spring Boot application to manage stars information.

## Requirements

- Java 17
- Maven

## Running the application

1. Clone the repository.
2. Navigate to the project directory.
3. Run `mvn spring-boot:run`.

## API Endpoints

- `GET /stars/{id}` - Get a specific star by ID.
- `POST /stars` - Add a new star.
- `PUT /stars/{id}` - Update an existing star.
- `DELETE /stars/{id}` - Delete a star.
- `POST /stars/closest?size={size}` - Get the closest stars.
- `POST /stars/distances` - Get the number of stars by distances.
- `POST /stars/unique` - Get unique stars.
