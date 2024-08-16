CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS ROLES
(
        ID SERIAL PRIMARY KEY,
        NAME VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS USERS
(
       ID SERIAL PRIMARY KEY,
       NAME VARCHAR(255) NOT NULL,
       SURNAME VARCHAR(255) NOT NULL,
       EMAIL VARCHAR(255) NOT NULL UNIQUE,
       PASSWORD VARCHAR(255) NOT NULL,
       DATE_OF_REGISTRATION TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS USERS_ROLES
(
        USER_ID BIGINT NOT NULL,
        ROLES_ID BIGINT NOT NULL,
        PRIMARY KEY (USER_ID, ROLES_ID),
        FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
        FOREIGN KEY (ROLES_ID) REFERENCES ROLES(ID)
);

CREATE TABLE IF NOT EXISTS COMPANIES
(
       ID UUID PRIMARY KEY DEFAULT gen_random_uuid(),
       NAME VARCHAR(255) NOT NULL,
       DESCRIPTION TEXT NOT NULL,
       WEBSITE VARCHAR(255) NOT NULL,
       INDUSTRY VARCHAR(30) NOT NULL,
       DATE_OF_REGISTRATION DATE NOT NULL,
       CONSTRAINT industry_check CHECK (INDUSTRY IN ('IT', 'MEDICINE', 'GOVERNMENT', 'SOCIAL', 'LAW', 'FINANCE', 'MILITARY', 'VOLUNTEER', 'EDUCATION'))
);

CREATE TABLE VACANCIES
(
       ID UUID PRIMARY KEY DEFAULT gen_random_uuid(),
       TITLE VARCHAR(255) NOT NULL,
       DESCRIPTION TEXT NOT NULL,
       LEVEL VARCHAR(10) NOT NULL CHECK (LEVEL IN ('INTERN', 'JUNIOR', 'MIDDLE', 'SENIOR')),
       YEARS_OF_EXPERIENCE INTEGER NOT NULL CHECK (YEARS_OF_EXPERIENCE >= 0 AND YEARS_OF_EXPERIENCE <= 20),
       FORMAT VARCHAR(10) NOT NULL CHECK (FORMAT IN ('ONSITE', 'REMOTE', 'HYBRID')),
       MIN_SALARY INTEGER NOT NULL CHECK (MIN_SALARY >= 0),
       MAX_SALARY INTEGER NOT NULL CHECK (MAX_SALARY >= 0),
       DATE_OF_PUBLICATION DATE NOT NULL,
       COMPANY_ID UUID NOT NULL,
       CONSTRAINT FK_COMPANY_ID FOREIGN KEY (COMPANY_ID) REFERENCES COMPANIES (ID) ON DELETE CASCADE
);

CREATE TABLE APPLICATIONS
(
      ID SERIAL PRIMARY KEY,
      RESUME VARCHAR(255) NOT NULL,
      APPLICATION_STATUS VARCHAR(20) NOT NULL CHECK (APPLICATION_STATUS IN ('CREATED', 'ACCEPTED', 'REJECTED', 'ARCHIVE')),
      EMAIL VARCHAR(255),
      VACANCY_ID UUID,
      CONSTRAINT FK_VACANCY_ID FOREIGN KEY (VACANCY_ID) REFERENCES VACANCIES (ID) ON DELETE CASCADE
);

