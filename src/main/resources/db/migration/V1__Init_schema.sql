CREATE TABLE `trucks` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `reg_number` CHAR(7) NOT NULL COMMENT 'Registration number on licence plate.',
  `drivers_quantity` TINYINT UNSIGNED NOT NULL COMMENT 'Size of drivers shift for the truck.',
  `capacity_tons` FLOAT UNSIGNED NOT NULL COMMENT 'Capacity for cargo in tons. ',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `reg_number_UNIQUE` (`reg_number` ASC))
ENGINE = InnoDB
COMMENT = 'Trucks list with basic characteristics.';

CREATE TABLE `truck_conditions` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(16) NOT NULL COMMENT 'Short reference name.',
  `description` VARCHAR(255) NULL COMMENT 'Human-readable description.',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
COMMENT = 'Truck condition value variants. ';

CREATE TABLE `towns` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL COMMENT 'Town name.',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `title_UNIQUE` (`name` ASC))
ENGINE = InnoDB
COMMENT = 'Towns on the country map.';

CREATE TABLE `trucks_state` (
  `truck_id` INT UNSIGNED NOT NULL,
  `truck_condition_id` INT UNSIGNED NOT NULL COMMENT 'Current truck condition.',
  `town_id` INT UNSIGNED NULL COMMENT 'Current truck location.',
  PRIMARY KEY (`truck_id`),
  INDEX `fk_trucks_state_2_idx` (`truck_condition_id` ASC),
  INDEX `fk_trucks_state_3_idx` (`town_id` ASC),
  CONSTRAINT `fk_truck`
    FOREIGN KEY (`truck_id`)
    REFERENCES `trucks` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_truck_condition`
    FOREIGN KEY (`truck_condition_id`)
    REFERENCES `truck_conditions` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_town`
    FOREIGN KEY (`town_id`)
    REFERENCES `towns` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Trucks current state information.';

CREATE TABLE `drivers` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `personal_number` INT UNSIGNED ZEROFILL NOT NULL COMMENT 'Drivers personal number.',
  `first_name` VARCHAR(45) NOT NULL COMMENT 'First name.',
  `last_name` VARCHAR(45) NOT NULL COMMENT 'Last name.',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `personal_number_UNIQUE` (`personal_number` ASC))
ENGINE = InnoDB
COMMENT = 'Drivers personal information';

CREATE TABLE `driver_statuses` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(16) UNIQUE NOT NULL COMMENT 'Short reference name.',
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = 'Driver status value variants.';

