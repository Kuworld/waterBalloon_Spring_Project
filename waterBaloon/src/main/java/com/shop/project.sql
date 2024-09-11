create table users(
  u_num number(6) not null,
  u_name varchar2(30) not null,
  u_id varchar2(30) not null,
  u_pw varchar2(100) not null,
  u_tel varchar2(20) not null,
  u_email varchar2(100) not null,
  u_gen number(1) not null, --1 남자, 2 여자
  u_addr varchar2(300) not null, --주소
  u_daddr varchar2(300) not null, --상세주소
  u_auth number(1) default 0, -- 권한 0일반회원, 1관리자
  u_out number(1) default 0, -- 0정상회원, 1탈퇴회원
  constraint users_u_num_pk primary key(u_num)
);
create sequence users_u_num_seq start with 100000 increment by 1;

create table category (
  c1_cat number(5) not null, --대분류 10000 부터 시작  
  c1_name varchar2(50),
  c2_cat number(5) not null, --소분류 100 부터 시작
  c2_name varchar2(50),
  constraint category1_c1_cat_pk primary key(c1_cat)
);

insert into items(
    i_num,
    c1_cat,
    c2_cat,
    i_name,
    i_price,
    i_maker,
    i_img1,
    i_img2,
    i_img3,
    i_content_det,
    i_content,
    i_stock,
    i_regdate,
    i_regstat
) values (
    item_i_num_seq.nextval,
    10000,
    1100,
    '아이스워터',
    6500,
    '먹는샘물',
    'muk500_20.jpg',
    '',
    '',
    '(1병당 325원)',
    '아이스워터 500mL * 20병',
    50,
    sysdate,
    0
);

SELECT
	c1_0.c1_cat,
	c1_0.c1_name,
	sc1_0.c1_cat,
	sc1_0.c2_cat,
	sc1_0.c2_name
FROM
	category c1_0
LEFT JOIN subcategory sc1_0 ON
	c1_0.c1_cat = sc1_0.c1_cat
WHERE
	c1_0.c1_name = '구인모'
ORDER BY
	sc1_0.c2_name
SELECT
	sc1_0.c2_cat,
	sc1_0.c2_name,
	sc1_0.c1_cat
FROM
	subcategory sc1_0
WHERE
	sc1_0.c1_cat ='10000'

select c1_0.c1_cat,c1_0.c1_name from category c1_0 WHERE sc1_0.c1_cat=10000

select sc1_0.c1_cat,sc1_0.c2_cat,sc1_0.c2_name from subcategory sc1_0 where sc1_0.c1_cat=10000
create table items (
  i_num number(20) not null,
  c1_cat number(5) not null,
  c2_cat number(5) not null,
  i_name varchar2(50) not null,
  i_price number(10) not null,
  i_maker varchar2(100) not null,--제조사
  i_img1 VARCHAR2(1000),
  i_img2 VARCHAR2(1000),
  i_img3 VARCHAR2(1000),
  i_content clob, -- 상세 설명
  i_content_det varchar2(1000), -- 개당 가격 추가 설명
  i_stock number(30), -- 재고
  i_regdate date default sysdate,--상품 등록 일시
  i_regstat number(1) default 0, -- 상품 보이게 안보이게 0은 등록 1는 리스트에 안보이게
  constraint item_i_num_pk primary key(i_num)
);
create sequence item_i_num_seq start with 1000000 increment by 1;

SELECT
	q1_0.id,
	q1_0.answer,
	q1_0.answered_date,
	q1_0.content,
	q1_0.created_date,
	q1_0.item_id,
	q1_0.user_name
FROM
	question q1_0
WHERE
	q1_0.item_id ='1000081';

create table cart (
  cart_num number(6) not null,
  u_num number(6) not null,
  i_num number(6) not null,
  cart_count number(5),
  cart_regstat number(1) default 0, -- 0은 미주문, 1는 리스트에서 삭제(주문을 했거나, 장바구니에서 뺐을때)
  constraint cart_cart_num_pk primary key(cart_num)
);
create sequence cart_cart_num_seq start with 1 increment by 1;

