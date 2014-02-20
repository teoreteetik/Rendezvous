Rendezvous
==========

1. Etapi esitus

Tööt teema

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
