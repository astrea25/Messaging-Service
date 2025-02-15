# Messaging-Service

A Spring Boot application that implements a retrofit service to enable communication between two applications.

## Features

- RESTful API endpoints for message handling
- Retrofit integration for inter-app communication
- Scalable messaging architecture
- Request/Response handling

## Prerequisites

- Java JDK 17 or higher
- Maven or Gradle
- Spring Boot 3.x

## Installation

1. Clone the repository:

```bash
git clone https://github.com/yourusername/messaging-service.git
cd messaging-service
```

2. Build the project:
    - File > Import > Existing Maven Project
    - Navigate to the project directory
    - Select the project and click Finish

3. Run the application:
    - Right click on the project in the Project Explorer
    - Select Run As > Spring Boot App

The Message Server will be available at `http://localhost:9998`
The Message of The Day Server will be available at `http://localhost:9999`

## Message Server Components

### Quote Entity
The Quote entity contains the following fields:
- Long id (Primary Key)
- String message
- String category (happy, sad, encouraging, generic)

### Components
1. **QuoteInitializer**
   - Initializes the database with predefined quotes
   - Loads various quotes with different categories
   - Ensures at least one quote with 'generic' category exists

2. **Message Component**
   - Retrieves quotes based on category
   - Features:
     - Searches quotes by category
     - Randomly selects a quote when multiple matches exist
     - Falls back to generic category if no matches found
     - Ensures at least one generic message is available

3. **MessageController**
   - Handles HTTP GET requests
   - Accepts category as a query parameter
   - Returns quote messages in JSON format
   - Integrates with Message Component for quote retrieval

### API Endpoints

GET `/api/message?category={category}`
- Parameters:
  - category: String (happy, sad, encouraging, generic)
- Returns:
  - JSON response containing the selected quote message

Example Response:
```json
{
    "message": "Your inspirational quote here",
    "category": "happy"
}
```

## Message of The Day Server Components

### User Entity
The User entity contains the following fields:
- Long id (Primary Key)
- String name
- String cellphoneNumber

### Components

1. **UserInitializer**
   - Initializes the database with predefined users
   - Loads various users with different names
   - Uses test cell phone numbers for development

2. **MessageOfTheDay Component**
   - Core business logic for message processing
   - Features:
     - Retrieves user by primary key
     - Fetches random messages from Message Server via Retrofit
     - Formats personalized messages ("Hello <name>, <message text>")
     - Integrates with Twilio for SMS delivery
     - Returns Twilio response status

3. **TwilioComponent**
   - Handles SMS communication
   - Features:
     - Sends SMS messages via Twilio API
     - Processes delivery responses
     - Returns structured TwilioReply objects

4. **MessageOfTheDayController**
   - Handles HTTP requests
   - Provides endpoints for:
     - Single user message delivery
     - Bulk message delivery to all users
     - Delivery status reporting

### API Endpoints

1. GET `/api/motd/send`
- Parameters:
  - userId: Long
  - category: String
- Returns:
  - TwilioReply JSON containing delivery status

2. GET `/api/motd/sendall`
- Parameters:
  - category: String
- Returns:
  - JSON containing number of successful deliveries

Example Response:
```json
{
    "status": "delivered",
    "messageSid": "SM123456789",
    "errorMessage": null
}
```

## Pre-requisite
- Client-UI Dependencies
- SpringBoot Dependencies
- MySQL Database: "message_quotes_db"
- MySQL Database: "messages_db"
- Twilio Account

## Configuration

The application requires the following environment variables:
- `TWILIO_ACCOUNT_SID` - Your Twilio account SID
- `TWILIO_AUTH_TOKEN` - Your Twilio authentication token
- `TWILIO_PHONE_NUMBER` - Your Twilio phone number

## Technologies Used

- Spring Boot
- Spring Data JPA
- MySQL
- Retrofit
- Twilio SDK
- Lombok (optional)

