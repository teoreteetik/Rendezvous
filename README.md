Rendezvous
==========

##1. Etapp

####Töö teema
Üldises mõistes on tegu tudengitele suunatud foorumiga või n-ö messageboardiga, et saada ja jagada jooksvat infot mingi hetkel käiva aine kohta.

Mille poolest erineb erineb Moodle'ist / Facebooki grupist:
* Ainete Moodle'i foorumid on tavaliselt surnud. Põhjuse hüpotees:
  * Liiga akadeemiline, iga tühja tähja pärast ei julgeta enda nime all teemat teha, õppejõud näeb jms.
  * Formattimise tugi puudub - matemaatilisi valemeid / programmikoodi on piin kirjutada.
* Facebooki grupid on enamasti ainult kursuste, mitte ainete kaupa ning sellega ei jõua sellises grupid tehtud aine x teemaline postitus kõigi aine kuulajateni.
  * Sama formattimise toe puudumine.
  * Sama anonüümsuse puudumine(ei julgeta enda nime all "lolli" küsimust küsida või mingeid aine materjale jagada)
  * Liigne teemade arv kujuneks grupi seinal spämmiks.
   
Erinevalt Stackoverflowst pole postituste kohta rangeid reegleid, rohkem stilis "anything goes" - peaasi, et ainet puudutav.
Ehk siis teemad stiilis "Tehke palun see ülesanne ära", "Eilse KT lahendused" on samuti ok.
Seega postituste olemuselt oleks rakendus rohkem chatroom kui pikkade aruteludega foorum.
   
#####Üldpilt näeks välja nii:
Kasutaja saab valida aasta õppeaasta (vaikimisi käesolev õa).  
Kuvatakse valitud õa ained (ainete lisamine toimub kasutajate endi poolt - kui mingit ainet pole lisatud, saab kasutaja selle ise lisada).  
Valides aine kuvatakse selle aine "foorumiteemad".  
Teemad võivad olla abipalved, materjalide jagamine, muu aineteemaline informatsioon.  
Olles sisse loginud saavad kasutajad ise postitada, teemadele vastata - seda saab teha enda kasutajanime alt või anonüümses režiimis (anonüümnse režiimi rakendamine kujutab endast seda, et enne postitamist tehakse linnuke "Postita anonüümselt" checkboxi, siis ei näe keegi, kes postituse tegi).  
Postitustel on LaTeXi ja syntax highlighting tugi matemaatiliste avaldiste ja koodijuppide jaoks.  
Võimalik funktsionaalsus - lihtsustatud Stackoverflow rep süsteem - igal postitusel on "Aitäh" nupuke vms - kui postitus oli kasulik, saavad kasutajad sellega postitajat tänada, nii on igal kasutajal oma "skoor" (ala reddit karma)


Hindamiseks esitatavad punktid:  
####5.4 Rakenduse koodirepositooriumi üles seadmine
Rakendus on jaotatud frontendi ja backendi jaoks kaheks eraldi projektiks. Kuna tegu on väiksema rakendusega, hoian nad paremaks haldamiseks ühes repos eraldi kaustades - front ja back.


#####5.3 Rakenduse ülesseadmine testkeskkonda
Testkeskkonnaks valisin AWSi lahenduse. Hetkel jooksevad seal 
* tomcat (http://ec2-54-213-178-112.us-west-2.compute.amazonaws.com:8080/)
* ja mysql (andmebaasiühenduse näide: http://ec2-54-213-178-112.us-west-2.compute.amazonaws.com:8080/back-1.0/rest/users)


####5.5 Prototüüp
Prototüüpi saab näha testkeskkonas http://ec2-54-213-178-112.us-west-2.compute.amazonaws.com:8080/front/app/#/subject/1
(war-i pakendamisega tekkisid encodingu probleemid, seega testkeskonnas võivad täpitähed hetkel katki olla.)  
Kohalikus masinas tuleb frontendi nägemiseks front/app kaustas olles käivitada käsk node ../web-server.js
Default aadress on sel juhul localhost:8000. Seda saab konfida web-server.js DEFAULT_PORT muutujaga.  
Kasutatud on Angulari ja Bootstrap 3 raamistikke, frontendi struktuur angular-seed põhjal.
Hetkel prototüübi koodi vaadata ei tasu, midagi ilusat sealt ei leia.


Tiimi liige: Lembit Gerz
