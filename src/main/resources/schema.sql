--/*DROP TABLE IF EXISTS `info_basica` CASCADE;
--DROP TABLE IF EXISTS `rel_sabores_productos` CASCADE;
--DROP TABLE IF EXISTS `rel_pedidos_productos` CASCADE;
--DROP TABLE IF EXISTS `registration_token` CASCADE;
--DROP TABLE IF EXISTS `profile_image` CASCADE;
--DROP TABLE IF EXISTS `producto_cesta CASCADE`;
--DROP TABLE IF EXISTS `sabor` CASCADE;
--DROP TABLE IF EXISTS `pedido` CASCADE;
--DROP TABLE IF EXISTS `metodo_pago` CASCADE;
--DROP TABLE IF EXISTS `metodo_envio` CASCADE;
--DROP TABLE IF EXISTS `info_vitaminas` CASCADE;
--DROP TABLE IF EXISTS `valor_nutricional` CASCADE;
--DROP TABLE IF EXISTS `hibernate_sequence` CASCADE;
--DROP TABLE IF EXISTS `foto` CASCADE;
--DROP TABLE IF EXISTS `descripcion` CASCADE;
--DROP TABLE IF EXISTS `comentario` CASCADE;
--DROP TABLE IF EXISTS `cesta` CASCADE;
--DROP TABLE IF EXISTS `producto` CASCADE;
--DROP TABLE IF EXISTS `sub_categoria` CASCADE;
--DROP TABLE IF EXISTS `usuario_direccion` CASCADE;
--DROP TABLE IF EXISTS `usuario` CASCADE;
--DROP TABLE IF EXISTS `user_roles` CASCADE;
--DROP TABLE IF EXISTS `users` CASCADE;
--DROP TABLE IF EXISTS `roles` CASCADE;
--DROP TABLE IF EXISTS `genero` CASCADE;
--DROP TABLE IF EXISTS `sub_categoria` CASCADE;
--DROP TABLE IF EXISTS `categoria` CASCADE;
--DROP TABLE IF EXISTS `categoria_padre` CASCADE;*/
--
--
--/*DROP SCHEMA IF EXISTS `test`;*/
--
CREATE SCHEMA IF NOT EXISTS `test`;
USE `test`;


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

CREATE TABLE IF NOT EXISTS `rel_sabores_productos` (
  `FK_SABOR` INTEGER NOT NULL,
  `FK_PRODUCTO` INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS `rel_pedidos_productos` (
  `FK_PEDIDO` INTEGER NOT NULL,
  `FK_PRODUCTO` INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS `registration_token` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `token` varchar(50) DEFAULT NULL,
  `expiry_date` date DEFAULT NULL,
  `FK_USUARIO` INTEGER DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `profile_image` (
  `id` INTEGER NOT NULL,
  `picByte` longblob NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
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

CREATE TABLE IF NOT EXISTS `sabor` (
  `id` INTEGER NOT NULL,
  `sabor` varchar(255) DEFAULT NULL,
  `sabor_eng` varchar(50) DEFAULT NULL,
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

CREATE TABLE IF NOT EXISTS `metodo_pago` (
  `id` INTEGER NOT NULL,
  `nombre` varchar(50) NOT NULL DEFAULT '',
  `nombre_eng` varchar(50) NOT NULL DEFAULT '',
  `descripcion` varchar(50) NOT NULL DEFAULT '',
  `descripcion_eng` varchar(50) NOT NULL DEFAULT '',
  `icono` varchar(50) NOT NULL DEFAULT '',
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

CREATE TABLE IF NOT EXISTS `info_vitaminas` (
  `id` INTEGER NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `valor` varchar(255) DEFAULT NULL,
  `FK_VALOR_NUTRICIONAL` INTEGER DEFAULT NULL,
  PRIMARY KEY (`id`)
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

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `foto` (
  `id` INTEGER NOT NULL,
  `principal` bit(1) DEFAULT NULL,
  `ruta` varchar(255) DEFAULT NULL,
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

CREATE TABLE IF NOT EXISTS `comentario` (
  `id` INTEGER NOT NULL,
  `autor` varchar(255) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `opinion` varchar(255) DEFAULT NULL,
  `puntuacion` double DEFAULT NULL,
  `FK_PRODUCTO` INTEGER DEFAULT NULL,
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

CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` INTEGER NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
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

CREATE TABLE IF NOT EXISTS `roles` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `genero` (
  `id` INTEGER NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `sub_categoria` (
  `id` INTEGER NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `FK_CATEGORIA` INTEGER DEFAULT NULL,
  `kkey` varchar(50) DEFAULT NULL,
  `nombre_eng` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

create table IF NOT EXISTS `categoria_padre` (
  `id` INTEGER not null PRIMARY KEY AUTO_INCREMENT,
  `nombre` varchar null default null,
  `kkey` varchar null default null,
  `nombre_eng` varchar null default null,
  `modulo` varchar null default null
);

CREATE TABLE IF NOT EXISTS `categoria` (
  `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `nombre` VARCHAR NULL DEFAULT NULL,
  `FK_CATEGORIA_PADRE` INTEGER NULL DEFAULT NULL,
  `kkey` VARCHAR NULL DEFAULT NULL,
  `nombre_eng` VARCHAR NULL DEFAULT NULL
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