
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




-----------------
Absolutely! Here's a **detailed README** explaining:

1. How the `manageCategories` module works
2. How the custom `hashset` is implemented and used
3. How categories are loaded from `expenditures.txt`
4. Error handling and safeguards

---

## 📝 `manageCategories` Module — README

### 📂 Purpose:

The `manageCategories` module is used to **manage expenditure categories**. It allows the user to:

* View all unique categories used so far
* Add a new category
* Remove an existing category

All categories are **automatically loaded** from the `expenditures.txt` file on startup.

---

### ✅ Features:

| Feature                    | Description                                            |
| -------------------------- | ------------------------------------------------------ |
| `listcategories()`         | Prints all unique categories stored in the program     |
| `addcategory()`            | Lets user add a new category (avoids duplicates)       |
| `deletecategory()`         | Lets user remove an existing category                  |
| `loadCategoriesFromFile()` | Extracts categories from the saved expenditure records |

---

### 📁 File Dependency:

* `Menu/expenditures.txt` — must contain entries with lines like `Category: Food`
  This file is parsed at the start to build a unique category list.

---

### 🧠 Internal Data Structure: `hashset`

Categories are stored in a **custom HashSet implementation** called `hashset` in the `DSA` package.

---

## 🧱 `hashset` Implementation — How It Works

This is a simplified version of Java’s built-in `HashSet`.

### 💡 Key Concepts:

* **Backed by an array of buckets**, each a linked list of entries (chaining)
* **No duplicates** allowed — values are only added if not already present
* **Hashing logic** is applied via `.hashCode()` on strings

### 📦 Basic Structure

```java
public class hashset {
    private class Node { String key; Node next; }
    private Node[] buckets = new Node[16];
    
    public void add(String key);
    public boolean contains(String key);
    public void remove(String key);
    public void printAll();
}
```

---

### ✅ `hashset` Operations

| Method          | Description                                       |
| --------------- | ------------------------------------------------- |
| `add(key)`      | Adds a value to the set if it's not already there |
| `contains(key)` | Checks whether a value exists in the set          |
| `remove(key)`   | Deletes a value from the set if it exists         |
| `printAll()`    | Displays all current values in the set            |

---

## 🧑‍💻 How It Works in `manageCategories`

1. `hashset` is initialized as `categorySet`
2. `loadCategoriesFromFile()` reads all `"Category: X"` lines from the expenditures file
3. Each unique value is added to the set
4. When the user wants to:

  * List → `printAll()` from the set
  * Add → check with `contains()`, then `add()`
  * Delete → check with `contains()`, then `remove()`

---

## ⚠️ Error Handling & Safeguards

| Area                  | How It’s Handled                                    |
| --------------------- | --------------------------------------------------- |
| File Read Errors      | Caught with `try-catch`, descriptive error printed  |
| Input Validation      | Prompts user again if invalid or empty              |
| Duplicate Additions   | Prevented using `contains()`                        |
| Deleting Non-existent | Message shown instead of error                      |
| Menu Selection        | Handles invalid numeric input with friendly message |

---

## 🧪 Sample Output

```
📂 Category Manager:
1. List Categories
2. Add Category
3. Delete Category
0. Back
Choose an option: 1

📋 Current Categories:
→ Food
→ Marketing
→ Utilities
→ Travel
```

---

## 🗂️ Directory Structure Example

```
/DSA/
 └── hashset.java

/Menu/
 ├── manageCategories.java
 └── expenditures.txt
```

---

## 🔚 Final Notes

* You can integrate `manageCategories` with your main menu
* If you want persistence (saving added/removed categories to a file), that can be implemented too — let me know!
* The current `hashset` implementation is intentionally lightweight for clarity and educational purposes.

---

Here is a detailed **README** file that explains the structure, functionality, and usage of your project, including how the `bankAccount` class interacts with your custom `DSA` (Data Structure Algorithms) classes and file storage.

---

# 📘 Bank Account Management System — README

## 📂 Project Structure

This project simulates a **Bank Account Management System** using **custom-built data structures**:

* `hashmap` — for storing account records by ID.
* `linkedList` — for tracking expenditures per account.
* `priorityqueue` — for identifying accounts with the lowest balances.

All account data is persisted to a file `accounts.txt`.

### 🗃 Packages:

* `Menu` — contains the user-facing class `bankAccount`.
* `DSA` — contains:

  * `hashmap<K, V>`
  * `linkedList`
  * `priorityqueue<T>`
  * `arrayList<T>` — supports the priority queue.

---

## 🧠 Features

### `Menu.bankAccount`

Handles **user interaction** and implements these features:

| Option | Action          | Description                                                                            |
| ------ | --------------- | -------------------------------------------------------------------------------------- |
| 1      | `listaccount()` | Lists all accounts from `accounts.txt` and shows lowest balances using priority queue. |
| 2      | `addaccount()`  | Adds a new account, writes to `accounts.txt`, stores in hashmap, tracks expenditures.  |
| 3      | `topup()`       | Adds money to an account and updates `accounts.txt`.                                   |

Accounts are stored in a custom-built `hashmap` using the `account ID` as the key. Each account:

* Is a `BankAccount` object.
* Contains a `linkedList` of expenditures.
* Is inserted into a `priorityqueue` for balance-based alerts.

---

## 📄 File: `accounts.txt`

Used to **persist** account information across sessions.

**Structure per line**:

```
<accountID>,<accountName>,<balance>
```

### Example:

```
001,John Doe,1000.0
002,Jane Smith,750.5
```

---

## 🏗 Custom Data Structures (DSA Package)

### ✅ `hashmap<K, V>`

* Custom implementation with chaining for collisions.
* Used to store account records (`<accountID> -> BankAccount`).

### ✅ `linkedList`

* Singly-linked list.
* Stores expenditure logs for each account.
* Methods:

  * `addLast(String)`
  * `printList()`
  * `toArray()`

### ✅ `priorityqueue<T extends Comparable<T>>`

* Implements **Min-Heap**.
* Used to always keep track of accounts with the **lowest balances**.
* Supports:

  * `add(T)`
  * `poll()`
  * `peek()`
  * `printAll()`

### ✅ `arrayList<T>`

* Custom dynamic array used internally by `priorityqueue`.

---

## ⚙️ Usage

1. Compile the project:

   ```bash
   javac Menu/*.java DSA/*.java
   ```

2. Run the main program that calls:

   ```java
   bankAccount.account();
   ```

3. Follow on-screen menu:

   ```
   1. List account
   2. Add Account
   3. Top-up
   ```

---

## 🛠 Error Handling

* ✅ File not found errors are handled when reading `accounts.txt`.
* ✅ Input validations are done (e.g., balance must be a number).
* ✅ Graceful messages on invalid account lookup or malformed lines.

---

## 🚀 Example Flow

### Add Account:

```
Enter Account ID: 003
Enter Name: Alice
Enter Initial Balance: 500.0
```

→ Adds to `accounts.txt`, hashmap, linked list, and priority queue.

### Top-up:

```
Enter Account ID: 003
Enter Amount to Top-up: 100
```

→ Updates balance in `accounts.txt` and in-memory data structures.

### List Accounts:

→ Displays all accounts and shows top 3 accounts with **lowest balance** using priority queue.

---

## ✅ To Do / Extend

* Add UI wrapper or CLI loop.
* Add withdrawal and delete account options.
* Save expenditure history to file (optional).

---