CREATE TABLE `drivers_state` (
  `driver_id` INT UNSIGNED NOT NULL,
  `driver_status_id` INT UNSIGNED NOT NULL COMMENT 'Current driver activity status.',
  `truck_id` INT UNSIGNED NULL COMMENT 'Current truck.',
  `town_id` INT UNSIGNED NULL COMMENT 'Current location.',
  PRIMARY KEY (`driver_id`),
  INDEX `fk_driver_status_idx` (`driver_status_id` ASC),
  INDEX `fk_truck_idx` (`truck_id` ASC),
  INDEX `fk_town_idx` (`town_id` ASC),
  CONSTRAINT `fk_driver`
    FOREIGN KEY (`driver_id`)
    REFERENCES `drivers` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_drivers_state_to_driver_status`
    FOREIGN KEY (`driver_status_id`)
    REFERENCES `driver_statuses` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_drivers_state_to_truck`
    FOREIGN KEY (`truck_id`)
    REFERENCES `trucks` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_drivers_state_to_town`
    FOREIGN KEY (`town_id`)
    REFERENCES `towns` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Drivers current state information.';

CREATE TABLE `orders` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_number` INT(6) ZEROFILL UNSIGNED NOT NULL COMMENT 'Order unique number.',
  `completed` BIT(1) NOT NULL COMMENT 'Order completion mark.',
  `truck_id` INT UNSIGNED NULL COMMENT 'A truck assigned to execute order.',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `order_number_UNIQUE` (`order_number` ASC),
  INDEX `fk_truck_idx` (`truck_id` ASC),
  CONSTRAINT `fk_orders_to_truck`
    FOREIGN KEY (`truck_id`)
    REFERENCES `trucks` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Freight orders.';

CREATE TABLE `orders_to_drivers` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id` INT UNSIGNED NOT NULL,
  `driver_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_orders_executors_to_order_idx` (`order_id` ASC),
  INDEX `fk_orders_executors_to_driver_idx` (`driver_id` ASC),
  CONSTRAINT `fk_orders_executors_to_order`
    FOREIGN KEY (`order_id`)
    REFERENCES `orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_executors_to_driver`
    FOREIGN KEY (`driver_id`)
    REFERENCES `drivers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Drivers assigned to execute orders.';

CREATE TABLE `cargo_statuses` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(16) UNIQUE NOT NULL COMMENT 'Short reference name.',
  `description` VARCHAR(255) NULL COMMENT 'Human-readable description.',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
COMMENT = 'Cargo status value variants.';

CREATE TABLE `cargos` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `cargo_number` INT(6) ZEROFILL NOT NULL COMMENT 'Unique number of cargo.',
  `title` VARCHAR(255) NOT NULL COMMENT 'Human-readable name of cargo.',
  `mass_kg` FLOAT UNSIGNED NOT NULL COMMENT 'Cargo mass in kilogrammes.',
  `cargo_status_id` INT UNSIGNED NOT NULL COMMENT 'Current cargo status.',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `cargo_number_UNIQUE` (`cargo_number` ASC),
  INDEX `fk_cargos_to_cargo_status_idx` (`cargo_status_id` ASC),
  CONSTRAINT `fk_cargos_to_cargo_status`
    FOREIGN KEY (`cargo_status_id`)
    REFERENCES `cargo_statuses` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Cargo information.';

CREATE TABLE `waypoint_types` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(16) UNIQUE NOT NULL COMMENT 'Short reference name.',
  `description` VARCHAR(255) NULL COMMENT 'Human-readable description.',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
COMMENT = 'Types of waypoints like loading or unloading.';

CREATE TABLE `orders_waypoints` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id` INT UNSIGNED NOT NULL,
  `town_id` INT UNSIGNED NOT NULL,
  `waypoint_type_id` INT UNSIGNED NOT NULL,
  `cargo_id` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_orders_waypoints_to_order_idx` (`order_id` ASC),
  INDEX `fk_orders_waypoints_to_town_idx` (`town_id` ASC),
  INDEX `fk_orders_waypoints_to_type_idx` (`waypoint_type_id` ASC),
  INDEX `fk_orders_waypoints_to_cargo_idx` (`cargo_id` ASC),
  CONSTRAINT `fk_orders_waypoints_to_order`
    FOREIGN KEY (`order_id`)
    REFERENCES `orders` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_orders_waypoints_to_town`
    FOREIGN KEY (`town_id`)
    REFERENCES `towns` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_orders_waypoints_to_type`
    FOREIGN KEY (`waypoint_type_id`)
    REFERENCES `waypoint_types` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_orders_waypoints_to_cargo`
    FOREIGN KEY (`cargo_id`)
    REFERENCES `cargos` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Waypoints containing by orders.';

CREATE TABLE `roads` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_town_id` INT UNSIGNED NOT NULL COMMENT 'One of the connected towns on map.',
  `second_town_id` INT UNSIGNED NOT NULL COMMENT 'Another of the connected towns on map.',
  `distance_km` FLOAT UNSIGNED NOT NULL COMMENT 'Distance between first and second town.',
  PRIMARY KEY (`id`),
  INDEX `fk_roads_to_town1_idx` (`first_town_id` ASC),
  INDEX `fk_roads_to_town2_idx` (`second_town_id` ASC),
  CONSTRAINT `fk_roads_to_town1`
    FOREIGN KEY (`first_town_id`)
    REFERENCES `towns` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_roads_to_town2`
    FOREIGN KEY (`second_town_id`)
    REFERENCES `towns` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Roads connecting towns on map.';

CREATE TABLE `driver_work_summary` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `driver_id` INT UNSIGNED NOT NULL COMMENT 'Worked driver.',
  `period_start` DATE NOT NULL COMMENT 'First day of the period.',
  `period_end` DATE NOT NULL COMMENT 'Last day of period.',
  `hours_worked` FLOAT NOT NULL COMMENT 'Hours worked during the period.',
  PRIMARY KEY (`id`),
  INDEX `fk_driver_work_summary_to_driver_idx` (`driver_id` ASC),
  CONSTRAINT `fk_driver_work_summary_to_driver`
    FOREIGN KEY (`driver_id`)
    REFERENCES `drivers` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Summary for driver work during some calendar period.';
