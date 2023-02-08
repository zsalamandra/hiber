--liquibase formatted sql

--changeset leonid.masleshov@moex.com:1
--comment: Initial database script

create type inguru_insurance_company_type as enum
    (
    'ALFA','IGS','RGS','SOGLASIE','MAKS','UGORIA','ZETTA','RESO','VSK','RENESSANS','INTOUCH','SOGAZ','GAIDE',
    'OSK','ABSOLUT','ASTRO_VOLGA','TINKOFF','VERNA','EUROINS','MAFI'
);

create table insurance_companies
(
    id uuid primary key,
    insurance_company_inn varchar(12) not null,
    insurance_company_name varchar(160) not null,
    inguru_company_id inguru_insurance_company_type,
    is_active boolean not null default false,
    create_timestamp timestamp with time zone not null,
    update_timestamp timestamp with time zone not null
);

create index idx_inguru_insurance_company_id on insurance_companies (inguru_company_id);

create type vehicle_type as enum
    (
    'CAR', 'TAXI', 'SMALL_BUS', 'BIG_BUS', 'SMALL_TRUCK', 'BIG_TRUCK', 'MOTO', 'TROLLEY', 'TRAM', 'SPECIAL', 'CITY_BUS'
);

create type vehicle_document_type as enum
    (
    'PTS', 'STS', 'EPTS'
);

create type vehicle_purpose_type as enum
    (
    'PERSONAL', 'TAXI', 'TRAINING', 'DANGEROUS', 'RENTAL', 'PASSENGER', 'SPECIAL', 'SERVICES', 'OTHER'
);

create type policy_type as enum
    (
    'OSAGO'
);

create type policy_usage_period as enum
    (
    'THREE', 'FOUR', 'FIVE', 'SIX', 'SEVEN', 'EIGHT', 'NINE', 'TWELVE'
);

create type policy_source_type as enum
    (
    'INGURU', 'WEB_LK', 'WEB_UNAUTHORIZED', 'MOBILE_APP'
);

create type batch_status_type as enum
    (
    'IMPORTING', 'COMPLETE', 'ERROR'
);

create type batch_provider_type as enum
    (
    'INGURU'
);

create type batch_initiator_type as enum
    (
    'FINUSLUGI', 'INGURU'
);

create type batch_journal_status_type as enum
    (
    'CREATED', 'UPDATED', 'SKIPPED', 'ERROR'
);

create cast (character varying as inguru_insurance_company_type) with inout as assignment;
create cast (character varying as batch_initiator_type) with inout as assignment;
create cast (character varying as batch_journal_status_type) with inout as assignment;
create cast (character varying as batch_provider_type) with inout as assignment;
create cast (character varying as batch_status_type) with inout as assignment;
create cast (character varying as policy_source_type) with inout as assignment;
create cast (character varying as policy_type) with inout as assignment;
create cast (character varying as policy_usage_period) with inout as assignment;
create cast (character varying as vehicle_document_type) with inout as assignment;
create cast (character varying as vehicle_purpose_type) with inout as assignment;
create cast (character varying as vehicle_type) with inout as assignment;

