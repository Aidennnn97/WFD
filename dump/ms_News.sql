-- MySQL dump 10.13  Distrib 8.0.30, for macos12 (x86_64)
--
-- Host: 127.0.0.1    Database: ms
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `News`
--

DROP TABLE IF EXISTS `News`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `News` (
  `news_id` int NOT NULL AUTO_INCREMENT,
  `news_title` varchar(200) DEFAULT NULL,
  `news_main_image` varchar(300) DEFAULT NULL,
  `news_content` text,
  `news_url` varchar(300) DEFAULT NULL,
  `crawling_date` datetime DEFAULT NULL,
  `news_views` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`news_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `News`
--

LOCK TABLES `News` WRITE;
/*!40000 ALTER TABLE `News` DISABLE KEYS */;
INSERT INTO `News` VALUES (1,'\"이제는 나가라\" 레알, 드디어 \'1,600억 먹튀\'와 담판','https://imgnews.pstatic.net/image/117/2022/11/07/202211071032618568_1_20221107121403800.jpg?type=w647','[마이데일리 = 최병진 기자] 레알 마드리드가 마침내 에당 아자르(31)를 내친다.2012-13시즌부터 첼시에서 활약한 아자르는 최고의 크랙이었다. 아자르는...','https://sports.news.naver.com/news?oid=117&aid=0003665158','2022-11-07 16:37:25','135419'),(2,'[epl.review] \'SON 없는\' 토트넘, 안방서 \'살라 2골\' 리버풀에 1-2 패배...4위','https://imgnews.pstatic.net/image/411/2022/11/07/0000018148_001_20221107032401565.jpg?type=w647','[포포투=정지훈]안와골절 부상으로 수술을 받은 손흥민이 명단에서 제외됐고, 토트넘이 안방에서 리버풀에 뼈아픈 패배를 당했다.토트넘 훗스퍼는 7일 오전 1시 ...','https://sports.news.naver.com/news?oid=411&aid=0000018148','2022-11-07 16:37:25','126457'),(3,'\"맘마미아!\"+이태리 손가락 제스처, 김민재의 \'현지화\'...동료도 폭소','https://imgnews.pstatic.net/image/117/2022/11/07/202211070817984548_1_20221107081806281.jpg?type=w647','[마이데일리 = 최병진 기자] 김민재(나폴리)가 이탈리아 적응이 끝났음을 리액션으로 전했다.나폴리는 지난 6일 오전 2시(한국시간) 이탈리아 게비스 스타디움...','https://sports.news.naver.com/news?oid=117&aid=0003665097','2022-11-07 16:37:25','111130'),(4,'손흥민과 연락한 콘테 감독 \"병원 아닌 집…월드컵 뛸 것\"','https://imgnews.pstatic.net/image/003/2022/11/07/NISI20221102_0019417628_web_20221102075637_20221107084812586.jpg?type=w647','기사내용 요약4일 수술 성공적으로 마친 뒤 회복 중[마르세유=AP/뉴시스] 토트넘 홋스퍼의 손흥민이 1일(현지시간) 프랑스 마르세유의 스타드 벨로드롬에서 열...','https://sports.news.naver.com/news?oid=003&aid=0011520533','2022-11-07 16:37:25','100917'),(5,'수술한 손흥민과 연락한 콘테 감독 “병원 아닌 집…월드컵 뛸 것”','https://imgnews.pstatic.net/image/018/2022/11/07/0005360509_001_20221107100302745.jpg?type=w647','지난 2일 부상 후 그라운드를 빠져나가는 손흥민.(사진=AFPBBNews)[이데일리 스타in 주미희 기자] 안토니오 콘테 토트넘 감독이 안와골절로 수술을 받...','https://sports.news.naver.com/news?oid=018&aid=0005360509','2022-11-07 16:37:25','93539'),(6,'[EPL 리뷰] \'호날두 침묵\' 맨유, 빌라에 1-3 충격 패배...\'6G 무패 좌절\'','https://imgnews.pstatic.net/image/413/2022/11/07/0000149483_001_20221107005801645.jpg?type=w647','호날두가 침묵한 가운데, 맨체스터 유나이티드가 무너졌다.맨체스터 유나이티드는 6일 오후 11시(한국시간) 영국 버밍엄에 위치한 빌라 파크에서 열린 2022-...','https://sports.news.naver.com/news?oid=413&aid=0000149483','2022-11-07 16:37:25','84218'),(7,'토트넘 주전감인지 의심될 정도…평점 \'5점\' 최악의 경기력','https://imgnews.pstatic.net/image/411/2022/11/07/0000018152_001_20221107091704663.jpg?type=w647','[포포투=김환]에릭 다이어가 최악의 경기력을 선보이며 팀의 패배를 막지 못했다.토트넘 훗스퍼는 7일 오전 1시 30분(한국시간) 영국 런던에 위치한 토트넘 ...','https://sports.news.naver.com/news?oid=411&aid=0000018152','2022-11-07 16:37:25','80029'),(8,'SON 없는 토트넘 4위 하락... \'살라 2골\' 리버풀에 1-2 패배','https://imgnews.pstatic.net/image/108/2022/11/07/0003102152_001_20221107072701269.jpg?type=w647','토트넘이 해리 케인(왼쪽)의 골에도 리버풀전 패배를 당했다. /사진=AFPBBNews=뉴스1손흥민의 결장 속에 토트넘 홋스퍼가 3위 탈환에 실패했다.토트넘은...','https://sports.news.naver.com/news?oid=108&aid=0003102152','2022-11-07 16:37:25','78936'),(9,'\'월드컵 앞두고 터졌다\' 프라이부르크 정우영 분데스리가 1호골','https://imgnews.pstatic.net/image/025/2022/11/07/0003236418_001_20221107091801075.jpg?type=w647','프라이부르크 공격수 정우영(오른쪽)이 7일 쾰른전에서 절묘한 왼발슛으로 선제 결승골을 터트리고 있다. AFP=연합뉴스 SC 프라이부르크 공격수 정우영(23)...','https://sports.news.naver.com/news?oid=025&aid=0003236418','2022-11-07 16:37:25','74335'),(10,'SON 없는 토트넘, \'살라 멀티골\' 리버풀에 1-2 패배','https://imgnews.pstatic.net/image/109/2022/11/07/0004735073_001_20221107055202824.jpg?type=w647','[사진] ⓒGettyimages(무단전재 및 재배포 금지)[OSEN=이인환 기자] 손흥민 없는 토트넘이 홈에서 무너졌다.토트넘은 7일 오전 1시 30분(한국...','https://sports.news.naver.com/news?oid=109&aid=0004735073','2022-11-07 16:37:25','65886'),(11,'하필 이때... \'EPL 천재\' 월드컵 희망 사라졌다 \'부상 불운\'','https://imgnews.pstatic.net/image/108/2022/11/07/0003102282_001_20221107133301252.jpg?type=w647','필리페 쿠티뉴. /사진=AFPBBNews=뉴스1\'천재\' 필리페 쿠티뉴(30·아스톤빌라)의 월드컵 희망이 완전히 사라진 것으로 보인다.영국 디애슬레틱은 7일(...','https://sports.news.naver.com/news?oid=108&aid=0003102282','2022-11-07 16:37:25','63996'),(12,'\'4연승\' 뉴캐슬, 토트넘 밀어내고 3위 탈환… 토트넘, 리버풀 잡아야 순위 지킨다','https://imgnews.pstatic.net/image/436/2022/11/07/0000065398_001_20221107011101545.jpg?type=w647','미겔 알미론(뉴캐슬유나이티드). 게티이미지코리아뉴캐슬유나이티드가 상승세를 타고 4연승을 달리며 잉글랜드 프리미어리그(EPL) 3위로 올라섰다.6일(한국시간)...','https://sports.news.naver.com/news?oid=436&aid=0000065398','2022-11-07 16:37:25','62151'),(13,'여기서 못 살겠어...\'차량 절도당한\' 흐비차, 이사 준비 중','https://imgnews.pstatic.net/image/311/2022/11/07/0001521549_001_20221107125101402.jpg?type=w647','(엑스포츠뉴스 권동환 기자) 김민재 팀 동료이자 SSC 나폴리 에이스 흐비차 크바라츠헬리아가 도둑들로부터 안전한 곳으로 이사할 준비를 하고 있다.이탈리아 매...','https://sports.news.naver.com/news?oid=311&aid=0001521549','2022-11-07 16:37:25','60922'),(14,'불행 중 다행... 콘테 감독 \"손흥민 월드컵 출전 확신\"','https://imgnews.pstatic.net/image/469/2022/11/07/0000706162_001_20221107131501651.jpg?type=w647','안토니오 콘테(왼쪽) 토트넘 감독이 팀 K리그와의 친선경기를 하루 앞둔 7월 12일 서울 마포구 서울월드컵경기장에서 열린 사전 기자회견에서 손흥민과 함께 \'...','https://sports.news.naver.com/news?oid=469&aid=0000706162','2022-11-07 16:37:25','54364'),(15,'콘테 감독 “손흥민 빨리 돌아올 것…월드컵 출전 확신”','https://imgnews.pstatic.net/image/028/2022/11/07/0002613633_001_20221107110904722.jpg?type=w647','안토니오 콘테 토트넘 홋스퍼 감독이 7일(한국시각) 영국 런던 토트넘 홋스퍼 스타디움에서 열린 2022∼2023 잉글랜드 프리미어리그(EPL) 15라운드 안...','https://sports.news.naver.com/news?oid=028&aid=0002613633','2022-11-07 16:37:25','44477'),(16,'데자뷔?... 주장 완장 패대기친 \'캡틴\' 호날두','https://imgnews.pstatic.net/image/311/2022/11/07/0001521386_001_20221107003801449.jpg?type=w647','(엑스포츠뉴스 권동환 기자) 오랜만에 주장 완장을 달고 경기에 나선 맨체스터 유나이티드 베테랑 크리스티아누 호날두가 또다시 팬들의 눈살을 찌푸리게 만들었다....','https://sports.news.naver.com/news?oid=311&aid=0001521386','2022-11-07 16:37:25','39603'),(17,'\"개인적 감정은 없어\" 도발했던 오바메양, 되로 주고 말로 받았다','https://imgnews.pstatic.net/image/413/2022/11/07/0000149495_001_20221107072601635.jpg?type=w647','피에르-에메릭 오바메양이 망신을 당했다.첼시는 6일 오후 9시(한국시간) 영국 런던에 위치한 스탬퍼드 브리지에서 열린 2022-23시즌 잉글리시 프리미어리그...','https://sports.news.naver.com/news?oid=413&aid=0000149495','2022-11-07 16:37:25','36914'),(18,'텐하흐 엄중 문책 \"룰을 어겼다\"','https://imgnews.pstatic.net/image/076/2022/11/07/2022110801000505200065311_20221107144206594.jpg?type=w647','에릭 텐하흐 감독(왼쪽)과 크리스티아누 호날두.로이터연합뉴스[스포츠조선 한동훈 기자] 맨체스터 유나이티드 에릭 텐하흐 감독이 패배 후 선수단을 엄중 문책했다...','https://sports.news.naver.com/news?oid=076&aid=0003935990','2022-11-07 16:37:25','36401'),(19,'\"EPL, 맨시티가 없었더라면 더 나았을 것\"...어째서?','https://imgnews.pstatic.net/image/411/2022/11/07/0000018164_001_20221107114101505.jpg?type=w647','[포포투=김환]맨체스터 시티의 독주 체제를 향한 걱정 섞인 비판이 나왔다.이번 시즌 잉글리시 프리미어리그(EPL) 분위기는 예년과 조금 다르다. 맨시티는 꾸...','https://sports.news.naver.com/news?oid=411&aid=0000018164','2022-11-07 16:37:25','35100'),(20,'[laliga.review] \'무리키-은디아예 골\' 마요르카, 비야레알 2-0 제압…이강인 72분','https://imgnews.pstatic.net/image/411/2022/11/07/0000018150_001_20221107042101482.jpg?type=w647','[포포투=김환]마요르카가 비야레알을 상대로 2-0 승리를 거뒀다. 선발 출전한 이강인은 72분을 소화했다.마요르카는 7일 오전 2시 30분(한국시간) 스페인...','https://sports.news.naver.com/news?oid=411&aid=0000018150','2022-11-07 16:37:25','34117');
/*!40000 ALTER TABLE `News` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-01 14:06:08
