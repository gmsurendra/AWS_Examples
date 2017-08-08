This is an example project for generating Random Data using andy gibsons DataFactory code.

The above sample generates a sample csv file with 3 million lines. 
We have added these columns to this file 
  1) bussiness Entity --> A string of length 4 
  2) branch number    --> An integer between 0 and 10000000
  3) account number   --> An integer between 0 and 10000000
  4) name             --> Account holder name, A random string
  5) address          --> Address of the account holder , A random Address.
  6) emailAddress     --> email address of the account holder, A random email address
  7) security number  --> Security number in which the account holder has a position, A random string of 8 charecters.
  8) settled qty      --> A random integer between 0 and 10000000
  9) trade qty        --> A random integer between 0 and 10000000
  
The loop has to be stopped and written onto the file, after every 1 million record in this program, as we are running 32 bit JVM although my machine and is capped with memory limit of 4GB of max memory usage, or else we might end up getting a Garbage collector out of memory error. 
