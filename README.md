# codeseektechtask

# 💵FootballManager💵💵

# Project Description:
- 🫡Welcome to the FootballManager project!🫡
- FootballManager is a web application built with Spring Boot that implements basic CRUD operations for managing data. 
- This solution is ideal for tracking and synchronizing information in real time.

## Setup

To set up the project, follow these steps:

### Prerequisites

Make sure you have the following software installed on your system:

- Java Development Kit (JDK) 17 or higher
- Spring Boot 3.4.1 or higher
- Apache Maven
- Apache Tomcat vesion 9 or higher
- DataBase: MYSQL

### Installation
- First of all, you should made your fork
- Second, clink on Code<> and clone link, after that open your Intellij Idea, click on Get from VCS
- past link, which you clone later


### Replace Placeholders:
To connect to your DB, you should replace PlaceHolders in application.properties
- Open package resources and open file application.properties in your project.
- Locate the placeholders that need to be replaced.
- These placeholders might include values such as
- spring.datasource.url= URL for your MySql
- spring.datasource.username= Replace with your MySql
- spring.datasource.password= Replace with your password Mysql
- pring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver


# Features 🤌:

## User 🤵‍♂️
- Registration
- Login
- Authentication by JWT

## Role 🙎‍♂️
- Create/update/remove a role
- Get role by roleName

## Team 💵
- Create team
- Soft-Delete team
- Update team information
- Get list of teams
- Get team by ID

## Player 🙎‍♂️
- Create player
- Soft-Delete player
- Update platey information
- Get list of players
- Get players by ID
- Transfer player to another team by ID


## Controllers 🕹

## AuthController 🤵‍♂️
- Sign in -> /auth/login - Post
- Sign up -> /auth/register - Post

## Team 💵
- Save team -> /teams - Post
- Get list of teams -> /teams - Get
- Get team by ID -> /teams/{id} - Get
- Soft-delete team by ID -> /teams/{id} - Delete
- Update team information by ID -> /teams/{id} - Put

## Player 🙎‍♂️
- Save player -> /players - Post
- Get list of player -> /players - Get
- Get player by ID -> /players/{id} - Get
- Soft-delete plater by ID -> /player/{id} - Delete
- Update player information by ID -> /players/{id} - Put
- Transfer player to another team by ID -> /players/transfet/{id}
