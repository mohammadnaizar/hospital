# Mini Hospital Emergency Management System

**Module:** CIT300 – Data Structures and Algorithms
**Assignment:** Individual Mid Assignment
**Student:** Asath (Index: 23DA2-0743)
**Language:** Java (no external libraries — all data structures implemented manually)

## Overview

A console-based (menu-driven) system that simulates a hospital emergency
department. It demonstrates four data structures, each implemented from
scratch (no `java.util.Stack`, `java.util.Queue`, `java.util.LinkedList`,
or `java.util.TreeMap` shortcuts):

| Requirement | Data Structure | Class |
|---|---|---|
| Patient Records | Binary Search Tree | `PatientBST.java` |
| Emergency Patient Queue | Queue (FIFO) | `EmergencyQueue.java` |
| Treatment History | Stack (LIFO) | `TreatmentStack.java` |
| Patient Visit History | Singly Linked List | `VisitHistory.java` |

## Project Structure

```
HospitalEMS/
├── README.md
└── src/
    └── hospital/
        ├── Patient.java                  # Patient model (holds a VisitHistory)
        ├── PatientBST.java                # BST: insert, search, delete, in-order traversal
        ├── EmergencyQueue.java            # Queue: enqueue, dequeue, display, empty check
        ├── TreatmentRecord.java           # Treatment record model
        ├── TreatmentStack.java            # Stack: push, pop, display, empty check
        ├── VisitHistory.java              # Singly Linked List: add, remove, search, display
        └── HospitalManagementSystem.java  # Main class — console menu
```

## How Each Data Structure Is Used

- **Patient Records (BST)** — Patients are keyed by `Patient ID` in a binary
  search tree, giving O(log n) average search/insert/delete and an
  in-order traversal that naturally lists patients in ascending ID order.
  Deletion handles all three standard BST cases (leaf, one child, two
  children via in-order successor).

- **Emergency Patient Queue (Queue)** — A linked-node queue with `front`
  and `rear` pointers. `enqueue()` adds to the rear, `dequeue()` removes
  from the front, enforcing strict FIFO order for who gets treated next.

- **Treatment History (Stack)** — A linked-node stack with a `top`
  pointer. Each completed treatment is `push()`ed on; `pop()` removes the
  most recently completed record first (LIFO), useful for "undo" / most
  recent activity views.

- **Patient Visit History (Singly Linked List)** — Each `Patient` object
  owns its own `VisitHistory` linked list of past visits (visit ID, date,
  doctor, diagnosis, treatment), supporting add, remove-by-ID,
  search-by-ID, and full display.

## How to Compile and Run

Requires a JDK (Java 17+ recommended; developed/tested on Java 21).

```bash
# From the project root
javac -d bin src/hospital/*.java
java -cp bin hospital.HospitalManagementSystem
```

You'll see a main menu:

```
------------------- MAIN MENU -------------------
1. Patient Records          (Binary Search Tree)
2. Emergency Patient Queue   (Queue)
3. Treatment History         (Stack)
4. Patient Visit History     (Singly Linked List)
0. Exit
--------------------------------------------------
```

### Suggested demo flow (for the video / screenshots)
1. Patient Records → Insert 2–3 patients.
2. Patient Records → Display all (in-order traversal) to show BST ordering.
3. Patient Records → Search / Delete a patient.
4. Emergency Queue → Enqueue a couple of registered patients, Display queue.
5. Emergency Queue → Dequeue to show FIFO order.
6. Treatment History → Push a completed treatment, Display, then Pop.
7. Visit History → Add a visit for a patient, Display, Search, Remove.

## Testing

The program was compiled with `javac` and exercised end-to-end (every
menu operation across all four data structures, including empty-state
and not-found edge cases) using a scripted input run before packaging.


