# Exception Handling Assignment

In this assignment, it is required to write a program that reads an ARXML file containing a list of containers, each with a unique ID, and reorders the containers alphabetically by their name sub- container “"SHORT-NAME".The program should write the reordered containers to anew ARXML file.
## Requirements
- The name of the arxml file shall be an argument which needs to passed through the command line.
- If the file is not having .arxml extension then you should trigger a user defined handled exception “NotVaildAutosarFileException”.
- If the file is empty, then you should trigger user defined unhandled exception “EmptyAutosarFileException”
- The output file shall be named as the same of the input file concatenated with “_mod.arxml” 
- • e.g. if the input was named “Rte_Ecuc.arxml” then the output should be
“Rte_Ecuc_mod.arxml”.
## Files Description
- **lab_06** : this is the implemention of program
- **Empty.arxml**: This is an empty arxml file for testing the empty file case
- **NormalCase.arxml**: This is  a normal arxml file for testing the normal case.
- **ExtensionTest.html**: This is the invalid extension file
- **testcases.bat**: This is the batch file that runs the program with different input files for testing purposes.
## How to run:
To run your program with a single input file, use the following command:

`java lab_06.java <your_file>`

This will create a new file called “NormalCase_mod.arxml” with the reordered containers.

To run your program with all the test files provided, use the following command:

`testcases.bat`

This will run your program with each of the test files and show the output on the console.
