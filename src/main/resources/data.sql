CREATE SCHEMA IF NOT EXISTS `test`;
USE `test`;


CREATE TABLE IF NOT EXISTS `categoria` (
  `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `nombre` VARCHAR NULL DEFAULT NULL,
  `FK_CATEGORIA_PADRE` INTEGER NULL DEFAULT NULL,
  `kkey` VARCHAR NULL DEFAULT NULL,
  `nombre_eng` VARCHAR NULL DEFAULT NULL
);

create table IF NOT EXISTS `categoria_padre` (
  `id` INTEGER not null PRIMARY KEY AUTO_INCREMENT,
  `nombre` varchar null default null,
  `kkey` varchar null default null,
  `nombre_eng` varchar null default null,
  `modulo` varchar null default null
);

CREATE TABLE IF NOT EXISTS `sub_categoria` (
  `id` INTEGER NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `FK_CATEGORIA` INTEGER DEFAULT NULL,
  `kkey` varchar(50) DEFAULT NULL,
  `nombre_eng` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `genero` (
  `id` INTEGER NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `roles` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(120) DEFAULT NULL,
  `remember_login` bit null default null,
  `username` varchar(20) DEFAULT NULL,
  `enabled` bit null default null,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
);

CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` INTEGER NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
);

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` INTEGER NOT NULL,
  `admin` bit null default null,
  `apellido` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `email_confirmado` bit(1) DEFAULT NULL,
  `nacimiento` datetime DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  `genero` INTEGER DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `usuario_direccion` (
  `id` INTEGER NOT NULL,
  `calle` varchar(255) DEFAULT NULL,
  `codigo_postal` varchar(255) DEFAULT NULL,
  `datos_adicionales` varchar(255) DEFAULT NULL,
  `destinatario` varchar(255) DEFAULT NULL,
  `localidad` varchar(50) DEFAULT NULL,
  `piso` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `FK_USUARIO` INTEGER DEFAULT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `producto` (
  `id` INTEGER NOT NULL,
  `descuento` double DEFAULT NULL,
  `disponible` bit(1) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `precio_final` double DEFAULT NULL,
  `puntuacion` double DEFAULT NULL,
  `tamano` double DEFAULT NULL,
  `valor_nutricional` tinyblob,
  `FK_CATEGORIA_PRODUCT` INTEGER DEFAULT NULL,
  `FK_SUB_CATEGORIA_PRODUCT` INTEGER DEFAULT NULL,
  `nombre_eng` varchar(255) DEFAULT NULL,
  `FK_CAT_PADRE_PRODUCT` INTEGER DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `cesta` (
  `id` INTEGER NOT NULL,
  `cantidadProductos` INTEGER DEFAULT NULL,
  `importe_total` double DEFAULT NULL,
  `importe_subtotal` double DEFAULT NULL,
  `envio` double DEFAULT NULL,
  `FK_USUARIO` INTEGER NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_FK_USUARIO` (`FK_USUARIO`)
);

