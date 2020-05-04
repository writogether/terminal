# New table template
#
# -- A, desc for A.
# drop table if exists `A`;
# create table `A`
# (
#     `id` int(11) not null auto_increment,
#     primary key (`id`)
# ) engine = InnoDB
#   default charset = utf8mb4;
# insert into `A`
# values ();
# commit;
# --

set names utf8mb4;

-- user, which contains info of users.
drop table if exists `user`;
create table `user`
(
    `id`           int(11)      not null auto_increment,
    `description`  varchar(255) not null default 'default_user_description',
    `username`     varchar(255) not null default 'default_user_username',
    `password`     varchar(255) not null default 'default_user_password',
    `phone_number` varchar(20)  not null default '00000000000',
    `email`        varchar(255) not null default 'default@default.com',
    `level`        int(11)      not null default 0,
    primary key (`id`)
) engine = InnoDB
  default charset = utf8mb4;
insert into `user` (username, password)
values ('userA', md5('passA'));
insert into `user` (username, password)
values ('userB', md5('passB'));
commit;
--

-- user_pwd, only for dev convenience.
drop table if exists `user_pwd`;
create table `user_pwd`
(
    `id`       int(11)      not null auto_increment,
    `password` varchar(255) not null default 'default_user_pwd_password',
    primary key (`id`)
) engine = InnoDB
  default charset = utf8mb4;
insert into `user_pwd`(password)
values ('passA');
insert into `user_pwd`(password)
values ('passB');
commit;
--

-- story, single part of a novel, contains all except for content.
drop table if exists `story`;
create table `story`
(
    `id`        int(11)      not null auto_increment,
    `father_id` int(11)      not null default 0,
    `author_id` int(11)      not null default 0,
    `title`     varchar(255) not null default 'default_chapter_title',
    primary key (`id`)
) engine = InnoDB
  default charset = utf8mb4;
insert into `story`
values ();
commit;
--

-- story_content, expensive to query.
drop table if exists `story_content`;
create table `story_content`
(
    `id`      int(11) not null auto_increment,
    `content` blob,
    primary key (`id`)
) engine = InnoDB
  default charset = utf8mb4;
insert into `story_content`
values ();
commit;
--

-- comment, allowed to contains small image.
drop table if exists `comment`;
create table `comment`
(
    `id`           int(11) not null auto_increment,
    `commenter_id` int(11) not null default 0,
    `story_id`     int(11) not null default 0,
    `content`      blob,
    primary key (`id`)
) engine = InnoDB
  default charset = utf8mb4;
insert into `comment`
values ();
commit;
--

-- eval, user could evaluate story.
drop table if exists `eval`;
create table `eval`
(
    `id`       int(11) not null auto_increment,
    `liker_id` int(11) not null        default 0,
    `story_id` int(11) not null        default 0,
    `type`     enum ('like','dislike') default 'like',
    primary key (`id`)
) engine = InnoDB
  default charset = utf8mb4;
insert into `eval`
values ();
commit;
--
