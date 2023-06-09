insert  into `productline`(`product_line`,`code`,`text_description`,`html_description`,`image`) values 

('Classic Cars',599302, 'Attention car enthusiasts: Make your wildest car ownership dreams come true. Whether you are looking for classic muscle cars, dream sports cars or movie-inspired miniatures, you will find great choices in this category. These replicas feature superb attention to detail and craftsmanship and offer features such as working steering system, opening forward compartment, opening rear trunk with removable spare wheel, 4-wheel independent spring suspension, and so on. The models range in size from 1:10 to 1:24 scale and include numerous limited edition and several out-of-production vehicles. All models include a certificate of authenticity from their manufacturers and come fully assembled and ready for display in the home or office.',NULL,NULL),

('Motorcycles',599302,'Our motorcycles are state of the art replicas of classic as well as contemporary motorcycle legends such as Harley Davidson, Ducati and Vespa. Models contain stunning details such as official logos, rotating wheels, working kickstand, front suspension, gear-shift lever, footbrake lever, and drive chain. Materials used include diecast and plastic. The models range in size from 1:10 to 1:50 scale and include numerous limited edition and several out-of-production vehicles. All models come fully assembled and ready for display in the home or office. Most include a certificate of authenticity.',NULL,NULL),

('Planes',433823,'Unique, diecast airplane and helicopter replicas suitable for collections, as well as home, office or classroom decorations. Models contain stunning details such as official logos and insignias, rotating jet engines and propellers, retractable wheels, and so on. Most come fully assembled and with a certificate of authenticity from their manufacturers.',NULL,NULL),

('Ships',433823,'The perfect holiday or anniversary gift for executives, clients, friends, and family. These handcrafted model ships are unique, stunning works of art that will be treasured for generations! They come fully assembled and ready for display in the home or office. We guarantee the highest quality, and best value.',NULL,NULL),

('Trains',123333,'Model trains are a rewarding hobby for enthusiasts of all ages. Whether you\'re looking for collectible wooden trains, electric streetcars or locomotives, you\'ll find a number of great choices for any budget within this category. The interactive aspect of trains makes toy trains perfect for young children. The wooden train sets are ideal for children under the age of 5.',NULL,NULL),

('Trucks and Buses',569331,'The Truck and Bus models are realistic replicas of buses and specialized trucks produced from the early 1920s to present. The models range in size from 1:12 to 1:50 scale and include numerous limited edition and several out-of-production vehicles. Materials used include tin, diecast and plastic. All models include a certificate of authenticity from their manufacturers and are a perfect ornament for the home and office.',NULL,NULL),

('Vintage Cars',223113,'Our Vintage Car models realistically portray automobiles produced from the early 1900s through the 1940s. Materials used include Bakelite, diecast, plastic and wood. Most of the replicas are in the 1:18 and 1:24 scale sizes, which provide the optimum in detail and accuracy. Prices range from $30.00 up to $180.00 for some special limited edition replicas. All models include a certificate of authenticity from their manufacturers and come fully assembled and ready for display in the home or office.',NULL,NULL) ON DUPLICATE KEY UPDATE product_line=product_line;

insert  into `product`(`product_id`,`product_name`,`product_line`,`code`,`product_scale`,`product_vendor`,`product_description`,`quantity_in_stock`,`buy_price`,`msrp`) values 

(1,'1969 Harley Davidson Ultimate Chopper','Motorcycles',599302,'1:10','Min Lin Diecast','PENDING',7933,'48.81','95.70'),

(2,'1952 Alpine Renault 1300','Classic Cars',599302,'1:10','Classic Metal Creations','PENDING',7305,'98.58','214.30'),

(3,'1996 Moto Guzzi 1100i','Motorcycles',599302,'1:10','Highway 66 Mini Classics','PENDING',6625,'68.99','118.94'),

(4,'2003 Harley-Davidson Eagle Drag Bike','Motorcycles',599302,'1:10','Red Start Diecast','PENDING',5582,'91.02','193.66'),

(5,'1972 Alfa Romeo GTA','Classic Cars',599302,'1:10','Motor City Art Classics','PENDING',3252,'85.68','136.00'),

(6,'1962 LanciaA Delta 16V','Classic Cars',599302,'1:10','Welly Diecast Productions', 'PENDING',6791,'103.42','147.74'),

