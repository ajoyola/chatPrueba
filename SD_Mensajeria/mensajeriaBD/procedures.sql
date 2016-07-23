DROP PROCEDURE IF EXISTS buscar_por_user;
DELIMITER //
CREATE PROCEDURE buscar_por_user(in user_name char(45), in clave char(45))
BEGIN
	select id, nombre, apellido from usuario
	where usuario.user=user_name and usuario.password=clave;
END;
// DELIMITER ;

DROP PROCEDURE IF EXISTS consultar_contactos;
DELIMITER //
CREATE PROCEDURE  consultar_contactos(in user_name char(45), in user_id int)
BEGIN
	select cont.nombre, cont.apellido, cont.user from usuario cont
	where cont.id in (select conocidoDelUsuario
	from usuario u, contactos c
	where c.userID=user_id and u.user=user_name);
END;
// DELIMITER ;
