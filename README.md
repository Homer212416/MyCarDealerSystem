Google Drive
https://drive.google.com/drive/folders/1Wgdptkqnu7HH3_lLd_BGixPG3tOHt2Id

Original Version
https://github.com/fhm-nzh/car-dealership-system

**demo0.31 updates:**
it can add cars now although has some other issues

**demo0.29 updates:**

dealershipInfoPage:
  - only keep delete button
  - added listener for delete button
      - add confirmation dialog in listen
  - reset dealership info source to Dealership class

dealership:
  - added a default constructor
  - added exists()
  - added bufferAllInfo()
  - added getInfoGUI()
  - added delete()
  - deleted existsAndSet()

DBManager:
  - cleaned up constructor
  - cleaned up initDB()
  - changed dealerships table
