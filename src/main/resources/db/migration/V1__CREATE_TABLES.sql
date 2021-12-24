CREATE TABLE sch_auth.t_menu_accesses
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    active       BOOLEAN,
    updated_date TIMESTAMP WITHOUT TIME ZONE,
    created_date TIMESTAMP WITHOUT TIME ZONE,
    menu_path    VARCHAR(255),
    menu_title   VARCHAR(255),
    menu_icon    VARCHAR(255),
    CONSTRAINT pk_t_menu_access PRIMARY KEY (id)
);

CREATE TABLE sch_auth.t_profiles
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    active       BOOLEAN,
    updated_date TIMESTAMP WITHOUT TIME ZONE,
    created_date TIMESTAMP WITHOUT TIME ZONE,
    description  VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_t_profiles PRIMARY KEY (id)
);

CREATE TABLE sch_auth.t_profile_menu_acesses
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    can_read         BOOLEAN,
    can_write        BOOLEAN,
    profile_id       BIGINT,
    menu_accesses_id BIGINT,
    CONSTRAINT pk_t_profile_menu_acess PRIMARY KEY (id)
);

ALTER TABLE sch_auth.t_profile_menu_acesses
    ADD CONSTRAINT FK_T_PROFILE_MENU_ACESS_ON_MENU_ACCESSES FOREIGN KEY (menu_accesses_id) REFERENCES sch_auth.t_menu_accesses (id);

ALTER TABLE sch_auth.t_profile_menu_acesses
    ADD CONSTRAINT FK_T_PROFILE_MENU_ACESS_ON_PROFILE FOREIGN KEY (profile_id) REFERENCES sch_auth.t_profiles (id);

CREATE TABLE sch_auth.t_users
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    active           BOOLEAN,
    updated_date     TIMESTAMP WITHOUT TIME ZONE,
    created_date     TIMESTAMP WITHOUT TIME ZONE,
    email            VARCHAR(255)                            NOT NULL,
    login            VARCHAR(255)                            NOT NULL,
    password_expired BOOLEAN,
    profile_id       BIGINT,
    street           VARCHAR(255),
    zip_code         VARCHAR(20),
    district         VARCHAR(100),
    city             VARCHAR(50),
    state            VARCHAR(50),
    CONSTRAINT pk_t_users PRIMARY KEY (id)
);

ALTER TABLE sch_auth.t_users
    ADD CONSTRAINT uc_t_users_email UNIQUE (email);

ALTER TABLE sch_auth.t_users
    ADD CONSTRAINT uc_t_users_login UNIQUE (login);

ALTER TABLE sch_auth.t_users
    ADD CONSTRAINT FK_T_USERS_ON_PROFILE FOREIGN KEY (profile_id) REFERENCES sch_auth.t_profiles (id);