(7,'1968 Ford Mustang','Classic Cars',599302,'1:12','Autoart Studio Design','Hood, doors and trunk all open to reveal highly detailed interior features. Steering wheel actually turns the front wheels. Color dark green.',68,'95.34','194.57'),

(8,'2001 Ferrari Enzo','Classic Cars',599302,'1:12','Second Gear Diecast','Turnable front wheels; steering function; detailed interior; detailed engine; opening hood; opening trunk; opening doors; and detailed chassis.',3619,'95.59','207.80'),

(9,'1958 Setra Bus','Trucks and Buses',569331,'1:12','Welly Diecast Productions','Model features 30 windows, skylights & glare resistant glass, working steering system, original logos',1579,'77.90','136.67'),

(10,'2002 Suzuki XREO','Motorcycles',599302,'1:12','Unimax Art Galleries','Official logos and insignias, saddle bags located on side of motorcycle, detailed engine, working steering, working suspension, two leather seats, luggage rack, dual exhaust pipes, small saddle bag located on handle bars, two-tone paint with chrome accents, superior die-cast detail , rotating wheels , working kick stand, diecast metal with plastic parts and baked enamel finish.',9997,'66.27','150.62'),

(11,'1969 Corvair Monza','Classic Cars',599302,'1:18','Welly Diecast Productions','1:18 scale die-cast about 10" long doors open, hood opens, trunk opens and wheels roll',6906,'89.14','151.08'),

(12,'1968 Dodge Charger','Classic Cars',599302,'1:12','Welly Diecast Productions','1:12 scale model of a 1968 Dodge Charger. Hood, doors and trunk all open to reveal highly detailed interior features. Steering wheel actually turns the front wheels. Color black',9123,'75.16','117.44'),

(13,'1969 Ford Falcon','Classic Cars',599302,'1:12','Second Gear Diecast','Turnable front wheels; steering function; detailed interior; detailed engine; opening hood; opening trunk; opening doors; and detailed chassis.',1049,'83.05','173.02'),

(14,'1970 Plymouth Hemi Cuda','Classic Cars',599302,'1:12','Studio M Art Models','Very detailed 1970 Plymouth Cuda model in 1:12 scale. The Cuda is generally accepted as one of the fastest original muscle cars from the 1970s. This model is a reproduction of one of the orginal 652 cars built in 1970. Red color.',5663,'31.92','79.80'),

(15,'1957 Chevy Pickup','Trucks and Buses',569331,'1:12','Exoto Designs','1:12 scale die-cast about 20" long Hood opens, Rubber wheels',6125,'55.70','118.50'),

(16,'1969 Dodge Charger','Classic Cars',599302,'1:12','Welly Diecast Productions','Detailed model of the 1969 Dodge Charger. This model includes finely detailed interior and exterior features. Painted in red and white.',7323,'58.73','115.16'),

(17,'1940 Ford Pickup Truck','Trucks and Buses',569331,'1:18','Studio M Art Models','This model features soft rubber tires, working steering, rubber mud guards, authentic Ford logos, detailed undercarriage, opening doors and hood,  removable split rear gate, full size spare mounted in bed, detailed interior with opening glove box',2613,'58.33','116.67'),

(18,'1993 Mazda RX-7','Classic Cars',599302,'1:18','Highway 66 Mini Classics','This model features, opening hood, opening doors, detailed engine, rear spoiler, opening trunk, working steering, tinted windows, baked enamel finish. Color red.',3975,'83.51','141.54'),

(19,'1937 Lincoln Berline','Vintage Cars',223113,'1:18','Motor City Art Classics','Features opening engine cover, doors, trunk, and fuel filler cap. Color black',8693,'60.62','102.74'),

(20,'1936 Mercedes-Benz 500K Special Roadster','Vintage Cars',223113,'1:18','Studio M Art Models','This 1:18 scale replica is constructed of heavy die-cast metal and has all the features of the original: working doors and rumble seat, independent spring suspension, detailed interior, working steering system, and a bifold hood that reveals an engine so accurate that it even includes the wiring. All this is topped off with a baked enamel finish. Color white.',8635,'24.26','53.91'),

(21,'1965 Aston Martin DB5','Classic Cars',599302,'1:18','Classic Metal Creations','Die-cast model of the silver 1965 Aston Martin DB5 in silver. This model includes full wire wheels and doors that open with fully detailed passenger compartment. In 1:18 scale, this model measures approximately 10 inches/20 cm long.',9042,'65.96','124.44'),

