## Introduction to Database Systems

1. ##### A **<u>database</u>** is a collection of related data.

Implicit properties:
（1） represents some **aspects of the real world**; 

 （2）a **logically coherent collection** of data;

 （3）designed and built for a **specific purpose**.



2. ##### A <u>**database management system**</u> (DBMS) is a collection of programs that enable users to create and maintain a database.

It is a general-purpose software system that facilitates the process of 

**defining**specifying data types, structures and constraints; 

**constructing:** storing data on some storage medium; 

**manipulating:** retrieving and manipulating data;

**sharing:** using data by multiple users/programs simultaneously.



3. A **database system** is part of information systems dealing with data retrieval and manipulation. It often refers to a DBMS plus a database.



4. Advantages of using a database system

**Data redundancy:** Data redundancy is controlled to ensure consistency and save the storage space.

**Data integrity:** Some integrity constraints can be enforced automatically by the DBMS.

**Data security:** Since the data is managed centrally, the DBMS ensures that the database access is through an authorized channel.





![截屏2023-08-20 10.31.54](/Users/linsiwei/Library/Application Support/typora-user-images/截屏2023-08-20 10.31.54.png)



**Three-level ANSI/SPARC Architecture**

**External Schema**

perspective of the user / application
 describes restructured parts of the database used in applications

**Conceptual or Logical Schema**

perspective of a community of users

describes what data is stored in the database and relationships among data (independent from their physical storage structures).

**Internal Schema**

perspective of the implementation / system realization

describes how data is stored in the database (e.g., physical storage structures).



**Derived Principles – Data Independence**

**Logical data independence**: change the conceptual/logical schemas without having to change external schemas or application programs

**Example:** If adding or removing entities, external schemas that refer only to the remaining data should not be affected.

**Physical data independence**: change the internal schemas without having to change the conceptual/logical schemas

**Example:** If physical files were reorganised, we should not have to change the conceptual/logical schemas.



基本术语



**Set Notation**

We need set notation to represent formal definitions in this course. A **set** is a collection of distinct elements.

Two basic properties of sets
 The elements in a set have no order.

e.g., {1, 2, 3} = {2, 3, 1}

Each element can not be in the set more than once.
 e.g., {Monday, Monday, Tuesday, Wednesday, Thursday, Friday, Friday} is Not a set. Note that **Multisets** allow to have duplicate elements.

**Two ways of specifying a set**

1. 1  {*x*1, . . . , *x**n*} (i.e., list all the elements in a set)

   { 2, 3, 4, 5 }
    {Sydney, Melbourne, Canberra} {} or ∅, i.e., the *empty* set.

2. 2  {*x*|φ} (i.e., describe the elements that satisfy a property φ)

   {x | x is a student currently enrolled in COMP7240} {x | x is an integer and x > 0}



![截屏2023-08-20 10.34.43](/Users/linsiwei/Library/Application Support/typora-user-images/截屏2023-08-20 10.34.43.png)

**Tuple Notation**

A **tuple** is an ordered list of *n* elements. (1,2,3,4,5)

(*Melbourne*, *Sydney* , *Canberra*)
 Two tuples are **equal** if they have the same elements in the same order.

(1, 2, 3) ̸= (2, 3, 1) (i.e., the order does matter!) The same element *can* be in a tuple twice.

(Monday, Monday, Tuesday, Wednesday, Thursday, Friday, Friday) is a tuple.

Ordered pairs are special cases of tuples.



**Cartesian Product of Sets**

The Cartesian product operation takes an ordered list of sets, and returns a set of tuples.

**Cartesian product** *D*1 × ... × *D**n* is the set of all possible combinations of values from the sets *D*1 , ..., *D**n* .

It contains all the tuples with the first element from the first set, the second element from the second set, ...

For example, *A* × *B* = {(*a*, *b*) | *a* ∈ *A* and *b* ∈ *B*}.
 If *A* = {2, 3} and *B* = {*Clubs*, *Diamonds*, *Hearts*, *Spades*}

Then *A* × *B* = {(2, *Clubs*), (2, *Diamonds*), (2, *Hearts*), (2, *Spades*), (3, *Clubs*), (3, *Diamonds*), (3, *Hearts*), (3, *Spades*)}.
 (2, *Clubs*) ∈ *A* × *B*, (*Spades*, 3) ∈/ *A* × *B*, (4, *Hearts*) ∈/ *A* × *B
\* {(3, *Clubs*), (3, *Diamonds*), (3, *Hearts*), (3, *Spades*)} ⊆ *A* ×B



**Relation Notation**

A **relation** is a subset of a Cartesian product of sets. **Example**

Let Z = {..., −1, 0, 1, 2, ...}, the set of all integers Let *R* = {(*x*,*y*)|*x* ∈ Z,*y* ∈ Z and *x* < *y*}.
 It is easy to see that *R* is a relation.

