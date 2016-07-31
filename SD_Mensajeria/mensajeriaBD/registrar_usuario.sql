CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_por_user`(
 in nombre char(60),
 in apellido char(60),
 in ciudad char(60),
 in user char(45), 
 in pass char(45), 
 in foto char(60))
BEGIN

    INSERT INTO usuario
         (
           nombre, apellido, ciudad, user, password, estado_conexion, fecha_ult_conexion, foto
         )
    VALUES 
         ( 
           nombre, apellido, ciudad, user, pass,'C', NULL , foto      
         ) ; 
END