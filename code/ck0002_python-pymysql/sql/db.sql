create table `person` (
	`id` int not null auto_increment,
    `first_name` varchar(255),
    `last_name` varchar(255),
    `age` int,
    `sex` char(1),
    `income` decimal,
	primary key (`id`)
);
