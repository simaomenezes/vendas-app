# vendas-app
	# app-library-api
	
		Cadastro de Autor

		# Descrição
		    Deseja-se cadastrar os autores de livros, bem como realizar suas atualizações,
		    consultas e permitir sua exclusão.

		# Atores
		    Somente o Gerente pode cadastrar, atualizar e remover Autores.
		    O usuário Operador poderá somente consultar os dados dos Autores.

		# Campos solicitados pelo Negócio
		    Dados que deverão ser guardados:
		        Nome *
		        Data de Nascimento *
		        Nacionalidade *

		    campos com (*) são obrigatórios

		# Campos Lógicos
		    Dados não solicitados pela equipe de negócio, mas são de controle da aplicação e auditoria:
		    ID - UUID
		    Data Cadastro
		    Data Ultima Atualização
		    Usuário Ultima Atualização


		# Regras de Negócio
		    Não permitir cadastrar um Autor com mesmo Nome, Data de Nascimento e Nacionalidade,
		    ou seja, se houver 2 autores com mesmos Nome, Data de Nascimento e Nacionalidade
		    serão considerados repetidos, não permitir.
		    Não permitir excluir um Autor que possuir algum livro.