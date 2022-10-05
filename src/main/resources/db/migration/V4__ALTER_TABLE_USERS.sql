ALTER TABLE sch_auth.t_users
    ADD COLUMN deleted bool;
ALTER TABLE sch_auth.t_users
    ADD COLUMN account_locked bool;