Google Drive
https://drive.google.com/drive/folders/1Wgdptkqnu7HH3_lLd_BGixPG3tOHt2Id

Original Version
https://github.com/fhm-nzh/car-dealership-system
***new InventoryPage***

*NEWEST UPDATES*
DelearshipDAO: fixed error and now drops all tables and then has new empty tables created

Main: no longer creates a new dealership row on opening of program

DBManager: Now has a close connection function and when running init checks that all tables are present
          NOTE: IF YOU NEED TO DROP A TABLE YOU NEED TO CLOSE THE CONNECTION AND THE CALL A NEW INIT ON THE DATABASE

Account Manage: edit no longer produces error if click it more than 3 times          
                delete now remove user from tables and refreshed the page
                add new user now adds them to the table and refreshes the page

GUI: scaling will remain consistent throughout the windows even when they have been rescaled


can remove search value

can remove all filters

filter automatically disable when result is not present

size and capacity correctly populate
**linked mainPages update**

Main:
makes sure atleast on user is in usersInfo table so that program cann run properly

User: 
getPageSecurity(int userID) now functioning

UserLayer: 
getPageSecurity(int userID) now functioning

DBManager:
Now has vehicleTable

All Controllers except for firstLaunchPageController and loginPageController now take UserID as a paramater.
Right now it is manual but when function should get userID from login info

Inventory, PastSales, DealershipInfo, and accountManage page are now linked and page drop down functions


