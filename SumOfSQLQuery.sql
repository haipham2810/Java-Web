use Library_Managerr

select*from [USER]

select*from feedback where id = 1

select*from category where id = 1

select*from [USER] where [role] = 0
select*from [USER] where role = 2 or role = 3
select*from [USER]

SELECT*FROM borrower
select*from book
select*from feedback

select u.username, u.[name], u.avt,  u.sex, r.[name] as [Role], u.datebirth, u.phone, u.gmail 
from [USER] u JOin [role] r 
on u.[role] = r.id  
where r.id != 0

select*from [role]
UPDATE [USER] "
                + "SET [password]=?, [role]=?, name=?, avt=?, sex=?, datebirth=?, phone=?, gmail=? "
                + "WHERE username=?