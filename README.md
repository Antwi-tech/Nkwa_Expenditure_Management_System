
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
3. Run the `Menu.addExpenditure` class from your IDE or terminal

```bash
javac Menu.addExpenditure.java
java Menu.addExpenditure
````

---

## 🧠 How It Works

### 1. `Menu.addExpenditure.java`

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
├── Menu.addExpenditure.java      # Main expenditure manager
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

> For academic, educational, or internal project use.


-------------------------------------------------------------------------

Here’s a **complete and detailed README** explaining your `viewExpenditure` and `addExpenditure` Java program, including the custom data structures (`arrayList`, `linkedList`, `hashmap`) and how they’re used. This will help anyone understand your project structure, design choices, and usage.

---

# 📘 Expenditure Tracking System (Java CLI)

This Java-based application allows users to **add**, **store**, **view**, and **search** expenditure records for a company. It provides persistent data storage via text files and uses **custom data structures** like `linkedList`, `arrayList`, and `hashmap`.

---

## 📁 Packages and Structure

```
Menu/
│   addExpenditure.java       ← Adds new expenditure records
│   viewExpenditure.java      ← Views and searches existing expenditures
│   expenditures.txt          ← Data storage file (formatted manually)
DSA/
│   linkedList.java           ← Custom singly linked list
│   arrayList.java            ← Custom resizable array list
│   hashmap.java              ← Custom key-value storage
```

---

## ✅ Features

### From `addExpenditure.java`:

* Add new expenditure via CLI
* Input validation (date, double, non-empty fields)
* Saves entries to a readable `.txt` file
* Tracks entry history using `linkedList`
* Stores in `hashmap` for quick lookup

### From `viewExpenditure.java`:

* Load expenditures from file on startup
* View by:

  * Chronological order
  * Sorted by category (A-Z)
  * Date range
  * Category
  * Bank account
* Uses:

  * `linkedList` to preserve insertion order
  * Custom `MyMap` for key-based access
  * `arrayList` for category sorting

---

## 🔍 viewExpenditure.java — Deep Dive

### 🔄 `loadExpendituresFromFile()`

* Reads the `expenditures.txt` file line-by-line.
* Extracts fields like `code`, `amount`, `date`, `category`, etc.
* Stores each entry in:

  * `MyMap` (a simplified internal `HashMap`)
  * `historyList` (to track entry order)

### 📋 `listChronological()`

* Prints entries in insertion order using `linkedList`.

### 🗂️ `sortByCategory()`

* Converts `linkedList` to array.
* Sorts using bubble sort by category (A–Z).
* Uses custom `arrayList`.

### 🔎 `searchByDateRange()`

* Accepts a start and end date from user.
* Displays entries within that range.

### 🔍 `searchByCategory()` and `searchByAccount()`

* Filters entries matching a specific category or bank account.

### 🧠 `MyMap` (Minimal HashMap)

Custom internal key-value store:

```java
private static class MyMap {
    arrayList<Entry> entries;

    private static class Entry {
        String key;
        Expenditure value;
    }
}
```

* Performs linear search to get/put.
* Avoids dependency on Java Collections.

---

## ➕ addExpenditure.java — Deep Dive

### 📥 `spending()`

* CLI prompts user for:

  * Item Code
  * Amount (double)
  * Date (LocalDate)
  * Phase (Construction, Marketing, Sales)
  * Category
  * Bank Account ID
* Each input validated with up to 3 tries.

### 🧾 `Expenditure` Class

Encapsulates expenditure data:

```java
class Expenditure {
    String code;
    double amount;
    LocalDate date;
    String phase;
    String category;
    String accountId;
}
```

### 💾 `saveToFile()`

Writes expenditure in human-readable form to `Menu/expenditures.txt`.

---

## 📦 DSA Package — Custom Data Structures

### 🔗 `linkedList`

Simplified singly-linked list used to:

* Track insertion order
* Enable iteration over entries (via `.toArray()`)

```java
public class linkedList {
    public void addLast(String value);
    public String[] toArray();
    public void printList();
}
```

### 📚 `arrayList<T>`

Custom resizable array with:

* `.add()`
* `.get()`, `.set()`
* `.swap()` → used for sorting
* `.size()`

```java
public class arrayList<T> {
    public void add(T item);
    public T get(int i);
    public void swap(int i, int j);
}
```

### 🧰 `hashmap<K,V>`

In `addExpenditure`, this is used for fast access:

```java
hashmap<String, Expenditure> expenditureMap;
```

Likely implemented with:

* Buckets
* Hashing using `key.hashCode() % capacity`

---

## 📄 expenditures.txt — Format

```txt
Code: EXP001
Amount: 4500.0
Date: 2025-07-01
Phase: Construction
Category: Materials
Account: 987654321

Code: EXP002
Amount: 1200.0
Date: 2025-07-05
Phase: Sales
Category: Travel
Account: 123456789
```

---

## 🧪 Example Usage

### Adding:

```
Item code: EXP123
Amount: 1000
Date: 2025-07-10
Phase: Marketing
Category: Ads
Bank Account ID: 555555555
```

### Viewing:

```
1. List all (chronological)
2. Sort by category (A-Z)
3. Search by date range
4. Search by category
5. Search by bank account
```

---

## 🔧 Why Use Custom Structures?

* Great for academic or learning environments.
* Helps understand internals of:

  * `ArrayList` resizing
  * `LinkedList` traversal
  * `HashMap` key-value mapping

---

## 🚀 Suggestions for Improvement

* Add saving in `.csv` format (optional)
* Replace `MyMap` with `hashmap` for consistency
* Use `java.util` collections in future upgrades
* Add GUI or web front-end

---

## 📌 Author Notes

* Structure is modular and easy to maintain.
* Input validation ensures user-friendly experience.
* All expenditure data is persistently stored and easily retrievable.

