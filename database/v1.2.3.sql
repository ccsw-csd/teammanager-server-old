create or replace
algorithm = UNDEFINED view `v_person_number_absences` as
select
    `p`.`saga` as `saga`,
    `p`.`username` as `username`,
    `p`.`name` as `name`,
    `p`.`lastname` as `lastname`,
    `p`.`email` as `email`,
    `p`.`center_id` as `center_id`,
    `p`.`businesscode` as `businesscode`,
    ((
    select
        count(1)
    from
        `absence_local` `a`
    where
        ((`a`.`saga` = `p`.`saga`)
        and (`a`.`year` = year(now())))) + (
    select
        count(1)
    from
        `absence_pon` `a`
    where
        ((`a`.`saga` = `p`.`saga`)
        and (`a`.`year` = year(now()))))) as `number_absences`
from
    `person` `p`
where
    (`p`.`active` = 1);

