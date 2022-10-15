## Notes

\> This is a place to put notes about the project obtained from the lecture(s).
___
We __mustn't__ use any `RuntimeException`s in our code, instead, use generic `Exception`s, those are checked exceptions.

Custom `Exception`s in `Java`:
* [linked article](https://www.javatpoint.com/custom-exception)

\> Consider implementing the so-called `factory method`.

***
\> **Ionel**: I moved the exceptions to the Setters because that's more logical... Michal don't kill me for making a new branch :)

___
Francisco Gomes ~ 8:24 PM
- Yeah, to avoid the messy, you can create two separate figures. Figure 1 with the class diagram of your project and all relationships (excluding exceptions), and Figure 2 just with the Exception hierarchy. By separating, we wont see the “arrows” between your classes but that’s fine because for the excepions classes, we only care about the hierarchy.

Francisco Gomes ~ 8:40 PM
Got this from a Student:
The design task 1 states that it should be done individually, but does this apply to the creation of the diagram?
- You can reuse the Figure itself, since the design is developed as a group. But I would expect a student to be able to create a diagram of their design “by themselves”.
  
Can my group share the diagram but have different justification?

- Yes, you can have the same Figure, but you must have justifications with your own words. I will be checking for plagiarism. The report is individual.
Furthermore, how do you implement the exceptions in the diagram / have you any material you can recommend that shows descibes the procedure?

- Same question as the one above from Kaisa, do two figures, one for your design, another for an Exception. There are no “special arrows” for an Exception. I will know that the class is anException if it extends from Exception.