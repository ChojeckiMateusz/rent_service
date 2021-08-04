SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

DROP SCHEMA IF EXISTS `rentservice` ;

CREATE SCHEMA IF NOT EXISTS `rentservice` DEFAULT CHARACTER SET utf8 ;
USE `rentservice` ;


DROP TABLE IF EXISTS `rentservice`.`products` ;

CREATE TABLE IF NOT EXISTS `rentservice`.`products` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`name`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


DROP TABLE IF EXISTS `rentservice`.`prices` ;

CREATE TABLE IF NOT EXISTS `rentservice`.`prices` (
  `id` INT NOT NULL,
  `price` DECIMAL(5,2) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_prices_productrs1_idx` (`id` ASC) VISIBLE,
  CONSTRAINT `fk_prices_products1`
    FOREIGN KEY (`id`)
    REFERENCES `rentservice`.`products` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


DROP TABLE IF EXISTS `rentservice`.`clients` ;

CREATE TABLE IF NOT EXISTS `rentservice`.`clients` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


DROP TABLE IF EXISTS `rentservice`.`users` ;

CREATE TABLE IF NOT EXISTS `rentservice`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(30) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `enabled` boolean,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


DROP TABLE IF EXISTS `rentservice`.`orders` ;

CREATE TABLE IF NOT EXISTS `rentservice`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `client_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `start_date` TIMESTAMP NOT NULL,
  `end_date` TIMESTAMP NOT NULL,
  `total_cost` DECIMAL(9,2) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_orders_clients1_idx` (`client_id` ASC) VISIBLE,
  INDEX `fk_orders_productrs1_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_orders_products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `rentservice`.`products` (`id`),
  CONSTRAINT `fk_orders_clients1`
    FOREIGN KEY (`client_id`)
    REFERENCES `rentservice`.`clients` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


DROP TABLE IF EXISTS `rentservice`.`userroles` ;

CREATE TABLE IF NOT EXISTS `rentservice`.`userroles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


DROP TABLE IF EXISTS `rentservice`.`users_to_role` ;

CREATE TABLE IF NOT EXISTS `rentservice`.`users_to_role` (
  `userroles_id` INT NOT NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`users_id`, `userroles_id`),
  INDEX `fk_users_to_roles_users1_idx` (`users_id` ASC) VISIBLE,
  INDEX `fk_users_to_roles_userroles` (`userroles_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_to_roles_userroles`
    FOREIGN KEY (`userroles_id`)
    REFERENCES `rentservice`.`userroles` (`id`),
  CONSTRAINT `fk_users_to_roles_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `rentservice`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `rentservice`.`clients`(`first_name`,`surname`) VALUES ('Jan', 'Kowalski');

INSERT INTO `rentservice`.`products`(`name`) VALUES ('PRALKA');
INSERT INTO `rentservice`.`products`(`name`) VALUES ('ODKURZACZ');

INSERT INTO `rentservice`.`userroles`(`type`) VALUES ('ADMIN');
INSERT INTO `rentservice`.`userroles`(`type`) VALUES ('USER');