(22,'1980s Black Hawk Helicopter','Planes',433823,'1:18','Red Start Diecast','1:18 scale replica of actual Army''s UH-60L BLACK HAWK Helicopter. 100% hand-assembled. Features rotating rotor blades, propeller blades and rubber wheels.',5330,'77.27','157.69'),

(23,'1917 Grand Touring Sedan','Vintage Cars',223113,'1:18','Welly Diecast Productions','This 1:18 scale replica of the 1917 Grand Touring car has all the features you would expect from museum quality reproductions: all four doors and bi-fold hood opening, detailed engine and instrument panel, chrome-look trim, and tufted upholstery, all topped off with a factory baked-enamel finish.',2724,'86.70','170.00'),

(24,'1948 Porsche 356-A Roadster','Classic Cars',599302,'1:18','Gearbox Collectibles','This precision die-cast replica features opening doors, superb detail and craftsmanship, working steering system, opening forward compartment, opening rear trunk with removable spare, 4 wheel independent spring suspension as well as factory baked enamel finish.',8826,'53.90','77.00'),

(25,'1995 Honda Civic','Classic Cars',599302,'1:18','Min Lin Diecast','This model features, opening hood, opening doors, detailed engine, rear spoiler, opening trunk, working steering, tinted windows, baked enamel finish. Color yellow.',9772,'93.89','142.25'),

(26,'1998 Chrysler Plymouth Prowler','Classic Cars',599302,'1:18','Gearbox Collectibles','Turnable front wheels; steering create function; detailed interior; detailed engine; opening hood; opening trunk; opening doors; and detailed chassis.',4724,'101.51','163.73'),

(27,'1911 Ford Town Car','Vintage Cars',223113,'1:18','Motor City Art Classics','Features opening hood, opening doors, opening trunk, wide white wall tires, front door arm rests, working steering system.',540,'33.30','60.54'),

(28,'1964 Mercedes Tour Bus','Trucks and Buses',569331,'1:18','Unimax Art Galleries','Exact replica. 100+ parts. working steering system, original logos',8258,'74.86','122.73'),

(29,'1932 Model A Ford J-Coupe','Vintage Cars',223113,'1:18','Autoart Studio Design','This model features grille-mounted chrome horn, lift-up louvered hood, fold-down rumble seat, working steering system, chrome-covered spare, opening doors, detailed and wired engine',9354,'58.48','127.13'),

(30,'1926 Ford Fire Engine','Trucks and Buses',569331,'1:18','Carousel DieCast Legends','Gleaming red handsome appearance. Everything is here the fire hoses, ladder, axes, bells, lanterns, ready to fight any inferno.',2018,'24.92','60.77'),

(31,'P-51-D Mustang','Planes',433823,'1:72','Gearbox Collectibles','Has retractable wheels and comes with a stand',992,'49.00','84.48'),

(32,'1936 Harley Davidson El Knucklehead','Motorcycles',599302,'1:18','Welly Diecast Productions','Intricately detailed with chrome accents and trim, official die-struck logos and baked enamel finish.',4357,'24.23','60.57'),

(33,'1928 Mercedes-Benz SSK','Vintage Cars',223113,'1:18','Gearbox Collectibles','This 1:18 replica features grille-mounted chrome horn, lift-up louvered hood, fold-down rumble seat, working steering system, chrome-covered spare, opening doors, detailed and wired engine. Color black.',548,'72.56','168.75'),

(34,'1999 Indy 500 Monte Carlo SS','Classic Cars',599302,'1:18','Red Start Diecast','Features include opening and closing doors. Color: Red',8164,'56.76','132.00'),

(35,'1913 Ford Model T Speedster','Vintage Cars',223113,'1:18','Carousel DieCast Legends','This 250 part reproduction includes moving handbrakes, clutch, throttle and foot pedals, squeezable horn, detailed wired engine, removable water, gas, and oil cans, pivoting monocle windshield, all topped with a baked enamel red finish. Each replica comes with an Owners Title and Certificate of Authenticity. Color red.',4189,'60.78','101.31'),

(36,'1934 Ford V8 Coupe','Vintage Cars',223113,'1:18','Min Lin Diecast','Chrome Trim, Chrome Grille, Opening Hood, Opening Doors, Opening Trunk, Detailed Engine, Working Steering System',5649,'34.35','62.46'),

