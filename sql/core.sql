# New table template
#
# -- A, desc for A.
# drop table if exists `A`;
# create table `A`
# (
#     `id` int(20) not null auto_increment,
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
    `id`           int(20)      not null auto_increment,
    `description`  varchar(255) not null default '',
    `username`     varchar(255) not null default '',
    `password`     varchar(255) not null default '',
    `phone_number` varchar(20)  not null default '',
    `email`        varchar(255) not null default '',
    `level`        int(20)      not null default 0,
    primary key (`id`)
) engine = InnoDB
  default charset = utf8mb4;
insert into `user` (username, password, phone_number)
values ('userA', md5('passA'), '20202020201');
insert into `user` (username, password, phone_number)
values ('userB', md5('passB'), '22222222222');
commit;
--

-- story, single part of a novel, contains all except for content.
drop table if exists `story`;
create table `story`
(
    `id`         int(20)       not null auto_increment,
    `father_id`  int(20)       not null default 0,
    `author_id`  int(20)       not null default 0,
    `title`      varchar(255)  not null default '',
    `tag`        varchar(255)  not null default 'Other',
    `valid`      boolean       not null default true,
    `open`       boolean       not null default true,
    `popularity` int(20)       not null default 0,
    `depth`      int(20)       not null default 0,
    `tree_id`    int(20)       not null default 0,
    `path`       varchar(1024) not null default 0,
    primary key (`id`)
) engine = InnoDB
  default charset = utf8mb4;
insert into `story`(tag)
values ('Other');
commit;
--

-- story_content, expensive to query.
drop table if exists `story_content`;
create table `story_content`
(
    `id`      int(20) not null auto_increment,
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
    `id`           int(20) not null auto_increment,
    `commenter_id` int(20) not null default 0,
    `story_id`     int(20) not null default 0,
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
    `id`       int(20) not null auto_increment,
    `user_id`  int(20) not null default 0,
    `story_id` int(20) not null default 0,
    `type`     varchar(255)     default '',
    primary key (`id`)
) engine = InnoDB
  default charset = utf8mb4;
insert into `eval`
values ();
commit;
--

-- eval, user could evaluate story.
drop table if exists `collect`;
create table `collect`
(
    `id`       int(20) not null auto_increment,
    `user_id`  int(20) not null default 0,
    `story_id` int(20) not null default 0,
    primary key (`id`)
) engine = InnoDB
  default charset = utf8mb4;
insert into `collect`
values ();
commit;
--

-- tree, story hierarchy.
drop table if exists `tree`;
create table `tree`
(
    `id`   int(20)       not null auto_increment,
    `tree` varchar(8196) not null default '',
    primary key (`id`)
) engine = InnoDB
  default charset = utf8mb4;
insert into `tree`
values ();
commit;
--
