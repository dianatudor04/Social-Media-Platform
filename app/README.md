-->PUNCTAJ OBTINUT 126/160
-->Tema 1 POO: ACSBook - Mini-platformă de Socializare
-->COMENZI IMPLEMENTATE:
-->In crearea temei am folosit urmatoarele clase:Utilizator, Postare, Comentariu si App.
-->Codul din App are la baza un bloc try-catch in care se verifica toate erorile mentionate in enuntul temei.
(Structuri if-else si alte blocuri try-catch)

-->Toate comenzile implementate au comentarii in codul sursa.Principalele cazuri pe care le-am tratat sunt:
***Utilizator***
-->1)Daca un utilizator nu exista in platforma,nu se poate face nicio actiune cu acesta.
-->2)Daca un utilizator exista in platforma,se verifica daca parola introdusa este corecta.
-->3)Daca un utilizator exista in platforma,se verifica daca acesta este logat.
***Postare si Comentariu***
-->1)Un user poate face o postare doar daca este logat.
-->2)Un user poate face un comentariu doar daca este logat.
-->3)Un user poate sterge o postare doar daca este logat si daca aceasta este postarea sa.
-->4)Un user poate sterge un comentariu doar daca este logat si daca acesta este comentariul sau.

LISTA COMENZILOR IMPLEMENTATE:
-->Creare utilizator
java tema1 –create-user -u ‘my_username’ -p ‘my_password’
-->Creare postare
 java tema1 –create-post -u ‘my_username’ -p ‘my_password’ -text ‘Astăzi mă simt
bine’
-->Ștergere postare
java tema1 –delete-post-by-id -u ‘my_username’ -p ‘my_password’ -id ‘post_ id1’

-->Comentează postare
a. java tema1 –comment-post -u ‘my_username’ -p ‘my_password’ –post-id ‘post_id1‘ -
text ‘text1’

-->Șterge comentariu postare
java tema1 –delete-comment-by-id -u ‘my_username’ -p ‘my_password’ -id ‘comment_
id1’
-->Curăță toate datele din platformă
java tema1 –cleanup-all
-->CSV FILE:
-->In fisierele csv se afla datele despre utilizatori,postari si comentarii. 
-->In username.csv se afla datele despre utilizatori. (username, password)
-->In post.csv se afla datele despre postari. (id, text, username)
-->In comment.csv se afla datele despre comentarii. (id, text, username, post_id)