(37,'1999 Yamaha Speed Boat','Ships',433823,'1:18','Min Lin Diecast','Exact replica. Wood and Metal. Many extras including rigging, long boats, pilot house, anchors, etc. Comes with three masts, all square-rigged.',4259,'51.61','86.02'),

(38,'18th Century Vintage Horse Carriage','Vintage Cars',223113,'1:18','Red Start Diecast','Hand crafted diecast-like metal horse carriage is re-created in about 1:18 scale of antique horse carriage. This antique style metal Stagecoach is all hand-assembled with many different parts.rnrnThis collectible metal horse carriage is painted in classic Red, and features turning steering wheel and is entirely hand-finished.',5992,'60.74','104.72'),

(39,'1903 Ford Model A','Vintage Cars',223113,'1:18','Unimax Art Galleries','Features opening trunk,  working steering system',3913,'68.30','136.59'),

(40,'1992 Ferrari 360 Spider red','Classic Cars',599302,'1:18','Unimax Art Galleries','his replica features opening doors, superb detail and craftsmanship, working steering system, opening forward compartment, opening rear trunk with removable spare, 4 wheel independent spring suspension as well as factory baked enamel finish.',8347,'77.90','169.34'),

(41,'1985 Toyota Supra','Classic Cars',599302,'1:18','Highway 66 Mini Classics','This model features soft rubber tires, working steering, rubber mud guards, authentic Ford logos, detailed undercarriage, opening doors and hood, removable split rear gate, full size spare mounted in bed, detailed interior with opening glove box',7733,'57.01','107.57'),

(42,'Collectable Wooden Train','Trains',123333,'1:18','Carousel DieCast Legends','Hand crafted wooden toy train set is in about 1:18 scale, 25 inches in total length including 2 additional carts, of actual vintage train. This antique style wooden toy train model set is all hand-assembled with 100% wood.',6450,'67.56','100.84'),

(43,'1969 Dodge Super Bee','Classic Cars',599302,'1:18','Min Lin Diecast','This replica features opening doors, superb detail and craftsmanship, working steering system, opening forward compartment, opening rear trunk with removable spare, 4 wheel independent spring suspension as well as factory baked enamel finish.',1917,'49.05','80.41'),

(44,'1917 Maxwell Touring Car','Vintage Cars',223113,'1:18','Exoto Designs','Features Gold Trim, Full Size Spare Tire, Chrome Trim, Chrome Grille, Opening Hood, Opening Doors, Opening Trunk, Detailed Engine, Working Steering System',7913,'57.54','99.21'),

(45,'1976 Ford Gran Torino','Classic Cars',599302,'1:18','Gearbox Collectibles','Highly detailed 1976 Ford Gran Torino "Starsky and Hutch" diecast model. Very well constructed and painted in red and white patterns.',9127,'73.49','146.99'),

(46,'1948 Porsche Type 356 Roadster','Classic Cars',599302,'1:18','Gearbox Collectibles','This model features working front and rear suspension on accurately replicated and actuating shock absorbers as well as opening engine cover, rear stabilizer flap,  and 4 opening doors.',8990,'62.16','141.28'),

(47,'1957 Vespa GS150','Motorcycles',599302,'1:18','Studio M Art Models','Features rotating wheels , working kick stand. Comes with stand.',7689,'32.95','62.17'),

(48,'1941 Chevrolet Special Deluxe Cabriolet','Vintage Cars',223113,'1:18','Exoto Designs','Features opening hood, opening doors, opening trunk, wide white wall tires, front door arm rests, working steering system, leather upholstery. Color black.',2378,'64.58','105.87'),

(49,'1970 Triumph Spitfire','Classic Cars',599302,'1:18','Min Lin Diecast','Features include opening and closing doors. Color: White.',5545,'91.92','143.62'),

(50,'1932 Alfa Romeo 8C2300 Spider Sport','Vintage Cars',223113,'1:18','Exoto Designs','This 1:18 scale precision die cast replica features the 6 front headlights of the original, plus a detailed version of the 142 horsepower straight 8 engine, dual spares and their famous comprehensive dashboard. Color black.',6553,'43.26','92.03'),

(51,'1904 Buick Runabout','Vintage Cars',223113,'1:18','Exoto Designs','Features opening trunk,  working steering system',8290,'52.66','87.77'),

