create table mobile_banking_before_norm(username VARCHAR(255) not null,
upi VARCHAR(255) not null,
mobile_number number(10) not null,
eamil varchar(255) not null,
wallet_type varchar(255) not null,
recharged_date date not null,
recharged_provider varchar(255) not null,
recharged_to VARCHAR(255) not null,
recharged_amount NUMBER(10) not null);

create table user_details(username VARCHAR(255) not null,
upi VARCHAR(255) not null,
mobile_number number(10) primary key not null,
eamil varchar(255) not null);

create table wallet_details(wallet_id number(10) primary key not null,
wallet_type varchar(255) not null);

create table user_wallet(mobile_number number(10),
wallet_id number(10),
primary key (mobile_number, wallet_id));

alter table user_wallet add foreign key(mobile_number) references user_details(mobile_number);
alter table user_wallet add foreign key(wallet_id) references wallet_details(wallet_id);


create table recharge_details(recharge_id number(10) primary key not null,
recharged_date date not null,
recharged_provider varchar(255) not null,
recharged_to VARCHAR(255) not null,
recharged_amount NUMBER(10) not null,
mobile_number number(10) not null);
alter table recharge_details add foreign key(mobile_number) references user_details(mobile_number);