# email_auto_generator
This is a application that allows user to generate many files at once by using a template and a csv file.

What you need to prepare:
1. a template TXT file
2. a input information CSV file

Then you can generate all the output files at once.

The program will substitute the [[placeholder]] in your template with the actual information in the csv file with the corresponding header.

## Example:

> Your cvs file (with 2 rows of info) :
>-
>
>name   | phone   | email       <-- this is header
>
>Jack   | 123456  | jack@email  <-- 1st row
>
>Mark   | 456123  | mark@email  <-- 2nd row


 
> Your template.txt file content:
>-
>
>Hi, my name is [[name]]
>
>And my email is : [[email]]
>
>Here is my phone : [[phone]]


## By run this code, we will generate 2 output files (since you have 2 rows in .csv):

>**The 1st file will look like this:**
>
>Hi, my name is Jack
>
>And my email is : jack@email
>
>Here is my phone : 123456

> 
>**The 2nd file will look like this:**
>
>Hi, my name is Mark
>
>And my email is : mark@email
>
>Here is my phone : 456123


# Input your command in the terminal
Valid commands are:

**--email** : only generate email messages

**--email-template \<filename>** : accept a filename that holds the email
template

**--letter** : only generate letters

**--letter-template \<filename>** : accept a filename that holds the letter
template

**--output-dir \<path>** : accept the name of a folder, all output is placed in this folder

**--csv-file \<path>** : accept the name of the csv file to process

>Example:
>-
>_--email --email-template myTemplate.txt --output-dir storage --csv-file info.csv_
>
>By input this argument, it will use the info.csv and myTemplate.txt to generate output files and store in storage folder
