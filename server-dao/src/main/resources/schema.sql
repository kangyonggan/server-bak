CREATE DATABASE site
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;

USE site;

CREATE TABLE article
(
  id            BIGINT(20) PRIMARY KEY  AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  title         VARCHAR(256)                           NOT NULL
  COMMENT '标题',
  summary       VARCHAR(512)                           NOT NULL                DEFAULT ''
  COMMENT '摘要',
  body          LONGTEXT                               NOT NULL
  COMMENT '内容',
  category_code VARCHAR(16)                            NOT NULL
  COMMENT '栏目代码',
  category_name VARCHAR(32)                            NOT NULL
  COMMENT '栏目名称',
  is_deleted    TINYINT                                NOT NULL                DEFAULT 0
  COMMENT '是否删除 {0:未删除, 1:已删除}',
  created_time  TIMESTAMP                              NOT NULL                DEFAULT current_timestamp
  COMMENT '创建时间',
  updated_time  TIMESTAMP                              NOT NULL                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '最后更新时间'
)
  COMMENT '文章表';
CREATE UNIQUE INDEX id_UNIQUE
  ON article (id);

CREATE TABLE article_index
(
  id            BIGINT(20) PRIMARY KEY  AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  article_id    BIGINT(20)                             NOT NULL
  COMMENT '文章ID',
  title         LONGTEXT                               NOT NULL
  COMMENT '标题',
  category_name LONGTEXT                               NOT NULL
  COMMENT '栏目名称',
  body          LONGTEXT                               NOT NULL
  COMMENT '内容'
)
  COMMENT '文章检索表';
CREATE UNIQUE INDEX id_UNIQUE
  ON article_index (id);
CREATE UNIQUE INDEX article_id_UNIQUE
  ON article_index (article_id);
ALTER TABLE article_index
  ENGINE = MyISAM;

ALTER TABLE `article_index`
  ADD FULLTEXT INDEX (`title`, `category_name`, `body`);
REPAIR TABLE article_index QUICK;

CREATE TABLE category
(
  id           BIGINT(20) PRIMARY KEY  AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  code         VARCHAR(16)                            NOT NULL
  COMMENT '栏目代码',
  name         VARCHAR(32)                            NOT NULL
  COMMENT '栏目名称',
  sort         INTEGER                                NOT NULL               DEFAULT 0
  COMMENT '排序',
  is_deleted   TINYINT                                NOT NULL               DEFAULT 0
  COMMENT '是否删除 {0:未删除, 1:已删除}',
  created_time TIMESTAMP                              NOT NULL               DEFAULT current_timestamp
  COMMENT '创建时间',
  updated_time TIMESTAMP                              NOT NULL               DEFAULT current_timestamp ON UPDATE current_timestamp
  COMMENT '最后更新时间'
)
  COMMENT '栏目表';
CREATE UNIQUE INDEX id_UNIQUE
  ON category (id);
CREATE UNIQUE INDEX code_UNIQUE
  ON category (code);

CREATE TABLE error_code
(
  id             BIGINT(20) PRIMARY KEY  AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  api_code       VARCHAR(32)                            NOT NULL
  COMMENT 'api代码',
  api_error_code VARCHAR(64)                            NOT NULL
  COMMENT 'api错误码',
  api_error_desc VARCHAR(128)                           NOT NULL
  COMMENT 'api错误描述',
  error_code     VARCHAR(64)                            NOT NULL
  COMMENT '内部错误码',
  error_desc     VARCHAR(128)                           NOT NULL
  COMMENT '内部错误描述',
  is_deleted     TINYINT                                NOT NULL               DEFAULT 0
  COMMENT '是否删除 {0:未删除, 1:已删除}',
  created_time   TIMESTAMP                              NOT NULL               DEFAULT current_timestamp
  COMMENT '创建时间',
  updated_time   TIMESTAMP                              NOT NULL               DEFAULT current_timestamp ON UPDATE current_timestamp
  COMMENT '最后更新时间'
)
  COMMENT '错误码表';
CREATE UNIQUE INDEX id_UNIQUE
  ON error_code (id);

CREATE TABLE api_command
(
  id           BIGINT(20) PRIMARY KEY  AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  api_code     VARCHAR(32)                            NOT NULL
  COMMENT 'api代码',
  params       VARCHAR(2048)                          NOT NULL               DEFAULT ''
  COMMENT '参数串',
  status       VARCHAR(1)                             NOT NULL               DEFAULT 'I'
  COMMENT '指令状态,{I:处理中,F:失败,E:异常,Y:成功}',
  result       LONGTEXT                               NULL
  COMMENT '参数串',
  is_deleted   TINYINT                                NOT NULL               DEFAULT 0
  COMMENT '是否删除 {0:未删除, 1:已删除}',
  created_time TIMESTAMP                              NOT NULL               DEFAULT current_timestamp
  COMMENT '创建时间',
  updated_time TIMESTAMP                              NOT NULL               DEFAULT current_timestamp ON UPDATE current_timestamp
  COMMENT '最后更新时间'
)
  COMMENT 'api指令表';
CREATE UNIQUE INDEX id_UNIQUE
  ON api_command (id);

CREATE TABLE book
(
  id           BIGINT(20) PRIMARY KEY  AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  code         VARCHAR(16)                            NOT NULL
  COMMENT '书代码',
  name         VARCHAR(64)                            NOT NULL
  COMMENT '书名称',
  author       VARCHAR(32)                            NOT NULL                DEFAULT ''
  COMMENT '作者',
  summary      VARCHAR(512)                           NOT NULL                DEFAULT ''
  COMMENT '摘要',
  lock_st      VARCHAR(1)                             NOT NULL                DEFAULT 'N'
  COMMENT '上锁状态,{N:正常,I:已锁}',
  is_finished  TINYINT                                NOT NULL                DEFAULT 0
  COMMENT '是否完结 {0:未完结, 1:已完结}',
  is_deleted   TINYINT                                NOT NULL                DEFAULT 0
  COMMENT '是否删除 {0:未删除, 1:已删除}',
  created_time TIMESTAMP                              NOT NULL                DEFAULT current_timestamp
  COMMENT '创建时间',
  updated_time TIMESTAMP                              NOT NULL                DEFAULT current_timestamp ON UPDATE current_timestamp
  COMMENT '最后更新时间'
)
  COMMENT '书籍表';
CREATE UNIQUE INDEX id_UNIQUE
  ON book (id);
CREATE UNIQUE INDEX code_UNIQUE
  ON book (code);

CREATE TABLE chapter
(
  id           BIGINT(20) PRIMARY KEY  AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  book_code    VARCHAR(16)                            NOT NULL
  COMMENT '书代码',
  book_name    VARCHAR(64)                            NOT NULL
  COMMENT '书名称',
  title        VARCHAR(128)                           NOT NULL
  COMMENT '章节名称',
  body         LONGTEXT                               NOT NULL
  COMMENT '内容',
  chapter_no   INT                                    NOT NULL
  COMMENT '章节序号',
  is_deleted   TINYINT                                NOT NULL               DEFAULT 0
  COMMENT '是否删除 {0:未删除, 1:已删除}',
  created_time TIMESTAMP                              NOT NULL               DEFAULT current_timestamp
  COMMENT '创建时间',
  updated_time TIMESTAMP                              NOT NULL               DEFAULT current_timestamp ON UPDATE current_timestamp
  COMMENT '最后更新时间'
)
  COMMENT '章节表';
CREATE UNIQUE INDEX id_UNIQUE
  ON chapter (id);