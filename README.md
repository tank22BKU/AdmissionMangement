# Admission Project

A comprehensive Java application for managing university entrance exam candidates, their preferences, and proctors. The project leverages modern Java features such as Lambda expressions, Stream API, Enums, Custom Exceptions, and advanced sorting to streamline admission management.

---

## 📋 Features

### Thí sinh (Candidate)
- **Unique ID** (Số báo danh)
- Full name, Gender, Year of Birth, Hometown
- Exam score, Priority score
- List of preferences (Nguyện vọng)

### Nguyện vọng (Preference)
- Auto-incremented ID
- Major code & name, University code, Admission group, Qualified score

### Giám thị (Proctor)
- Unique ID
- Work unit, Full name, Gender, Year of Birth, Hometown

---

## 🛠️ Functionalities

- **Manage Preferences**
    - Add, update, delete preference
    - Search by major code, name, university code, admission group, qualified score
    - Display preference list

- **Manage Candidates**
    - Add, update, delete candidate
    - Add or remove preferences for each candidate
    - Search by ID, name, gender, or exam score
    - Display candidate list (including preferences)

- **Manage Proctors**
    - Add, update, delete proctor
    - Search by proctor ID, work unit, name, gender
    - Display proctor list

- **Admission Results**
    - List admitted candidates and their qualifying scores by preference ID
    - List admitted candidates by university code and major code
    - Sort admitted list by preference (descending exam score)

- **File Operations**
    - Save admitted candidates by preference ID to file
    - Load proctor and candidate lists from file

- **Modern Java Utilization**
    - Menu-driven interface
    - Lambda expressions & Stream API for efficient data handling
    - Enums for type safety
    - Custom Exceptions for validation
    - Advanced sorting with Comparator & Comparable

---

## 🚀 Getting Started

1. **Clone the repository:**
   ```bash
   git clone https://github.com/tank22BKU/admission-project.git
   cd src
   cd DashBoard
   ```

2. **Compile the project:**
   ```bash
   javac options.jav
   ```

3. **Run the application:**
   ```bash
   java options
   ```

---

## 💡 Example Menu

```
1. Manage Candidates
2. Manage Preferences
3. Manage Proctors
4. Admission Results
5. File Operations
0. Exit
```

---

## 📝 Requirements

- Java 8 or higher
- Console-based interface

---

## 📂 Project Structure

```
src/
 ├── DashBoard/           
 ├── Management/         
 ├── Object/            
 └── Readme
```

---

## 🤝 Contribution

Contributions are welcome! Please fork this repo and submit a pull request.

---

## ✨ Author

- [tank22BKU](https://github.com/tank22BKU)
