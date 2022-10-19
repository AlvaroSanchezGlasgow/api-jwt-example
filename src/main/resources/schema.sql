create table T_USERS(
  id                identity  not null PRIMARY KEY,
  username          varchar(100) not null,
  password          varchar(60) not null,
  firstName         varchar(100) not null,
  lastName          varchar(100) not null,
  email            varchar(50) not null,
  comment           varchar(100) not null,
  isActive          char(1) not null,
  registration_date date
);


create table T_CLIENTS(
  id identity  not null PRIMARY KEY,
  name varchar(50) not null,
  website varchar(50) not null,
  client_manager varchar(50) not null,
  registration_date date not null
);

