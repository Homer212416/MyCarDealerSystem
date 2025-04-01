Google Drive
https://drive.google.com/drive/folders/1Wgdptkqnu7HH3_lLd_BGixPG3tOHt2Id

Original Version
https://github.com/fhm-nzh/car-dealership-system

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


