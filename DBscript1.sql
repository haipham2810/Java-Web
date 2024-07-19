USE [master]
GO
/****** Object:  Database [Library_Managerr]    Script Date: 6/29/2024 8:58:51 AM ******/
CREATE DATABASE [Library_Managerr]
use [Library_Managerr]
ALTER DATABASE [Library_Managerr] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Library_Managerr].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Library_Managerr] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Library_Managerr] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Library_Managerr] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Library_Managerr] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Library_Managerr] SET ARITHABORT OFF 
GO
ALTER DATABASE [Library_Managerr] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Library_Managerr] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Library_Managerr] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Library_Managerr] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Library_Managerr] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Library_Managerr] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Library_Managerr] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Library_Managerr] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Library_Managerr] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Library_Managerr] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Library_Managerr] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Library_Managerr] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Library_Managerr] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Library_Managerr] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Library_Managerr] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Library_Managerr] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Library_Managerr] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Library_Managerr] SET RECOVERY FULL 
GO
ALTER DATABASE [Library_Managerr] SET  MULTI_USER 
GO
ALTER DATABASE [Library_Managerr] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Library_Managerr] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Library_Managerr] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Library_Managerr] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Library_Managerr] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Library_Managerr] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'Library_Managerr', N'ON'
GO
ALTER DATABASE [Library_Managerr] SET QUERY_STORE = ON
GO
ALTER DATABASE [Library_Managerr] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [Library_Managerr]
GO
/****** Object:  Table [dbo].[role]    Script Date: 6/29/2024 8:58:51 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[role](
	[id] [int] NOT NULL,
	[name] [nvarchar](300) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[book]    Script Date: 6/29/2024 8:58:51 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[book](
	[id] [int] NOT NULL,
	[name] [nvarchar](300) NOT NULL,
	[img] [nvarchar](50) NULL,
	[author] [nvarchar](200) NULL,
	[category] [int] NOT NULL,
	[publisher] [nvarchar](200) NULL,
	[language] [nvarchar](50) NULL,
	[total] [int] NULL,
	[current] [int] NULL,
	[position] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[borrower]    Script Date: 6/29/2024 8:58:51 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[borrower](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](50) NULL,
	[book_id] [int] NULL,
	[form] [date] NULL,
	[to] [date] NULL,
	[status] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[category]    Script Date: 6/29/2024 8:58:51 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[category](
	[id] [int] NOT NULL,
	[name] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[feedback]    Script Date: 6/29/2024 8:58:51 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[feedback](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [nvarchar](50) NULL,
	[title] [nvarchar](50) NULL,
	[content] [nvarchar](300) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[USER]    Script Date: 6/29/2024 8:58:51 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[USER](
	[username] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NOT NULL,
	[role] [INT] NOT NULL,
	[name] [nvarchar](50) NULL,
	[avt] [nvarchar](50) NULL,
	[sex] [bit] NULL,
	[datebirth] [date] NULL,
	[phone] [nvarchar](15) NULL,
	[gmail] [nvarchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


GO
INSERT [dbo].[book] ([id], [name], [img], [author], [category], [publisher], [language], [total], [current], [position]) VALUES (10001, N'Code: The Hidden Language of Computer Hardware and Software', N'img/book/book1.jpg', N'Charles Petzold', 1, N'Microsoft Press; 1st edition (October 11, 2000)', N'english', 5, 1, N'F34')
INSERT [dbo].[book] ([id], [name], [img], [author], [category], [publisher], [language], [total], [current], [position]) VALUES (10002, N'The Self-Taught Computer Scientist ', N'img/book/book2.jpg', N'Cory Althoff ', 1, N'Wiley; 1st edition (October 1, 2021)', N'english', 4, 0, N'F36')
INSERT [dbo].[book] ([id], [name], [img], [author], [category], [publisher], [language], [total], [current], [position]) VALUES (10003, N'The Chip : How Two Americans Invented the Microchip and Launched a Revolution', N'img/book/book3.jpg', N'T. R. Reid', 1, N'Random House Trade Paperbacks; Revised edition (October 9, 2001)', N'english', 3, 2, N'F35')
INSERT [dbo].[book] ([id], [name], [img], [author], [category], [publisher], [language], [total], [current], [position]) VALUES (10004, N'The Second Machine Age: Work, Progress, and Prosperity in a Time of Brilliant Technologies', N'img/book/book4.jpg', N'Erik Brynjolfsson', 1, N'giáo dục', N'english', 2, 0, N'A37')
INSERT [dbo].[book] ([id], [name], [img], [author], [category], [publisher], [language], [total], [current], [position]) VALUES (10005, N'The Innovators: How a Group of Hackers, Geniuses, and Geeks Created the Digital Revolution', N'img/book/book5.jpg', N'Walter Isaacson', 1, N'Walter Isaacson', N'english', 3, 2, N'E12')
INSERT [dbo].[book] ([id], [name], [img], [author], [category], [publisher], [language], [total], [current], [position]) VALUES (10006, N'A Programmers Guide to Computer Science: A virtual degree for the self-taught developer', N'img/book/book6.jpg', N'Dr. William M Springer II', 1, N'Jaxson Media; Illustrated edition (July 28, 2019)', N'english', 3, 1, N'E54')
INSERT [dbo].[book] ([id], [name], [img], [author], [category], [publisher], [language], [total], [current], [position]) VALUES (10007, N'A handbook for teacher research : From design to implementation', N'img/book/book7.jpg', N'Colin Lankshear, Michele Knobel', 2, N'Open University Press', N'english', 3, 2, N'B34')
INSERT [dbo].[book] ([id], [name], [img], [author], [category], [publisher], [language], [total], [current], [position]) VALUES (10008, N'A course in phonetics', N'img/book/book8.jpg', N' Ladefoged, Peter', 2, N'Cengage Learning, 2011', N'english', 3, 2, N'G54')
INSERT [dbo].[book] ([id], [name], [img], [author], [category], [publisher], [language], [total], [current], [position]) VALUES (10009, N'Language: Its structure and use', N'img/book/book9.jpg', N'Finegan, Edward', 2, N'Thomson Wadsworth, c2004,p', N'english', 3, 2, N'S34')
INSERT [dbo].[book] ([id], [name], [img], [author], [category], [publisher], [language], [total], [current], [position]) VALUES (10010, N'An introduction to linguistic theory and language acquisition', N'img/book/book10.jpg', N'Crain, Stephen; Lillo-Martin, Diane C. (Diane Carolyn)', 2, N'Wiley, 1999', N'english', 3, 3, N'G54')
INSERT [dbo].[book] ([id], [name], [img], [author], [category], [publisher], [language], [total], [current], [position]) VALUES (10011, N'An introduction to functional grammar', N'img/book/book11.jpg', N'Zoe Erotopoulos', 2, N'Arnold, 2004', N'english', 3, 1, N'C54')
INSERT [dbo].[book] ([id], [name], [img], [author], [category], [publisher], [language], [total], [current], [position]) VALUES (10012, N'An introduction to pragmatics: Social action for language teachers', N'img/book/book12.jpg', N'Halliday, M. A. K. (Michael Alexander Kirkwood)', 2, N'University of Michigan Press, 2003', N'english', 4, 1, N'D31')
INSERT [dbo].[book] ([id], [name], [img], [author], [category], [publisher], [language], [total], [current], [position]) VALUES (10013, N'500 Activities for the Primary Classroom', N'img/book/book13.jpg', N'LoCastro, Virginia.', 3, N'Macmillan Education', N'english', 3, 1, N'D43')
INSERT [dbo].[book] ([id], [name], [img], [author], [category], [publisher], [language], [total], [current], [position]) VALUES (10014, N'The Economics Book', N'img/book/book14.jpg', N'Carol Read', 3, N'Dorling Kindersley, 2012', N'english', 3, 2, N'Q21')
INSERT [dbo].[book] ([id], [name], [img], [author], [category], [publisher], [language], [total], [current], [position]) VALUES (10015, N'Principles for Dealing with the Changing World Order: Why Nations Succeed and Fail', N'img/book/book15.jpg', N'Dorling Kindersley', 3, N'Avid Reader Press / Simon & Schuster; 1st edition (November 30, 2021)', N'english', 3, 3, N'A02')
INSERT [dbo].[book] ([id], [name], [img], [author], [category], [publisher], [language], [total], [current], [position]) VALUES (10016, N'Business Valuation: The Most Complete Guide on How to Value a Business Through Updated Financial Valuation Methods', N'img/book/book16.jpg', N'Ray Dalio', 3, N'EquaMoney Press (February 17, 2023)', N'english', 3, 2, N'D87')
INSERT [dbo].[book] ([id], [name], [img], [author], [category], [publisher], [language], [total], [current], [position]) VALUES (10017, N'Financial Management Essentials You Always Wanted To Know (Color) (Self Learning Management)', N'img/book/book17.jpg', N'Nathan S. Goodwin', 3, N'Vibrant Publishers (September 19, 2019)', N'english', 3, 2, N'N21')
INSERT [dbo].[book] ([id], [name], [img], [author], [category], [publisher], [language], [total], [current], [position]) VALUES (10018, N'The Psychology of Money: Timeless lessons on wealth, greed, and happiness', N'img/book/book18.jpg', N'Kalpesh Ashar', 3, N'Harriman House (September 8, 2020)', N'english', 3, 2, N'A44')
INSERT [dbo].[book] ([id], [name], [img], [author], [category], [publisher], [language], [total], [current], [position]) VALUES (10019, N'Millionaire Mindset', N'img/book/book19.jpg', N'Paperback', 3, N'Get Rich Books (February 22, 2023)', N'english', 4, 1, N'A34')
INSERT [dbo].[book] ([id], [name], [img], [author], [category], [publisher], [language], [total], [current], [position]) VALUES (10020, N'English Grammar for Students of Spanish: The Study Guide for Those Learning Spanish, 7th edition', N'img/book/book20.jpg', N'Reveal Riches', 4, N'Olivia & Hill Press; 7th edition (December 18, 2012)', N'english', 3, 3, N'J78')
INSERT [dbo].[book] ([id], [name], [img], [author], [category], [publisher], [language], [total], [current], [position]) VALUES (10021, N'FRENCH, ENGLISH GRAMMAR FOR STUDENTS OF FRENCH, 7TH ED', N'img/book/book21.jpg', N'Emily Spinelli', 4, N'Olivia & Hill Press; 7th edition (July 17, 2013)', N'english', 3, 2, N'K51')
INSERT [dbo].[book] ([id], [name], [img], [author], [category], [publisher], [language], [total], [current], [position]) VALUES (10022, N'French All-in-One For Dummies', N'img/book/book22.jpg', N'Jacqueline Morton', 4, N'For Dummies; 1st edition (October 9, 2012)', N'english', 3, 2, N'F23')
INSERT [dbo].[book] ([id], [name], [img], [author], [category], [publisher], [language], [total], [current], [position]) VALUES (10023, N'Easy French Step-By-Step: Master High-Frequency Grammar for French Proficiency--Fast', N'img/book/book23.jpg', N'The Experts at Dummies', 4, N'McGraw Hill; 1st edition (December 1, 2008)', N'english', 3, 3, N'L44')
INSERT [dbo].[book] ([id], [name], [img], [author], [category], [publisher], [language], [total], [current], [position]) VALUES (10024, N'French For Dummies 2nd Edition', N'img/book/book24.jpg', N'Myrna Bell Rochester', 4, N'For Dummies; 2nd edition (August 30, 2011)', N'english', 3, 2, N'M34')
INSERT [dbo].[book] ([id], [name], [img], [author], [category], [publisher], [language], [total], [current], [position]) VALUES (10025, N'Tiếng Việt Văn Việt cho người Việt', N'img/book/book25.jpg', N'Cao Xuân Hạo', 4, N'Nhã Nam', N'vietnamese', 3, 3, N'T34')
INSERT [dbo].[book] ([id], [name], [img], [author], [category], [publisher], [language], [total], [current], [position]) VALUES (10026, N'Dẫn Luận Về Ngôn Ngữ Học', N'img/book/book26.jpg', N'Partha Dasgupta', 4, N'Nhà xuất bản Đồng Đức', N'vietnamese', 3, 2, N'N34')
GO
SET IDENTITY_INSERT [dbo].[borrower] ON 

INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (1, N'user1', 10001, CAST(N'2023-03-09' AS Date), CAST(N'2023-03-24' AS Date), N'Borrowed')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (2, N'user1', 10002, CAST(N'2023-02-09' AS Date), CAST(N'2023-02-24' AS Date), N'Borrowed')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (3, N'user2', 10013, CAST(N'2023-03-09' AS Date), CAST(N'2023-03-24' AS Date), N'Borrowed')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (4, N'user2', 10019, CAST(N'2023-02-09' AS Date), CAST(N'2023-02-24' AS Date), N'Borrowed')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (5, N'user3', 10001, CAST(N'2023-03-09' AS Date), CAST(N'2023-03-24' AS Date), N'Borrowed')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (6, N'user4', 10004, CAST(N'2023-03-09' AS Date), CAST(N'2023-03-24' AS Date), N'Borrowed')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (7, N'user4', 10006, CAST(N'2023-03-09' AS Date), CAST(N'2023-03-24' AS Date), N'Borrowed')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (8, N'user3', 10012, CAST(N'2023-03-09' AS Date), CAST(N'2023-03-24' AS Date), N'Borrowed')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (9, N'user2', 10018, CAST(N'2022-11-02' AS Date), CAST(N'2022-11-24' AS Date), N'Returned')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (10, N'user2', 10008, CAST(N'2022-11-02' AS Date), CAST(N'2022-11-24' AS Date), N'Returned')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (11, N'user3', 10021, CAST(N'2022-11-02' AS Date), CAST(N'2022-11-24' AS Date), N'Returned')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (12, N'user3', 10011, CAST(N'2022-11-02' AS Date), CAST(N'2022-11-24' AS Date), N'Returned')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (13, N'user4', 10016, CAST(N'2022-12-02' AS Date), CAST(N'2022-12-24' AS Date), N'Returned')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (14, N'user4', 10012, CAST(N'2022-12-02' AS Date), CAST(N'2022-12-24' AS Date), N'Returned')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (15, N'user2', 10011, CAST(N'2022-12-02' AS Date), CAST(N'2022-12-24' AS Date), N'Returned')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (16, N'user4', 10002, CAST(N'2022-12-02' AS Date), CAST(N'2022-12-24' AS Date), N'Returned')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (17, N'user2', 10017, CAST(N'2022-12-02' AS Date), CAST(N'2022-12-24' AS Date), N'Returned')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (18, N'user3', 10022, CAST(N'2023-01-02' AS Date), CAST(N'2023-01-24' AS Date), N'Returned')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (19, N'user1', 10005, CAST(N'2023-01-02' AS Date), CAST(N'2023-01-24' AS Date), N'Returned')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (20, N'user1', 10003, CAST(N'2023-01-02' AS Date), CAST(N'2023-01-24' AS Date), N'Returned')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (21, N'user1', 10007, CAST(N'2023-02-02' AS Date), CAST(N'2023-03-24' AS Date), N'Returned')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (22, N'user4', 10024, CAST(N'2023-02-02' AS Date), CAST(N'2023-02-24' AS Date), N'Returned')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (23, N'user1', 10017, CAST(N'2023-02-02' AS Date), CAST(N'2023-03-24' AS Date), N'Returned')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (24, N'user2', 10001, CAST(N'2023-02-02' AS Date), CAST(N'2023-03-24' AS Date), N'Returned')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (25, N'user4', 10014, CAST(N'2023-03-02' AS Date), CAST(N'2023-03-24' AS Date), N'Returned')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (26, N'user3', 10016, CAST(N'2023-03-02' AS Date), CAST(N'2023-03-24' AS Date), N'Returned')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (27, N'user1', 10012, CAST(N'2023-03-02' AS Date), CAST(N'2023-03-24' AS Date), N'Returned')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (28, N'user2', 10026, CAST(N'2023-03-02' AS Date), CAST(N'2023-03-24' AS Date), N'Returned')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (29, N'user1', 10019, CAST(N'2023-03-02' AS Date), CAST(N'2023-03-24' AS Date), N'Returned')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (30, N'user3', 10009, CAST(N'2023-03-02' AS Date), CAST(N'2023-03-24' AS Date), N'Returned')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (32, N'user1', 10002, CAST(N'2024-06-29' AS Date), CAST(N'2024-07-13' AS Date), N'Borrowed')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (33, N'user2', 10013, NULL, NULL, N'Processing')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (34, N'user2', 10019, NULL, NULL, N'Processing')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (35, N'user3', 10001, NULL, NULL, N'Processing')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (36, N'user4', 10004, NULL, NULL, N'Processing')
INSERT [dbo].[borrower] ([id], [username], [book_id], [form], [to], [status]) VALUES (37, N'user4', 10006, NULL, NULL, N'Processing')
SET IDENTITY_INSERT [dbo].[borrower] OFF
GO
INSERT [dbo].[category] ([id], [name]) VALUES (1, N'Computer Science')
INSERT [dbo].[category] ([id], [name]) VALUES (2, N'Math')
INSERT [dbo].[category] ([id], [name]) VALUES (3, N'Language')
INSERT [dbo].[category] ([id], [name]) VALUES (4, N'Economic')
GO


INSERT [dbo].[role] ([id], [name]) VALUES (1, N'Admin')
INSERT [dbo].[role] ([id], [name]) VALUES (0, N'User')
INSERT [dbo].[role] ([id], [name]) VALUES (2, N'Employee')
INSERT [dbo].[role] ([id], [name]) VALUES (3, N'Manager')

SET IDENTITY_INSERT [dbo].[feedback] ON 

INSERT [dbo].[feedback] ([id], [user_id], [title], [content]) VALUES (1, N'user1', N'Đánh giá thư viện', N'Thư viện có rất nhiều đầu sách hay')
INSERT [dbo].[feedback] ([id], [user_id], [title], [content]) VALUES (2, N'user2', N'sách thú vị ', N'thư viện thiết kế đẹp')
INSERT [dbo].[feedback] ([id], [user_id], [title], [content]) VALUES (3, N'user3', N'sách rất bổ ích', N'thư viện rất hữu ích cho sinh viên')
INSERT [dbo].[feedback] ([id], [user_id], [title], [content]) VALUES (4, N'user1', N'Góp Ý', N'thư viện nên bổ xung thêm bàn ghế cho sinh viên tự học ạ')
SET IDENTITY_INSERT [dbo].[feedback] OFF
GO
INSERT [dbo].[USER] ([username], [password], [role], [name], [avt], [sex], [datebirth], [phone], [gmail]) VALUES (N'admin', N'admin', 1, NULL, N'img/avt/avt.jpg', NULL, NULL, NULL, NULL)
INSERT [dbo].[USER] ([username], [password], [role], [name], [avt], [sex], [datebirth], [phone], [gmail]) VALUES (N'user1', N'123456', 0, N'alex1', N'img/avt/avt1.jpg', 1, CAST(N'2000-12-23' AS Date), N'0123443789', N'user1@gmail.com')
INSERT [dbo].[USER] ([username], [password], [role], [name], [avt], [sex], [datebirth], [phone], [gmail]) VALUES (N'user12', N'123456', 0, N'Tran Minh Thong', N'img/avt/avt.jpg', 0, CAST(N'2024-12-12' AS Date), N'123456789', N'thongngu0189@gmail.com')
INSERT [dbo].[USER] ([username], [password], [role], [name], [avt], [sex], [datebirth], [phone], [gmail]) VALUES (N'user2', N'123456', 0, N'alex2', N'img/avt/avt2.jpg', 1, CAST(N'2000-12-23' AS Date), N'0195456789', N'user2@gmail.com')
INSERT [dbo].[USER] ([username], [password], [role], [name], [avt], [sex], [datebirth], [phone], [gmail]) VALUES (N'user3', N'123456', 0, N'alex3', N'img/avt/avt3.jpg', 0, CAST(N'2000-12-23' AS Date), N'0123493489', N'user3@gmail.com')
INSERT [dbo].[USER] ([username], [password], [role], [name], [avt], [sex], [datebirth], [phone], [gmail]) VALUES (N'user4', N'123456', 0, N'alex4', N'img/avt/avt4.jpg', 1, CAST(N'2000-12-23' AS Date), N'0193214789', N'user4@gmail.com')

INSERT [dbo].[USER] ([username], [password], [role], [name], [avt], [sex], [datebirth], [phone], [gmail]) VALUES (N'employee3', N'123456', 2, N'yen2', N'img/avt/avt2.jpg', 1, CAST(N'2001-12-23' AS Date), N'0195456789', N'employee2@gmail.com')
INSERT [dbo].[USER] ([username], [password], [role], [name], [avt], [sex], [datebirth], [phone], [gmail]) VALUES (N'employee4', N'123456', 2, N'yen3', N'img/avt/avt3.jpg', 0, CAST(N'2000-12-23' AS Date), N'0123493489', N'employee3@gmail.com')
INSERT [dbo].[USER] ([username], [password], [role], [name], [avt], [sex], [datebirth], [phone], [gmail]) VALUES (N'manager2', N'123456', 3, N'yen4', N'img/avt/avt4.jpg', 1, CAST(N'2000-12-23' AS Date), N'0193214789', N'manager@gmail.com')
INSERT [dbo].[USER] ([username], [password], [role], [name], [avt], [sex], [datebirth], [phone], [gmail]) VALUES (N'manager1', N'123456', 2, N'yen4', N'img/avt/avt4.jpg', 1, CAST(N'2000-12-23' AS Date), N'0193214789', N'manager@gmail.com')

GO
ALTER TABLE [dbo].[book] ADD  DEFAULT ('img/book/book.jpg') FOR [img]
GO
ALTER TABLE [dbo].[USER] ADD  DEFAULT ('img/avt/avt.jpg') FOR [avt]
GO
ALTER TABLE [dbo].[book]  WITH CHECK ADD FOREIGN KEY([category])
REFERENCES [dbo].[category] ([id])
GO
ALTER TABLE [dbo].[borrower]  WITH CHECK ADD FOREIGN KEY([book_id])
REFERENCES [dbo].[book] ([id])
GO
ALTER TABLE [dbo].[borrower]  WITH CHECK ADD FOREIGN KEY([username])
REFERENCES [dbo].[USER] ([username])
GO
ALTER TABLE [dbo].[feedback]  WITH CHECK ADD FOREIGN KEY([user_id])
REFERENCES [dbo].[USER] ([username])
GO
ALTER TABLE [dbo].[USER]  WITH CHECK ADD FOREIGN KEY([role])
REFERENCES [dbo].[role] ([id])
GO
USE [master]
GO
ALTER DATABASE [Library_Managerr] SET  READ_WRITE 
GO
