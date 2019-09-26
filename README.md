# AD-FIB
drop table imagen;
drop table usuarios;

create table usuarios (
		id_usuario varchar (256) primary key, 
		password varchar (256)
);

insert into usuarios values ('admin', 'admin');
create table imagen (
        id              int NOT NULL,
        titulo           varchar (256) NOT NULL,
        descripcion     varchar (1024) NOT NULL,
        palabras_clave        varchar (256) NOT NULL,
        autor          varchar (256) NOT NULL,
        fecha_creacion   varchar (256) NOT NULL,  /* Format AAAA/MM/DD Asked to the user*/
        fecha_alta    varchar (256) NOT NULL,  /* Format AAAA/MM/DD Filled when stored */
        nombre_fichero        varchar (512) NOT NULL, /* Only the name of the file, directory is fixed by the Web Application*/
        primary key (id) , 
		foreign key (autor) references usuarios(id_usuario)
);
