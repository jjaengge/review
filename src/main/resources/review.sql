-- tbl_user_info Table Create SQL
CREATE TABLE review.tbl_user_info
(
    `seq`         INT             NOT NULL    AUTO_INCREMENT,
    `user_id`     VARCHAR(100)    NOT NULL,
    `user_point`  INT             NOT NULL,
    PRIMARY KEY (seq)
);

ALTER TABLE review.tbl_user_info
    ADD CONSTRAINT UC_user_id UNIQUE (user_id);


-- tbl_user_info Table Create SQL
CREATE TABLE review.tbl_place_info
(
    `seq`       INT             NOT NULL    AUTO_INCREMENT,
    `place_id`  VARCHAR(100)    NOT NULL,
    PRIMARY KEY (seq)
);

ALTER TABLE review.tbl_place_info
    ADD CONSTRAINT UC_place_id UNIQUE (place_id);


-- tbl_user_info Table Create SQL
CREATE TABLE review.tbl_review_info
(
    `seq`             INT              NOT NULL    AUTO_INCREMENT,
    `user_seq`        INT              NOT NULL,
    `place_seq`       INT              NOT NULL,
    `review_id`       VARCHAR(100)     NOT NULL,
    `review_content`  VARCHAR(1000)    NULL,
    PRIMARY KEY (seq)
);

ALTER TABLE review.tbl_review_info
    ADD CONSTRAINT FK_tbl_review_info_user_seq_tbl_user_info_seq FOREIGN KEY (user_seq)
        REFERENCES review.tbl_user_info (seq) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE review.tbl_review_info
    ADD CONSTRAINT FK_tbl_review_info_place_seq_tbl_place_info_seq FOREIGN KEY (place_seq)
        REFERENCES review.tbl_place_info (seq) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE review.tbl_review_info
    ADD CONSTRAINT UC_review_id UNIQUE (review_id);


-- tbl_user_info Table Create SQL
CREATE TABLE review.tbl_point_audit_info
(
    `seq`            INT             NOT NULL    AUTO_INCREMENT,
    `user_seq`       INT             NOT NULL,
    `reason`         VARCHAR(30)     NOT NULL,
    `ago_point`      INT             NOT NULL    DEFAULT 0,
    `current_point`  INT             NOT NULL    DEFAULT 0,
    `review_id`      VARCHAR(100)    NOT NULL,
    `place_id`       VARCHAR(100)    NOT NULL,
    `create_at`      BIGINT          NOT NULL,
    PRIMARY KEY (seq)
);

ALTER TABLE review.tbl_point_audit_info
    ADD CONSTRAINT FK_tbl_point_audit_info_user_seq_tbl_user_info_seq FOREIGN KEY (user_seq)
        REFERENCES review.tbl_user_info (seq) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- tbl_user_info Table Create SQL
CREATE TABLE review.tbl_review_photo_info
(
    `seq`         INT             NOT NULL    AUTO_INCREMENT,
    `review_seq`  INT             NOT NULL,
    `photo_id`    VARCHAR(100)    NOT NULL,
    PRIMARY KEY (seq)
);

ALTER TABLE review.tbl_review_photo_info
    ADD CONSTRAINT FK_tbl_review_photo_info_review_seq_tbl_review_info_seq FOREIGN KEY (review_seq)
        REFERENCES review.tbl_review_info (seq) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE review.tbl_review_photo_info ADD UNIQUE INDEX UI_review_photo  (review_seq, photo_id);


