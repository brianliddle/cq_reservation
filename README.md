# cq_reservation

===============================
Campspot Programming Challenge
===============================

Most destination resorts don’t host guests who are willing to stay for only one night. Their guests are not simply passing through the area; they’ve often travelled far with lots of luggage specifically to stay there, and it’s not worth it for them to stay one night. As a result, a reservation system for resorts needs to prevent one night gaps in its reservation grid, or schedule of inventory. If one-night gaps occur on the grid, they will not be sold, and this results in lost income for the resort owner. For example, Campspot uses a one-night gap rule, as this is a common problem for camping resort owners. However, owners’ gap problems aren’t limited to one-night gaps. Some resorts only host guests who stay a minimum three or four nights. Those resorts may want to prevent one-, two-, and three-night gaps.

To support this use case, Campspot has a configurable business rule called a “gap rule” that allows resort owners to define which types of gaps are not allowed.

Your task is to implement the most basic version of a gap rule, which prevents new reservations on a campsite that will create gaps of a specified size.

------------------------------------

Deliverables we expect from you include:

   1. Souce code for executable program that takes in a standard JSON input file (described below) and returns which campsites are available to book
   2. Executable test cases for your program
   3. Written documentation that explains:
      a. How to build and run your program and tests
      b. A high-level description of your approach to solving the problem
      c. Any assumptions or special considerations that were made

------------------------------------

We've provided a sample input file (attached to this email), which you can use to test your program.

The expected output of the sample file is:
   5 - "Daniel Boone Bungalow"
   6 - "Teddy Roosevelt Tent Site"
   8 - "Bear Grylls Cozy Cave"
   9 - "Wyatt Earp Corral"

The JSON input file contains a single object with the following keys:

   "search": the dates of the reservation we are hoping to make
   "gapRules": an array of different gap rules. Each rule has a single property "gapSize" which is the size of the disallowed gap.
   "campsites": An array of the campsites that are available to book, each has an id and name.
   "reservations": An array of the existing reservations that must be considered. Each has a campsite ID, start date, and end date