(52,'1940s Ford truck','Trucks and Buses',569331,'1:18','Motor City Art Classics','This 1940s Ford Pick-Up truck is re-created in 1:18 scale of original 1940s Ford truck. This antique style metal 1940s Ford Flatbed truck is all hand-assembled. This collectible 1940''s Pick-Up truck is painted in classic dark green color, and features rotating wheels.',3128,'84.76','121.08'),

(53,'1939 Cadillac Limousine','Vintage Cars',223113,'1:18','Studio M Art Models','Features completely detailed interior including Velvet flocked drapes,deluxe wood grain floor, and a wood grain casket with seperate chrome handles',6645,'23.14','50.31'),

(54,'1957 Corvette Convertible','Classic Cars',599302,'1:18','Classic Metal Creations','1957 die cast Corvette Convertible in Roman Red with white sides and whitewall tires. 1:18 scale quality die-cast with detailed engine and underbvody. Now you can own The Classic Corvette.',1249,'69.93','148.80'),

(55,'1957 Ford Thunderbird','Classic Cars',599302,'1:18','Studio M Art Models','This 1:18 scale precision die-cast replica, with its optional porthole hardtop and factory baked-enamel Thunderbird Bronze finish, is a 100% accurate rendition of this American classic.',3209,'34.21','71.27'),

(56,'1970 Chevy Chevelle SS 454','Classic Cars',599302,'1:24','Unimax Art Galleries','This model features rotating wheels, working streering system and opening doors. All parts are particularly delicate due to their precise scale and require special care and attention. It should not be picked up by the doors, roof, hood or trunk.',1005,'49.24','73.49'),

(57,'1970 Dodge Coronet','Classic Cars',599302,'1:24','Highway 66 Mini Classics','1:24 scale die-cast about 18" long doors open, hood opens and rubber wheels',4074,'32.37','57.80'),

(58,'1997 BMW R 1100 S','Motorcycles',599302,'1:24','Autoart Studio Design','Detailed scale replica with working suspension and constructed from over 70 parts',7003,'60.86','112.70'),

(59,'1966 Shelby Cobra 427 S/C','Classic Cars',599302,'1:24','Carousel DieCast Legends','This diecast model of the 1966 Shelby Cobra 427 S/C includes many authentic details and operating parts. The 1:24 scale model of this iconic lighweight sports car from the 1960s comes in silver and it''s own display case.',8197,'29.18','50.31'),

(60,'1928 British Royal Navy Airplane','Planes',433823,'1:24','Classic Metal Creations','Official logos and insignias',3627,'66.74','109.42'),

(61,'1939 Chevrolet Deluxe Coupe','Vintage Cars',223113,'1:24','Motor City Art Classics','This 1:24 scale die-cast replica of the 1939 Chevrolet Deluxe Coupe has the same classy look as the original. Features opening trunk, hood and doors and a showroom quality baked enamel finish.',7332,'22.57','33.19'),

(62,'1960 BSA Gold Star DBD34','Motorcycles',599302,'1:24','Highway 66 Mini Classics','Detailed scale replica with working suspension and constructed from over 70 parts',15,'37.32','76.17'),

(63,'18th century schooner','Ships',433823,'1:24','Carousel DieCast Legends','All wood with canvas sails. Many extras including rigging, long boats, pilot house, anchors, etc. Comes with 4 masts, all square-rigged.',1898,'82.34','122.89'),

(64,'1938 Cadillac V-16 Presidential Limousine','Vintage Cars',223113,'1:24','Classic Metal Creations','This 1:24 scale precision die cast replica of the 1938 Cadillac V-16 Presidential Limousine has all the details of the original, from the flags on the front to an opening back seat compartment complete with telephone and rifle. Features factory baked-enamel black finish, hood goddess ornament, working jump seats.',2847,'20.61','44.80'),

(65,'1962 Volkswagen Microbus','Trucks and Buses',569331,'1:24','Autoart Studio Design','This 1:18 scale die cast replica of the 1962 Microbus is loaded with features: A working steering system, opening front doors and tailgate, and famous two-tone factory baked enamel finish, are all topped of by the sliding, real fabric, sunroof.',2327,'61.34','127.79'),

(66,'1982 Ducati 900 Monster','Motorcycles',599302,'1:24','Highway 66 Mini Classics','Features two-tone paint with chrome accents, superior die-cast detail , rotating wheels , working kick stand',6840,'47.10','69.26'),

