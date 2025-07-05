# 🌦️ Weather Forecast Telegram Bot

<!-- Badges -->
<p>
  <img src="https://img.shields.io/badge/Java-17-blue?logo=java" alt="Java 17">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?logo=spring" alt="Spring Boot">
  <img src="https://img.shields.io/badge/PostgreSQL-DATABASE-blue?logo=postgresql" alt="PostgreSQL">
  <img src="https://img.shields.io/badge/Telegram%20Bot%20API-Ready-blue?logo=telegram" alt="Telegram Bot API">
  <img src="https://img.shields.io/badge/License-MIT-yellow" alt="License: MIT">
</p>

A powerful and user-friendly Telegram bot built with **Spring Boot** to provide real-time weather forecasts with multi-language support.

**[Try the Bot Live](https://t.me/myWeatherBot_clonebot)**

---

<p align="center">
  <a href="#-bot-main-menu">Bot Menu</a> •
  <a href="#-project-overview">Project Overview</a> •
  <a href="#-key-features">Key Features</a> •
  <a href="#-tech-stack">Tech Stack</a> •
  <a href="#-getting-started">Getting Started</a> •
  <a href="#-author">Author</a>
</p>

---

## 🤖 Bot's Main Menu

Here is an interactive representation of the bot's main menu. The design is adaptive and looks great in both **light and dark modes**.

<div align="center">
  <table style="border: none; border-collapse: separate; border-spacing: 8px; width: 80%; max-width: 500px;">
      <tbody>
          <!-- First Row: Ob-havo (Weather) -->
          <tr>
              <td colspan="2" style="background-color: var(--color-canvas-subtle); border: 1px solid var(--color-border-default); border-radius: 6px; padding: 12px; text-align: center; font-weight: 600; font-size: 16px;">
                  ☀️ Ob-havo
              </td>
          </tr>
          <!-- Second Row -->
          <tr>
              <td style="background-color: var(--color-canvas-subtle); border: 1px solid var(--color-border-default); border-radius: 6px; padding: 12px; text-align: center; font-weight: 600;">
                  📍 Joriy shahar
              </td>
              <td style="background-color: var(--color-canvas-subtle); border: 1px solid var(--color-border-default); border-radius: 6px; padding: 12px; text-align: center; font-weight: 600;">
                  🏙️ Boshqa shaharlar
              </td>
          </tr>
          <!-- Third Row -->
          <tr>
              <td style="background-color: var(--color-canvas-subtle); border: 1px solid var(--color-border-default); border-radius: 6px; padding: 12px; text-align: center; font-weight: 600;">
                  ⚙️ Sozlamalar
              </td>
              <td style="background-color: var(--color-canvas-subtle); border: 1px solid var(--color-border-default); border-radius: 6px; padding: 12px; text-align: center; font-weight: 600;">
                  🔔 Qo'ng'iroqcha
              </td>
          </tr>
          <!-- Fourth Row -->
          <tr>
              <td style="background-color: var(--color-canvas-subtle); border: 1px solid var(--color-border-default); border-radius: 6px; padding: 12px; text-align: center; font-weight: 600;">
                  ❓ Yo'riqnoma
              </td>
              <td style="background-color: var(--color-canvas-subtle); border: 1px solid var(--color-border-default); border-radius: 6px; padding: 12px; text-align: center; font-weight: 600;">
                  ✉️ Biz bilan aloqa
              </td>
          </tr>
      </tbody>
  </table>
</div>

## 📋 Project Overview

The **Weather Forecast Bot** is a backend application designed to deliver accurate weather information directly to users on Telegram. The system is built on a robust Spring Boot architecture, utilizes a PostgreSQL database for data persistence, and manages its database schema through migrations. It offers a seamless user experience with an intuitive interface and multi-language capabilities.

## ✨ Key Features

-   **🌍 Multi-language Support:** Users can interact with the bot in Uzbek, Russian, and English. The language can be changed at any time through the settings menu.

-   **☀️ Real-Time Weather Data:** Get up-to-date weather information for any city in the world simply by sending its name.

-   **📍 Current City Forecast:** Save a default city for quick access to its weather forecast without re-typing the name.

-   **⚙️ User-Friendly Settings:** Easily configure bot preferences, such as language, through an interactive menu.

-   **📞 Direct Contact:** A built-in option for users to contact the developer for support or feedback.

-   **🔔 Notification System (Future Goal):** Functionality to set up daily or scheduled weather alerts for a specified location.

## 🛠️ Tech Stack

-   **Backend Framework:** `Spring Boot`
-   **Database:** `PostgreSQL`
-   **Database Migration:** `Flyway` / `Liquibase`
-   **Programming Language:** `Java 17`
-   **Build Tool:** `Maven` / `Gradle`
-   **API:** `Telegram Bot API`

## 🚀 Getting Started

Follow these instructions to get a local copy of the project up and running.

### Prerequisites

-   Java 17 (or higher)
-   PostgreSQL installed and running
-   Maven or Gradle

### Installation

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/AbdulbositAbdurahimovDeveloper/your-repo-name.git
    ```

2.  **Configure the application:**
    Navigate to `src/main/resources/application.properties` and update the following properties with your credentials:

    ```properties
    # Telegram Bot Configuration
    bot.token=YOUR_TELEGRAM_BOT_TOKEN
    bot.username=YOUR_BOT_USERNAME

    # Database Configuration
    spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
    spring.datasource.username=your_db_username
    spring.datasource.password=your_db_password
    ```

3.  **Run the application:**
    You can run the project using your IDE or via the command line:
    ```bash
    # Using Maven
    mvn spring-boot:run
    ```

## 👤 Author

This project is developed and maintained by **Abdulbosit Abdurahimov**.

-   **GitHub:** [@AbdulbositAbdurahimovDeveloper](https://github.com/AbdulbositAbdurahimovDeveloper)
-   **Telegram:** [@Abdul_bosit_dev](https://t.me/Abdul_bosit_dev)
-   **LinkedIn:** [Abdulbosit Abdurahimov](https://www.linkedin.com/in/abdulbosit-abdurahimov-a40b38356/)
-   **Personal Channel:** [ByCodeDiary](https://t.me/ByCodeDiary)
