Feature: Compras de Doces 
  Realizar compras na categoria doces e adicionar todos os produtos 

  @comprasDoces
  Scenario: Adicionar todos os produtos da categoria doces ao carrinho
    Given Acessar o site de compras
    And Selecionar categoria "Doces"
    When  Adicionar todos os produtos ao carrinho
    And Selecionar categoria "Todos"
    And Acessar o carrinho
    And Aumentar a quantidade do produto "Brigadeiro" em 4 unidades
    And Clicar no botão Finalizar Compra
    Then Validar a mensagem "Pedido realizado com sucesso!"
    And Clicar no botão Fechar
    
@comprarBebidas
  Scenario: Adicionar todos os produtos da categoria doces ao carrinho
    Given Acessar o site de compras
    And Selecionar categoria "Bebidas"		
    When  Adicionar todos os produtos ao carrinho
    And Selecionar categoria "Todos"
    And Adicionar o produto "Rissole médio" ao carrinho
    And Acessar o carrinho
    And Aumentar a quantidade do produto "Rissole médio" em 9 unidades
		And Diminuir a quantidade do produto "Rissole médio" em 5 unidades
    And Validar o valor total dos produtos
    And Clicar no botão Finalizar Compra
    Then Validar a mensagem "Pedido realizado com sucesso!"
    And Clicar no botão Fechar