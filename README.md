

## 📘 Add Expenditure

````markdown
# 🧾 Expenditure Tracker Program (Java)

This Java program allows users to record and manage expenditures through the terminal. It stores each expenditure entry in memory (using custom data structures) and appends them to a `.txt` file for long-term storage.

---

## 📌 Features

- Input and validation of:
  - Item code
  - Amount
  - Date (format: YYYY-MM-DD)
  - Phase (construction, marketing, or sales)
  - Category
  - Bank Account ID
- Error checking with 3 attempts max per field
- Inputs are case-insensitive and stored consistently
- Expenditures are saved to a readable `expenditures.txt` file
- Uses custom implementations of:
  - `hashmap` to store expenditure records
  - `linkedList` to store insertion order (history)

---

## 🏁 How to Run

1. Clone or download the project
2. Make sure you have Java 8 or later installed
3. Run the `addExpenditure` class from your IDE or terminal

```bash
javac addExpenditure.java
java addExpenditure
````

---

## 🧠 How It Works

### 1. `addExpenditure.java`

The main class that:

* Captures user input
* Validates each field
* Creates an `Expenditure` object
* Saves it in memory (`hashmap` and `linkedList`)
* Persists it to a `.txt` file
* Allows user to enter multiple expenditures

### 2. File Storage (`expenditures.txt`)

Each expenditure is appended (not overwritten) to a text file for future reference. The data is stored in a readable format like:

```
Code: A001
Amount: 2500.0
Date: 2025-07-12
Phase: Construction
Category: Materials
Account: ACC123

```

### 3. Input Validation

Each user input is checked:

* Text fields cannot be empty
* Amount must be a valid, non-negative number
* Date must follow the format `YYYY-MM-DD`
* Only 3 invalid attempts allowed before the program exits

---

## 🧱 Custom Data Structures

### 🧮 `hashmap<K, V>`

This is a simplified version of Java’s built-in `HashMap`:

#### 🔹 Purpose

Stores key-value pairs where:

* **Key** = `itemCode`
* **Value** = `Expenditure` object

#### 🔹 How it works:

* Internally uses an array of buckets (size 16)
* Uses `hashCode()` of the key to calculate an index
* Uses **chaining** (linked list per bucket) to resolve hash collisions

#### 🔹 Key methods:

* `put(key, value)` — Adds or updates a key
* `get(key)` — Retrieves the value for a given key
* `printAll()` — Prints all key-value pairs

---

### 🔗 `linkedList`

A simple singly linked list implementation to keep track of insertion history:

#### 🔹 Purpose

Stores the order of `itemCode`s added.

#### 🔹 Key methods:

* `addLast(data)` — Appends new node at the end
* `printList()` — Displays history as `A001 -> A002 -> A003 -> null`

---

## 🔁 Example Flow

```text
----- Fill Up All Fields -----
Item code: A001
Amount: 1200
Date of issue (YYYY-MM-DD): 2025-07-12
Phase (Construction/Marketing/Sales): Marketing
Category: Advertising
Bank Account ID: ACC890

✅ Expenditure Added Successfully!

A001 => Code: A001
Amount: 1200.0
Date: 2025-07-12
Phase: Marketing
Category: Advertising
Account: ACC890

Expenditure History: A001 -> null

➕ Add another expenditure? (yes/no): no
👋 Exiting. Goodbye!
```

---

## 📂 File Structure

```
project-root/
│
├── DSA/
│   ├── hashmap.java         # Custom HashMap implementation
│   └── linkedList.java      # Custom LinkedList implementation
│
├── addExpenditure.java      # Main expenditure manager
├── expenditures.txt         # Where all entries are saved
└── README.md                # 📘 You're reading it!
```

---

## 📌 Notes

* No external libraries required — fully written in core Java
* Can be run in any IDE like IntelliJ or Eclipse
* Easily extensible for:

    * File import
    * Budget limits
    * GUI interface (JavaFX or Swing)

---

## ✍️ Author


> For academic, educational, or internal project use.
