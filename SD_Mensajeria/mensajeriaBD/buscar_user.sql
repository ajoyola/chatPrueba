CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_por_user`(in user_name char(45), in clave char(45))
BEGIN
	select id, nombre, apellido, foto from usuario
	where usuario.user=user_name and usuario.password=clave;
END