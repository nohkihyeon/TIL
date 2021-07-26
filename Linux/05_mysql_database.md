# mysql

- `rename table topic to topic_backup` <br>
![image](https://user-images.githubusercontent.com/65120581/126920967-d86cdd58-c986-4f94-be32-2d73ff36534c.png)

## 테이블 분리
- Table structure for table `author`
```mysql
CREATE TABLE `author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `profile` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
```
- Dumping data for table `author`
```mysql
 
INSERT INTO `author` VALUES (1,'khNoh','developer');
INSERT INTO `author` VALUES (2,'hsLee','database administrator');
INSERT INTO `author` VALUES (3,'jsPark','data scientist, developer');
```
- Table structure for table `topic`
```mysql
CREATE TABLE `topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `description` text,
  `created` datetime NOT NULL,
  `author_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
```
-  Dumping data for table `topic`
```mysql
INSERT INTO `topic` VALUES (1,'MySQL','MySQL is...', NOW(),1);
INSERT INTO `topic` VALUES (2,'Oracle','Oracle is ...','2018-01-03 13:01:10',1);
INSERT INTO `topic` VALUES (3,'SQL Server','SQL Server is ...','2018-01-20 11:01:10',2);
INSERT INTO `topic` VALUES (4,'PostgreSQL','PostgreSQL is ...','2018-01-23 01:03:03',3);
INSERT INTO `topic` VALUES (5,'MongoDB','MongoDB is ...','2018-01-30 12:31:03',1);
```
## 테이블 JOIN <br>
![image](https://user-images.githubusercontent.com/65120581/126921728-7222480c-685e-4332-b51f-8959e72aeba2.png)

- `SELECT * FROM topic LEFT JOIN author ON topic.author_id = author.id`
![image](https://user-images.githubusercontent.com/65120581/126922038-df640e3e-6e6d-4c6a-bb63-8c49a8009e4b.png)
- 중복을 제거하기 위해서 테이블을 분리하며, JOIN 연산자를 이용해서 테이블을 효과적으로 조회 가능
