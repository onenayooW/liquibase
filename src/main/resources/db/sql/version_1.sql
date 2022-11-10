
create table flyway_schema_history
(
    installed_rank int                                 not null
        primary key,
    version        varchar(50)                         null,
    description    varchar(200)                        not null,
    type           varchar(20)                         not null,
    script         varchar(1000)                       not null,
    checksum       int                                 null,
    installed_by   varchar(100)                        not null,
    installed_on   timestamp default CURRENT_TIMESTAMP not null,
    execution_time int                                 not null,
    success        tinyint(1)                          not null
);

create index flyway_schema_history_s_idx
    on flyway_schema_history (success);

create table kbboard
(
    id            bigint auto_increment
        primary key,
    created_at    datetime     null,
    deleted_at    datetime     null,
    sorting_order bigint       null,
    edited_at     datetime     null,
    updated_at    datetime     null,
    version       bigint       null,
    board_type    varchar(255) null,
    title         varchar(255) not null,
    members       json         null,
    constraint UK_9qwq08ccwfe7sbbtkjuu06cgk
        unique (title),
    constraint UK_f5ccqepfu0033cpq9rd36f3w0
        unique (board_type)
);

create table kblane
(
    id            bigint auto_increment
        primary key,
    created_at    datetime     null,
    deleted_at    datetime     null,
    sorting_order bigint       null,
    edited_at     datetime     null,
    updated_at    datetime     null,
    version       bigint       null,
    emit_events   json         null,
    is_done_lane  bit          null,
    is_init_lane  bit          null,
    slug          varchar(255) not null,
    title         varchar(255) not null,
    board_id      bigint       null,
    constraint UK_978rbe3jfpye0o5bev6cwa7yw
        unique (slug),
    constraint FK8vd7f7xncptix3s7pomg84ywu
        foreign key (board_id) references kbboard (id)
);

create index IDX_KBLANE_TITLE
    on kblane (title);

create table kbuser_contact
(
    id            bigint auto_increment
        primary key,
    created_at    datetime     null,
    deleted_at    datetime     null,
    sorting_order bigint       null,
    edited_at     datetime     null,
    updated_at    datetime     null,
    version       bigint       null,
    address       varchar(255) null,
    customer_type varchar(255) null,
    email         varchar(255) null,
    full_name     varchar(255) null,
    line_id       varchar(255) null,
    mobile_no     varchar(255) null,
    province      varchar(255) null,
    remark        json         null,
    user_hash_id  varchar(255) null,
    constraint IDX_KBUSERCONTACT_EMAIL
        unique (email),
    constraint IDX_KBUSERCONTACT_FULLNAME
        unique (full_name)
);

create table kbcard
(
    id              bigint auto_increment
        primary key,
    created_at      datetime     null,
    deleted_at      datetime     null,
    sorting_order   bigint       null,
    edited_at       datetime     null,
    updated_at      datetime     null,
    version         bigint       null,
    assignees       json         null,
    brand_uid       varchar(255) null,
    detail          varchar(255) null,
    due_date        datetime     null,
    image_urls      json         null,
    is_done         bit          null,
    item_hash_id    varchar(255) null,
    margin_price    double       null,
    model_uid       varchar(255) null,
    owner_hash_id   varchar(10)  null,
    price           double       null,
    reporters       json         null,
    title           varchar(255) null,
    uid             varchar(255) null,
    lane_id         bigint       null,
    json_detail     json         null,
    contact_user_id bigint       null,
    comments        json         null,
    constraint IDX_KBCARD_UID
        unique (uid),
    constraint FK49tqb4ldad1akr902e1u6w13e
        foreign key (contact_user_id) references kbuser_contact (id),
    constraint FKd6vbdjrq54xuk4mf9c992pmbi
        foreign key (lane_id) references kblane (id)
);

create index IDX_KBCARD_OWNERHASH_ID
    on kbcard (owner_hash_id);

create index IDX_KBUSERCONTACT_MOBILE
    on kbuser_contact (mobile_no);

