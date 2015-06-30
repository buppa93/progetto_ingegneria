use test_ingegneria;

insert into agenzie(numero, nome, indirizzo)
values("ag001", "f.lli Buono", "Gallipoli, Corso Roma 22"),
("ag002", "Blurent", "Gallipoli, Lungomare Marconi 15"),
("ag003", "MotoAndAuto", "Lecce, viale Università 11"),
("ag004", "Noleggi e Vendite", " Copertino, via Circonvallazione snc");

insert into utente(id_utente, nome, cognome, num_telefono, password, tipo)
values("01", "Ivan", "Scorrano", "3337423387", "asd123", "adm"),
("02", "Giuseppe", "Sansone", "0832555756", "fgdg546", "adm"),
("03", "Antonella", "Palamà", "3483326444", "th4r6h4", "cli"),
("04", "Donato", "Tanieli", "3383445561", "fdfv", "cli"),
("05", "Lorenzo", "Greco", "3277898841", "hy5dh", "cli"),
("06", "Stefano", "Carrino", "3334786521", "g4f6gfbg", "cli");


insert into tipo_contratto(id_tipo, tipo_noleggio, tipo_chilometraggio)
values("101", "giornaliero", "illimitato"),
("102", "settimanale", "illimitato"),
("103", "giornaliero", "limitato"),
("104", "settimanale", "limitato");
 
 
insert into fascia( nome, n_porte, n_posti, tipo_vettura)
values("A", 3, 4, "utilitaria"),
("B", 5, 5, "berlina"),
("C", 5, 7, "monovolume"),
("D", 3, 2, "autocarro");


insert into contratto(numero_ordine, id_agenzia, id_cliente, data_inizio, data_fine, agenzia_rest, tipo, kmmax)
values("cont1", "ag002", "01", "2015-12-22", "2016-01-07", "ag001", 101, 200000),
("cont2", "ag004", "05", "2015-08-27", "2015-10-01", "ag004", 103, 10000),
("cont3", "ag003", "04", "2015-07-25", "2015-08-01", "ag003", 102, 100000);

insert into auto(targa, modello, marca, km, id_cliente, id_contratto, id_agenzia, fascia, disponibilita)
values("at256re", "panda", "fiat", 20000, "02", NULL, "ag002", "A", 2),
("le398hh", "leon", "seat", 320000, "04", NULL, "ag002", "B", 4),
("ba333bv", "megane", "renault", 150000, "06", NULL, "ag004", "B", 2);