(67,'1949 Jaguar XK 120','Classic Cars',599302,'1:24','Classic Metal Creations','Precision-engineered from original Jaguar specification in perfect scale ratio. Features opening doors, superb detail and craftsmanship, working steering system, opening forward compartment, opening rear trunk with removable spare, 4 wheel independent spring suspension as well as factory baked enamel finish.',2350,'47.25','90.87'),

(68,'1958 Chevy Corvette Limited Edition','Classic Cars',599302,'1:24','Carousel DieCast Legends','The operating parts of this 1958 Chevy Corvette Limited Edition are particularly delicate due to their precise scale and require special care and attention. Features rotating wheels, working streering, opening doors and trunk. Color dark green.',2542,'15.91','35.36'),

(69,'1900s Vintage Bi-Plane','Planes',433823,'1:24','Autoart Studio Design','Hand crafted diecast-like metal bi-plane is re-created in about 1:24 scale of antique pioneer airplane. All hand-assembled with many different parts. Hand-painted in classic yellow and features correct markings of original airplane.',5942,'34.25','68.51'),

(70,'1952 Citroen-15CV','Classic Cars',599302,'1:24','Exoto Designs','Precision crafted hand-assembled 1:18 scale reproduction of the 1952 15CV, with its independent spring suspension, working steering system, opening doors and hood, detailed engine and instrument panel, all topped of with a factory fresh baked enamel finish.',1452,'72.82','117.44'),

(71,'1982 Lamborghini Diablo','Classic Cars',599302,'1:24','Second Gear Diecast','This replica features opening doors, superb detail and craftsmanship, working steering system, opening forward compartment, opening rear trunk with removable spare, 4 wheel independent spring suspension as well as factory baked enamel finish.',7723,'16.24','37.76'),

(72,'1912 Ford Model T Delivery Wagon','Vintage Cars',223113,'1:24','Min Lin Diecast','This model features chrome trim and grille, opening hood, opening doors, opening trunk, detailed engine, working steering system. Color white.',9173,'46.91','88.51'),

(73,'1969 Chevrolet Camaro Z28','Classic Cars',599302,'1:24','Exoto Designs','1969 Z/28 Chevy Camaro 1:24 scale replica. The operating parts of this limited edition 1:24 scale diecast model car 1969 Chevy Camaro Z28- hood, trunk, wheels, streering, suspension and doors- are particularly delicate due to their precise scale and require special care and attention.',4695,'50.51','85.61'),

(74,'1971 Alpine Renault 1600s','Classic Cars',599302,'1:24','Welly Diecast Productions','This 1971 Alpine Renault 1600s replica Features opening doors, superb detail and craftsmanship, working steering system, opening forward compartment, opening rear trunk with removable spare, 4 wheel independent spring suspension as well as factory baked enamel finish.',7995,'38.58','61.23'),

(75,'1937 Horch 930V Limousine','Vintage Cars',223113,'1:24','Autoart Studio Design','Features opening hood, opening doors, opening trunk, wide white wall tires, front door arm rests, working steering system',2902,'26.30','65.75'),

(76,'2002 Chevy Corvette','Classic Cars',599302,'1:24','Gearbox Collectibles','The operating parts of this limited edition Diecast 2002 Chevy Corvette 50th Anniversary Pace car Limited Edition are particularly delicate due to their precise scale and require special care and attention. Features rotating wheels, poseable streering, opening doors and trunk.',9446,'62.11','107.08'),

(77,'1940 Ford Delivery Sedan','Vintage Cars',223113,'1:24','Carousel DieCast Legends','Chrome Trim, Chrome Grille, Opening Hood, Opening Doors, Opening Trunk, Detailed Engine, Working Steering System. Color black.',6621,'48.64','83.86'),

(78,'1956 Porsche 356A Coupe','Classic Cars',599302,'1:18','Classic Metal Creations','Features include: Turnable front wheels; steering create function; detailed interior; detailed engine; opening hood; opening trunk; opening doors; and detailed chassis.',6600,'98.30','140.43'),

(79,'Corsair F4U ( Bird Cage)','Planes',433823,'1:24','Second Gear Diecast','Has retractable wheels and comes with a stand. Official logos and insignias.',6812,'29.34','68.24'),