create table ktbrand
(
    id                 bigint auto_increment
        primary key,
    created_at         datetime      null,
    deleted_at         datetime      null,
    sorting_order      bigint        null,
    edited_at          datetime      null,
    updated_at         datetime      null,
    version            bigint        null,
    canonical_url_meta varchar(512)  null,
    description        text          null,
    description_meta   text          null,
    image_url          varchar(512)  null,
    image_url_meta     varchar(512)  null,
    name               varchar(255)  not null,
    number_of_items    bigint        null,
    placeholder        varchar(255)  null,
    search_synonym     varchar(1024) null,
    slug               varchar(255)  not null,
    thai_name          varchar(255)  null,
    title_meta         text          null,
    uid                bigint        not null,
    constraint UK_2fmf1e3b3t12rncsl1ydpqiwm
        unique (uid),
    constraint UK_m8g6cyhewn1j67u1glkkr4glc
        unique (slug)
);

create index IDX_KTBRAND_NAME
    on ktbrand (name);

create index IDX_KTBRAND_THAINAME
    on ktbrand (thai_name);

create table ktfile
(
    id                 bigint auto_increment
        primary key,
    created_at         datetime      null,
    deleted_at         datetime      null,
    sorting_order      bigint        null,
    edited_at          datetime      null,
    updated_at         datetime      null,
    version            bigint        null,
    category           varchar(255)  null,
    driver             varchar(255)  null,
    file_extension     varchar(2048) null,
    file_original_name varchar(2048) null,
    file_type          varchar(255)  null,
    full_url           varchar(2048) null,
    is_public          bit           null,
    is_resized         bit           null,
    path               varchar(2048) null,
    real_path          varchar(2048) null,
    size               bigint        null,
    uid                bigint        null,
    user_uid           bigint        null
);

create index IDX_KTFILE_UID
    on ktfile (uid);

create index IDX_KTFILE_USERID
    on ktfile (user_uid);

create table ktmaster_index
(
    dtype               int          not null,
    id                  bigint auto_increment
        primary key,
    created_at          datetime     null,
    deleted_at          datetime     null,
    sorting_order       bigint       null,
    edited_at           datetime     null,
    updated_at          datetime     null,
    version             bigint       null,
    attribute_data_type varchar(255) null,
    description         text         null,
    image_url           varchar(512) null,
    name                varchar(255) not null,
    placeholder         varchar(255) null,
    slug                varchar(255) not null,
    thai_name           varchar(255) not null,
    uid                 bigint       not null,
    constraint UK_bjxubcw5qod15a4ohj1cy0lgs
        unique (slug),
    constraint UK_n4fbd7s9lhs9vk6j3wh0bhr4x
        unique (uid)
);

create table ktcategory
(
    id                 bigint auto_increment
        primary key,
    created_at         datetime      null,
    deleted_at         datetime      null,
    sorting_order      bigint        null,
    edited_at          datetime      null,
    updated_at         datetime      null,
    version            bigint        null,
    breadcrumb_path    json          null,
    canonical_url_meta varchar(512)  null,
    description        text          null,
    description_meta   text          null,
    image_url          varchar(512)  null,
    image_url_meta     varchar(512)  null,
    name               varchar(255)  not null,
    number_of_items    bigint        null,
    path               json          null,
    placeholder        varchar(255)  null,
    search_synonym     text          null,
    slug               varchar(255)  not null,
    thai_name          varchar(255)  null,
    title_meta         text          null,
    uid                bigint        not null,
    master_index_id    bigint        null,
    root_category_id   bigint        null,
    blog_slug          varchar(1024) null,
    constraint UK_dls0exj68ut5d2jchvynltt60
        unique (slug),
    constraint UK_hcf3oaqx2ogfdwdov65jdg39b
        unique (uid),
    constraint FKiitp691nandbe28n36kw3lgi2
        foreign key (master_index_id) references ktmaster_index (id),
    constraint FKmlr3xodmxxndafswww1p880b2
        foreign key (root_category_id) references ktcategory (id)
);

create table ktbrand_root_categories
(
    brands_id          bigint not null,
    root_categories_id bigint not null,
    constraint FK1b9br7dvdiyffh1n77ovleg99
        foreign key (brands_id) references ktbrand (id),
    constraint FK6i83j81rly6fwj9vwbraginoj
        foreign key (root_categories_id) references ktcategory (id)
);

create index IDX_KTCATEGORY_MASTERINDEX
    on ktcategory (master_index_id);

create index IDX_KTCATEGORY_NAME
    on ktcategory (name);

create index IDX_KTCATEGORY_ROOTCATEGORY
    on ktcategory (root_category_id);

