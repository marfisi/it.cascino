pg_dump -U cascino_user_db -h localhost -n cascino_schema -F p -C --inserts -t articoli_foto -f .\bck_articoli_foto.sql cascino_db