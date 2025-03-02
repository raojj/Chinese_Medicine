##### Functional Dependencies

课件： [Functional Dependencies (Part 1).pdf](../../../../ANU /Assignments/COMP 6240/Week5/Functional Dependencies (Part 1).pdf) 

​             [Functional Dependencies (Part 2).pdf](../../../../ANU /Assignments/COMP 6240/Week5/Functional Dependencies (Part 2).pdf) 

​             [Functional Dependencies (Part 3).pdf](../../../../ANU /Assignments/COMP 6240/Week5/Functional Dependencies (Part 3).pdf) 

**Database Design Issues**

We have seen the following database design issues so far:

Data inconsistency

Data redundancy

Update anomalies



**Why Functional Dependencies?**

We need some **formal way** of analysing whether a database schema is well-designed, or why one is better than another. 

FDs are developed to defifine the **goodness** and **badness** of (relational) database design in a formal way.

**Top down**: start with a relation schema and FDs, and produce smallerrelation schemas in certain normal form (called *normalisation*).

**Bottom up**: start with attributes and FDs, and produce relation schemas (*not popular in practice*).

**FDs tell us “relationship between and among attributes”!**



![截屏2023-08-20 10.58.33](/Users/linsiwei/Library/Application Support/typora-user-images/截屏2023-08-20 10.58.33.png)

**FD Definition**

A **FD** says that, within a relation, the values of some attributes determine

the values of other attributes.



If attributes *A**,* *B**,* *C* determine attributes *D**,* *E*, then we write

*{**A**,* *B*, *C**} → {**D,* *E}*

This means, if two tuples have the same values for *A**,* *B* and *C*, then

they must also have the same values for *D* and *E*. 

*A**,* *B* and *C* are the **determinant**, while *D* and *E* are the **dependent**.



**Formal Defifinition**

Let *R* be a relation schema.

A **FD** on *R* is an expression *X* *→* *Y* with attribute sets *X**,* *Y* *⊆* *R*.

A relation *r*(*R*) **satisfifies** *X* *→* *Y* **on** *R* if, for any two tuples *t*1*,* *t*2 *∈* *r*(*R*), whenever the tuples *t*1 and *t*2 coincide on values of *X*, they also coincide on values of *Y*. 

* ​      t*1[*X*] = *t*2[*X*]  

​                   *⇓* 

* ​       t*1[*Y*] = *t*2[*Y*]

A FD is **trivial** if it can *always* be satisfified, e.g.,

*{**A**,* *B**,* *C**} → {**C**}* 

*{**A**,* *B**,* *C**} → {**A**,* *B**}*

**Syntactical convention**: (1) Instead of *{**A**,* *B**,* *C**}*, we may use *ABC*. (2)

*A**,* *B**, . . .* for individual attributes and *X**,* *Y**, . . .* for sets of attributes.





![截屏2023-08-20 11.28.20](/Users/linsiwei/Library/Application Support/typora-user-images/截屏2023-08-20 11.28.20.png)



**How to Identify FDs in General?**

A functional dependency specififies a constraint on the relation schema that

must hold **at all times**.

In real-life applications, we often use the following approaches:

(1) **Analyse data requirements**

Can be provided in the form of discussion with application users

and/or data requirement specififications.

(2) **Analyse sample data**

Useful when application users are unavailable for consultation and/or

the document is incomplete.



**Identifying FDs - Analyse Data Requirements**

Consider the following relation schema:

RENTAL=*{*CustID,CustName,PropertyNo,DateStart,Owner*}* .

**Data requirements:**

1 

Each customer can be uniquely identifified by his or her customer ID.

*{*CustID*} → {*CustName*}* 

2 A customer cannot rent two or more properties from the same date.

*{*CustID, DateStart*} → {*PropertyNo*}* 

3 A customer cannot rent the same property more than once.

*{*PropertyNo, CustID*} → {*DateStart*}* 

4 

Each property can be uniquely identifified by its owner.

*{*Owner*} → {*PropertyNo*}*



![截屏2023-08-20 11.30.07](/Users/linsiwei/Library/Application Support/typora-user-images/截屏2023-08-20 11.30.07.png)



![截屏2023-08-20 11.37.11](/Users/linsiwei/Library/Application Support/typora-user-images/截屏2023-08-20 11.37.11.png)

To design a good database, we need to consider **all possible FDs**.

If each student works on one project and each project has one supervisor,

does each student have one project supervisor?

*{{*StudentID*}→{*ProjectNo*}*, 

*{*ProjectNo*}→{*Supervisor*}}*        *|*=      *{*StudentID*}→{*Supervisor*}*

We use the notation Σ *|*= *X* *→* *Y* to denote that *X* *→* *Y* is **implied** by the set Σ of FDs. We write Σ*∗* for all possible FDs **implied** by Σ.

![截屏2023-08-20 11.41.55](/Users/linsiwei/Library/Application Support/typora-user-images/截屏2023-08-20 11.41.55.png)