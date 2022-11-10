

migration:
	liquibase --changeLogFile=src/main/resources/db/changelog/db.changelog-master.xml --username=t2h_liquibase --password=t2h_liquibase --url=jdbc:mysql://localhost:3306/t2h_liquibase?useUnicode=yes&characterEncoding=UTF-8 update