(80,'1936 Mercedes Benz 500k Roadster','Vintage Cars',223113,'1:24','Red Start Diecast','This model features grille-mounted chrome horn, lift-up louvered hood, fold-down rumble seat, working steering system and rubber wheels. Color black.',2081,'21.75','41.03'),

(81,'1992 Porsche Cayenne Turbo Silver','Classic Cars',599302,'1:24','Exoto Designs','This replica features opening doors, superb detail and craftsmanship, working steering system, opening forward compartment, opening rear trunk with removable spare, 4 wheel independent spring suspension as well as factory baked enamel finish.',6582,'69.78','118.28'),

(82,'1936 Chrysler Airflow','Vintage Cars',223113,'1:24','Second Gear Diecast','Features opening trunk,  working steering system. Color dark green.',4710,'57.46','97.39'),

(83,'1900s Vintage Tri-Plane','Planes',433823,'1:24','Unimax Art Galleries','Hand crafted diecast-like metal Triplane is Re-created in about 1:24 scale of antique pioneer airplane. This antique style metal triplane is all hand-assembled with many different parts.',2756,'36.23','72.45'),

(84,'1961 Chevrolet Impala','Classic Cars',599302,'1:18','Classic Metal Creations','This 1:18 scale precision die-cast reproduction of the 1961 Chevrolet Impala has all the features-doors, hood and trunk that open; detailed 409 cubic-inch engine; chrome dashboard and stick shift, two-tone interior; working steering system; all topped of with a factory baked-enamel finish.',7869,'32.33','80.84'),

(85,'1980’s GM Manhattan Express','Trucks and Buses',569331,'1:32','Motor City Art Classics','This 1980’s era new look Manhattan express is still active, running from the Bronx to mid-town Manhattan. Has 35 opeining windows and working lights. Needs a battery.',5099,'53.93','96.31'),

(86,'1997 BMW F650 ST','Motorcycles',599302,'1:32','Exoto Designs','Features official die-struck logos and baked enamel finish. Comes with stand.',178,'66.92','99.89'),

(87,'1982 Ducati 996 R','Motorcycles',599302,'1:32','Gearbox Collectibles','Features rotating wheels , working kick stand. Comes with stand.',9241,'24.14','40.23'),

(88,'1954 Greyhound Scenicruiser','Trucks and Buses',569331,'1:32','Classic Metal Creations','Model features bi-level seating, 50 windows, skylights & glare resistant glass, working steering system, original logos',2874,'25.98','54.11'),

(89,'1950''s Chicago Surface Lines Streetcar','Trains',123333,'1:32','Gearbox Collectibles','This streetcar is a joy to see. It has 80 separate windows, electric wire guides, detailed interiors with seats, poles and drivers controls, rolling and turning wheel assemblies, plus authentic factory baked-enamel finishes (Green Hornet for Chicago and Cream and Crimson for Boston).',8601,'26.72','62.14'),

(90,'1996 Peterbilt 379 Stake Bed with Outrigger','Trucks and Buses',569331,'1:32','Red Start Diecast','This model features, opening doors, detailed engine, working steering, tinted windows, detailed interior, die-struck logos, removable stakes operating outriggers, detachable second trailer, functioning 360-degree self loader, precision molded resin trailer and trim, baked enamel finish on cab',814,'33.61','64.64'),

(91,'1928 Ford Phaeton Deluxe','Vintage Cars',223113,'1:32','Highway 66 Mini Classics','This model features grille-mounted chrome horn, lift-up louvered hood, fold-down rumble seat, working steering system',136,'33.02','68.79'),

(92,'1974 Ducati 350 Mk3 Desmo','Motorcycles',599302,'1:32','Second Gear Diecast','This model features two-tone paint with chrome accents, superior die-cast detail , rotating wheels , working kick stand',3341,'56.13','102.05'),

(93,'1930 Buick Marquette Phaeton','Vintage Cars',223113,'1:50','Studio M Art Models','Features opening trunk,  working steering system',7062,'27.06','43.64'),

(94,'Diamond T620 Semi-Skirted Tanker','Trucks and Buses',569331,'1:50','Highway 66 Mini Classics','This limited edition model is licensed and perfectly scaled for Lionel Trains. The Diamond T620 has been produced in solid precision diecast and painted with a fire baked enamel finish. It comes with a removable tanker and is a perfect model to add authenticity to your static train or car layout or to just have on display.',1016,'68.29','115.75'),

