# EconoMe-webtech

For the Web Technologies semester project in the summer semester of 2024, 
we are developing a Personal Budget Tracker that allows users to track and categorize their expenses and income. 
The application will also feature the ability to set and monitor saving goals. 
Additionally, we are considering implementing user account management to enhance personalization.

## Introduction

EconoMe is a frontend application designed to help users manage their finances effectively. The application provides a user-friendly interface to track budgets, add transactions, and categorize expenses. This project utilizes Vue 3 and Vite for fast and efficient development.

## Features

### Home View

In the home view you can add your new transactions. To submit a transaction all field must be filled. AFter filling all mandatory fields the submit button can be pressed.

- **Add Transaction**: Easily add income or expense transactions.
- **Add Category**: Create custom categories for better expense tracking.
- **Add Currency**: Manage multiple currencies for your transactions.
- **Add Payment Method**: Specify payment methods for your transactions.

### Overview

In the overview view you can see all your recent transactions, filter them using the buttons or use the API to calculate all your expenses to the selected currencies.
By pressing the delete button you can delete transactions you dont want or need anymore... They cant be restored!!!
Through the search field you can search your transactions after their name.

- **See Transactions**: View a list of all your transactions in one place.
- **Filter Transactions**: Filter transactions by year, month, type, and category to find specific entries.
- **Search Function**: Search through your transactions quickly and efficiently.
- **Change Currency**: Switch between currencies using a real-time API for accurate conversions.

### Dashboard

In the dashboard you have three different dashboards that are displayed, which are filtered by the available years (select with the first button)

- **Multiple Dashboards**: View three different dashboards to visualize your financial data effectively. (Income per month, Expense per month, pie chart of income / expense per year)
