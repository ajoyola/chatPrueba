DROP TABLE IF EXISTS `contactos`;
CREATE TABLE contactos (
	idcontactos integer NOT NULL AUTO_INCREMENT,
    userID int(11) NOT NULL,    
	conocidoDelUsuario int(11) NOT NULL,
    PRIMARY KEY (idcontactos),
	INDEX usuario_ind (userID),
	FOREIGN KEY(userID) REFERENCES usuario(id),
	FOREIGN KEY(conocidoDelUsuario) REFERENCES usuario(id)
);