*R* ⊆ Z × Z.

(0,1) ∈ *R*, (−4,−2) ∈ *R
\* but (0, 0) ̸∈ *R*, (100, −2) ̸∈ *R*.

![截屏2023-08-20 10.38.21](/Users/linsiwei/Library/Application Support/typora-user-images/截屏2023-08-20 10.38.21.png)





![截屏2023-08-20 10.40.33](/Users/linsiwei/Library/Application Support/typora-user-images/截屏2023-08-20 10.40.33.png)

**The Basics**

A **relation schema** has a **relation name** and a list of **attributes**. Each attribute is associated with a **domain**.
 A relation schema can be expressed by

*R*(*A*1,...,*A**n*), or
 *R*(*A*1 :*dom*(*A*1),...,*A**n* :*dom*(*A**n*)),

where *A*1 , .., *A**n* are attributes of *R* and *dom*(*A**i* ) is the domain of *A**i* . **Example**: The relation schema in the previous example is

ENROL(StudentID, CourseNo, Semester, Status, EnrolDate), or

ENROL(StudentID: INT, CourseNo: STRING, Semester: STRING, Status: STRING, EnrolData: DATE).



**The Basics**

Let *R*(*A*1, ..., *A**n*) be a relation schema.

A **tuple** in *R* is a list *t* of values, i.e., *t* ∈ *dom*(*A*1) × ... × *dom*(*A**n*). **Example**: The previous example has the following tuples:

(456, COMP2400, 2016 S2, active, 25/05/2016)∈
 INT × STRING × STRING × STRING × DATE.

(458, COMP1130, 2016 S1, active, 20/02/2016)∈
 INT × STRING × STRING × STRING × DATE.

(459, COMP2400, 2016 S2, active, 11/06/2016)∈
 INT × STRING × STRING × STRING × DATE.

A **relation** *r*(*R*) is a set of tuples *r*(*R*) ⊆ *dom*(*A*1) × ... × *dom*(*A**n*). **Example**: The previous example has the following relation:

*r*(ENROL) ⊆ INT × STRING × STRING × STRING × DATE.



**The Basics**

A **relational database schema** *S* is
 a set of relation schemas *S* = {*R*1,...,*R**m*}, and a set of integrity constraints *IC*.

A **relational database state** of *S* is a set of relations such that

there is just one relation for each relation schema in *S*, and all the relations satisfy the integrity constraints *IC*.



##### Integrity Constraints

Constraints are **conditions** that must hold on *all* relations in a database state.

The *main types* of constraints in the relational data model include:

1.   **Domain constraints**;
2.   **Key constraints**;
3.   **Entity integrity constraints**;
4.   **Referential integrity constraints**.





Every value in a tuple must be from the **domain of its attribute**.

INT 

VARCHAR

 DATE

 SMALLINT

 NOT NULL





![截屏2023-08-20 10.45.51](/Users/linsiwei/Library/Application Support/typora-user-images/截屏2023-08-20 10.45.51.png)

**Key Constraints - Definitions**

Let *R*(*A*1, . . . , *A**n*) be a relation schema.
 A **superkey** *SK* of *R* is a subset of attributes of *R*, i.e., *SK* ⊆ {*A*1,...,*A**n*},

such that
 no two distinct tuples in *r*(*R*) can have the same value for *SK*.

A superkey *SK* of *R* is **minimal** if there is no other superkey *SK* on R. A minimal superkey is also known as a **candidate key**.

⊂ *SK* held

A **primary key** *PK* of *R* is a minimal superkey of *R*, (i.e., a primary key is one of the candidate keys). If a relation has only one candidate key then that would be the primary key.![截屏2023-08-20 10.46.25](/Users/linsiwei/Library/Application Support/typora-user-images/截屏2023-08-20 10.46.25.png)

![截屏2023-08-20 10.46.36](/Users/linsiwei/Library/Application Support/typora-user-images/截屏2023-08-20 10.46.36.png)

**Entity Integrity Constraints**

Specifying a primary key also invokes the entity integrity constraint.

**null** is a special value, which represents the value of an attribute that may be unknown or inapplicable.

The **entity integrity constraint** states that **no primary key value can be** **NULL**.

This is because primary key values are used to *identify* individual tuples in a relation.



**Note:** Other attributes of *R* may be constrained to disallow null values, even though they are not attributes in the primary key.





![截屏2023-08-20 10.48.49](/Users/linsiwei/Library/Application Support/typora-user-images/截屏2023-08-20 10.48.49.png)



**Data Definition Language – Alter and Drop Table**

The ALTER TABLE statement is used to modify an existing relation schema, including:

changing the name of a table;
 adding or dropping an attribute; changing the definition of an attribute; adding or dropping table constraints.

The DROP TABLE statement is used to remove an existing relation schema from a database schema.