create index IDX_KTCATEGORY_THAINAME
    on ktcategory (thai_name);

create index IDX_KTMASTERINDEX_ATTBTYPE
    on ktmaster_index (attribute_data_type);

create index IDX_KTMASTERINDEX_DTYPE
    on ktmaster_index (dtype);

create index IDX_KTMASTERINDEX_NAME
    on ktmaster_index (name);

create index IDX_KTMASTERINDEX_THAINAME
    on ktmaster_index (thai_name);

create table ktmenu
(
    id            bigint auto_increment
        primary key,
    created_at    datetime     null,
    deleted_at    datetime     null,
    sorting_order bigint       null,
    edited_at     datetime     null,
    updated_at    datetime     null,
    version       bigint       null,
    description   text         null,
    name          varchar(255) not null,
    path          json         null,
    uid           bigint       not null,
    constraint UK_crhs9rd7byegpm8y1dppppgwb
        unique (name),
    constraint UK_kv2657fkcuibl9aii1kvaroet
        unique (uid)
);

create table ktmodel
(
    id                 bigint auto_increment
        primary key,
    created_at         datetime      null,
    deleted_at         datetime      null,
    sorting_order      bigint        null,
    edited_at          datetime      null,
    updated_at         datetime      null,
    version            bigint        null,
    canonical_url_meta varchar(512)  null,
    description        text          null,
    description_meta   text          null,
    image_url          varchar(512)  null,
    image_url_meta     varchar(512)  null,
    model_name         varchar(255)  not null,
    name               varchar(255)  not null,
    number_of_items    bigint        null,
    placeholder        varchar(255)  null,
    search_synonym     varchar(1024) null,
    slug               varchar(255)  not null,
    thai_name          varchar(255)  null,
    title_meta         text          null,
    uid                bigint        not null,
    brand_id           bigint        null,
    root_category_id   bigint        null,
    constraint UK_b3emqjen11ue6hs8jq5qbnnpu
        unique (uid),
    constraint UK_efrnoidlds6n2gsm9m4upuktw
        unique (slug),
    constraint FK68xsgfaujwndvwiht0cdiiqrw
        foreign key (root_category_id) references ktcategory (id),
    constraint FKsnmsv4pjxcfggmfnc8bpgttv9
        foreign key (brand_id) references ktbrand (id)
);

create index IDX_KTMODEL_BRAND
    on ktmodel (brand_id);

create index IDX_KTMODEL_MODELNAME
    on ktmodel (model_name);

create index IDX_KTMODEL_NAME
    on ktmodel (name);

create index IDX_KTMODEL_ROOTCATEGORY
    on ktmodel (root_category_id);

create index IDX_KTMODEL_THAINAME
    on ktmodel (thai_name);

create table ktpopular_category
(
    id            bigint auto_increment
        primary key,
    created_at    datetime      null,
    deleted_at    datetime      null,
    sorting_order bigint        null,
    edited_at     datetime      null,
    updated_at    datetime      null,
    version       bigint        null,
    keyword       varchar(255)  null,
    slug          varchar(1024) null,
    slug_type     varchar(255)  null
);

create index IDX_KTPOPULAR_CATEGORY_SLUGTYPE
    on ktpopular_category (slug_type);

create table ssdictionary
(
    id            bigint auto_increment
        primary key,
    created_at    datetime     null,
    deleted_at    datetime     null,
    sorting_order bigint       null,
    edited_at     datetime     null,
    updated_at    datetime     null,
    version       bigint       null,
    direct_intent json         null,
    name          varchar(255) null,
    popularity    bigint       not null,
    ranking       bigint       not null,
    search_input  json         null,
    synnonyms     json         null,
    image_url     text         null,
    constraint IDX_SSDICTIONARY_NAME
        unique (name)
);

create table ssintent
(
    dtype         varchar(31)  not null,
    id            bigint auto_increment
        primary key,
    created_at    datetime     null,
    deleted_at    datetime     null,
    sorting_order bigint       null,
    edited_at     datetime     null,
    updated_at    datetime     null,
    version       bigint       null,
    description   varchar(255) null,
    name          varchar(255) not null,
    ranking       bigint       null,
    target        varchar(255) not null,
    image_url     text         null,
    constraint IDX_SSINTENT_NAME
        unique (name)
);

