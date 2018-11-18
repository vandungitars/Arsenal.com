create database arsenal;
use arsenal;

CREATE TABLE admin (
  id_admin int NOT NULL IDENTITY(1,1),
  username varchar(50),
  password varchar(60),
  PRIMARY KEY (id_admin)
);
CREATE TABLE video (
  id_video int NOT NULL IDENTITY(1,1),
  link_video varchar(100),
  title_video varchar(255),
  PRIMARY KEY (id_video)
);
CREATE TABLE type_news(
  id_type int NOT NULL IDENTITY(1,1),
  types varchar(100),
  PRIMARY KEY (id_type)
);
INSERT INTO type_news VALUES ('Tin tức'),('Chuyển nhượng');
CREATE TABLE news (
  id_news int NOT NULL IDENTITY(1,1),
  content_news text,
  description_news varchar(255),
  title_news varchar(255),
  timeupdate_news varchar(255),
  image_news varchar(45),
  description_image varchar(255),
  id_type int,
  PRIMARY KEY (id_news),
  CONSTRAINT FK_NEWS_TYPE FOREIGN KEY (id_type) REFERENCES type_news (id_type)
);
CREATE TABLE comment (
  id_comment int NOT NULL IDENTITY(1,1),
  id_news int not null,
  content_comment varchar(255),
  PRIMARY KEY (id_comment),
  CONSTRAINT FK_comment_news FOREIGN KEY (id_news) REFERENCES news (id_news)
);
CREATE TABLE schedule (
  id_schedule int NOT NULL IDENTITY(1,1),
  time varchar(50),
  team_customer varchar(255),
  team_home varchar(255),
  PRIMARY KEY (id_schedule)
);
CREATE TABLE product (
  id_product int NOT NULL IDENTITY(1,1),
  season varchar(45),
  name_product varchar(255),
  price_product int,
  image_product varchar(100),
  PRIMARY KEY (id_product)
);
CREATE TABLE detailproduct (
  id_detailproduct int NOT NULL IDENTITY(1,1),
  color_product varchar(255),
  size_product varchar(255),
  PRIMARY KEY (id_detailproduct)
);
CREATE TABLE product_detail (
  id_product_detail int NOT NULL IDENTITY(1,1),
  id_product int NOT NULL,
  id_detailproduct int NOT NULL,
  PRIMARY KEY (id_product_detail),
  CONSTRAINT FK_pd_detailproduct FOREIGN KEY (id_detailproduct) REFERENCES detailproduct (id_detailproduct),
  CONSTRAINT FK_pd_product FOREIGN KEY (id_product) REFERENCES product (id_product)
);
CREATE TABLE customer (
  id_customer int NOT NULL IDENTITY(1,1),
  address_customer varchar(255),
  name_customer varchar(255),
  phone_customer int,
  PRIMARY KEY (id_customer)
);
CREATE TABLE customer_product (
  id_customer_product int NOT NULL IDENTITY(1,1),
  id_product int NOT NULL,
  id_customer int NOT NULL,
  PRIMARY KEY (id_customer_product),
  CONSTRAINT FK_cp_customer FOREIGN KEY (id_customer) REFERENCES customer (id_customer),
  CONSTRAINT FK_cp_product FOREIGN KEY (id_product) REFERENCES product (id_product)
);
CREATE TABLE practise (
  id_practise int NOT NULL IDENTITY(1,1),
  image varchar(45),
  PRIMARY KEY (id_practise)
);
CREATE TABLE player (
  id_player int NOT NULL IDENTITY(1,1),
  squadnumber int,
  country_player varchar(255),
  image_player varchar(255),
  name_player varchar(255),
  location_player varchar(45),
  born_player date,
  PRIMARY KEY (id_player)
);
