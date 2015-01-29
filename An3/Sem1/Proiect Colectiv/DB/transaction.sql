DROP TABLE IF EXISTS `graveyards_management_app`.`transactions` ;

CREATE TABLE IF NOT EXISTS `graveyards_management_app`.`transactions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `trans_time` DATETIME NOT NULL,
  `document_number` BIGINT NULL,
  `modification_details` VARCHAR(500) NULL,
  `before_trans` VARCHAR(500) NULL,
  `after_trans` VARCHAR(500) NULL,
  `table_name` VARCHAR(45) NULL,
  `record_id` int NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_users_transactions`
    FOREIGN KEY (`user_id`)
    REFERENCES `graveyards_management_app`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_users_transactions_idx` ON `graveyards_management_app`.`transactions` (`user_id` ASC);

-------------------------------------


use `graveyards_management_app`;

insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (1, '2014-09-19', 580, 'Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est. Phasellus sit amet erat.', '6, 5677, \'2014-06-11\', \'2014-01-19\', 1', '7, 5677, \'2014-06-11\', \'2014-01-19\', 1', 'receipts');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (2, '2014-01-17', 2838, 'Vivamus vel nulla eget eros elementum pellentesque.', '6, 5677, \'2014-06-11\', \'2014-01-19\', 1', '7, 5677, \'2014-06-11\', \'2014-01-19\', 1', 'receipts');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (3, '2014-02-18', 1339, 'Mauris sit amet eros.', '6, 5677, \'2014-06-11\', \'2014-01-19\', 1', '7, 5677, \'2014-06-11\', \'2014-01-19\', 1', 'receipts');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (4, '2014-10-04', 4026, 'Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem. Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat.', '6, 5677, \'2014-06-11\', \'2014-01-19\', 1', '7, 5677, \'2014-06-11\', \'2014-01-19\', 1', 'receipts');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (5, '2014-10-15', 1599, 'Nulla tellus. In sagittis dui vel nisl. Duis ac nibh.', '6, 5677, \'2014-06-11\', \'2014-01-19\', 1', '7, 5677, \'2014-06-11\', \'2014-01-19\', 1', 'receipts');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (6, '2014-05-23', 5981, 'Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.', '6, 5677, \'2014-06-11\', \'2014-01-19\', 1', '7, 5677, \'2014-06-11\', \'2014-01-19\', 1', 'receipts');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (7, '2014-06-29', 701, 'Praesent lectus. Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis. Duis consequat dui nec nisi volutpat eleifend.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (8, '2014-11-09', 745, 'Donec vitae nisi. Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus. Curabitur at ipsum ac tellus semper interdum.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (9, '2013-12-30', 6942, 'Etiam vel augue.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (10, '2014-03-03', 400, 'Duis bibendum. Morbi non quam nec dui luctus rutrum. Nulla tellus. In sagittis dui vel nisl.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (11, '2014-03-19', 1951, 'In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (12, '2014-06-06', 7171, 'Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque. Duis bibendum.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (13, '2014-11-04', 7031, 'Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam. Nam tristique tortor eu pede.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (14, '2014-07-19', 2606, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin risus.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (15, '2014-05-08', 267, 'Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus. Pellentesque at nulla.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (16, '2014-03-03', 5245, 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros. Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (17, '2014-04-26', 3326, 'Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est. Phasellus sit amet erat.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (18, '2014-07-16', 7430, 'Integer ac neque. Duis bibendum.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (19, '2013-12-01', 4032, 'Integer ac neque. Duis bibendum. Morbi non quam nec dui luctus rutrum. Nulla tellus.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (1, '2014-03-04', 5055, 'Sed ante. Vivamus tortor.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (2, '2014-10-21', 1550, 'Fusce posuere felis sed lacus. Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl. Nunc rhoncus dui vel sem.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (3, '2014-10-31', 4639, 'Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (4, '2014-06-25', 3364, 'Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat. Curabitur gravida nisi at nibh.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (5, '2014-04-30', 2504, 'Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus. Suspendisse potenti.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (6, '2014-08-10', 535, 'Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis. Fusce posuere felis sed lacus.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');

