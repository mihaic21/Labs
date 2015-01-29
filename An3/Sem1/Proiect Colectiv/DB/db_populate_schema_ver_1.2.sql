USE `graveyards_management_app` ;

SET FOREIGN_KEY_CHECKS = 0; 

truncate table graveyards;

insert into graveyards (name, deleted) values ('Melvin', 1);
insert into graveyards (name, deleted) values ('Oneill', 1);
insert into graveyards (name, deleted) values ('Meadow Valley', 0);
insert into graveyards (name, deleted) values ('Nevada', 1);
insert into graveyards (name, deleted) values ('Arrowood', 0);
insert into graveyards (name, deleted) values ('Chinook', 0);
insert into graveyards (name, deleted) values ('Graedel', 1);
insert into graveyards (name, deleted) values ('Merrick', 1);
insert into graveyards (name, deleted) values ('Merchant', 1);
insert into graveyards (name, deleted) values ('Manufacturers', 0);
insert into graveyards (name, deleted) values ('Swallow', 1);
insert into graveyards (name, deleted) values ('Arkansas', 0);
insert into graveyards (name, deleted) values ('5th', 0);
insert into graveyards (name, deleted) values ('Comanche', 1);
insert into graveyards (name, deleted) values ('Summit', 1);
insert into graveyards (name, deleted) values ('Anzinger', 0);
insert into graveyards (name, deleted) values ('Pine View', 0);
insert into graveyards (name, deleted) values ('Cherokee', 1);
insert into graveyards (name, deleted) values ('Hagan', 1);
insert into graveyards (name, deleted) values ('Morning', 0);
insert into graveyards (name, deleted) values ('Golf', 1);
insert into graveyards (name, deleted) values ('New Castle', 0);
insert into graveyards (name, deleted) values ('Heath', 0);
insert into graveyards (name, deleted) values ('Manufacturer', 1);
insert into graveyards (name, deleted) values ('Hauk', 0);

------------------------------------------------------------------------------------------

truncate table parcels;

insert into parcels (graveyard_id, number, deleted) values (15, 4259, 0);
insert into parcels (graveyard_id, number, deleted) values (1, 1270, 0);
insert into parcels (graveyard_id, number, deleted) values (10, 8560, 1);
insert into parcels (graveyard_id, number, deleted) values (25, 1180, 1);
insert into parcels (graveyard_id, number, deleted) values (5, 5034, 1);
insert into parcels (graveyard_id, number, deleted) values (2, 7244, 0);
insert into parcels (graveyard_id, number, deleted) values (1, 3818, 0);
insert into parcels (graveyard_id, number, deleted) values (21, 4681, 0);
insert into parcels (graveyard_id, number, deleted) values (17, 5493, 0);
insert into parcels (graveyard_id, number, deleted) values (20, 4101, 0);
insert into parcels (graveyard_id, number, deleted) values (2, 8488, 1);
insert into parcels (graveyard_id, number, deleted) values (18, 7768, 1);
insert into parcels (graveyard_id, number, deleted) values (6, 7290, 0);
insert into parcels (graveyard_id, number, deleted) values (19, 6186, 1);
insert into parcels (graveyard_id, number, deleted) values (7, 6939, 0);
insert into parcels (graveyard_id, number, deleted) values (15, 7729, 0);
insert into parcels (graveyard_id, number, deleted) values (22, 2116, 0);
insert into parcels (graveyard_id, number, deleted) values (4, 965, 1);
insert into parcels (graveyard_id, number, deleted) values (10, 2093, 1);
insert into parcels (graveyard_id, number, deleted) values (4, 2398, 0);
insert into parcels (graveyard_id, number, deleted) values (19, 2031, 0);
insert into parcels (graveyard_id, number, deleted) values (1, 8003, 0);
insert into parcels (graveyard_id, number, deleted) values (9, 3245, 1);
insert into parcels (graveyard_id, number, deleted) values (12, 2646, 1);
insert into parcels (graveyard_id, number, deleted) values (3, 2023, 1);

------------------------------------------------------------------------------------------

truncate table graves;

