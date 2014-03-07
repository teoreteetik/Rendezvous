create table users(
    id serial primary key,
    username varchar(20) not null,
    name varchar(50)
);

create table subject(
    id serial primary key,
    name varchar(255) not null,
    code varchar(100),
    year smallint not null,
    semester smallint not null
);

create table topic(
    id serial primary key,
    title varchar(255) not null,
    text_plain text,
    text_html text,
    anonymous boolean not null,
    subject_id integer not null,
    user_id integer not null,
    date_posted timestamp with time zone not null,
    
    foreign key (user_id) references users(id),
    foreign key (subject_id) references subject(id)
);

create table comment(
    id serial primary key,
    text_plain text,
    text_html text,
    anonymous boolean not null,
    topic_id integer not null,
    user_id integer not null,
    date_posted timestamp with time zone not null,
    
    foreign key (user_id) references users(id),
    foreign key (topic_id) references topic(id)
);

create table upvote(
    id serial primary key not null,
    user_id integer not null,
    topic_id integer ,
    comment_id integer,
    
    foreign key (topic_id) references topic(id),
    foreign key (comment_id) references comment(id)
);
alter table upvote add constraint upvote_constraint check(topic_id is null != comment_id is null);