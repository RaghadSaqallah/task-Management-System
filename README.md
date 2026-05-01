[README.md](https://github.com/user-attachments/files/27272608/README.md)
# 📋 Task Management System

A desktop application built with **Java** and **JavaFX** that allows users to manage tasks, track their status, and filter/search through them — with persistent storage via CSV.

---

## 🚀 Features

- ✅ Add new tasks with title, status, assigned user, and date
- 🔍 Search tasks by user name
- 📊 View total, open, and closed task counts
- 🔝 Find the user with the highest number of tasks
- 🔎 Filter tasks starting with "A" and length of 7 characters
- 📅 View the 4 earliest added tasks
- 🎨 Customize font family, size, and style via menu bar
- 💾 Data persisted in a CSV file (`taskData.csv`)

---

## 🗂️ Project Structure

```
Task/
├── src/
│   ├── app/
│   │   └── Main.java                  # Entry point
│   ├── controllers/
│   │   └── MainAppController.java     # UI logic & event handlers
│   ├── models/
│   │   └── task.java                  # Task model
│   ├── view/
│   │   └── mainApp.fxml               # JavaFX UI layout
│   └── data/
│       └── taskData.csv               # Persistent task storage
```

---

## 🛠️ Technologies Used

| Technology | Purpose |
|------------|---------|
| Java 11+   | Core language |
| JavaFX     | Desktop UI framework |
| FXML       | UI layout definition |
| CSV        | Data persistence |
| NetBeans IDE | Development environment |

---

## ⚙️ Setup & Run

### Prerequisites
- Java JDK 11 or higher
- JavaFX SDK
- NetBeans IDE (recommended)

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/task-management-system.git
   cd task-management-system
   ```

2. **Open in NetBeans**
   - File → Open Project → select the project folder

3. **Update the CSV file path**
   
   In `MainAppController.java`, update the hardcoded path to match your machine:
   ```java
   // Change this line in initialize() and saveToFile():
   "C:\\Users\\AL\\Documents\\NetBeansProjects\\Task\\src\\data\\taskData.csv"
   
   // Replace with your own path, for example:
   "C:\\Users\\YourName\\...\\Task\\src\\data\\taskData.csv"
   ```

4. **Run the project**
   - Right-click the project → Run, or press `F6`

---

## 📄 CSV Data Format

The `taskData.csv` file uses the following structure:

```
id,title,status,addedBy,date
1,Setup Project,closed,Ali,2026-04-01
2,Analyze Requirements,open,Sami,2026-04-05
...
```

---

## 👤 Author

**Raghad Saqallah**  
Version: 1.0
