# Ocean Explorer Kata (Java)

**Author: Samarth Chandel**

---

## 📌 Problem Statement

This project simulates a remotely controlled probe exploring the ocean floor.

The probe operates on a 2D grid and follows a sequence of commands:
- **F** → Move Forward
- **B** → Move Backward
- **L** → Turn Left
- **R** → Turn Right

The probe:
- Must stay within the grid boundaries  
- Must avoid obstacles  
- Tracks all visited coordinates  
- Stops execution if an obstacle is encountered  

---

## 🧠 Approach

The solution is designed using Object-Oriented Programming principles.

The system is divided into small, focused components:
- A coordinate class to represent positions  
- A direction enum to manage movement and rotation  
- A grid class to handle boundaries and obstacles  
- A probe class to execute commands and manage state  

Commands are processed using a mapping approach (`Map<Character, Runnable>`), which keeps the logic simple and easy to extend.

---

## ⚙️ Features

- Grid-based navigation  
- Forward and backward movement  
- Direction rotation (left/right)  
- Obstacle detection with exception handling  
- Boundary-safe movement  
- Tracks visited path (no duplicates, order preserved)  
- Clean and readable console output  

---

## ⚠️ Assumptions

- The grid is rectangular and has fixed dimensions  
- The probe always starts within valid bounds  
- Commands are valid and case-sensitive (`F`, `B`, `L`, `R`)  
- Execution stops immediately when an obstacle is encountered  

---

## 🧪 Edge Cases Handled

- Movement outside grid boundaries is ignored  
- Invalid commands result in an exception  
- Probe stops when encountering an obstacle  
- Duplicate positions are not stored in the visited path  
- Null or empty command input is handled safely  

---

## ▶️ How to Run

### Prerequisites:
- Java (JDK 17 or above)

### Run the application:
- Open `OceanExplorerDemo.java`
- Click **Run ▶️** in VS Code

### Run tests:
- Open `ProbeTest.java`
- Click **Run Test**

---

## 🧾 Example Output
=== Ocean Explorer Simulation Started ===

Commands Executed: FFRFFLBBF

=== Final Status ===
Final Position : (2, 1)
Facing Direction : N

Visited Path:
(0, 0) -> (0, 1) -> (0, 2) -> ...

=== Simulation Ended ===


---

## 🧪 Test Coverage

Unit tests are implemented using JUnit 5 and cover:
- Movement and rotation  
- Boundary conditions  
- Obstacle handling  
- Invalid command scenarios  
- Visited path tracking  

---

## 🤖 AI Usage

AI tools were used to assist with improving code structure, formatting, and readability.  
However, the core logic, problem understanding, and design decisions were implemented independently.

---

## 📌 Note

This implementation focuses on solving the core navigation problem.

In a real-world system, additional considerations such as logging, APIs, and network reliability would be required.

---

## 👨‍💻 Author

**Samarth Chandel**