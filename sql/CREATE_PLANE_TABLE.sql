CREATE TABLE PLANE (
  ID         NUMBER PRIMARY KEY,
  PLANE_MODEL            VARCHAR(50) NOT NULL,
  CODE                   VARCHAR(50) NOT NULL,
  YEAR_PRODUCED          DATE NOT NULL,
  AVG_FUEL_CONSUMPTION   NUMBER NOT NULL
)