create table orderlist (
  o_num number(6) not null, -- 주문번호
  u_num number(6) not null, -- 회원번호(hidden)
  o_sprice number(10), -- 총 결제금액
  o_date date default sysdate, -- 주문 날짜
  o_addr varchar2(300) not null, -- 받는분 주소
  o_daddr varchar2(300) not null, --받는분 상세주소
  o_tel varchar2(15) not null, -- 받는분 전화번호
  o_req varchar2(2000), -- 배송 요청사항
  constraint orderlist_o_num primary key(o_num)
);
create sequence orderlist_o_num_seq start with 10000 increment by 1;

create table d_orderlist (
  d_num number(6) not null, -- 주문 상세번호
  u_num number(6) not null,
  o_num number(6) not null,
  i_num number(6) not null,
  d_count number(30),
  d_price number(10),
  constraint d_order_d_num primary key(d_num)
);
create sequence d_order_d_num_seq start with 1 increment by 1;

create table noti (
  n_num number(6) not null,
  n_title varchar2(20) not null,
  u_num number(6),
  n_content clob,
  n_img varchar2(1000),
  n_view_count number(10), -- 조회수
  n_date date default sysdate,
  constraint noti_n_num primary key(n_num)
);
create sequence noti_n_num_seq start with 1 increment by 1;

create table review (
  r_num number(6) not null,
  u_num number(6) not null,
  i_num number(6),
  r_title varchar2(20),
  r_date date default sysdate,
  r_content clob,
  r_img1 VARCHAR2(1000),
  r_img2 VARCHAR2(1000),
  r_img3 VARCHAR2(1000),
  r_grade number(1),
  constraint review_r_num primary key(r_num)
);
create sequence review_r_num_seq start with 1 increment by 1;

create table comment_t(
  c_num number(6) not null,
  u_num number(6) not null,
  r_num number(5) not null,
  c_content clob,
  c_date date default sysdate,
  constraint comment_t_c_num primary key(c_num)
);
create sequence comment_c_num_seq start with 1 increment by 1;

create table qna(
  q_num number(6) not null,
  u_num number(6) not null,
  q_title varchar2(20),
  q_regdate date default sysdate,
  q_content clob
);
create sequence qna_q_num_seq start with 1 increment by 1;

-- u_num(회원번호) 관련
alter table items add constraint fk_item_u_num foreign key(u_num) references users(u_num); -- 상품
alter table cart add constraint fk_cart_u_num foreign key(u_num) references users(u_num); -- 장바구니
alter table orderlist add constraint fk_orderlist_u_num foreign key(u_num) references users(u_num); -- 주문
alter table d_orderlist add constraint fk_d_orderlist_u_num foreign key(u_num) references users(u_num); -- 상세주문
alter table noti add constraint fk_noti_u_num foreign key(u_num) references users(u_num); -- 공지사항
alter table review add constraint fk_review_u_num foreign key(u_num) references users(u_num); -- 후기
alter table comment_t add constraint fk_comment_t_u_num foreign key(u_num) references users(u_num); -- 댓글
alter table qna add constraint fk_qna_q_num foreign key(u_num) references users(u_num); -- 문의사항

-- i_num(상품번호)관련
alter table cart add constraint fk_cart_i_num foreign key(i_num) references items(i_num);
alter table d_orderlist add constraint fk_d_orderlist_i_num foreign key(i_num) references items(i_num);
alter table review add constraint fk_review_i_num foreign key(i_num) references items(i_num);

-- 카테고리 분류
alter table items add constraint fk_items_c1_cat foreign key(c1_cat) references category1(c1_cat);
alter table items add constraint fk_items_c2_cat foreign key(c2_cat) references category2(c2_cat);

-- o_num(주문번호) 관련
alter table d_orderlist add constraint fk_d_orderlist_o_num foreign key(o_num) references orderlist(o_num);

-- 후기
alter table comment_t add constraint fk_review_c2_cat foreign key(r_num) references review(r_num);

-- 관리자 아이디
insert into users values(users_u_num_seq.nextval, '관리자', 'admin', 'admin123', 'admin@admin.com', '010-1234-5678', 1, '서울특별시', '그린컴퓨터아카데미', 1, 0);

commit;