(95,'1962 City of Detroit Streetcar','Trains',123333,'1:50','Classic Metal Creations','This streetcar is a joy to see. It has 99 separate windows, electric wire guides, detailed interiors with seats, poles and drivers controls, rolling and turning wheel assemblies, plus authentic factory baked-enamel finishes (Green Hornet for Chicago and Cream and Crimson for Boston).',1645,'37.49','58.58'),

(96,'2002 Yamaha YZR M1','Motorcycles',599302,'1:50','Autoart Studio Design','Features rotating wheels , working kick stand. Comes with stand.',600,'34.17','81.36'),

(97,'The Schooner Bluenose','Ships',433823,'1:700','Autoart Studio Design','All wood with canvas sails. Measures 31 1/2 inches in Length, 22 inches High and 4 3/4 inches Wide. Many extras.rnThe schooner Bluenose was built in Nova Scotia in 1921 to fish the rough waters off the coast of Newfoundland. Because of the Bluenose racing prowess she became the pride of all Canadians. Still featured on stamps and the Canadian dime, the Bluenose was lost off Haiti in 1946.',1897,'34.00','66.67'),

(98,'American Airlines: B767-300','Planes',433823,'1:700','Min Lin Diecast','Exact replia with official logos and insignias and retractable wheels',5841,'51.15','91.34'),

(99,'The Mayflower','Ships',433823,'1:700','Studio M Art Models','Measures 31 1/2 inches Long x 25 1/2 inches High x 10 5/8 inches WidernAll wood with canvas sail. Extras include long boats, rigging, ladders, railing, anchors, side cannons, hand painted, etc.',737,'43.30','86.61'),

(100,'HMS Bounty','Ships',433823,'1:700','Unimax Art Galleries','Measures 30 inches Long x 27 1/2 inches High x 4 3/4 inches Wide. rnMany extras including rigging, long boats, pilot house, anchors, etc. Comes with three masts, all square-rigged.',3501,'39.83','90.52'),

(101,'America West Airlines B757-200','Planes',433823,'1:700','Motor City Art Classics','Official logos and insignias. Working steering system. Rotating jet engines',9653,'68.80','99.72'),

(102,'The USS Constitution Ship','Ships',433823,'1:700','Red Start Diecast','All wood with canvas sails. Measures 31 1/2" Length x 22 3/8" High x 8 1/4" Width. Extras include 4 boats on deck, sea sprite on bow, anchors, copper railing, pilot houses, etc.',7083,'33.97','72.28'),

(103,'1982 Camaro Z28','Classic Cars',599302,'1:18','Carousel DieCast Legends','Features include opening and closing doors. Color: White. rnMeasures approximately 9 1/2" Long.',6934,'46.53','101.15'),

(104,'ATA: B757-300','Planes',433823,'1:700','Highway 66 Mini Classics','Exact replia with official logos and insignias and retractable wheels',7106,'59.33','118.65'),

(105,'F/A 18 Hornet 1/72','Planes',433823,'1:72','Motor City Art Classics','10" Wingspan with retractable landing gears.Comes with pilot',551,'54.40','80.00'),

(106,'The Titanic','Ships',433823,'1:700','Carousel DieCast Legends','Completed model measures 19 1/2 inches long, 9 inches high, 3inches wide and is in barn red/black. All wood and metal.',1956,'51.09','100.17'),

(107,'The Queen Mary','Ships',433823,'1:700','Welly Diecast Productions','Exact replica. Wood and Metal. Many extras including rigging, long boats, pilot house, anchors, etc. Comes with three masts, all square-rigged.',5088,'53.63','99.31'),

(108,'American Airlines: MD-11S','Planes',433823,'1:700','Second Gear Diecast','Polished finish. Exact replia with official logos and insignias and retractable wheels',8820,'36.27','74.03'),

(109,'Boeing X-32A JSF','Planes',433823,'1:72','Motor City Art Classics','10" Wingspan with retractable landing gears.Comes with pilot',4857,'32.77','49.66'),

(110,'Pont Yacht','Ships',433823,'1:72','Unimax Art Galleries','Measures 38 inches Long x 33 3/4 inches High. Includes a stand.rnMany extras including rigging, long boats, pilot house, anchors, etc. Comes with 2 masts, all square-rigged',414,'33.30','54.60') ON DUPLICATE KEY UPDATE product_id=product_id;

