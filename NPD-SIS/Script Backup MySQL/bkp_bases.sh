#!/bin/bash
# Desenvolvido por: Eduardo Maronas Monks
# Script para criacao de dumps dos 
# bancos MySQL e Postgres
# MySQL:
# Devera ser criado um usuario dumper ou
# similar com permissoes em todas as bases
# Exemplo: GRANT ALL PRIVILEGES ON *.* TO dumper@localhost IDENTIFIED BY 'senac2010' WITH GRANT OPTION;
# Postgres:
# Dever ser permitido o localhost como trust
# no arquivo pg_hba.conf
# Armazenamento remoto:
# Devera ser permitido ao usuario root local acessar por
# SSH, com certificado no host remoto, com o usuario onde
# sera o armazenamento dos dumps 

# -- Variaveis de Ambiente ---

DATA=$(date +%Y-%m-%d_%H-%M)

# Diretorio local de backup
PBACKUP="/home/ecacarva/backup"

HOST=$(hostname)

# Usuario dumper e a senha do MySQL
#MYSQL --

MUSER="root"

MSENHA="123Mudar"

# -- LIMPEZA ---
# Os arquivos dos ultimos 30 dias serao mantidos
NDIAS="30"

if [ ! -d ${PBACKUP} ]; then
	
	echo ""
	echo " A pasta de backup nao foi encontrada!"
	mkdir -p ${PBACKUP}
	echo " Iniciando Tarefa de backup..."
	echo ""

else

	echo ""
	echo " Rotacionando backups mais antigos que $NDIAS"
	echo ""

	find ${PBACKUP} -type d -mtime +$NDIAS -exec rm -rf {} \;

fi

# -- SCRIPT ---


# echo "Iniciando o backup" |mutt -s "Backup $HOST Iniciado" $EMAIL


if [ ! -d $PBACKUP/$DATA/mysql ]; then

        mkdir -p $PBACKUP/$DATA/mysql

fi


for basemysql in `/usr/bin/mysql -u $MUSER -p$MSENHA --execute="show databases;" |grep -v "information_schema" |grep -v "performance_schema" |grep -v Database`; do

        /usr/bin/mysqldump -u $MUSER --password=$MSENHA --databases $basemysql > $PBACKUP/$DATA/mysql/$basemysql.txt

        cd $PBACKUP/$DATA/mysql/

        tar -czvf $basemysql.tar.gz $basemysql.txt
		 
		sha1sum $basemysql.tar.gz > $basemysql.sha1

        rm -rf $basemysql.txt

	cd /

done

DAYOFWEEK=$(date +"%u")
if [ "${DAYOFWEEK}" -eq 7  ];  then

  # Todas as bases
  /usr/bin/mysqldump -p -u ${MUSER} --password=${MSENHA} --all-databases  > ${PBACKUP}/${DATA}/mysql/mysql_tudo.txt

   cd ${PBACKUP}/${DATA}/mysql/

   tar -czvf mysql_tudo.tar.gz mysql_tudo.txt
   
   sha1sum mysql_tudo.tar.gz > mysql_tudo.sha1

   rm -f mysql_tudo.txt
    
  
fi


cd /

# Usuarios
/usr/bin/mysqldump -u $MUSER --password=$MSENHA --no-create-info  mysql user > $PBACKUP/$DATA/mysql/usuarios.sql


# Grants
/usr/bin/mysql -u $MUSER --password=$MSENHA --skip-column-names -A -e"SELECT CONCAT('SHOW GRANTS FOR ''',user,'''@''',host,''';') FROM mysql.user WHERE user<>''" | mysql -u $MUSER --password=$MSENHA --skip-column-names -A | sed 's/$/;/g' > $PBACKUP/$DATA/mysql/grants.sql


DAYOFWEEK=$(date +"%u")
if [ "${DAYOFWEEK}" -eq 7  ];  then

 # Otimizacao das tabelas 
   /usr/bin/mysqlcheck -A -o -u root --password="123Mudar"

fi

exit 0