CREATE TABLE IF NOT EXISTS `comentario` (
  `id` INTEGER NOT NULL,
  `autor` varchar(255) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `opinion` varchar(255) DEFAULT NULL,
  `puntuacion` double DEFAULT NULL,
  `FK_PRODUCTO` INTEGER DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `descripcion` (
  `id` INTEGER NOT NULL,
  `apartado` longtext,
  `beneficios` mediumtext,
  `caracteristicas` mediumtext,
  `subtitulo` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `FK_PRODUCTO` INTEGER NOT NULL,
  `apartado_eng` longtext,
  `beneficios_eng` mediumtext,
  `caracteristicas_eng` mediumtext,
  `subtitulo_eng` varchar(255) DEFAULT NULL,
  `titulo_eng` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `foto` (
  `id` INTEGER NOT NULL,
  `principal` bit(1) DEFAULT NULL,
  `ruta` varchar(255) DEFAULT NULL,
  `FK_PRODUCTO` INTEGER DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `valor_nutricional` (
  `id` INTEGER NOT NULL,
  `advertencias` longtext,
  `alergias` longtext,
  `conservacion` varchar(255) DEFAULT NULL,
  `dosis` INTEGER DEFAULT NULL,
  `dosis_diaria` INTEGER DEFAULT NULL,
  `dosis_envase` INTEGER DEFAULT NULL,
  `ingredientes` longtext,
  `modo_empleo` mediumtext,
  `otros_ingredientes` longtext,
  `FK_PRODUCTO` INTEGER DEFAULT NULL,
  `advertencias_eng` longtext,
  `alergias_eng` longtext,
  `conservacion_eng` varchar(255) DEFAULT NULL,
  `ingredientes_eng` longtext,
  `modo_empleo_eng` mediumtext,
  `otros_ingredientes_eng` longtext,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `info_vitaminas` (
  `id` INTEGER NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `valor` varchar(255) DEFAULT NULL,
  `FK_VALOR_NUTRICIONAL` INTEGER DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `metodo_envio` (
  `id` INTEGER NOT NULL,
  `nombre` varchar(50) NOT NULL DEFAULT '',
  `nombre_eng` varchar(50) NOT NULL DEFAULT '',
  `descripcion` varchar(255) NOT NULL DEFAULT '',
  `descripcion_eng` varchar(255) NOT NULL DEFAULT '',
  `icono` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `metodo_pago` (
  `id` INTEGER NOT NULL,
  `nombre` varchar(50) NOT NULL DEFAULT '',
  `nombre_eng` varchar(50) NOT NULL DEFAULT '',
  `descripcion` varchar(50) NOT NULL DEFAULT '',
  `descripcion_eng` varchar(50) NOT NULL DEFAULT '',
  `icono` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `pedido` (
  `id` INTEGER NOT NULL,
  `cantidadProductos` INTEGER DEFAULT NULL,
  `enviado` bit(1) NOT NULL,
  `fechaPedido` datetime DEFAULT NULL,
  `finalizado` bit(1) NOT NULL,
  `pagado` bit(1) NOT NULL,
  `precioTotal` double DEFAULT NULL,
  `FK_USUARIO` INTEGER NOT NULL,
  `destinatario` varchar(50) DEFAULT NULL,
  `num_pedido` varchar(50) DEFAULT NULL,
  `FK_PEDIDO_METODO_ENVIO` INTEGER DEFAULT NULL,
  `precio_envio` double DEFAULT NULL,
  `FK_PEDIDO_METODO_PAGO` INTEGER DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `sabor` (
  `id` INTEGER NOT NULL,
  `sabor` varchar(255) DEFAULT NULL,
  `sabor_eng` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `producto_cesta` (
  `id` INTEGER NOT NULL,
  `cantidad` INTEGER NOT NULL,
  `FK_SABOR_PRODUCT_CESTA` INTEGER DEFAULT NULL,
  `FK_PRODUCTO_PRODUCT_CESTA` INTEGER DEFAULT NULL,
  `FK_CESTA` INTEGER DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `profile_image` (
  `id` INTEGER NOT NULL,
  `picByte` longblob NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `registration_token` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `token` varchar(50) DEFAULT NULL,
  `expiry_date` date DEFAULT NULL,
  `FK_USUARIO` INTEGER DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `rel_pedidos_productos` (
  `FK_PEDIDO` INTEGER NOT NULL,
  `FK_PRODUCTO` INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS `rel_sabores_productos` (
  `FK_SABOR` INTEGER NOT NULL,
  `FK_PRODUCTO` INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS `info_basica` (
  `id` INTEGER NOT NULL,
  `valor_energetico` INTEGER DEFAULT NULL,
  `azucares` INTEGER DEFAULT NULL,
  `grasas` INTEGER DEFAULT NULL,
  `hidratos` INTEGER DEFAULT NULL,
  `proteinas` INTEGER DEFAULT NULL,
  `saturadas` INTEGER DEFAULT NULL,
  `sodio` INTEGER DEFAULT NULL,
  `FK_VALOR_NUTRICIONAL` INTEGER NOT NULL,
  PRIMARY KEY (`id`)
);




alter table `categoria` add constraint IF NOT EXISTS `FK_CATEGORIA_PADRE`
    foreign key (`FK_CATEGORIA_PADRE`)
    references `categoria_padre` (`id`);

 alter table `sub_categoria` add constraint IF NOT EXISTS `FK_CATEGORIA`
    foreign key (`FK_CATEGORIA`)
    references `categoria` (`id`);  

alter table `user_roles` add constraint IF NOT EXISTS `FKh8ciramu9cc9q3qcqiv4ue8a6`
    foreign key (`role_id`)
    references `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;


alter table `user_roles` add constraint IF NOT EXISTS `FKhfh9dx7w3ubf1co1vdev94g3f`
    foreign key (`user_id`)
    references `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

    alter table `usuario` add constraint IF NOT EXISTS `FKnval6i7nhmkj2dasui69ms0v0`
    foreign key (`genero`)
    references `genero` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

    alter table `usuario_direccion` add constraint IF NOT EXISTS `FK_USUARIO`
    foreign key (`FK_USUARIO`)
    references `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

    alter table `sub_categoria` add constraint IF NOT EXISTS `FK_CATEGORIA`
    foreign key (`FK_CATEGORIA`)
    references `categoria` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

    alter table `producto` add constraint IF NOT EXISTS `FK_CATEGORIA_PRODUCT`
    foreign key (`FK_CATEGORIA_PRODUCT`)
    references `categoria` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

     alter table `producto` add constraint IF NOT EXISTS `FK_CAT_PADRE_PRODUCT`
    foreign key (`FK_CAT_PADRE_PRODUCT`)
    references `categoria_padre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

     alter table `producto` add constraint IF NOT EXISTS `FK_SUB_CATEGORIA_PRODUCT`
    foreign key (`FK_SUB_CATEGORIA_PRODUCT`)
    references `sub_categoria` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

    alter table `cesta` add constraint IF NOT EXISTS `FKb1qy73bjjnd3t5cws16q42wdn`
    foreign key (`FK_USUARIO`)
    references `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

    alter table `comentario` add constraint IF NOT EXISTS `FKsgmvgljfjeed6urb3o7avwq2r`
    foreign key (`FK_PRODUCTO`)
    references `producto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

     alter table `descripcion` add constraint IF NOT EXISTS `FK4ceevf69cc0vvt4nk38d031uy`
    foreign key (`FK_PRODUCTO`)
    references `producto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

      alter table `foto` add constraint IF NOT EXISTS `FKn7xkvrbssbjejm9bpg7ck2ckf`
    foreign key (`FK_PRODUCTO`)
    references `producto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

    alter table `valor_nutricional` add constraint IF NOT EXISTS `FK_valor_nutricional_producto`
    foreign key (`FK_PRODUCTO`)
    references `producto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

    alter table `info_vitaminas` add constraint IF NOT EXISTS `FK8c2itt02sufp4ykkgqddj7yf2`
    foreign key (`FK_VALOR_NUTRICIONAL`)
    references `valor_nutricional` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

    alter table `pedido` add constraint IF NOT EXISTS `FK_PEDIDO_METODO_ENVIO`
    foreign key (`FK_PEDIDO_METODO_ENVIO`)
    references `metodo_envio` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

    alter table `pedido` add constraint IF NOT EXISTS `FK_PEDIDO_METODO_PAGO`
    foreign key (`FK_PEDIDO_METODO_PAGO`)
    references `metodo_pago` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

    alter table `pedido` add constraint IF NOT EXISTS `FKikxrb1vvn614sd127mbd969hn`
    foreign key (`FK_USUARIO`)
    references `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

    alter table `producto_cesta` add constraint IF NOT EXISTS `FK_CESTA`
    foreign key (`FK_CESTA`)
    references `cesta` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

    alter table `producto_cesta` add constraint IF NOT EXISTS `FK_PRODUCTO_PRODUCT_CESTA`
    foreign key (`FK_PRODUCTO_PRODUCT_CESTA`)
    references `producto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

    alter table `producto_cesta` add constraint IF NOT EXISTS `FK_SABOR_PRODUCT_CESTA`
    foreign key (`FK_SABOR_PRODUCT_CESTA`)
    references `sabor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

    alter table `registration_token` add constraint IF NOT EXISTS `FK_TOKEN_USUARIO`
    foreign key (`FK_USUARIO`)
    references `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

    alter table `rel_pedidos_productos` add constraint IF NOT EXISTS `FK60x9pttcxqitf0ruiwaq1imts`
    foreign key (`FK_PEDIDO`)
    references `pedido` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

    alter table `rel_pedidos_productos` add constraint IF NOT EXISTS `FKtl0qus2o12i10j49kijixchsr`
    foreign key (`FK_PRODUCTO`)
    references `producto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

     alter table `rel_sabores_productos` add constraint IF NOT EXISTS `FKh9t97e4khkwsi7b4s6sqlgqef`
    foreign key (`FK_PRODUCTO`)
    references `producto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

    alter table `rel_sabores_productos` add constraint IF NOT EXISTS `FKk436bk8el7k5v1xod2j1rnrqa`
    foreign key (`FK_SABOR`)
    references `sabor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

    alter table `info_basica` add constraint IF NOT EXISTS `FKkeafqxhhm3u7w21rj30505qdh`
    foreign key (`FK_VALOR_NUTRICIONAL`)
    references `valor_nutricional` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;





DELETE FROM `info_basica` CASCADE;
DELETE FROM `rel_sabores_productos` CASCADE;
DELETE FROM `rel_pedidos_productos` CASCADE;
DELETE FROM `registration_token` CASCADE;
DELETE FROM `profile_image` CASCADE;
DELETE FROM `producto_cesta` CASCADE;
DELETE FROM `sabor` CASCADE;
DELETE FROM `pedido` CASCADE;
DELETE FROM `metodo_pago` CASCADE;
DELETE FROM `metodo_envio` CASCADE;
DELETE FROM `info_vitaminas` CASCADE;
DELETE FROM `valor_nutricional` CASCADE;
/*DELETE FROM `hibernate_sequence` CASCADE;*/
DELETE FROM `foto` CASCADE;
DELETE FROM `descripcion` CASCADE;
DELETE FROM `comentario` CASCADE;
DELETE FROM `cesta` CASCADE;
DELETE FROM `producto` CASCADE;
DELETE FROM `sub_categoria` CASCADE;
DELETE FROM `usuario_direccion` CASCADE;
DELETE FROM `usuario` CASCADE;
DELETE FROM `user_roles` CASCADE;
DELETE FROM `users` CASCADE;
DELETE FROM `roles` CASCADE;
DELETE FROM `genero` CASCADE;
DELETE FROM `sub_categoria` CASCADE;
DELETE FROM `categoria` CASCADE;
DELETE FROM `categoria_padre` CASCADE;


-- Volcando datos para la tabla shop.categoria_padre: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `categoria_padre` DISABLE KEYS */;
INSERT INTO `categoria_padre` (`id`, `nombre`, `kkey`, `nombre_eng`, `modulo`) VALUES
	(1, 'Nutrición', 'nutricion', 'Nutrition', 'products'),
	(2, 'Alimentación', 'alimentacion', 'Feeding', 'feeding'),
	(3, 'Promociones', 'promociones', 'Promotions', 'promotions');
INSERT INTO `categoria` (`id`, `nombre`, `FK_CATEGORIA_PADRE`, `kkey`, `nombre_eng`) VALUES
	(1, 'Proteina', 1, 'proteina', 'Protein'),
	(2, 'Hidratos de Carbono', 1, 'hidratos', 'Carbs'),
	(3, 'Quemadores', 1, 'quemadores', 'Burners'),
	(4, 'Energía', 1, 'energia', 'Energy'),
	(5, 'Desayuno', 2, 'desayuno', 'Breakfast');
INSERT INTO `sub_categoria` (`id`, `nombre`, `FK_CATEGORIA`, `kkey`, `nombre_eng`) VALUES
	(1, 'Concentrado de Suero', 1, 'concentrado', 'Whey Concentrate'),
	(2, 'Aislado de Proteína', 1, 'aislado', 'Protein Isolate'),
	(3, 'Hidrolizado de Proteína', 1, 'hidrolizado', 'Protein Hydrolyzate'),
	(4, 'Proteína Vegetal', 1, 'vegetal', 'Vegetal Protein'),
	(5, 'Ganador de Masa', 2, 'ganador', 'Mass Gainer'),
	(6, 'Vitargo', 2, 'vitargo', 'Vitargo'),
	(7, 'Termogénicos', 3, 'termogenico', 'Thermogenics'),
	(8, 'L-Carnitina', 3, 'carnitina', 'L-Carnitine'),
	(9, 'Diuréticos', 3, 'diuretico', 'Diuretics'),
	(10, 'CLA', 3, 'cla', 'CLA'),
	(11, 'Preentrenamiento', 4, 'preentreno', 'Pre Workout'),
	(12, 'Cafeína', 4, 'cafeina', 'Cafeine'),
	(13, 'Creatina', 4, 'creatina', 'Creatine'),
	(14, 'Tortitas', 5, 'tortitas', 'Pancakes'),
	(15, 'Cremas', 5, 'cremas', 'Creams'),
	(16, 'Pudding', 5, 'pudding', 'Pudding');
INSERT INTO `genero` (`id`, `nombre`) VALUES
	(1, 'Masculino'),
	(2, 'Femenino'),
	(3, 'Otro');
INSERT INTO `users` (`id`, `email`, `password`, `remember_login`, `username`, `enabled`) VALUES
	(1, 'hugo221989@hotmail.es', 'Password', '1', 'hugo89', '1'),
	(11, 'hugoonetto@gmail.com', 'Password', '1', 'hugomail', '0');
INSERT INTO `roles` (`id`, `name`) VALUES
	(1, 'ROLE_USER'),
	(2, 'ROLE_MODERATOR'),
	(3, 'ROLE_ADMIN');
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
	(1, 1),
	(11, 1);
INSERT INTO `usuario` (`id`, `admin`, `apellido`, `email`, `email_confirmado`, `nacimiento`, `nombre`, `telefono`, `usuario`, `genero`) VALUES
	(1, '0', 'Onetto', 'hugo221989@hotmail.es', NULL, '1989-03-22 01:00:00', 'Hugo', '665554546654', 'hugo89', 3),
	(213, '0', NULL, 'hugoonetto@gmail.com', '0', NULL, NULL, NULL, 'hugomail', NULL);
INSERT INTO `usuario_direccion` (`id`, `calle`, `codigo_postal`, `datos_adicionales`, `destinatario`, `localidad`, `piso`, `telefono`, `FK_USUARIO`) VALUES
	(179, 'Brasil', '46018', 'puerta 4', 'Hugo', 'Barcelona', '18', '6665551236', 1);
INSERT INTO `producto` (`id`, `descuento`, `disponible`, `nombre`, `precio`, `precio_final`, `puntuacion`, `tamano` , `FK_CATEGORIA_PRODUCT`, `FK_SUB_CATEGORIA_PRODUCT`, `nombre_eng`, `FK_CAT_PADRE_PRODUCT`) VALUES
	(1, 0, '1', 'Proteina Whey', 29.95, 29.95, 4, 2000, 1, 1, 'Whey Protein', 1),
	(2, 0, '1', 'Proteina Whey', 17.95, 17.95, 4, 1000, 1, 1, 'Whey Protein', 1),
	(3, 10, '1', 'Big Shot - Pre-Workout', 23.99, 21.69, 5, 300, 4, 11, 'Big Shot - Pre-Workout', 1),
	(4, 0, '1', 'Vitargo', 31.99, 31.99, 3, 2000, 2, 6, 'Vitargo', 1),
	(5, 0, '0', 'CLA Powder', 17.9, 17.9, 4, 150, 3, 10, 'CLA Powder', 1),
	(6, 0, '1', 'Creatina Creapure', 12.99, 12.99, 5, 300, 4, 13, 'Creapure Creatine', 1),
	(7, 0, '1', 'Tortitas Proteicas', 15.99, 15.99, 4, 900, 5, 14, 'Protein pancake', 2),
	(8, 0, '1', 'Crema de Cacahuete', 5.94, 5.94, 5, 1000, 5, 15, 'Peanut Butter', 2),
	(9, 0, '1', 'Pudding Proteico', 22.9, 22.9, 4, 600, 5, 16, 'Protein Pudding', 2),
	(10, 0, '1', 'WHEY CHOCO BUTTER', 5.09, 5.09, 5, 250, 5, 15, 'WHEY CHOCO BUTTER', 2);
INSERT INTO `cesta` (`id`, `cantidadProductos`, `importe_total`, `importe_subtotal`, `envio`, `FK_USUARIO`) VALUES
	(119, 6, 149.72, 149.72, 0, 1);
INSERT INTO `comentario` (`id`, `autor`, `fecha`, `opinion`, `puntuacion`, `FK_PRODUCTO`) VALUES
	(1, 'Armando Guerra', '2020-05-07 19:56:13', 'Proteina de calidad', 5, 1),
	(2, 'Debora Meltrozo', '2020-05-07 19:50:48', 'Sabor chocolate muy recomendable', 4, 1),
	(3, 'Benito Camela', '2020-05-07 20:04:28', 'Excelente calidad precio', 5, 1),
	(4, 'Luisao Lucho', '2020-02-07 19:58:11', 'Con bacon están to wenasss', 4, 1),
	(5, 'Kabestro Gomez', '2020-04-07 12:58:57', 'Me las acabé en un día', 5, 1),
	(6, 'Kabestro Gomez', '2020-04-07 12:58:57', 'Me las acabé en un día', 5, 2),
	(7, 'Debora Meltrozo', '2020-05-07 19:50:48', 'Savor chocolate muy recomendable', 4, 2),
	(8, 'Armando Guerra', '2020-05-07 19:56:13', 'Proteina de calidad', 5, 2),
	(9, 'Benito Camela', '2020-05-07 20:04:28', 'Excelente calidad precio', 5, 2),
	(10, 'Luisao Lucho', '2020-02-07 19:58:11', 'Protes baratas ', 4, 2);
INSERT INTO `descripcion` (`id`, `apartado`, `beneficios`, `caracteristicas`, `subtitulo`, `titulo`, `FK_PRODUCTO`, `apartado_eng`, `beneficios_eng`, `caracteristicas_eng`, `subtitulo_eng`, `titulo_eng`) VALUES
	(1, 'Whey Pure Fusion de Amix Nutrition es una excelente combinación de aislado CFM® y concentrado de proteína de suero de gran calidad, obtenida mediante ultra y microfiltración a baja temperatura, técnicas que aseguran una proteína con una gran pureza y un perfil de aminoácidos completo, perfecta para una rápida recuperación.', 'Mejora las adaptaciones al entrenamiento y acelera la recuperación post-entrenamiento.', '130 kcal por dosis.*', 'Combinación de proteínas de gran calidad.', 'WHEY PURE FUSION', 1, 'Whey Pure Fusion from Amix Nutrition is an excellent combination of CFM® isolate and high-quality whey protein concentrate, obtained by ultra and low-temperature microfiltration, techniques that ensure a protein with high purity and a complete amino acid profile, perfect for a quick recovery.', 'Improves adaptations to training and accelerates post-workout recovery.', '130 kcal per dose. *', 'High quality protein combination.', 'WHEY PURE FUSION'),
	(2, 'Whey Pure Fusion de Amix Nutrition es una excelente combinación de aislado CFM® y concentrado de proteína de suero de gran calidad, obtenida mediante ultra y microfiltración a baja temperatura, técnicas que aseguran una proteína con una gran pureza y un perfil de aminoácidos completo, perfecta para una rápida recuperación.', 'Mejora las adaptaciones al entrenamiento y acelera la recuperación post-entrenamiento.', '130 kcal por dosis.*', 'Combinación de proteínas de gran calidad.', 'WHEY PURE FUSION', 2, 'Whey Pure Fusion from Amix Nutrition is an excellent combination of CFM® isolate and high-quality whey protein concentrate, obtained by ultra and low-temperature microfiltration, techniques that ensure a protein with high purity and a complete amino acid profile, perfect for a quick recovery.', 'Improves adaptations to training and accelerates post-workout recovery.', '130 kcal per dose. *', 'High quality protein combination.', 'WHEY PURE FUSION'),
	(3, 'Vitargo® Pure es un carbohidrato largo complejo con baja osmolalidad, una fórmula patentada a base de amilopectina que repone las reservas de glucógeno un 70% más rápido que los carbohidratos convencionales, ayudando a mejorar el rendimiento al aportar energía rápida a los músculos.', 'Proporciona energía para mantener el rendimiento deportivo durante los entrenamientos y competiciones de resistencia.', '100% Vitargo.', 'Carbohidrato único, cero azúcares.', 'VITARGO PURE', 4, 'Vitargo® Pure is a complex long carbohydrate with low osmolality, a proprietary amylopectin-based formula that replenishes glycogen stores 70% faster than conventional carbohydrates, helping to improve performance by providing rapid energy to the muscles.', 'Provides energy to maintain sports performance during training and endurance competitions.', '100% Vitargo.', 'Unique carbohydrate, zero sugars.', 'VITARGO PURE'),
	(4, 'C4 Original de Cellucor es un pre-entreno especialmente formulado con ingredientes enfocados para mejorar la intensidad, la fuerza y la resistencia en tus entrenamientos más intensos.', NULL, '47% del Valor de Referencia de Nutrientes (VRN) de Vitamina C por dosis (6,5g).', '¡El pre-entreno explosivo original!', 'C4 ORIGINAL', 3, 'Cellucor C4 Original is a pre-workout specially formulated with focused ingredients to improve intensity, strength and endurance in your most intense workouts.', NULL, '47% of the Vitamin C Nutrient Reference Value (NRV) per dose (6.5g).', 'The original explosive pre-workout!', 'C4 ORIGINAL'),
	(5, '¡No lo pienses más! Incluye en tu rutina de suplementación Ácido linoleico Conjugado y favorece la pérdida de peso ayudando a mantener los niveles de colesterol. ¡Acuérdate que siempre debes de combinarlo con una rutina de ejercicio', 'Contribuye a reducir los niveles de colesterol en sangre.', '2400mg de CLA por dosis (3 Perlas).', '¡Potencia tu rendimiento y favorece la pérdida de peso!', 'CLA POWDER 150', 5, 'Think no more! Include Conjugated Linoleic Acid in your supplementation routine and promotes weight loss by helping to maintain cholesterol levels. Remember that you should always combine it with an exercise routine', 'It contributes to reducing blood cholesterol levels.', '2400mg of CLA per dose (3 Pearls).', 'Boost your performance and promote weight loss!', 'CLA POWDER 150'),
	(6, 'Creatina Creapure está formulada 100% con creatina monohidrato pura por lo que es el suplemento perfecto para aquellos deportistas que practican actividad física de corta duración y elevada intensidad como esprintar, correr, saltar etc...', '100% creatina monohidrato creapure..', '3000mg de Creatina monohidrato Creapure por dosis (3,1g).', '¡Máxima pureza!', 'CREATINA CREAPURE', 6, 'Creatine Creapure is formulated 100% with pure creatine monohydrate, making it the perfect supplement for those athletes who practice short duration and high intensity physical activity such as sprinting, running, jumping, etc.', '100% creatine monohydrate creapure ..', '3000mg of Creatine monohydrate Creapure per dose (3.1g).', 'Maximum purity!', 'CREAPURE CREATINE'),
	(7, 'Con la Preparación para Tortitas de KOT Nutrition podrás elaborar exquisitos pancakes ricos en proteínas, ideales para tus desayunos y meriendas.', 'Personas que sigan planes de pérdida de peso.', 'Solamente 92 Kcal por sobre.\r\nRicas en proteínas.\r\n45% proteínas de leche.\r\nFácil preparación.\r\n7 Sobres de 25 g en cada envase.', 'Pancakes hiperproteicos fáciles de preparar.', 'TORTITAS PROTEICAS', 7, 'With the KOT Nutrition Pancake Preparation you can make delicious protein-rich pancakes, ideal for your breakfasts and snacks.', 'People who follow weight loss plans.\r\nPerfect for breakfasts and snacks.\r\nUse within a balanced diet.', '\r\nOnly 92 Kcal per envelope.\r\nRich in protein.\r\n45% milk protein.\r\nEasy preparation.\r\n7 25 g sachets in each container.', 'Hyperproteic pancakes easy to prepare.', 'PROTEIN PANCAKES'),
	(8, 'Si lo que buscas es un delicioso alimento proteico que puedas incluir en tu alimentación habitual ...¡prueba esta increíble crema suave de cacahuete! A parte de ser una fuente natural de proteína, estos cacahuetes aportan fibra, vitamina E y magnesio.\r\n\r\n¿QUÉ ES CREMA DE CACAHUETE SUAVE?\r\nCrema de cacahuete suave de Prozis es una crema de cacahuete elaborada partir de un sólo ingrediente. Deliciosa y natural con mucho sabor y un alto contenido proteico. ¡Ideal si quieres ponerte en forma!', 'Aporta 30g proteína cada 100g.\r\nAlternativa como snack o crema dulce saludable para deportistas.\r\nAporta fibra, vitamina E y magnesio.\r\nAyuda a mantener la masa muscular.\r\nSabroso y fácil de untar.\r\nTextura suave.', 'Sin sal añadida.\r\nSin aceites hidrogenados.\r\nSin aromas artificiales.\r\nBajo contenido en azúcares.\r\nRica en vitamina E y magnesio.\r\nAdecuada para dietas veganas, kosher y halal.\r\n100% cacahuetes.\r\nTextura suave.\r\n1000g por envase', '100% CACAHUETES. ¡NADA MÁS!', 'CREMA DE CACAHUETE SUAVE', 8, 'If what you are looking for is a delicious protein food that you can include in your regular diet ... try this incredible soft peanut butter! In addition to being a natural source of protein, these peanuts provide fiber, vitamin E, and magnesium.\r\n\r\nWHAT IS SOFT PEANUT CREAM?\r\nProzis Soft Peanut Butter is a peanut butter made from a single ingredient. Delicious and natural with a lot of flavor and a high protein content. Ideal if you want to get in shape!', 'It provides 30g protein every 100g.\r\nAlternative as a snack or healthy sweet cream for athletes.\r\nIt provides fiber, vitamin E and magnesium.\r\nHelps maintain muscle mass.\r\nTasty and easy to spread.\r\nSmooth texture.', 'No added salt.', '100% PEANUTS. NOTHING ELSE!', 'SOFT PEANUT BUTTER'),
	(9, 'Amix te da posibilidad de prepararte un exquisito y cremoso postre compuesto por una mezcla única de proteínas de calidad con Protein Pudding Cream, perteneciente a la línea de calidad a Mr. Poppers.\r\n\r\nGracias al contenido en proteína de suero de leche obtenida por CFM®, cada ración de Protein Pudding Cream te aporta ¡25g de proteína! Para disfrutarlo, ¡solo tienes que mezclar con agua y listo!\r\n\r\nAmix Nutrition es una compañía fundada en 2003 que desarrolla productos innovadores, fabricados con ingredientes de máxima calidad. En este tiempo, se ha convertido en una de las principales marcas de referencia dentro de la nutrición deportiva y su amplia gama de complementos es la opción elegida por deportistas de todo el mundo.', NULL, 'Postre enriquecido con proteína de suero de leche.\r\n25,6g de proteínas por dosis (50g).\r\n15g de carbohidratos por dosis.\r\n1,9g de grasas por dosis.\r\nFácil y rápida preparación.\r\n600g por envase.\r\nLa información nutricional puede variar ligeramente dependiendo del sabor.', '¡Cremoso y proteico postre!', 'PROTEIN PUDDING CREAM MR. POPPERS', 9, '\r\nAmix gives you the possibility of preparing an exquisite and creamy dessert consisting of a unique blend of quality proteins with Protein Pudding Cream, belonging to the quality line of Mr. Poppers.\r\n\r\nThanks to the content of whey protein obtained by CFM®, each serving of Protein Pudding Cream gives you 25g of protein! To enjoy it, you just have to mix with water and that\''s it!\r\n\r\nAmix Nutrition is a company founded in 2003 that develops innovative products, made with the highest quality ingredients. In this time, it has become one of the main reference brands in sports nutrition and its wide range of supplements is the option chosen by athletes around the world.', NULL, '\r\nDessert enriched with whey protein.\r\n25.6g of protein per dose (50g).\r\n15g of carbohydrates per dose.\r\n1.9g of fat per dose.\r\nQuick and easy preparation.\r\n600g per container.\r\nNutritional information may vary slightly depending on flavor.', 'Creamy and protein dessert!', 'PROTEIN PUDDING CREAM MR. POPPERS'),
	(10, 'Whey Choco Butter Bonbon de Prozis es una crema de chocolate proteica, sin aceite de palma y con un bajo contenido en azúcar que te ayudará a controlar tu peso de forma más efectiva. ¿A qué esperas para probarla?', 'Fuente de proteína.\r\nBajo en azúcares y sin aceite de palma.\r\nIncreíblemente deliciosa.\r\nApta para veganos.', '5g de hidratos de carbono por dosis (1 cucharada)\r\n8g de grasa por dosis.\r\n21% de proteína.\r\nBajo en azúcares.\r\nFácil de untar.\r\nTextura cremosa.\r\nSabor bonbon.\r\n250g por envase.', 'LA CREMA DE CHOCOLATE MÁS SALUDABLE', 'WHEY CHOCO BUTTER BONBON 250G', 10, 'Whey Choco Butter Bonbon by Prozis is a protein chocolate cream, without palm oil and with a low sugar content that will help you control your weight more effectively. What are you waiting to try it?', 'Protein source.\r\nLow in sugars and without palm oil.\r\nIncredibly delicious.\r\nSuitable for vegans.', '5g carbohydrates per serving (1 tablespoon)\r\n8g of fat per serving.\r\n21% protein.\r\nLow in sugars.\r\nEasy to spread.\r\nCreamy texture.\r\nBonbon flavor.\r\n250g per container.', 'THE HEALTHIER CHOCOLATE CREAM', 'WHEY CHOCO BUTTER BONBON 250G');

INSERT INTO `foto` (`id`, `principal`, `ruta`, `FK_PRODUCTO`) VALUES
	(1, '1', 'xtremeWheyProtein/imagen1.jpg', 1),
	(2, '0', 'xtremeWheyProtein/imagen2.jpg', 1),
	(3, '0', 'xtremeWheyProtein/imagen3.jpg', 1),
	(4, '0', 'xtremeWheyProtein/imagen4.jpg', 1),
	(5, '1', 'xtremeWheyProtein/imagen1.jpg', 2),
	(6, '0', 'xtremeWheyProtein/imagen2.jpg', 2),
	(7, '0', 'xtremeWheyProtein/imagen3.jpg', 2),
	(8, '0', 'xtremeWheyProtein/imagen4.jpg', 2),
	(9, '1', 'preentreno/imagen1.jpg', 3),
	(10, '0', 'preentreno/imagen2.jpg', 3),
	(11, '0', 'preentreno/imagen3.jpg', 3),
	(12, '0', 'preentreno/imagen4.jpg', 3),
	(13, '1', 'vitargo/imagen1.jpg', 4),
	(14, '0', 'vitargo/imagen2.jpg', 4),
	(15, '0', 'vitargo/imagen3.jpg', 4),
	(16, '0', 'vitargo/imagen4.jpg', 4),
	(17, '1', 'CLA150/imagen1.jpg', 5),
	(18, '0', 'CLA150/imagen2.jpg', 5),
	(19, '0', 'CLA150/imagen3.jpg', 5),
	(20, '0', 'CLA150/imagen4.jpg', 5),
	(21, '1', 'creatina150/imagen1.jpg', 6),
	(22, '0', 'creatina150/imagen2.jpg', 6),
	(23, '0', 'creatina150/imagen3.jpg', 6),
	(24, '0', 'creatina150/imagen4.jpg', 6),
	(25, '1', 'alimentacion/pancake/imagen1.jpg', 7),
	(26, '0', 'alimentacion/pancake/imagen2.jpg', 7),
	(27, '0', 'alimentacion/pancake/imagen3.jpg', 7),
	(28, '0', 'alimentacion/pancake/imagen4.jpg', 7),
	(29, '1', 'alimentacion/peanutbutter/imagen1.jpg', 8),
	(30, '0', 'alimentacion/peanutbutter/imagen2.jpg', 8),
	(31, '0', 'alimentacion/peanutbutter/imagen3.jpg', 8),
	(32, '0', 'alimentacion/peanutbutter/imagen4.jpg', 8),
	(33, '1', 'alimentacion/pudding/imagen1.jpg', 9),
	(34, '0', 'alimentacion/pudding/imagen2.jpg', 9),
	(35, '0', 'alimentacion/pudding/imagen3.jpg', 9),
	(36, '0', 'alimentacion/pudding/imagen4.jpg', 9),
	(37, '1', 'alimentacion/chocoButter/imagen1.jpg', 10),
	(38, '0', 'alimentacion/chocoButter/imagen2.jpg', 10),
	(39, '0', 'alimentacion/chocoButter/imagen3.jpg', 10),
	(40, '0', 'alimentacion/chocoButter/imagen4.jpg', 10);
/*INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(232),
	(232),
	(232),
	(232),
	(232),
	(232),
	(232),
	(232),
	(232),
	(232),
	(232),
	(232),
	(232);*/
INSERT INTO `valor_nutricional` (`id`, `advertencias`, `alergias`, `conservacion`, `dosis`, `dosis_diaria`, `dosis_envase`, `ingredientes`, `modo_empleo`, `otros_ingredientes`, `FK_PRODUCTO`, `advertencias_eng`, `alergias_eng`, `conservacion_eng`, `ingredientes_eng`, `modo_empleo_eng`, `otros_ingredientes_eng`) VALUES
	(1, 'Si tiene alguna pregunta sobre el uso del producto, le recomendamos que consulte con su médico o nutricionista. La información nutricional puede variar de un sabor a otro.', 'Contiene leche. Puede contener trazas de soja, huevo y gluten. Este producto no debe sustituir una alimentación variada y equilibrada ni un estilo de vida saludable. Conservar herméticamente sellado en su envase original en un lugar fresco, seco y alejado de la luz solar directa.', 'Mantener en un lugar fresco y seco.', 35, 35, 66, ' Concentrado de Proteína de Suero , Aislado de Proteína de Suero Microfiltrada por Flujo Cruzado(con fracciones de proteína beta-lactoalbmina, alfa lactoalbumina, albumina de suero bobino, inmunoglobulina, glicomacropeptidos, lactoperina, lactoperosidasa.) , Maltodextrina , Goma Xantana , Goma Guar , Lecitina de Soja , Aromas , Sucralosa(Splenda.) , Acesulfamo K , Colorantes ( Raiz de Remolacha(Extracto de Remolacha) ) , Enzimas Digestivas ( Amilasa , Lipasa , Lactasa , Celulasa , Proteasa Bacteriana Neutra )', 'Realizar una dosis de 35 g de producto con 200 ml de agua o leche desnatada al finalizar el entrenamiento.', NULL, 1, 'If you have any questions about the use of the product, we recommend that you consult your doctor or nutritionist. Nutritional information may vary from one flavor to another.', 'Contains milk. May contain traces of soy, egg, and gluten. This product should not replace a varied and balanced diet or a healthy lifestyle. Store tightly sealed in its original container in a cool, dry place and away from direct sunlight.', 'Keep in a cool and dry place.', 'Whey Protein Concentrate, Cross Flow Microfiltered Whey Protein Isolate (with fractions of beta-lactoalbmine protein, alpha lactoalbumine, bovine serum albumin, immunoglobulin, glycomacropeptides, lactoperine, lactoperosidase.), Maltodextrin, Gum Xantana, Guar Gum Soy Lecithin, Flavors, Sucralose (Splenda.), Acesulfame K, Dyes (Beet Root (Beet Extract)), Digestive Enzymes (Amylase, Lipase, Lactase, Cellulase, Neutral Bacterial Protease)', 'Perform a dose of 35 g of product with 200 ml of water or skim milk at the end of the training.', NULL),
	(2, 'Mantener fuera del alcance de los niños más pequeños. No superar la dosis diaria expresamente recomendada. No debe utilizarse como sustituto de una dieta equilibrada. Este producto no pretende diagnosticar, tratar, curar o prevenir ninguna enfermedad.', 'Leche, Soja, Huevo, Gluten, Pescado, Cacahuetes, Crustáceos, Altramuces, Cereales, Frutos Secos (Arbolios), Benzoato de Bencilo', ' Mantener en un lugar fresco y seco.', 30, 30, 10, 'L-Citrulina , L-Arginina , Beta Alanina , L-Glutamina ( Kyowa™ Quality ) , Creatina Monohidrato ( Kre-Alkalyn® ) , L-Leucina , Ácido D-Aspártico , L-Lisina , L-Taurina , Acidulante ( Ácido Cítrico ) , Aroma Natural , Antiaglomerante ( Dióxido de Silicio ) , Colorante ( Beta Caroteno ) , Extracto de Panax Notoginseng , Extracto de Raíz de Astragalus Membranaceus (AstraGin™) , Vitamina B3 ( Nicotinamida ) , Extracto de Pimienta Negra ( BioPerina® )', 'Diluir 30 g de producto (un cazo raso) en 250-300ml de agua. Tomar 20 minutos antes de la actividad física.', NULL, 3, 'Keep out of the reach of little kids. Do not exceed the expressly recommended daily dose. Keep away from direct sunlight. Food supplements should not be used as a substitute for a balanced diet.', 'Milk, Soybeans, Eggs, Gluten, Fish, Peanuts, Crustaceans, Lupins, Cereals, Nuts (Arbolios), Benzyl Benzoate', 'Keep in a cool and dry place.', 'L-Citrulline, L-Arginine, Beta Alanine, L-Glutamine (Kyowa ™ Quality), Creatine Monohydrate (Kre-Alkalyn®), L-Leucine, D-Aspartic Acid, L-Lysine, L-Taurine, Acidulant (Citric Acid ), Natural Flavor, Anti-caking Agent (Silicon Dioxide), Dye (Beta Carotene), Panax Notoginseng Extract, Astragalus Membranaceus Root Extract (AstraGin ™), Vitamin B3 (Nicotinamide), Black Pepper Extract (BioPerina®)', 'Dilute 30 g of product (a flat saucepan) in 250-300ml of water. Take 20 minutes before physical activity.', NULL),
	(3, ' Mantener fuera del alcance de los niños más pequeños. No superar la dosis diaria expresamente recomendada. No debe utilizarse como sustituto de una dieta equilibrada. Este producto no pretende diagnosticar, tratar, curar o prevenir ninguna enfermedad.', 'No contiene Gluten', 'Mantener en un lugar fresco y seco.', 72, 72, 27, 'Almidón', 'Rellenar una botella de 200 ml de agua. Añadir 2,5 cucharadas de vitargo con 300-400 ml de agua y agitar vigorosamente.', NULL, 4, 'Keep out of the reach of little kids. Do not exceed the expressly recommended daily dose. Keep away from direct sunlight. Food supplements should not be used as a substitute for a balanced diet.', 'Does not contain Gluten', 'Keep in a cool and dry place.', 'Starch', 'Fill a 200 ml bottle of water. Add 2.5 tablespoons of vitargo with 300-400 ml of water and shake vigorously.', NULL),
	(4, 'Si tiene alguna pregunta sobre el uso del producto, le recomendamos que consulte con su médico o nutricionista. La información nutricional puede variar de un sabor a otro.', 'Contiene leche. Puede contener trazas de soja, huevo y gluten. Este producto no debe sustituir una alimentación variada y equilibrada ni un estilo de vida saludable. Conservar herméticamente sellado en su envase original en un lugar fresco, seco y alejado de la luz solar directa.', 'Mantener en un lugar fresco y seco.', 35, 35, 33, ' Concentrado de Proteína de Suero , Aislado de Proteína de Suero Microfiltrada por Flujo Cruzado(con fracciones de proteína beta-lactoalbmina, alfa lactoalbumina, albumina de suero bobino, inmunoglobulina, glicomacropeptidos, lactoperina, lactoperosidasa.) , Maltodextrina , Goma Xantana , Goma Guar , Lecitina de Soja , Aromas , Sucralosa(Splenda.) , Acesulfamo K , Colorantes ( Raiz de Remolacha(Extracto de Remolacha) ) , Enzimas Digestivas ( Amilasa , Lipasa , Lactasa , Celulasa , Proteasa Bacteriana Neutra )', 'Realizar una dosis de 35 g de producto con 200 ml de agua o leche desnatada al finalizar el entrenamiento.', NULL, 2, 'If you have any questions about the use of the product, we recommend that you consult your doctor or nutritionist. Nutritional information may vary from one flavor to another.', 'Contains milk. May contain traces of soy, egg, and gluten. This product should not replace a varied and balanced diet or a healthy lifestyle. Store tightly sealed in its original container in a cool, dry place and away from direct sunlight.', 'Keep in a cool and dry place.', 'Whey Protein Concentrate, Cross Flow Microfiltered Whey Protein Isolate (with fractions of beta-lactoalbmine protein, alpha lactoalbumine, bovine serum albumin, immunoglobulin, glycomacropeptides, lactoperine, lactoperosidase.), Maltodextrin, Gum Xantana, Guar Gum Soy Lecithin, Flavors, Sucralose (Splenda.), Acesulfame K, Dyes (Beet Root (Beet Extract)), Digestive Enzymes (Amylase, Lipase, Lactase, Cellulase, Neutral Bacterial Protease)', 'Perform a dose of 35 g of product with 200 ml of water or skim milk at the end of the training.', NULL),
	(5, 'Mantener fuera del alcance de los niños más pequeños. No superar la dosis diaria expresamente recomendada. Mantener alejado de la luz solar directa. Los complementos alimenticios no deben utilizarse como sustitutos de una dieta equilibrada.', 'Contiene leche. Puede contener trazas de soja, huevo y gluten. ', 'Mantener en un lugar fresco y seco.', 3, 3, 30, 'Aceite de Cártamo , Gelatina , Humectante ( Glicerol )', 'Tomar 3 perlas al día con las comidas', NULL, 5, 'Keep out of the reach of little kids. Do not exceed the expressly recommended daily dose. Keep away from direct sunlight. Food supplements should not be used as a substitute for a balanced diet.', 'Contains milk. May contain traces of soy, egg, and gluten. This product should not replace a varied and balanced diet or a healthy lifestyle. Store tightly sealed in its original container in a cool, dry place and away from direct sunlight.', 'Keep in a cool and dry place.', 'Safflower Oil, Gelatin, Moisturizer (Glycerol)', 'Take 3 pearls a day with meals', NULL),
	(6, 'No apto para mujeres embarazada o en periodo de lactancia. Mantener fuera del alcance de los niños más pequeños. No superar la dosis diaria expresamente recomendada. Mantener alejado de la luz solar directa. Los complementos alimenticios no deben utilizarse como sustitutos de una dieta equilibrada. Solo para adultos.', 'Contiene leche. Puede contener trazas de soja, huevo y gluten. Este producto no debe sustituir una alimentación variada y equilibrada ni un estilo de vida saludable. Conservar herméticamente sellado en su envase original en un lugar fresco, seco y alejado de la luz solar directa.', 'Mantener en un lugar fresco y seco.', 3, 3, 96, 'Creapure® ( Creatina Monohidrato ) , Aromas , Edulcorantes ( Sucralosa , Acesulfamo K ) , Acido ( Ácido Cítrico ) , Colorante ( Beta Caroteno )', 'Tomar 1 dosis al día. Mezclar con la cantidad deseada de agua o bebida preferida. Sugerencia: añadir 4 cazos rasos (3.1g) a 200-250ml de agua.', NULL, 6, 'Not suitable for pregnant or lactating women. Keep out of the reach of little kids. Do not exceed the expressly recommended daily dose. Keep away from direct sunlight. Food supplements should not be used as a substitute for a balanced diet. Only for adults.', 'Contains milk. May contain traces of soy, egg, and gluten. This product should not replace a varied and balanced diet or a healthy lifestyle. Store tightly sealed in its original container in a cool, dry place and away from direct sunlight.', 'Keep in a cool and dry place.', 'Creapure® (Creatine Monohydrate), Flavors, Sweeteners (Sucralose, Acesulfame K), Acid (Citric Acid), Color (Beta Carotene)', 'Take 1 dose a day. Mix with the desired amount of water or preferred drink. Suggestion: add 4 level saucepans (3.1g) to 200-250ml of water.', NULL),
	(7, 'Si tiene alguna pregunta sobre el uso del producto, le recomendamos que consulte con su médico o nutricionista. La información nutricional puede variar de un sabor a otro.', 'Contiene leche. Puede contener trazas de soja, huevo y gluten. Este producto no debe sustituir una alimentación variada y equilibrada ni un estilo de vida saludable. Conservar herméticamente sellado en su envase original en un lugar fresco, seco y alejado de la luz solar directa.', 'Mantener en un lugar fresco y seco.', 25, 1, 7, 'Proteína de Leche(45.8%) , Harina de Trigo ( Gluten ) , Clara de Huevo en Polvo , Huevo Entero en Polvo , Fécula de Patata Modificada , Acidificante: Ácido Cítrico , Sal , Levadura en Polvo , Bicarbonato de Sodio , Polidextrosa , Emulgente: Lecitina de Soja , Edulcorantes ( Sucralosa , Acesulfamo Potásico )', 'Precalentar a fuego medio una sartén con revestimiento anti-adhesivo. Mezclar el contenido de una dosis en un tazón o una batidora con 70 ml de agua fría. Verter la mezcla en la sartén caliente. Cocinar de 1 a 2 minutos por cada lado.', NULL, 7, 'If you have any questions about the use of the product, we recommend that you consult your doctor or nutritionist. Nutritional information may vary from one flavor to another.', 'Contains milk. May contain traces of soy, egg, and gluten. This product should not replace a varied and balanced diet or a healthy lifestyle. Store tightly sealed in its original container in a cool, dry place and away from direct sunlight.', 'Keep in a cool and dry place.', 'Milk Protein (45.8%), Wheat Flour (Gluten), Egg White Powder, Whole Egg Powder, Modified Potato Starch, Acidifying: Citric Acid, Salt, Sodium Bicarbonate, Polydextrose, Emulsifier: Soy Lecithin, Sweeteners (Sucralose, Acesulfame Potassium)', 'Preheat a skillet with anti-adhesive coating over medium heat. Mix the contents of one dose in a bowl or a mixer with 70 ml of cold water. Pour the mixture into the hot pan. Cook 1 to 2 minutes on each side.', NULL),
	(8, 'Si tiene alguna pregunta sobre el uso del producto, le recomendamos que consulte con su médico o nutricionista. La información nutricional puede variar de un sabor a otro.', 'Contiene leche. Puede contener trazas de soja, huevo y gluten. Este producto no debe sustituir una alimentación variada y equilibrada ni un estilo de vida saludable. Conservar herméticamente sellado en su envase original en un lugar fresco, seco y alejado de la luz solar directa.', 'Mantener en un lugar fresco y seco.', 33, 1, 33, ' Cacahuetes (100%)', 'Consumir al gusto. Es normal cierta separación en el aceite, simplemente agitar antes de su utilización.', NULL, 8, 'If you have any questions about the use of the product, we recommend that you consult your doctor or nutritionist. Nutritional information may vary from one flavor to another.', 'Contains milk. May contain traces of soy, egg, and gluten. This product should not replace a varied and balanced diet or a healthy lifestyle. Store tightly sealed in its original container in a cool, dry place and away from direct sunlight.', 'Keep in a cool and dry place.', 'Peanuts (100%)', '\r\nConsume to taste. A certain separation in the oil is normal, simply shake before use.', NULL),
	(9, 'Si tiene alguna pregunta sobre el uso del producto, le recomendamos que consulte con su médico o nutricionista. La información nutricional puede variar de un sabor a otro.', 'Contiene leche. Puede contener trazas de soja, huevo y gluten. Este producto no debe sustituir una alimentación variada y equilibrada ni un estilo de vida saludable. Conservar herméticamente sellado en su envase original en un lugar fresco, seco y alejado de la luz solar directa.', 'Mantener en un lugar fresco y seco.', 50, 12, 50, 'Mezcla de Proteinas Amix (53%) ( Aislado de Proteína de Suero , Aislado y Concentrado de Proteina de Suero de Leche CFM , Concentrado de Proteína de Suero ) , Harina de Arroz Instantanea Extrusionada , Coco Deshidratado (9.6%) , Estabilizantes ( Goma Guar , Goma Xantana ) , Aroma , Cloruro de Sodio , Edulcorante ( Sucralosa )', 'Consumir al gusto. Es normal cierta separación en el aceite, simplemente agitar antes de su utilización.', NULL, 9, 'If you have any questions about the use of the product, we recommend that you consult your doctor or nutritionist. Nutritional information may vary from one flavor to another.', 'Contains milk. May contain traces of soy, egg, and gluten. This product should not replace a varied and balanced diet or a healthy lifestyle. Store tightly sealed in its original container in a cool, dry place and away from direct sunlight.', 'Keep in a cool and dry place.', 'Amix Protein Blend (53%) (Whey Protein Isolate, CFM Milk Whey Protein Isolate and Concentrate, Whey Protein Concentrate), Extruded Instant Rice Flour, Dehydrated Coconut (9.6%), Stabilizers (Guar Gum , Xanthan Gum), Aroma, Sodium Chloride, Sweetener (Sucralose)', '\r\nConsume to taste. A certain separation in the oil is normal, simply shake before use.', NULL),
	(10, 'Si tiene alguna pregunta sobre el uso del producto, le recomendamos que consulte con su médico o nutricionista. La información nutricional puede variar de un sabor a otro.', 'Contiene leche. Puede contener trazas de soja, huevo y gluten. Este producto no debe sustituir una alimentación variada y equilibrada ni un estilo de vida saludable. Conservar herméticamente sellado en su envase original en un lugar fresco, seco y alejado de la luz solar directa.', 'Mantener en un lugar fresco y seco.', 33, 1, 33, 'Aceite Vegetal ( Aceite de Semilla de Colza , Aceite de Karite , Aceite de Coco ) , Edulcorantes ( Maltitol ) , Concentrado de Proteína de Suero(26%) , Avellanas (15%) , Polvo de Cacao Reducido en Grasa , Lecitina de Soja , Aroma', 'Consumir al gusto. Es normal cierta separación en el aceite, simplemente agitar antes de su utilización.', NULL, 10, 'If you have any questions about the use of the product, we recommend that you consult your doctor or nutritionist. Nutritional information may vary from one flavor to another.', 'Contains milk. May contain traces of soy, egg, and gluten. This product should not replace a varied and balanced diet or a healthy lifestyle. Store tightly sealed in its original container in a cool, dry place and away from direct sunlight.', 'Keep in a cool and dry place.', 'Vegetable Oil (Rapeseed Oil, Shea Oil, Coconut Oil), Sweeteners (Maltitol), Whey Protein Concentrate (26%), Hazelnuts (15%), Cocoa Powder Reduced Fat, Soy Lecithin, Smell', '\r\nConsume to taste. A certain separation in the oil is normal, simply shake before use.', NULL);

INSERT INTO `info_basica` (`id`, `valor_energetico`, `azucares`, `grasas`, `hidratos`, `proteinas`, `saturadas`, `sodio`, `FK_VALOR_NUTRICIONAL`) VALUES
	(1, 377, 2, 8, 5, 75, 1, 280, 1),
	(2, 0, NULL, NULL, NULL, NULL, NULL, NULL, 2),
	(3, 375, 0, 0, 92, 0, 0, 0, 3),
	(4, 377, 2, 8, 5, 75, 1, 280, 4),
	(5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5),
	(6, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6),
	(7, 368, 1, 4, 24, 58, 2, 2, 7),
	(8, 600, 1, 12, 3, 28, 10, 0, 8),
	(9, 372, 9, 6, 30, 50, 2, 1, 9),
	(10, 586, 7, 37, 12, 22, 8, 1, 10);
INSERT INTO `info_vitaminas` (`id`, `nombre`, `valor`, `FK_VALOR_NUTRICIONAL`) VALUES
	(1, 'L-Citrulina', '7621', 2),
	(2, 'L-Arginina', '4317', 2),
	(3, 'L-Glutamina', '3200', 2),
	(4, 'Beta Alanina', '3318', 2),
	(5, 'Creatina', '1999', 2),
	(6, 'L-Leucina', '1500', 2),
	(7, 'Cártamo', '3000', 5),
	(8, 'Ácido Linoleico Conjugado', '2400', 5),
	(9, 'Creapure®', '3000', 6);
INSERT INTO `metodo_envio` (`id`, `nombre`, `nombre_eng`, `descripcion`, `descripcion_eng`, `icono`) VALUES
	(1, 'MRW', 'MRW', 'Empresa de transporte Express', 'Express transport company ', 'mrw.png'),
	(2, 'SEUR', 'SEUR', 'Empresa de transporte', 'Transport company', 'seur.png');
INSERT INTO `metodo_pago` (`id`, `nombre`, `nombre_eng`, `descripcion`, `descripcion_eng`, `icono`) VALUES
	(1, 'Visa', 'Visa', 'Tarjeta de crédito, Visa, Mastercard', 'Credit card, Visa, Mastercard', 'visa.png'),
	(2, 'Transferencia', 'Transfer', 'Ingreso/Transferencia Bancaria', 'Bank Income / Transfer', 'transfer.png');
INSERT INTO `pedido` (`id`, `cantidadProductos`, `enviado`, `fechaPedido`, `finalizado`, `pagado`, `precioTotal`, `FK_USUARIO`, `destinatario`, `num_pedido`, `FK_PEDIDO_METODO_ENVIO`, `precio_envio`, `FK_PEDIDO_METODO_PAGO`) VALUES
	(1, 3, '1', '2020-05-21 11:36:06', '1', '1', 79.84, 1, 'Luiso Aceitillo Lopez', '12SAD2', 1, 0, 1),
	(2, 1, '0', '2020-05-22 16:59:55', '1', '1', NULL, 1, 'Luis Aceitillo', '14AEW3', 1, NULL, 1),
	(3, 1, '1', '2020-05-22 17:39:25', '1', '1', NULL, 1, 'Luiso Bocadillo', '44RTT21', 2, NULL, 1);
	
INSERT INTO `sabor` (`id`, `sabor`, `sabor_eng`) VALUES
	(1, 'Chocolate', 'Chocolate'),
	(2, 'Fresa', 'Strawberry'),
	(3, 'Plátano', 'Banana'),
	(4, 'Cookies', 'Cookies'),
	(5, 'Caramelo', 'Caramel'),
	(6, 'Chocolate Blanco', 'White Chocolate'),
	(7, 'Chocolate con Leche', 'Milk Chocolate'),
	(8, 'Vainilla', 'Vanilla'),
	(9, 'Crema de Cacahuete', 'Peanut Butter'),
	(10, 'Brownie', 'Brownie'),
	(11, 'Natural', 'Natural'),
	(12, 'Naranja', 'Orange'),
	(13, 'Limón', 'Lemon');
	
INSERT INTO `producto_cesta` (`id`, `cantidad`, `FK_SABOR_PRODUCT_CESTA`, `FK_PRODUCTO_PRODUCT_CESTA`, `FK_CESTA`) VALUES
	(229, 1, 1, 2, 119),
	(230, 3, 10, 4, 119),
	(231, 2, 11, 5, 119);
INSERT INTO `registration_token` (`id`, `token`, `expiry_date`, `FK_USUARIO`) VALUES
	(6, '2f9c4e5a-6f74-41da-a0a0-c9cd9ebd32c8', '2020-07-15', 213);
INSERT INTO `rel_pedidos_productos` (`FK_PEDIDO`, `FK_PRODUCTO`) VALUES
	(1, 1),
	(1, 4),
	(1, 5),
	(2, 1);

INSERT INTO `rel_sabores_productos` (`FK_SABOR`, `FK_PRODUCTO`) VALUES
	(1, 1),
	(6, 1),
	(2, 1),
	(10, 1),
	(2, 3),
	(3, 3),
	(10, 4),
	(4, 4),
	(7, 4),
	(5, 4),
	(1, 2),
	(9, 2),
	(3, 2),
	(8, 2),
	(11, 5),
	(11, 6),
	(12, 6),
	(13, 6),
	(1, 7),
	(9, 7),
	(9, 8),
	(1, 9),
	(3, 9),
	(1, 10);
