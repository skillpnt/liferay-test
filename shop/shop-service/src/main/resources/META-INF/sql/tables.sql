create table shop_Electronics (
	electronicsId LONG not null primary key,
	name VARCHAR(75) null,
	typeId LONG,
	price LONG,
	count INTEGER,
	inStock BOOLEAN,
	archived BOOLEAN,
	description VARCHAR(75) null
);

create table shop_ElectronicsEmployee (
	employeeId LONG not null,
	electronicsTypeId LONG not null,
	primary key (employeeId, electronicsTypeId)
);

create table shop_ElectronicsType (
	electronicsTypeId LONG not null primary key,
	name VARCHAR(75) null
);

create table shop_Employee (
	employeeId LONG not null primary key,
	lastName VARCHAR(75) null,
	firstName VARCHAR(75) null,
	patronymic VARCHAR(75) null,
	birthdate DATE null,
	positionId LONG,
	gender BOOLEAN
);

create table shop_PositionType (
	positionId LONG not null primary key,
	name VARCHAR(75) null
);

create table shop_Purchase (
	purchaseId LONG not null primary key,
	electronicsId LONG,
	employeeId LONG,
	purchaseDate DATE null,
	purchaseTypeId LONG
);

create table shop_purchaseType (
	purchaseTypeId LONG not null primary key,
	name VARCHAR(75) null
);