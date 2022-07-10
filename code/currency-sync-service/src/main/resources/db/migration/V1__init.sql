CREATE TABLE `exchange_rate`
(
    `currency` CHAR(3)        NOT NULL,
    `date`     DATE           NOT NULL,
    `rate`     DECIMAL(12, 4) NOT NULL COMMENT 'EUR to currency exchange rate',
    PRIMARY KEY (`currency`, `date`)
);