insert into graves (parcel_id, number, surface, remarks, image_uri, deleted) values (23, 6846, 5510.81, 'In sagittis dui vel nisl. Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.

Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst.', 'https://google.co.jp/pede/justo.png', 0);
insert into graves (parcel_id, number, surface, remarks, image_uri, deleted) values (9, 5118, 2381.02, 'Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus.

Phasellus in felis. Donec semper sapien a libero. Nam dui.', 'http://alexa.com/pede/morbi/porttitor/lorem/id.html', 1);
insert into graves (parcel_id, number, surface, remarks, image_uri, deleted) values (4, 5259, 6858.92, 'Sed ante. Vivamus tortor. Duis mattis egestas metus.

Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.

Quisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.', 'http://dion.ne.jp/lorem/id.js', 0);
insert into graves (parcel_id, number, surface, remarks, image_uri, deleted) values (1, 2780, 5944.22, 'Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus.', 'https://freewebs.com/felis/eu/sapien/cursus.png', 0);
insert into graves (parcel_id, number, surface, remarks, image_uri, deleted) values (2, 4417, 5711.41, 'Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus.

Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero.

Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum. Integer a nibh.', 'http://springer.com/morbi/sem/mauris/laoreet/ut/rhoncus/aliquet.jpg', 1);
insert into graves (parcel_id, number, surface, remarks, image_uri, deleted) values (11, 8147, 3689.12, 'Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.

Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.', 'http://usgs.gov/nisi/vulputate/nonummy/maecenas/tincidunt/lacus/at.aspx', 0);
insert into graves (parcel_id, number, surface, remarks, image_uri, deleted) values (10, 7514, 86.21, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin risus. Praesent lectus.

Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis.', 'http://acquirethisname.com/vestibulum/rutrum/rutrum/neque/aenean.js', 0);
insert into graves (parcel_id, number, surface, remarks, image_uri, deleted) values (1, 3733, 5658.37, 'In sagittis dui vel nisl. Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.

Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst.

Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.', 'http://ucoz.ru/nulla/elit/ac/nulla/sed/vel/enim.jpg', 0);
insert into graves (parcel_id, number, surface, remarks, image_uri, deleted) values (24, 5840, 83.91, 'Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius.', 'http://imageshack.us/quis.jsp', 1);
insert into graves (parcel_id, number, surface, remarks, image_uri, deleted) values (25, 7992, 5534.57, 'Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.

Praesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.

Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem.', 'https://nifty.com/quis.jsp', 0);
insert into graves (parcel_id, number, surface, remarks, image_uri, deleted) values (1, 6674, 1157.86, 'Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti.

Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris.', 'http://phpbb.com/non/velit/nec/nisi/vulputate.aspx', 1);
insert into graves (parcel_id, number, surface, remarks, image_uri, deleted) values (12, 5325, 4436.28, 'Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst.', 'http://princeton.edu/ut/volutpat/sapien/arcu/sed/augue/aliquam.xml', 0);
insert into graves (parcel_id, number, surface, remarks, image_uri, deleted) values (8, 5541, 4147.21, 'Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum.

Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est.

Phasellus sit amet erat. Nulla tempus. Vivamus in felis eu sapien cursus vestibulum.', 'http://omniture.com/rutrum/nulla/nunc/purus.html', 1);
insert into graves (parcel_id, number, surface, remarks, image_uri, deleted) values (6, 6401, 3494.33, 'Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est.', 'https://army.mil/porttitor/pede/justo/eu.js', 0);
insert into graves (parcel_id, number, surface, remarks, image_uri, deleted) values (2, 7273, 4434.91, 'Etiam vel augue. Vestibulum rutrum rutrum neque. Aenean auctor gravida sem.', 'http://indiatimes.com/ante/nulla.aspx', 1);
insert into graves (parcel_id, number, surface, remarks, image_uri, deleted) values (22, 3077, 3389.58, 'Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque.', 'https://etsy.com/pulvinar/nulla/pede/ullamcorper.aspx', 1);
insert into graves (parcel_id, number, surface, remarks, image_uri, deleted) values (20, 5719, 6971.94, 'Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi.

Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.

Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus.', 'https://purevolume.com/blandit/ultrices.xml', 0);
insert into graves (parcel_id, number, surface, remarks, image_uri, deleted) values (15, 6255, 3324.36, 'In hac habitasse platea dictumst. Etiam faucibus cursus urna. Ut tellus.

Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi.

Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.', 'http://mayoclinic.com/in/porttitor/pede/justo/eu.xml', 0);
insert into graves (parcel_id, number, surface, remarks, image_uri, deleted) values (17, 5932, 3921.83, 'Sed ante. Vivamus tortor. Duis mattis egestas metus.

Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.

Quisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.', 'http://joomla.org/libero/nullam/sit/amet/turpis/elementum/ligula.aspx', 1);
insert into graves (parcel_id, number, surface, remarks, image_uri, deleted) values (5, 8652, 1862.11, 'Praesent id massa id nisl venenatis lacinia. Aenean sit amet justo. Morbi ut odio.

Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', 'https://artisteer.com/eget/tincidunt/eget/tempus/vel.js', 1);
insert into graves (parcel_id, number, surface, remarks, image_uri, deleted) values (25, 6547, 5348.29, 'Pellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.

Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', 'https://eepurl.com/ornare/imperdiet/sapien.html', 1);
insert into graves (parcel_id, number, surface, remarks, image_uri, deleted) values (14, 3163, 2190.37, 'In congue. Etiam justo. Etiam pretium iaculis justo.

In hac habitasse platea dictumst. Etiam faucibus cursus urna. Ut tellus.

Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi.', 'https://is.gd/integer/a/nibh/in/quis.jsp', 0);
insert into graves (parcel_id, number, surface, remarks, image_uri, deleted) values (15, 544, 6536.43, 'Morbi non lectus. Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis.

Fusce posuere felis sed lacus. Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl. Nunc rhoncus dui vel sem.', 'https://reddit.com/consequat.html', 1);
insert into graves (parcel_id, number, surface, remarks, image_uri, deleted) values (12, 4413, 218.48, 'Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.', 'http://sfgate.com/convallis/duis.json', 0);
insert into graves (parcel_id, number, surface, remarks, image_uri, deleted) values (25, 8686, 4521.19, 'Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.

Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum.', 'http://bloomberg.com/sapien/in/sapien.jsp', 0);

------------------------------------------------------------------------------------------

truncate table deads;

insert into deads (grave_id, first_name, last_name, religion, deleted) values (14, 'Aimée', 'Ruiz', 'musulman', 1);
insert into deads (grave_id, first_name, last_name, religion, deleted) values (25, 'Ráo', 'Hudson', 'musulman', 1);
insert into deads (grave_id, first_name, last_name, religion, deleted) values (24, 'Vénus', 'King', 'ortodox', 1);
insert into deads (grave_id, first_name, last_name, religion, deleted) values (11, 'Chloé', 'Myers', 'musulman', 0);
insert into deads (grave_id, first_name, last_name, religion, deleted) values (2, 'Marie-ève', 'Hall', 'musulman', 1);
insert into deads (grave_id, first_name, last_name, religion, deleted) values (2, 'Léonore', 'Mendoza', 'ateu', 0);
insert into deads (grave_id, first_name, last_name, religion, deleted) values (21, 'Adélie', 'Edwards', 'musulman', 1);
insert into deads (grave_id, first_name, last_name, religion, deleted) values (5, 'Méghane', 'Clark', 'psd-ist', 1);
insert into deads (grave_id, first_name, last_name, religion, deleted) values (15, 'Méthode', 'Cunningham', 'ateu', 0);
insert into deads (grave_id, first_name, last_name, religion, deleted) values (8, 'Yú', 'Ramos', 'psd-ist', 0);
insert into deads (grave_id, first_name, last_name, religion, deleted) values (11, 'Intéressant', 'Brown', 'psd-ist', 0);
insert into deads (grave_id, first_name, last_name, religion, deleted) values (24, 'Andréanne', 'Johnson', 'ortodox', 0);
insert into deads (grave_id, first_name, last_name, religion, deleted) values (17, 'Rébecca', 'Burns', 'ortodox', 0);
insert into deads (grave_id, first_name, last_name, religion, deleted) values (8, 'Hélène', 'Lynch', 'evreu', 1);
insert into deads (grave_id, first_name, last_name, religion, deleted) values (22, 'Aí', 'Carter', 'evreu', 0);
insert into deads (grave_id, first_name, last_name, religion, deleted) values (1, 'Cloé', 'Fox', 'musulman', 0);
insert into deads (grave_id, first_name, last_name, religion, deleted) values (8, 'Méline', 'Bishop', 'ateu', 0);
insert into deads (grave_id, first_name, last_name, religion, deleted) values (24, 'Anaëlle', 'Gibson', 'evreu', 0);
insert into deads (grave_id, first_name, last_name, religion, deleted) values (21, 'Styrbjörn', 'Dixon', 'musulman', 0);
insert into deads (grave_id, first_name, last_name, religion, deleted) values (19, 'Zhì', 'Perry', 'psd-ist', 1);
insert into deads (grave_id, first_name, last_name, religion, deleted) values (13, 'Tú', 'Torres', 'evreu', 1);
insert into deads (grave_id, first_name, last_name, religion, deleted) values (20, 'Renée', 'Parker', 'ortodox', 0);
insert into deads (grave_id, first_name, last_name, religion, deleted) values (19, 'Mélia', 'Schmidt', 'evreu', 0);
insert into deads (grave_id, first_name, last_name, religion, deleted) values (14, 'Laurène', 'Wallace', 'ortodox', 1);
insert into deads (grave_id, first_name, last_name, religion, deleted) values (9, 'Aí', 'Wilson', 'ateu', 1);

------------------------------------------------------------------------------------------

truncate table funerals;

insert into funerals (dead_id, date, time, deleted) values (20, '2014-09-01', '11:57', 1);
insert into funerals (dead_id, date, time, deleted) values (21, '2014-01-26', '10:15', 1);
insert into funerals (dead_id, date, time, deleted) values (7, '2014-07-21', '8:13', 1);
insert into funerals (dead_id, date, time, deleted) values (25, '2014-04-11', '4:08', 1);
insert into funerals (dead_id, date, time, deleted) values (25, '2014-01-14', '9:13', 0);
insert into funerals (dead_id, date, time, deleted) values (13, '2014-03-31', '11:36', 0);
insert into funerals (dead_id, date, time, deleted) values (25, '2014-04-04', '6:51', 0);
insert into funerals (dead_id, date, time, deleted) values (17, '2014-06-09', '11:28', 1);
insert into funerals (dead_id, date, time, deleted) values (11, '2014-07-19', '9:33', 0);
insert into funerals (dead_id, date, time, deleted) values (13, '2014-08-10', '12:28', 0);
insert into funerals (dead_id, date, time, deleted) values (23, '2014-04-25', '10:27', 0);
insert into funerals (dead_id, date, time, deleted) values (23, '2013-11-29', '3:26', 0);
insert into funerals (dead_id, date, time, deleted) values (10, '2014-09-05', '7:29', 0);
insert into funerals (dead_id, date, time, deleted) values (25, '2014-04-10', '1:07', 0);
insert into funerals (dead_id, date, time, deleted) values (7, '2014-09-19', '10:20', 1);
insert into funerals (dead_id, date, time, deleted) values (10, '2014-05-12', '8:15', 1);
insert into funerals (dead_id, date, time, deleted) values (14, '2014-05-14', '9:13', 1);
insert into funerals (dead_id, date, time, deleted) values (3, '2013-11-24', '7:39', 0);
insert into funerals (dead_id, date, time, deleted) values (1, '2014-01-16', '6:20', 0);
insert into funerals (dead_id, date, time, deleted) values (7, '2014-08-08', '7:34', 1);
insert into funerals (dead_id, date, time, deleted) values (13, '2014-07-10', '12:06', 0);
insert into funerals (dead_id, date, time, deleted) values (25, '2014-06-18', '11:26', 1);
insert into funerals (dead_id, date, time, deleted) values (7, '2014-04-18', '4:04', 1);
insert into funerals (dead_id, date, time, deleted) values (2, '2014-08-02', '1:48', 1);
insert into funerals (dead_id, date, time, deleted) values (10, '2014-08-14', '3:16', 1);

------------------------------------------------------------------------------------------

truncate table monuments;

insert into monuments (grave_id) values (15);
insert into monuments (grave_id) values (10);
insert into monuments (grave_id) values (14);
insert into monuments (grave_id) values (6);
insert into monuments (grave_id) values (12);
insert into monuments (grave_id) values (20);
insert into monuments (grave_id) values (7);
insert into monuments (grave_id) values (16);
insert into monuments (grave_id) values (11);
insert into monuments (grave_id) values (22);


------------------------------------------------------------------------------------------

truncate table owners;

insert into owners (first_name, last_name, cnp, address,  deleted) values ('Célestine', 'Davis', '1293495866543', 'Paungde, Myanmar, Carioca, 5954, 4450, 73, 55, 0', 0);
insert into owners (first_name, last_name, cnp, address,  deleted) values ('Néhémie', 'Young', '1293495866543', 'Tío Pujio, Argentina, Springview, 716, 35130, 78, 34, 1', 1);
insert into owners (first_name, last_name, cnp, address,  deleted) values ('Célestine', 'Oliver', '1293495866543', 'Tougan, Burkina Faso, Monument, 8356, 3540, 40, 90, 1', 1);
insert into owners (first_name, last_name, cnp, address,  deleted) values ('Annotés', 'Perkins', '1293495866543', 'Espinosa, Brazil, Bowman, 7041, 5, 42, 71, 0', 0);
insert into owners (first_name, last_name, cnp, address,  deleted) values ('Lài', 'Adams', '1293495866543', 'Pidvynohradiv, Ukraine, Orin, 655, 36, 76, 35, 0', 1);
insert into owners (first_name, last_name, cnp, address,  deleted) values ('Tán', 'Carpenter', '1293495866543', 'Baturaden, Indonesia, Coleman, 8929, 123, 29, 34, 1', 1);
insert into owners (first_name, last_name, cnp, address,  deleted) values ('Gaétane', 'Watson', '1293495866543', 'Pont Cassé, Dominica, Banding, 7468, 7, 15, 31, 0', 0);
insert into owners (first_name, last_name, cnp, address,  deleted) values ('Maëlyss', 'Martin', '1293495866543', 'Shenzhong, China, Schlimgen, 1786, 3, 68, 100, 1', 0);
insert into owners (first_name, last_name, cnp, address,  deleted) values ('Angélique', 'Armstrong', '1293495866543', 'Pakuncen, Indonesia, Dexter, 1805, 04557, 67, 36, 0', 1);
insert into owners (first_name, last_name, cnp, address,  deleted) values ('Irène', 'Sims', '1293495866543', 'Taoyuan, China, Dennis, 8923, 793, 83, 88, 1', 0);
insert into owners (first_name, last_name, cnp, address,  deleted) values ('Léane', 'Allen', '1293495866543', 'Al Jahra, Kuwait, Declaration, 1993, 5109, 86, 5, 1', 0);
insert into owners (first_name, last_name, cnp, address,  deleted) values ('Zhì', 'Day', '1293495864543', 'Gizalki, Poland, Comanche, 1220, 445, 13, 23, 0', 0);
insert into owners (first_name, last_name, cnp, address,  deleted) values ('Aloïs', 'Bailey', '1293695866543', 'Gizalki, Poland, Comanche, 1220, 445, 13, 23, 0', 1);
insert into owners (first_name, last_name, cnp, address,  deleted) values ('Cléopatre', 'Ross', '1293495866543', 'Roche-à-Bateau, Haiti, Esch, 3714, 8, 71, 54, 0', 1);
insert into owners (first_name, last_name, cnp, address,  deleted) values ('Kévina', 'Snyder', '1293495866543', 'Pakisrejo, Indonesia, Jay, 6107, 37, 81, 73, 0', 1);
insert into owners (first_name, last_name, cnp, address,  deleted) values ('Nélie', 'Ford', '1293491866543', 'Kongsvinger, Norway, Buell, 6582, 84, 75, 98, 0', 1);
insert into owners (first_name, last_name, cnp, address,  deleted) values ('Léone', 'Clark', '1293495166543', 'Ryazan, Russia, Toban, 3159, 96329, 38, 88, 0', 0);
insert into owners (first_name, last_name, cnp, address,  deleted) values ('Inès', 'Allen', '1293495816543', 'Caruaru, Brazil, Shoshone, 8271, 8081, 35, 36, 0', 1);
insert into owners (first_name, last_name, cnp, address,  deleted) values ('Naëlle', 'Wilson', '1293495466543', 'Trabulheira, Portugal, High Crossing, 3465, 67, 7, 24, 1', 1);
insert into owners (first_name, last_name, cnp, address,  deleted) values ('Geneviève', 'Alexander', '1253495866543', 'Boa Viagem, Brazil, Fair Oaks, 8010, 2121, 24, 34, 1', 0);
insert into owners (first_name, last_name, cnp, address,  deleted) values ('Mélissandre', 'Duncan', '1393495866543', 'Qinxi, China, Starling, 4660, 402, 100, 67, 0', 1);
insert into owners (first_name, last_name, cnp, address,  deleted) values ('Mà', 'Kim', '1293495866533', 'Ozerki, Russia, Parkside, 2580, 5212, 91, 38, 0', 0);
insert into owners (first_name, last_name, cnp, address,  deleted) values ('Maïwenn', 'Spencer', '1293455866543', 'Mosteirô, Portugal, Carberry, 1383, 1948, 19, 63, 0', 1);
insert into owners (first_name, last_name, cnp, address,  deleted) values ('Mà', 'Morales', '1293495866743', 'El Tabo, Chile, Towne, 5171, 1, 62, 100, 0', 1);
insert into owners (first_name, last_name, cnp, address,  deleted) values ('Marlène', 'Gibson', '1293495866545', 'Kanash, Russia, Memorial, 2332, 180, 34, 8, 1', 0);

------------------------------------------------------------------------------------------

truncate table concessions;

insert into concessions (owner_id, grave_id, number, date, period, deleted) values (8, 23, 6775, '2014-03-28', 1996, 0);
insert into concessions (owner_id, grave_id, number, date, period, deleted) values (8, 11, 7744, '2014-05-20', 1429, 0);
insert into concessions (owner_id, grave_id, number, date, period, deleted) values (20, 24, 7578, '2014-07-14', 2144, 1);
insert into concessions (owner_id, grave_id, number, date, period, deleted) values (18, 24, 8516, '2014-01-30', 767, 1);
insert into concessions (owner_id, grave_id, number, date, period, deleted) values (19, 1, 7481, '2014-04-04', 8248, 1);
insert into concessions (owner_id, grave_id, number, date, period, deleted) values (21, 25, 3482, '2014-09-22', 7345, 1);
insert into concessions (owner_id, grave_id, number, date, period, deleted) values (25, 24, 4359, '2014-10-24', 5890, 0);
insert into concessions (owner_id, grave_id, number, date, period, deleted) values (10, 19, 856, '2014-03-05', 8161, 0);
insert into concessions (owner_id, grave_id, number, date, period, deleted) values (8, 21, 1717, '2014-08-29', 6106, 1);
insert into concessions (owner_id, grave_id, number, date, period, deleted) values (7, 11, 8299, '2014-11-02', 7170, 1);
insert into concessions (owner_id, grave_id, number, date, period, deleted) values (12, 19, 615, '2014-07-08', 3511, 1);
insert into concessions (owner_id, grave_id, number, date, period, deleted) values (19, 7, 3153, '2014-04-22', 8706, 1);
insert into concessions (owner_id, grave_id, number, date, period, deleted) values (24, 21, 3854, '2014-08-14', 2478, 0);
insert into concessions (owner_id, grave_id, number, date, period, deleted) values (13, 2, 7166, '2014-06-30', 3668, 0);
insert into concessions (owner_id, grave_id, number, date, period, deleted) values (5, 18, 5740, '2013-12-14', 6168, 0);
insert into concessions (owner_id, grave_id, number, date, period, deleted) values (3, 10, 2159, '2013-12-25', 1372, 1);
insert into concessions (owner_id, grave_id, number, date, period, deleted) values (3, 23, 343, '2014-09-11', 2352, 1);
insert into concessions (owner_id, grave_id, number, date, period, deleted) values (19, 21, 6010, '2014-06-22', 5193, 0);
insert into concessions (owner_id, grave_id, number, date, period, deleted) values (16, 10, 8193, '2014-09-02', 7213, 0);
insert into concessions (owner_id, grave_id, number, date, period, deleted) values (24, 5, 2986, '2014-01-05', 1398, 1);
insert into concessions (owner_id, grave_id, number, date, period, deleted) values (10, 24, 8367, '2014-09-28', 3080, 0);
insert into concessions (owner_id, grave_id, number, date, period, deleted) values (19, 23, 2106, '2014-08-28', 651, 0);
insert into concessions (owner_id, grave_id, number, date, period, deleted) values (5, 18, 1178, '2014-06-03', 2190, 1);
insert into concessions (owner_id, grave_id, number, date, period, deleted) values (20, 18, 6775, '2014-09-18', 5980, 0);
insert into concessions (owner_id, grave_id, number, date, period, deleted) values (14, 18, 8986, '2014-08-04', 4007, 1);

------------------------------------------------------------------------------------------

truncate table receipts;

insert into receipts (concession_id, recepit_number, starting_date, ending_date, deleted) values (6, 5677, '2014-06-11', '2014-01-19', 1);
insert into receipts (concession_id, recepit_number, starting_date, ending_date, deleted) values (3, 6059, '2014-06-12', '2014-07-10', 0);
insert into receipts (concession_id, recepit_number, starting_date, ending_date, deleted) values (21, 5900, '2014-01-08', '2014-09-07', 1);
insert into receipts (concession_id, recepit_number, starting_date, ending_date, deleted) values (10, 3043, '2014-09-14', '2014-02-13', 0);
insert into receipts (concession_id, recepit_number, starting_date, ending_date, deleted) values (5, 1015, '2014-03-28', '2013-12-18', 1);
insert into receipts (concession_id, recepit_number, starting_date, ending_date, deleted) values (22, 1196, '2014-11-13', '2013-12-29', 0);
insert into receipts (concession_id, recepit_number, starting_date, ending_date, deleted) values (19, 3967, '2014-07-01', '2013-12-31', 1);
insert into receipts (concession_id, recepit_number, starting_date, ending_date, deleted) values (23, 4814, '2014-04-13', '2014-10-14', 1);
insert into receipts (concession_id, recepit_number, starting_date, ending_date, deleted) values (6, 2146, '2014-05-13', '2014-04-01', 0);
insert into receipts (concession_id, recepit_number, starting_date, ending_date, deleted) values (3, 8035, '2014-10-21', '2013-12-20', 0);
insert into receipts (concession_id, recepit_number, starting_date, ending_date, deleted) values (21, 1856, '2014-08-16', '2014-05-19', 1);
insert into receipts (concession_id, recepit_number, starting_date, ending_date, deleted) values (19, 2794, '2013-12-30', '2013-12-26', 1);
insert into receipts (concession_id, recepit_number, starting_date, ending_date, deleted) values (4, 1223, '2014-05-10', '2014-04-08', 0);
insert into receipts (concession_id, recepit_number, starting_date, ending_date, deleted) values (14, 1435, '2014-09-06', '2014-03-28', 1);
insert into receipts (concession_id, recepit_number, starting_date, ending_date, deleted) values (6, 5136, '2014-01-15', '2013-12-24', 0);
insert into receipts (concession_id, recepit_number, starting_date, ending_date, deleted) values (6, 6703, '2014-08-25', '2014-10-18', 1);
insert into receipts (concession_id, recepit_number, starting_date, ending_date, deleted) values (5, 4466, '2014-04-12', '2014-09-21', 0);
insert into receipts (concession_id, recepit_number, starting_date, ending_date, deleted) values (1, 518, '2014-07-16', '2013-11-29', 1);
insert into receipts (concession_id, recepit_number, starting_date, ending_date, deleted) values (18, 2840, '2014-01-13', '2014-05-28', 1);
insert into receipts (concession_id, recepit_number, starting_date, ending_date, deleted) values (13, 8899, '2014-06-13', '2014-01-11', 1);
insert into receipts (concession_id, recepit_number, starting_date, ending_date, deleted) values (5, 7193, '2014-03-05', '2014-07-18', 1);
insert into receipts (concession_id, recepit_number, starting_date, ending_date, deleted) values (8, 1547, '2014-10-17', '2013-12-28', 0);
insert into receipts (concession_id, recepit_number, starting_date, ending_date, deleted) values (2, 5710, '2014-05-22', '2014-05-04', 1);
insert into receipts (concession_id, recepit_number, starting_date, ending_date, deleted) values (18, 1459, '2014-06-13', '2014-10-28', 1);
insert into receipts (concession_id, recepit_number, starting_date, ending_date, deleted) values (2, 8088, '2014-03-29', '2014-06-09', 0);

------------------------------------------------------------------------------------------

truncate table complaints;

insert into complaints (number, deleted) values (6290, 0);
insert into complaints (number, deleted) values (6067, 1);
insert into complaints (number, deleted) values (3052, 0);
insert into complaints (number, deleted) values (4037, 0);
insert into complaints (number, deleted) values (8515, 1);
insert into complaints (number, deleted) values (3403, 1);
insert into complaints (number, deleted) values (1641, 1);
insert into complaints (number, deleted) values (713, 1);
insert into complaints (number, deleted) values (199, 0);
insert into complaints (number, deleted) values (8315, 0);
insert into complaints (number, deleted) values (7705, 0);
insert into complaints (number, deleted) values (2115, 0);
insert into complaints (number, deleted) values (8986, 0);
insert into complaints (number, deleted) values (4504, 0);
insert into complaints (number, deleted) values (5401, 1);
insert into complaints (number, deleted) values (8326, 0);
insert into complaints (number, deleted) values (3524, 1);
insert into complaints (number, deleted) values (275, 0);
insert into complaints (number, deleted) values (3388, 1);
insert into complaints (number, deleted) values (3935, 0);
insert into complaints (number, deleted) values (1510, 1);
insert into complaints (number, deleted) values (4100, 0);
insert into complaints (number, deleted) values (2186, 0);
insert into complaints (number, deleted) values (1179, 1);
insert into complaints (number, deleted) values (7302, 1);

------------------------------------------------------------------------------------------

truncate table users;

insert into users (username, password) values ('cjames0', '0a0EyLk');
insert into users (username, password) values ('egraham1', 'qxW3Ngrr9Aog');
insert into users (username, password) values ('aandrews2', 'fDaMdY3QR');
insert into users (username, password) values ('schavez3', 'XwcGUJiwLVoU');
insert into users (username, password) values ('aromero4', 'YiofAupH9');
insert into users (username, password) values ('ptaylor5', 'ur3yKtTAB');
insert into users (username, password) values ('klane6', 'F1LjN667JEg');
insert into users (username, password) values ('jperry7', '6WzlJ4aI');
insert into users (username, password) values ('rwagner8', '0gjiiFIOBE');
insert into users (username, password) values ('pmorales9', 'jDbYGZxQzOWC');
insert into users (username, password) values ('drobinsona', 'DuOE4RXqRO');
insert into users (username, password) values ('dwoodb', '1kOO2Ak0L');
insert into users (username, password) values ('rsnyderc', 'kEXQfi');
insert into users (username, password) values ('dbelld', 'BJMZaZx');
insert into users (username, password) values ('cwagnere', 'F6Aor9Lg81');
insert into users (username, password) values ('afullerf', 'OOME9t2av');
insert into users (username, password) values ('mburnsg', 'YDyG2wF0Qzif');
insert into users (username, password) values ('fchapmanh', 'EEyqSlx95');
insert into users (username, password) values ('lrobertsoni', 'Gd7Q0dk');
insert into users (username, password) values ('jfrazierj', 'aswqxS');
insert into users (username, password) values ('rjacobsk', '6SYctwX');
insert into users (username, password) values ('smedinal', 'eMSAh6w');
insert into users (username, password) values ('rharveym', 'R1v3LqtnRd');
insert into users (username, password) values ('rsnydern', '9Lv8wpY3');
insert into users (username, password) values ('wbrookso', 'nx0S0bDlA');

------------------------------------------------------------------------------------------

truncate table transactions;

insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (1, '2014-09-19', 580, 'Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est. Phasellus sit amet erat.', '6, 5677, \'2014-06-11\', \'2014-01-19\', 1', '7, 5677, \'2014-06-11\', \'2014-01-19\', 1', 'receipts');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (2, '2014-01-17', 2838, 'Vivamus vel nulla eget eros elementum pellentesque.', '6, 5677, \'2014-06-11\', \'2014-01-19\', 1', '7, 5677, \'2014-06-11\', \'2014-01-19\', 1', 'receipts');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (3, '2014-02-18', 1339, 'Mauris sit amet eros.', '6, 5677, \'2014-06-11\', \'2014-01-19\', 1', '7, 5677, \'2014-06-11\', \'2014-01-19\', 1', 'receipts');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (4, '2014-10-04', 4026, 'Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem. Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat.', '6, 5677, \'2014-06-11\', \'2014-01-19\', 1', '7, 5677, \'2014-06-11\', \'2014-01-19\', 1', 'receipts');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (5, '2014-10-15', 1599, 'Nulla tellus. In sagittis dui vel nisl. Duis ac nibh.', '6, 5677, \'2014-06-11\', \'2014-01-19\', 1', '7, 5677, \'2014-06-11\', \'2014-01-19\', 1', 'receipts');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (6, '2014-05-23', 5981, 'Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.', '6, 5677, \'2014-06-11\', \'2014-01-19\', 1', '7, 5677, \'2014-06-11\', \'2014-01-19\', 1', 'receipts');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (7, '2014-06-29', 701, 'Praesent lectus. Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis. Duis consequat dui nec nisi volutpat eleifend.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (8, '2014-11-09', 745, 'Donec vitae nisi. Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus. Curabitur at ipsum ac tellus semper interdum.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (9, '2013-12-30', 6942, 'Etiam vel augue.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (10, '2014-03-03', 400, 'Duis bibendum. Morbi non quam nec dui luctus rutrum. Nulla tellus. In sagittis dui vel nisl.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (11, '2014-03-19', 1951, 'In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (12, '2014-06-06', 7171, 'Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque. Duis bibendum.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (13, '2014-11-04', 7031, 'Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam. Nam tristique tortor eu pede.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (14, '2014-07-19', 2606, 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin risus.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (15, '2014-05-08', 267, 'Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus. Pellentesque at nulla.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (16, '2014-03-03', 5245, 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros. Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (17, '2014-04-26', 3326, 'Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est. Phasellus sit amet erat.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (18, '2014-07-16', 7430, 'Integer ac neque. Duis bibendum.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (19, '2013-12-01', 4032, 'Integer ac neque. Duis bibendum. Morbi non quam nec dui luctus rutrum. Nulla tellus.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (1, '2014-03-04', 5055, 'Sed ante. Vivamus tortor.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (2, '2014-10-21', 1550, 'Fusce posuere felis sed lacus. Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl. Nunc rhoncus dui vel sem.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (3, '2014-10-31', 4639, 'Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (4, '2014-06-25', 3364, 'Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat. Curabitur gravida nisi at nibh.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (5, '2014-04-30', 2504, 'Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus. Suspendisse potenti.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');
insert into transactions (user_id, trans_time, document_number, modification_details, `before_trans`, `after_trans`, `table_name`) values (6, '2014-08-10', 535, 'Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis. Fusce posuere felis sed lacus.', '\'wbrookso\', \'nx0S0bDlA\'', '\'wbrookso\', \'nx0S0lA\'', 'users');

------------------------------------------------------------------------------------------

truncate table states;

insert into states (name) values ('just');
insert into states (name) values ('some');
insert into states (name) values ('dummy');
insert into states (name) values ('states');

------------------------------------------------------------------------------------------

truncate table requests;

insert into requests (state_id, current_number, registration_date, infocet_number, deleted) values (3, 7562, '2014-02-10', 4304, 0);
insert into requests (state_id, current_number, registration_date, infocet_number, deleted) values (3, 3988, '2014-03-03', 5512, 1);
insert into requests (state_id, current_number, registration_date, infocet_number, deleted) values (1, 2668, '2014-06-28', 8544, 1);
insert into requests (state_id, current_number, registration_date, infocet_number, deleted) values (1, 5115, '2014-07-15', 4640, 0);
insert into requests (state_id, current_number, registration_date, infocet_number, deleted) values (1, 3919, '2014-01-07', 1000, 0);
insert into requests (state_id, current_number, registration_date, infocet_number, deleted) values (4, 3244, '2014-05-08', 1170, 0);
insert into requests (state_id, current_number, registration_date, infocet_number, deleted) values (1, 1550, '2014-02-12', 4401, 0);
insert into requests (state_id, current_number, registration_date, infocet_number, deleted) values (2, 8934, '2014-03-13', 87, 0);
insert into requests (state_id, current_number, registration_date, infocet_number, deleted) values (3, 162, '2014-06-06', 5305, 0);
insert into requests (state_id, current_number, registration_date, infocet_number, deleted) values (1, 7062, '2014-04-04', 4690, 0);
insert into requests (state_id, current_number, registration_date, infocet_number, deleted) values (1, 4033, '2014-08-01', 5796, 0);
insert into requests (state_id, current_number, registration_date, infocet_number, deleted) values (1, 2904, '2014-09-01', 3956, 0);
insert into requests (state_id, current_number, registration_date, infocet_number, deleted) values (3, 3445, '2014-10-01', 8015, 1);
insert into requests (state_id, current_number, registration_date, infocet_number, deleted) values (1, 8385, '2014-03-25', 6833, 0);
insert into requests (state_id, current_number, registration_date, infocet_number, deleted) values (4, 4708, '2014-01-15', 7806, 1);
insert into requests (state_id, current_number, registration_date, infocet_number, deleted) values (2, 2502, '2014-03-21', 673, 1);
insert into requests (state_id, current_number, registration_date, infocet_number, deleted) values (4, 5461, '2013-12-25', 4265, 0);
insert into requests (state_id, current_number, registration_date, infocet_number, deleted) values (3, 5393, '2014-04-17', 892, 1);
insert into requests (state_id, current_number, registration_date, infocet_number, deleted) values (2, 6885, '2014-10-21', 8328, 1);
insert into requests (state_id, current_number, registration_date, infocet_number, deleted) values (4, 590, '2014-07-17', 4650, 1);
insert into requests (state_id, current_number, registration_date, infocet_number, deleted) values (3, 5019, '2014-01-04', 1221, 1);
insert into requests (state_id, current_number, registration_date, infocet_number, deleted) values (4, 330, '2014-01-09', 4, 1);
insert into requests (state_id, current_number, registration_date, infocet_number, deleted) values (2, 937, '2014-02-15', 4980, 0);
insert into requests (state_id, current_number, registration_date, infocet_number, deleted) values (4, 7964, '2014-06-28', 6004, 1);
insert into requests (state_id, current_number, registration_date, infocet_number, deleted) values (2, 7956, '2014-05-10', 7897, 0);

SET FOREIGN_KEY_CHECKS = 1;