create table policies
(
    id uuid primary key,
    vehicle_brand varchar(30) not null,
    vehicle_model varchar(30) not null,
    vehicle_type vehicle_type not null,
    vehicle_year smallint not null,
    vehicle_license_plate varchar(10),
    vehicle_vin varchar(17),
    vehicle_body_num varchar(12),
    vehicle_chassis_num varchar(17),
    vehicle_power smallint not null,
    vehicle_weight integer,
    vehicle_max_weight integer,
    vehicle_seats smallint,
    vehicle_doc_type vehicle_document_type not null,
    vehicle_doc_serial varchar(4),
    vehicle_doc_number varchar(15) not null,
    vehicle_doc_date date not null,
    vehicle_doc_valid boolean not null,
    vehicle_dc_number varchar(15),
    vehicle_dc_validity date,
    vehicle_dc_issue_date date,
    vehicle_purpose vehicle_purpose_type not null,
    vehicle_use_trailer boolean not null,
    multidrive boolean not null,
    owner_first_name varchar(50) not null,
    owner_middle_name varchar(50),
    owner_last_name varchar(50) not null,
    owner_birth_date date not null,
    owner_passport_foreign boolean not null,
    owner_passport_serial varchar(4),
    owner_passport_number varchar(10) not null,
    owner_passport_date date not null,
    owner_registration_address jsonb null,
    insurer_is_owner boolean not null,
    insurer_first_name varchar(50),
    insurer_middle_name varchar(50),
    insurer_last_name varchar(50),
    insurer_birth_date date,
    insurer_passport_foreign boolean,
    insurer_passport_serial varchar(4),
    insurer_passport_number varchar(10),
    insurer_passport_date date,
    insurer_registration_address jsonb,
    insurer_phone varchar(15) not null,
    insurer_email varchar(256) not null,
    order_id integer,
    policy_serial varchar(3),
    policy_number varchar(10),
    type_policy policy_type,
    policy_date timestamp,
    policy_end_date timestamp,
    use_period policy_usage_period not null,
    insurance_company_id UUID references insurance_companies (id),
    -- I avoided using of "money" type because of this:
    -- https://stackoverflow.com/questions/18169627/money-data-on-postgresql-using-java
    policy_price decimal,
    policy_doc_id uuid,
    url_policy_doc_id integer,
    policy_received policy_source_type,
    moex_promotion boolean,
    prev_policy_serial varchar(3),
    prev_policy_number varchar(10),
    inguru_order_id uuid,
    created_date timestamp not null,
    url_received_date timestamp,
    payment_date timestamp,
    request_complete_date timestamp,
    comission decimal,
    comission_rate decimal,
    request_browser_id uuid,
    create_timestamp timestamp with time zone not null,
    update_timestamp timestamp with time zone not null
);

create table licenses
(
    id uuid primary key,
    serial varchar(4),
    number varchar(6) not null,
    exp_start_date date not null,
    date date,
    is_valid boolean not null,
    is_foreign boolean not null,
    previous_license_id uuid,
    first_name varchar(50),
    last_name varchar(50),
    middle_name varchar(50),
    create_timestamp timestamp with time zone not null,
    update_timestamp timestamp with time zone not null
);

create table drivers
(
    id uuid primary key,
    policy_id uuid not null references policies (id),
    driver_first_name varchar(50) not null,
    driver_last_name varchar(50) not null,
    driver_middle_name varchar(50),
    driver_birth_date date not null,
    license_id uuid not null references licenses(id),
    create_timestamp timestamp with time zone not null,
    update_timestamp timestamp with time zone not null
);

create table customer_policies
(
    id uuid primary key,
    order_policy_id uuid not null references policies (id),
    customer_id uuid not null,
    is_personal boolean not null default false
);

create table suggestions
(
    id uuid primary key,
    policy_id uuid not null references policies(id),
    insurance_company_id uuid not null references insurance_companies(id),
    e_id integer,
    cost decimal not null,
    date timestamp not null,
    prolongation_begin_date date,
    expires_at timestamp not null,
    error_message text
);

create index idx_suggestions_expires_at on suggestions (expires_at);

create table batch_inguru
(
    id uuid primary key,
    provider_id batch_provider_type not null,
    batch_status batch_status_type not null,
    errors_count smallint,
    total_count smallint,
    initiator batch_initiator_type not null,
    api_version smallint not null,
    create_timestamp timestamp with time zone not null,
    update_timestamp timestamp with time zone not null
);

create table batch_journal
(
    id uuid primary key,
    batch_id uuid not null references batch_inguru (id),
    ext_order_id integer not null,
    status batch_journal_status_type not null,
    errors jsonb,
    create_timestamp timestamp with time zone not null
);


--rollback drop type inguru_insurance_company_type;
--rollback drop index idx_inguru_insurance_company_id;
--rollback drop table customer_policies;
--rollback drop table drivers;
--rollback drop table licenses;
--rollback drop table policies;
--rollback drop type vehicle_type;
--rollback drop type vehicle_document_type;
--rollback drop type vehicle_purpose_type;
--rollback drop type policy_type;
--rollback drop type policy_usage_period;
--rollback drop type policy_source_type;
--rollback drop table insurance_companies;
--rollback drop table suggestions;
--rollback drop index idx_suggestions_expires_at;
--rollback drop table batch_journal;
--rollback drop table batch_inguru;
--rollback drop type batch_status_type;
--rollback drop type batch_initiator_type;
--rollback drop type batch_provider_type;
--rollback drop type batch_journal_status_type;