DROP TABLE data;

CREATE TABLE data( 
    celebrity_id SMALLINT NOT NULL AUTO_INCREMENT, 
    feature TEXT NOT NULL, 
    name VARCHAR(40), 
    gender CHAR(6), 
    birth SMALLINT, 
    PRIMARY KEY ( celebrity_id )
    );

LOAD DATA LOCAL INFILE '/home/ubuntu/imt_data/imt_data.csv' 
INTO TABLE data FIELDS TERMINATED BY ',' 
OPTIONALLY ENCLOSED BY '"'
IGNORE 1 LINES;