# proteinfolding

I used Genetic Algorithm and Java to do the project.

## What is protein folding
To be honest? I can't find the right words to explain it very easy, but I do think this 2 min video here give you a great overview to what it is. https://www.youtube.com/watch?v=KpedmJdrTpY

## How protein is displayed
Each protein chain consists of a string of "ones" and "zeros". Example: protein = {101011111011101101}. "Ones" are displayed in this project as a black 2d-square and "zeros" are displayed as a white 2d-square. 

![image](https://github.com/codingPineAppl3/proteinfolding/assets/51518928/9ac3e4fa-d838-4101-a8e1-428cb1be7254)

## What is the goal?
The goal is to calculate the maximum possible energy that can emerge after each protein folding. How can this be established? In the above image we can see the structure of a protein chain after one generation. Every time two non-chained black square are side by side, we count energy = +1. So in the first picture we have an energy = 2. In the 2nd picture we have the same protein chain after 13 generation. Here we have energy = 4. The higher the energy, the better the protein chain becomes.

![image](https://github.com/codingPineAppl3/proteinfolding/assets/51518928/2dbd04c2-a1c1-4a57-a4c9-2b53d8ebda6f)



