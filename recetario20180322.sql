CREATE DATABASE  IF NOT EXISTS `recetario` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `recetario`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: recetario
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auxingrediente`
--

DROP TABLE IF EXISTS `auxingrediente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auxingrediente` (
  `id` int(11) NOT NULL,
  `Cant` varchar(11) DEFAULT NULL COMMENT 'Cantidad que se usara de ingrediente\n',
  `Unidad` varchar(70) DEFAULT NULL COMMENT 'Unidad de medida del ingrediente',
  `idR` int(11) DEFAULT NULL COMMENT 'relacion entre recata y auxIngrediente',
  `IdI` int(11) DEFAULT NULL COMMENT 'Relacion entre ingrediente y auxIngrediente',
  PRIMARY KEY (`id`),
  KEY `fk_AuxIngrediente_Receta1_idx` (`idR`),
  KEY `fk_AuxIngrediente_Ingrediente1_idx` (`IdI`),
  CONSTRAINT `fk_AuxIngrediente_Ingrediente1` FOREIGN KEY (`IdI`) REFERENCES `ingrediente` (`IdI`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_AuxIngrediente_Receta1` FOREIGN KEY (`idR`) REFERENCES `receta` (`idR`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auxingrediente`
--

LOCK TABLES `auxingrediente` WRITE;
/*!40000 ALTER TABLE `auxingrediente` DISABLE KEYS */;
/*!40000 ALTER TABLE `auxingrediente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingrediente`
--

DROP TABLE IF EXISTS `ingrediente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingrediente` (
  `IdI` int(11) NOT NULL COMMENT 'Llave primaria del ingrediente',
  `Nom` varchar(80) DEFAULT NULL COMMENT 'Nombre del ingrediente',
  PRIMARY KEY (`IdI`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingrediente`
--

LOCK TABLES `ingrediente` WRITE;
/*!40000 ALTER TABLE `ingrediente` DISABLE KEYS */;
/*!40000 ALTER TABLE `ingrediente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procedimiento`
--

DROP TABLE IF EXISTS `procedimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `procedimiento` (
  `idP` int(11) NOT NULL COMMENT 'Llave primaria del procedimiento ',
  `Descripcion` varchar(400) DEFAULT NULL COMMENT 'Procedimiento o paso a seguir para la receta',
  `Nom` varchar(45) DEFAULT NULL COMMENT 'Nombre del paso ',
  `idR` int(11) DEFAULT NULL COMMENT 'Llave foranea, relacion entre procedimiento y receta',
  PRIMARY KEY (`idP`),
  KEY `fk_Procedimiento_Receta1_idx` (`idR`),
  CONSTRAINT `fk_Procedimiento_Receta1` FOREIGN KEY (`idR`) REFERENCES `receta` (`idR`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procedimiento`
--

LOCK TABLES `procedimiento` WRITE;
/*!40000 ALTER TABLE `procedimiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `procedimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receta`
--

DROP TABLE IF EXISTS `receta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receta` (
  `idR` int(11) NOT NULL COMMENT 'Llave primaria de la receta',
  `Nom` varchar(150) DEFAULT NULL COMMENT 'Nombre de la receta',
  `Tipo` varchar(200) DEFAULT NULL COMMENT 'Es a que tipo de cocina pertenece la receta',
  `Valor` varchar(2) DEFAULT NULL COMMENT 'valoracion que le dan a la receta',
  `img` varchar(500) DEFAULT NULL COMMENT 'Imagen o foto de la receta',
  `IdU` int(11) DEFAULT NULL COMMENT 'Llave foranea, relacion entre usuario y receta',
  PRIMARY KEY (`idR`),
  KEY `fk_Receta_Usuario_idx` (`IdU`),
  CONSTRAINT `fk_Receta_Usuario` FOREIGN KEY (`IdU`) REFERENCES `usuario` (`IdU`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receta`
--

LOCK TABLES `receta` WRITE;
/*!40000 ALTER TABLE `receta` DISABLE KEYS */;
/*!40000 ALTER TABLE `receta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `IdU` int(11) NOT NULL COMMENT 'LLave primaria de usuario.',
  `Nom` varchar(250) DEFAULT NULL COMMENT 'Nombre completo del usuario.',
  `Usuario` varchar(100) DEFAULT NULL COMMENT 'NickName del usuario',
  `FNac` date DEFAULT NULL COMMENT 'Fecha de nacimiento del usuario',
  `Cont` varchar(30) DEFAULT NULL COMMENT 'Contraseña del usuario.',
  `Imagen` varchar(500) DEFAULT NULL COMMENT 'Foto de perfil del usuario',
  PRIMARY KEY (`IdU`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `vsttblaux`
--

DROP TABLE IF EXISTS `vsttblaux`;
/*!50001 DROP VIEW IF EXISTS `vsttblaux`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vsttblaux` AS SELECT 
 1 AS `idaux`,
 1 AS `Cantidad`,
 1 AS `Unidad`,
 1 AS `idReceta`,
 1 AS `IdIngrediente`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vsttblingrediente`
--

DROP TABLE IF EXISTS `vsttblingrediente`;
/*!50001 DROP VIEW IF EXISTS `vsttblingrediente`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vsttblingrediente` AS SELECT 
 1 AS `IdIngrediente`,
 1 AS `Nombre`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vsttblprocedimiento`
--

DROP TABLE IF EXISTS `vsttblprocedimiento`;
/*!50001 DROP VIEW IF EXISTS `vsttblprocedimiento`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vsttblprocedimiento` AS SELECT 
 1 AS `idProcedimiento`,
 1 AS `Descripcion`,
 1 AS `Nombre`,
 1 AS `idReceta`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vsttblreceta`
--

DROP TABLE IF EXISTS `vsttblreceta`;
/*!50001 DROP VIEW IF EXISTS `vsttblreceta`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vsttblreceta` AS SELECT 
 1 AS `idReceta`,
 1 AS `Nombre`,
 1 AS `Tipo`,
 1 AS `Valoracion`,
 1 AS `imagen`,
 1 AS `IdUsuario`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vsttblusuario`
--

DROP TABLE IF EXISTS `vsttblusuario`;
/*!50001 DROP VIEW IF EXISTS `vsttblusuario`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vsttblusuario` AS SELECT 
 1 AS `IdUsuario`,
 1 AS `Nombre`,
 1 AS `Usuario`,
 1 AS `FNacimiento`,
 1 AS `Contraseña`,
 1 AS `Imagen`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping events for database 'recetario'
--

--
-- Dumping routines for database 'recetario'
--
/*!50003 DROP PROCEDURE IF EXISTS `spBusUsuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `spBusUsuario`(in pusu varchar(100),pcont varchar(30))
BEGIN
select * from usuario where Usuario=pusu and Cont=pcont;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `spInsAux` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `spInsAux`(in pcant varchar(11), pUni varchar(70), pidr int, pidi int)
BEGIN
set @id= (select ifnull(max(NoCompra),0)+1 from vsttblaux);
insert into auxingrediente values(@id, pcant, pUni, pidr, pidi);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `spInsIngrediente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `spInsIngrediente`(in pnom varchar(80))
BEGIN
set @id= (select ifnull(max(NoCompra),0)+1 from vsttblingrediente);
insert into ingrediente values(@id, upper(pnom));
   
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `spInsProcedimiento` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `spInsProcedimiento`(in pdes varchar(400), pnom varchar(45), pidr int)
BEGIN
set @id= (select ifnull(max(NoCompra),0)+1 from vsttblprocedimiento);
insert into procedimiento values(@id, pdes, pnom, pidr);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `spInsUsuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `spInsUsuario`(in pnom varchar(250), pusu varchar(100), pfnac date, pcont varchar(30), pimg varchar(500))
BEGIN
set @idu= (select ifnull(max(NoCompra),0)+1 from vsttblusuario);
insert into usuario values(@idu, pnom, pusu, pfnac, pcont, pimg);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `vsttblaux`
--

/*!50001 DROP VIEW IF EXISTS `vsttblaux`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vsttblaux` AS select `auxingrediente`.`id` AS `idaux`,`auxingrediente`.`Cant` AS `Cantidad`,`auxingrediente`.`Unidad` AS `Unidad`,`auxingrediente`.`idR` AS `idReceta`,`auxingrediente`.`IdI` AS `IdIngrediente` from `auxingrediente` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vsttblingrediente`
--

/*!50001 DROP VIEW IF EXISTS `vsttblingrediente`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vsttblingrediente` AS select `ingrediente`.`IdI` AS `IdIngrediente`,`ingrediente`.`Nom` AS `Nombre` from `ingrediente` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vsttblprocedimiento`
--

/*!50001 DROP VIEW IF EXISTS `vsttblprocedimiento`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vsttblprocedimiento` AS select `procedimiento`.`idP` AS `idProcedimiento`,`procedimiento`.`Descripcion` AS `Descripcion`,`procedimiento`.`Nom` AS `Nombre`,`procedimiento`.`idR` AS `idReceta` from `procedimiento` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vsttblreceta`
--

/*!50001 DROP VIEW IF EXISTS `vsttblreceta`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vsttblreceta` AS select `receta`.`idR` AS `idReceta`,`receta`.`Nom` AS `Nombre`,`receta`.`Tipo` AS `Tipo`,`receta`.`Valor` AS `Valoracion`,`receta`.`img` AS `imagen`,`receta`.`IdU` AS `IdUsuario` from `receta` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vsttblusuario`
--

/*!50001 DROP VIEW IF EXISTS `vsttblusuario`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vsttblusuario` AS select `usuario`.`IdU` AS `IdUsuario`,`usuario`.`Nom` AS `Nombre`,`usuario`.`Usuario` AS `Usuario`,`usuario`.`FNac` AS `FNacimiento`,`usuario`.`Cont` AS `Contraseña`,`usuario`.`Imagen` AS `Imagen` from `usuario` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-22 22:16:25
