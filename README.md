A Library Management System is a software built to handle the primary housekeeping functions of a library, It help libraries keep track of the books and their checkouts, as well as members’ subscriptions and profiles.

#### System Requirements
We will focus on the following set of requirements while designing the Library Management System:

- Any library member should be able to search books by their title, author, subject category as well by the publication date.
- Each book will have a unique identification number and other details including a rack number which will help to physically locate the book.
- There could be more than one copy of a book, and library members should be able to check-out and reserve any copy. We will call each copy of a book, a book item.
- The system should be able to retrieve information like who took a particular book or what are the books checked-out by a specific library member.
- There should be a maximum limit (5) on how many books a member can check-out.
- There should be a maximum limit (10) on how many days a member can keep a book.
- The system should be able to collect fines for books returned after the due date.
- Members should be able to reserve books that are not currently available.

#### Use Case Diagram
We have 2 main actors in our system:

- Librarian: Mainly responsible for adding and modifying books, book items, and users. The Librarian can also issue, reserve, and return book items.
- Member: All members can search the catalog, as well as check-out, reserve, renew, and return a book.

Here are the top use cases of the Library Management System:
- Add/Remove/Edit book: To add, remove or modify a book or book item.
- Search Books: To search books by title, author, subject or publication date.
- Register new account/cancel membership: To add a new member or cancel the membership of an existing member.
- Check-out book: To borrow a book from the library.
- Reserve book: To reserve a book which is not currently available.
- Renew a book: To reborrow an already checked-out book.
- Return a book: To return a book to the library which was issued to a member.

#### Class Diagram
Here are the main classes of our Library Management System:

- Library: The central part of the organization for which this software has been designed. It has attributes like ‘Name’ to distinguish it from any other libraries and ‘Address’ to describe its location.
- Book: The basic building block of the system. Every book will have ISBN, Title, Subject, Publishers, etc.
- BookItem: Any book can have multiple copies, each copy will be considered a book item in our system. Each book item will have a unique barcode.
- Account: We will have two types of accounts in the system, one will be a general member, and the other will be a librarian.
- BookReservation: Responsible for managing reservations against book items.
- BookLending: Manage the checking-out of book items.
- Author: This class will encapsulate a book author.
