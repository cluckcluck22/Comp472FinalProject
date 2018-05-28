# Comp472FinalProject: Candy Crush Solver

What is this project
----------------------------------
This project is a weak AI system that was desigened to solve a version of the classic slide puzzle game. It takes in a list of board states, then outputs the path the empty piece of the board would have to move to in order for the puzzle to be completed. A completed state for this type of puzzle was the top row and the bottom row of a 5 x 3 board to be symettrical over the x axis.

Breakdown of the Project:
----------------------------------
This designed solution broke the problem down into four parts. They are listed below

->Input/output handling of the game boards/ solutions

->Determining if a generated state of the board was a finished(goal) state that our AI had reached

->Implementing a version of A* pathfinding to traverse the different states of the board in order to find a solution

->Development of Heuristics that would help find the shortest solution path for the game while reducing the search time.

Breakdown of who did what:
----------------------------------

Eric Davies: Implemntation of A* pathfinding with optimizations to list storage of visited states

Jennifer Sunahara: Development of the heuristics used in the final project, along with testing different blends of the implemented heuristics

Jesse Tsang: Developed heuristics that did not get used in the final project build along with developing the file handling functionality

Fatin Hossain: Worked on unimplemented heuristics along with a goal state checking function.
