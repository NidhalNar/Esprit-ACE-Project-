# ACE - Event Management Platform

ACE is a comprehensive event management platform built across multiple environments, designed to simplify event discovery, booking, and participation. The platform includes web, desktop, and mobile applications, allowing users to easily manage and book events, view them on Google Maps, and manage personal accounts. It is powered by JavaFX, Symfony, and CodeNameOne, with a MySQL database for data storage.

## Overview

ACE consists of three platforms:

1. **ACE JavaFX (Desktop Application)**: A feature-rich desktop client developed in JavaFX, focusing on user experience and efficient event management for administrators and users.
2. **ACE Mobile (CodeNameOne)**: A mobile application providing a seamless experience for users on the go, designed with CodeNameOne for cross-platform functionality (iOS and Android).
3. **ACE Symfony (Web Application)**: A web application built with Symfony, allowing users to access the platform online, view events, and manage bookings.

The `ace.sql` file initializes the MySQL database, supporting all event, user, and booking-related data.

## Features

### Event Management
- Create, edit, and delete events.
- View event details, including descriptions, dates, locations, and available spots.
- Interactive map integration using Google Maps for easy navigation and location visibility.

### User Management
- User registration and login.
- Personal profiles with user information and event history.
- Role-based access control for users and administrators.

### Booking System
- Real-time event booking system.
- Reservation confirmation and notifications.
- Booking history accessible to users.

### Google Maps Integration
- Events are displayed on Google Maps, allowing users to view locations and receive directions.
- Users can filter events by proximity and availability.

## Technologies

- **JavaFX**: Desktop application development.
- **CodeNameOne**: Mobile application development for iOS and Android.
- **Symfony**: Web application development and API backend.
- **MySQL**: Database for storing user, event, and booking data.
- **Google Maps API**: Integrated for map-based event viewing and booking.

## Installation

1. **Database Setup**:
   - Import `ace.sql` into a MySQL database to create the required schema and tables.

2. **JavaFX Application**:
   - Clone the repository and run the project in a JavaFX-compatible IDE (like IntelliJ IDEA or Eclipse).

3. **Mobile Application (CodeNameOne)**:
   - Set up a CodeNameOne project in your IDE.
   - Clone the repository and follow CodeNameOne's guidelines for Android/iOS deployment.

4. **Web Application (Symfony)**:
   - Clone the repository.
   - Install dependencies using Composer: `composer install`.
   - Configure the `.env` file for database connection.
   - Run migrations: `php bin/console doctrine:migrations:migrate`.
   - Start the Symfony server: `php bin/console server:start`.

## Usage

Once the applications are set up, users can access the following functionalities:

- **Event Discovery**: Browse and filter upcoming events.
- **Booking**: Reserve a spot for events and receive confirmation.
- **User Management**: View and update user profiles, event history, and booking status.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request.

---
