CREATE TABLE PERSON (
         ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
         FIRST_NAME VARCHAR(100),
         LAST_NAME VARCHAR(100),
         MONEY DOUBLE,
         cur_timestamp TIMESTAMP DEFAULT NOW()
       );
