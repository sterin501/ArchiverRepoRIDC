It is RIDC code to automate the replication .
There issues when you try to replicate Framework folders between two servers
This code will do below things

Step 1 : Getting Batch Files of Archiver Collection  .....
Step 2 : Getting Contens .........
Step 3 :  Folder Verification on Target  .........
Step 4 : Transferring to Target ...
Step 5 : Getting Archiver Transfer status  ...
Step 6 : Verifying  Transfer and Content Count  ...
Step 7 : Deleting Batchlist .......


Configration :

Edit the config.properties file with source ,target URLs and Archiver collection details 

Example to run


java TransferAutomation


Other java classes

GetArchiverStaus.java  : To get the archiver status
VerifyFFinTarger.java  : To verify the target folder 
