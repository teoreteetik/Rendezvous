Rendezvous
==========

1. Etapi esitus

Töö teema
Üldises mõistes on tegu tudengitele sunatud foorumiga või n-ö messageboardiga, et saada ja jagada jooksvat infot mingi hetkel käiva aine kohta.


Mille poolest erineb erineb Moodle'ist / Facebooki grupist:
    Ainete Moodle'i foorumid on tavaliselt surnud. Põhjuse hüpotees:
        Liiga akadeemiline, iga tühja tähja pärast ei julgeta teemat teha, õppejõud näeb jms.
        Formattimise tugi puudub - matemaatilisi valemeid / programmikoodi on piin kirjutada.
    Facebooki grupid on enamasti ainult kursuste, mitte ainete kaupa ning sellega ei jõua sellises grupid tehtud aine x teemaline postitus kõigi aine kuulajateni.
    Sama formattimise toe puudumine.
    Sama anonüümsuse puudumine(ei julgeta oma nime all "lolli" küsimust küsida või mingeid aine materjale jagada)
    Liigne teemade arv kujuneks grupi seinal spämmiks.
   
Erinevalt Stackoverflowst pole postituste kohta rangeid reegleid, rohkem stilis "anything goes" - peaasi, et ainet puudutav.
Ehk siis teemad stiilis "Tehke palun see ülesanne ära", "Eilse KT lahendused" on samuti ok.
Seega postituste olemuselt oleks rakendus rohkem chatroom kui pikkade aruteludega foorum.
   
Üldpilt näeks välja nii - Kasutaja saab valida aasta õppeaasta (vaikimisi käesolev õa).
Kuvatakse valitud õa ained (ainete lisamine toimub kasutajate endi poolt - kui mingit ainet pole lisatud, saab kasutaja selle ise lisada)
Valides aine kuvatakse selle aine "foorumiteemad".
Teemad võivad olla abipalved, materjalide jagamine, muu aineteemaline informatsion.
Olles sisse loginud saavad kasutajad ise teemasid algatada, teemadele vastata - seda saab teha enda kasutajanime alt või anonüümses režiimis.
(Võimalik funktsionaalsus - lihtsustatud Stackoverflow rep süsteem - igal postitusel on "Aitäh" nupuke vms - kui postitus oli kasulik, saavad kasutajad sellega postitajat tänada, nii on igal kasutajal oma "skoor")



Kõik kasutajad saavad näha õppeaasta x aine y teemasid sorteerituna teemasse tehtud viimase postituse kuupäeva järgi.
Kasutajad saavad registreeruda Google Accountiga.
Sisseloginud kasutajad saavad postitada teemasid ja vastata olemasolevatele postitustele nii oma kasutajanime alt kui ka anonüümses režiimis.
Sisseloginud kasutajad saavad lisada puuduvaid aineid (rakendub süsteemne kontroll, et antud koodiga ainet juba loodud pole).
Sisseloginud kasutajad saavad oma postitusi muuta, posituse muudatuste ajalugu ei säilitata.
Sisseloginud kasutajatel on oma ülalkirjeldatud "Aitäh"ide skoor.
Postitustes saab kasutada LaTeX vormingut ning syntax highlightingut.
Postitust kirjutades kuvatakse preview-as-you-type (eelkõige oluline LaTeXi kirjutamisel)
Postituse kirjutamisel cache'itakse postitus (kui kuva kinni panna ja taasavada, siis on tekst alles)
Admin kasutaja saab postitusi kustutada.
Põhifunktsionaalsus.









1) Github repo
Rakendus on jaotatud frontendi ja backendi jaoks kaheks eraldi projektiks. Kuna tegu on väiksema projektiga hoian nad paremaks haldamiseks ühes repos eraldi kasutades - front ja back.


2) Testkeskond
Testkeskkonnaks valisin AWSi lahenduse. Hetkel jookseb seal 
tomcat (http://ec2-54-213-178-112.us-west-2.compute.amazonaws.com:8080/)
ja mysql (andmebaasiühenduse näide: http://ec2-54-213-178-112.us-west-2.compute.amazonaws.com:8080/back-1.0/rest/users)


2) Prototüüp
Prototüüpi saab näha testkeskkonas http://ec2-54-213-178-112.us-west-2.compute.amazonaws.com:8080/front/app/#/subject/1
(war-i pakendamisega tekkisid encodingu probleemid, seega testkeskonnas võivad täpitähed hetkel katki olla.)
Kohalikus masinas tuleb frontendi käivitamiseks olla front/app kaustas ning käivitada käsk node ../web-server.js
Kasutatud on Angulari ja Bootstrap 3 raamistikke, frontendi struktuur angular-seed põhjal.
Hetkel prototüübi koodi vaadata ei tasu, midagi ilusat sealt ei leia.


Tiimi liige: Lembit Gerz
