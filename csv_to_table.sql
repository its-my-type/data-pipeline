DROP TABLE full_data;

DROP TABLE data;

CREATE TABLE full_data( 
    celebrity_id SMALLINT NOT NULL AUTO_INCREMENT, 
    feature TEXT NOT NULL, 
    name VARCHAR(40), 
    gender CHAR(6), 
    birth SMALLINT, 
    PRIMARY KEY ( celebrity_id )
    );

LOAD DATA LOCAL INFILE '/home/ubuntu/imt_data/imt_data.csv' 
INTO TABLE full_data FIELDS TERMINATED BY ',' 
OPTIONALLY ENCLOSED BY '"'
IGNORE 1 LINES;

CREATE TABLE data AS SELECT * FROM full_data WHERE 1=2;

INSERT INTO data SELECT * FROM full_data WHERE birth NOT IN (0);